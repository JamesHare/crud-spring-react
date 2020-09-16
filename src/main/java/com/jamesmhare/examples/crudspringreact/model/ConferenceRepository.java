package com.jamesmhare.examples.crudspringreact.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {

    Conference findByName(String name);

}
