package io.github.gabbloquet.rent.domain;

import io.github.gabbloquet.rent.domain.model.Customer;

public interface CustomerRepository {
    void add(Customer customer);

    Customer get(String name);
}
