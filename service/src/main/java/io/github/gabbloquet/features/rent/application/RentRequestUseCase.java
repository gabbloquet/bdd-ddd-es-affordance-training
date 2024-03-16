package io.github.gabbloquet.features.rent.application;

import io.github.gabbloquet.rent.domain.CarRepository;
import io.github.gabbloquet.rent.domain.CustomerRepository;
import io.github.gabbloquet.rent.domain.model.Car;
import io.github.gabbloquet.rent.domain.model.Customer;
import io.github.gabbloquet.rent.domain.model.RentRequest;
import io.github.gabbloquet.rent.domain.model.RentalRequest;
import io.github.gabbloquet.rent.infra.InMemoryCarRepository;
import io.github.gabbloquet.rent.infra.InMemoryCustomerRepository;
import lombok.AllArgsConstructor;

public class RentRequestUseCase {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    public RentRequestUseCase(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public RentalRequest execute(RentRequest command) {
        Car car = carRepository.get(command.carName());
        Customer customer = customerRepository.get(command.name());
        return customer.rent(car);
    }
}
