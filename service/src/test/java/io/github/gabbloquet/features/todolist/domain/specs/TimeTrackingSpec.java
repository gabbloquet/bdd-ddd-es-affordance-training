package io.github.gabbloquet.features.todolist.domain.specs;

import io.cucumber.java.fr.Etantdonné;
import io.github.gabbloquet.todolist.todolist.model.LocalDateTimeSupplier;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TimeTrackingSpec {

    @Autowired
    private LocalDateTimeSupplier localDateTimeSupplier;

    @Etantdonné("la date et l'heure du jour {dateTime}")
    public void la_date_du_jour_a(LocalDateTime dateTime) {
        when(localDateTimeSupplier.get())
                .thenReturn(dateTime);
    }
}
