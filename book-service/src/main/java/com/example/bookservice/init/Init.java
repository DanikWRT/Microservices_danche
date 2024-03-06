package com.example.bookservice.init;

import com.example.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

public class Init {
    @Component
    public class DatabaseConfig {

        private final MongoTemplate mongoTemplate;

        @Autowired
        public DatabaseConfig(MongoTemplate mongoTemplate) {
            this.mongoTemplate = mongoTemplate;
        }

        @PostConstruct
        public CommandLineRunner commandLineRunner() {
            return args -> {
                // Создаем список книг для сохранения в базу данных
                List<Book> books = Arrays.asList(
                        Book.builder().title("Book 1").description("Description 1").imageLink("Link 1").build(),
                        Book.builder().title("Book 2").description("Description 2").imageLink("Link 2").build(),
                        Book.builder().title("Book 3").description("Description 3").imageLink("Link 3").build()
                );
                // Сохраняем книги в базу данных
                mongoTemplate.insertAll(books);
            };
        }
    }
}
