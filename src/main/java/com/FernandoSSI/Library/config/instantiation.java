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

        Book b1 = new Book(null, "fundation", "isaac asimov", 50.0, "novo");
        Book b2 = new Book(null, "cavernas de aço", "isaac asimov", 40.0, "novo");
        Book b3 = new Book(null, "iluminado", "stephen king", 40.0, "usado");

        bookRepository.saveAll(Arrays.asList(b1, b2, b3));
    }
}
