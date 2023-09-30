package com.FernandoSSI.Library.config;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class instantiation implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {

        bookRepository.deleteAll();

        Book b1 = new Book("fundation", "isaac asimov", 50.0, "novo");
        Book b2 = new Book("cavernas de aço", "isaac asimov", 40.0, "novo");

        bookRepository.saveAll(Arrays.asList(b1, b2));
    }
}
