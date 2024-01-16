package com.FernandoSSI.Library.services;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.domain.Order;
import com.FernandoSSI.Library.dto.BookDTO;
import com.FernandoSSI.Library.repositories.BookRepository;
import com.FernandoSSI.Library.repositories.OrderRepository;
import com.FernandoSSI.Library.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Order> findByClientName(String text, Pageable pageable){
        return repo.findByClientName(text, pageable);
    }

    public Page<Order> findByTitle(String text, Pageable pageable){
        return repo.findByTitle(text, pageable);
    }

    public Page<Order> findByDate(String text, Pageable pageable){
        return repo.findByDate(text, pageable);
    }

    public Page<Order> find (String text, Pageable pageable){
        return repo.find(text, pageable);
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
            boolean found = false;
            for (BookDTO existingBook : newOrder.getBooks()) {
                if (existingBook.getId().equals(i.getId())) {
                    existingBook.setQuantity(i.getQuantity());
                    found = true;
                    break;
                }
            }
            Book book = bookRepo.findExactBook(i.getTitle(), i.getAuthor(), i.getCondition());
            if (!found) {
                book.setQuantity(book.getQuantity() - i.getQuantity());
                bookRepo.save(book);
            }
        }
        newOrder.setBooks(order.getBooks());
        newOrder.setOrderStatus(order.getOrderStatus());
        newOrder.setTotalPrice(order.getTotalPrice());
        return repo.save(newOrder);
    }
}
