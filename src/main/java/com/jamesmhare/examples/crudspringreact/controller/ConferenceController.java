package com.jamesmhare.examples.crudspringreact.controller;

import com.jamesmhare.examples.crudspringreact.model.Conference;
import com.jamesmhare.examples.crudspringreact.model.ConferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ConferenceController {

    private final Logger log = LoggerFactory.getLogger(ConferenceController.class);
    private ConferenceRepository conferenceRepository;

    public ConferenceController(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @GetMapping("/conferences")
    Collection<Conference> conferences() {
        return conferenceRepository.findAll();
    }

    @GetMapping("/conference/{id}")
    ResponseEntity<?> getConference(@PathVariable Long id) {
        Optional<Conference> group = conferenceRepository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/conference")
    ResponseEntity<Conference> createConference(@Valid @RequestBody Conference conference) throws URISyntaxException {
        log.info("Request to create conference: {}", conference);
        Conference result = conferenceRepository.save(conference);
        return ResponseEntity.created(new URI("/api/group/" + result.getId()))
                .body(result);
    }

    @PutMapping("/conference/{id}")
    ResponseEntity<Conference> updateConference(@Valid @RequestBody Conference conference) {
        log.info("Request to update conference: {}", conference);
        Conference result = conferenceRepository.save(conference);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/conference/{id}")
    public ResponseEntity<?> deleteConference(@PathVariable Long id) {
        log.info("Request to delete conference: {}", id);
        conferenceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
