package io.github.gabbloquet.rent.domain;

import io.github.gabbloquet.rent.domain.model.Car;

public interface CarRepository {
    void add(Car car);

    Car get(String carName);
}
