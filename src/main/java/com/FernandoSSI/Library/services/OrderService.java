package com.FernandoSSI.Library.services;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.domain.Client;
import com.FernandoSSI.Library.domain.Order;
import com.FernandoSSI.Library.dto.BookDTO;
import com.FernandoSSI.Library.repositories.BookRepository;
import com.FernandoSSI.Library.repositories.OrderRepository;
import com.FernandoSSI.Library.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;
    @Autowired
    private BookRepository bookRepo;

    public List<Order> findAll(){
        return repo.findAll();
    }

    public Order findById(String id){
        Optional<Order> order = repo.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Order> findByClientName(String text){
        return repo.findByClientName(text);
    }

    public Order insert(Order order){
        Double totalValue = 0.0;
        for(BookDTO i : order.getBooks()){
            totalValue += i.getTotalPrice();
            Book book = bookRepo.findExactBook(i.getTitle(), i.getAuthor(), i.getCondition());
            if (book != null){
                book.setQuantity(book.getQuantity()- i.getQuantity());
                bookRepo.save(book);
            }
        }
        order.setTotalPrice(totalValue);

        return repo.insert(order);
    }

    public void delete(String id){
        if(repo.findById(id)!=null){
            repo.deleteById(id);
        }
    }

    public Order update(Order order){
        Order newOrder = findById(order.getId());
        newOrder.setDate(order.getDate());
        newOrder.setClient(order.getClient());
        for(BookDTO i : order.getBooks()){
            Book book = bookRepo.findExactBook(i.getTitle(), i.getAuthor(), i.getCondition());
            if (book != null){
                book.setQuantity(book.getQuantity() + i.getQuantity());
                bookRepo.save(book);
            }
        }
        newOrder.setBooks(order.getBooks());
        newOrder.setOrderStatus(order.getOrderStatus());
        Double totalValue = 0.0;
        for(BookDTO i : order.getBooks()){
            totalValue += i.getTotalPrice();
            Book book = bookRepo.findExactBook(i.getTitle(), i.getAuthor(), i.getCondition());
            if (book != null){
                book.setQuantity(book.getQuantity()- i.getQuantity());
                bookRepo.save(book);
            }
        }
        order.setTotalPrice(totalValue);
        return repo.save(newOrder);
    }
}
