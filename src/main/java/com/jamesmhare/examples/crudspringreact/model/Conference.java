package com.jamesmhare.examples.crudspringreact.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "conference")
public class Conference {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String address;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Attendee user;
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Session> sessions;

}
