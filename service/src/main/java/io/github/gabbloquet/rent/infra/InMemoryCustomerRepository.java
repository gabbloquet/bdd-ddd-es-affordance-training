package io.github.gabbloquet.rent.infra;

import io.github.gabbloquet.rent.domain.CustomerRepository;
import io.github.gabbloquet.rent.domain.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomerRepository implements CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer get(String name) {
        return customers.stream()
                .filter(customer -> customer.name().equals(name))
                .findFirst()
                .get();
    }
}
