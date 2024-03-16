package io.github.gabbloquet.features.rent.domain.specs;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import io.github.gabbloquet.features.rent.application.RentRequestUseCase;
import io.github.gabbloquet.rent.domain.model.RentRequest;
import io.github.gabbloquet.rent.domain.CarRepository;
import io.github.gabbloquet.rent.domain.CustomerRepository;
import io.github.gabbloquet.rent.infra.InMemoryCarRepository;
import io.github.gabbloquet.rent.domain.model.Car;
import io.github.gabbloquet.rent.domain.model.Customer;
import io.github.gabbloquet.rent.domain.model.RentalRequest;
import io.github.gabbloquet.rent.infra.InMemoryCustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidateRentSpec {

    private RentalRequest rentalRequest;

    private final CarRepository carRepository = new InMemoryCarRepository();
    private final CustomerRepository customerRepository = new InMemoryCustomerRepository();
    private final RentRequestUseCase rentRequestUseCase = new RentRequestUseCase(carRepository, customerRepository);

    @Etantdonné("Mr {string} propriétaire d’un téléphone jetable")
    public void mr_propriétaire_d_un_téléphone_jetable(String name) {
        Customer customer = new Customer(name, true, false);
        customerRepository.add(customer);
    }

    @Etantdonné("Mr {string} ayant un numéro provisoire")
    public void mrAyantUnNumeroProvisoire(String name) {
        Customer customer = new Customer(name, false, true);
        customerRepository.add(customer);
    }

    @Etantdonné("une {string} à {int}€ par mois")
    public void une_voiture_a(String carName, int price) {
        Car car = new Car(carName, price);
        carRepository.add(car);
    }

    @Quand("Mr {string} demande à louer une {string}")
    public void mrDemandeALouerUne(String name, String carName) {
        RentRequest command = new RentRequest(name, carName);
        rentalRequest = rentRequestUseCase.execute(command);
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
