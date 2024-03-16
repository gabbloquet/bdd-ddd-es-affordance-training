package io.github.gabbloquet.rent.infra;

import io.github.gabbloquet.rent.domain.CarRepository;
import io.github.gabbloquet.rent.domain.model.Car;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCarRepository implements CarRepository {

    private final List<Car> cars = new ArrayList<>();

    @Override
    public void add(Car car) {
        cars.add(car);
    }

    @Override
    public Car get(String carName) {
        return cars.stream().filter(car -> car.name().equals(carName)).findFirst().get();
    }
}
