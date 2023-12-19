package com.FernandoSSI.Library.services;

import com.FernandoSSI.Library.domain.Order;
import com.FernandoSSI.Library.dto.BookDTO;
import com.FernandoSSI.Library.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public List<Order> findAll(){
        return repo.findAll();
    }

    public List<Order> findByClientName(String text){
        return repo.findByClientName(text);
    }

    public Order insert(Order order){
        Double totalValue = 0.0;
        for(BookDTO i : order.getBooks()){
            totalValue += i.getTotalPrice();
        }
        order.setTotalPrice(totalValue);

        return repo.insert(order);
    }

}
