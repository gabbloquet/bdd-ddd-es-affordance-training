package io.github.gabbloquet.features.rent.domain.specs;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import io.github.gabbloquet.rent.domain.model.Car;
import io.github.gabbloquet.rent.domain.model.Customer;
import io.github.gabbloquet.rent.domain.model.RentalRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidateRentSpec {

    private Customer customer;
    private Car car;
    private RentalRequest rentalRequest;

    @Etantdonné("Mr {string} propriétaire d’un téléphone jetable")
    public void mr_propriétaire_d_un_téléphone_jetable(String name) {
        this.customer = new Customer(name);
    }

    @Etantdonné("Mr {string} ayant un numéro provisoire")
    public void mrAyantUnNumeroProvisoire(String name) {
        this.customer = new Customer(name);
    }

    @Etantdonné("une {string} à {int}€ par mois")
    public void une_voiture_a(String carName, int price) {
        this.car = new Car(carName, price);
    }

    @Quand("Mr {string} demande à louer une {string}")
    public void mrDemandeALouerUne(String name, String carName) {
        rentalRequest = customer.rent(car);
    }

    @Alors("sa demande de location est invalidée")
    public void saDemandeDeLocationEstInvalidee() {
        assertThat(rentalRequest.status()).isEqualTo(RentalRequest.Status.NOT_VALIDATED);
    }

    @Alors("Mr Tapie est informé {string}")
    public void mrTapieEstInformeNumeroDeTelephoneIncompatible(String expectedMessage) {
        assertThat(rentalRequest.message()).isEqualTo(expectedMessage);
    }

    @Alors("sa demande de location est validée")
    public void saDemandeDeLocationEstValidee() {
        assertThat(rentalRequest.status()).isEqualTo(RentalRequest.Status.VALIDATED);
    }
}
