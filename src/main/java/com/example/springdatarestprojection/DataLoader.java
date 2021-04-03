package com.example.springdatarestprojection;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PersonRepos personRepos;

    private final AddressRepos addressRepos;

    @Override
    public void run(String... args) {

        Address address = new Address("Secret Street");
        address = addressRepos.save(address);

        Person alice = new Person("Alice");
        alice.setAddress(address);
        personRepos.save(alice);
    }
}
