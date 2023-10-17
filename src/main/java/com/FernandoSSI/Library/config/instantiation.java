package com.FernandoSSI.Library.config;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.domain.Category;
import com.FernandoSSI.Library.dto.CategoryDTO;
import com.FernandoSSI.Library.repositories.BookRepository;
import com.FernandoSSI.Library.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;

@Configuration
public class instantiation implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        bookRepository.deleteAll();
        categoryRepository.deleteAll();

        Category c1 = new Category(null,"Terror");
        Category c2 = new Category(null,"Sci-fi");
        Category c3 = new Category(null,"Fantasia");
        categoryRepository.saveAll(Arrays.asList(c1,c2,c3));

        Book b1 = new Book(null, "fundação", "isaac asimov", 50.0, "novo", c2);
        Book b2 = new Book(null, "cavernas de aço", "isaac asimov", 40.0, "novo", c2);
        Book b3 = new Book(null, "iluminado", "stephen king", 40.0, "usado", c1);
        Book b4 = new Book(null, "o hobbit", "tolkien", 80.0, "novo", c3);

        bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4));
        c1.getBooks().addAll(Arrays.asList(b3));
        c2.getBooks().addAll(Arrays.asList(b1, b2));
        c3.getBooks().addAll(Arrays.asList(b4));


        categoryRepository.saveAll(Arrays.asList(c1,c2,c3));
    }
}
