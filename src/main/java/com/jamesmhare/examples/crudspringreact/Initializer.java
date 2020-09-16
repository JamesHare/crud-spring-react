package com.jamesmhare.examples.crudspringreact;

import com.jamesmhare.examples.crudspringreact.model.Conference;
import com.jamesmhare.examples.crudspringreact.model.ConferenceRepository;
import com.jamesmhare.examples.crudspringreact.model.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {

    private final ConferenceRepository repository;

    public Initializer(ConferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("DefCon", "DerbyCon", "BSides", "IAPPA").forEach(name ->
                repository.save(new Conference(name))
        );

        Conference defCon = repository.findByName("DefCon");
        Session session = Session.builder().title("How I Hacked Something")
                .description("Learn about how I hacked something. It was fun.")
                .date(Instant.parse("2020-12-12T11:00:00.000Z"))
                .build();
        defCon.setSessions(Collections.singleton(session));
        repository.save(defCon);

        repository.findAll().forEach(System.out::println);
    }

}
