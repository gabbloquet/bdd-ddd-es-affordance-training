package io.github.gabbloquet.rent.domain.model;

public record RentalRequest(Status status, String message) {

    public enum Status {
        VALIDATED,
        NOT_VALIDATED
    }
}
