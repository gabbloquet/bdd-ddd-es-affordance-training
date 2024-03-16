package io.github.gabbloquet.rent.domain.model;

public record Customer(String name) {
    public RentalRequest rent(Car car) {
        if(car.name().contains("TWINGO")){
            return new RentalRequest(
                    RentalRequest.Status.VALIDATED,
                    null
            );
        }
        return new RentalRequest(
                RentalRequest.Status.NOT_VALIDATED,
                "Numéro de téléphone incompatible"
        );
    }
}
