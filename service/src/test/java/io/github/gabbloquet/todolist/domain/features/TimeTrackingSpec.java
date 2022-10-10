package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Etantdonné;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TimeTrackingSpec {

    @Autowired
    private Supplier<LocalDateTime> localDateTimeSupplier;

    @Etantdonné("la date du jour {dateTime}")
    public void la_date_du_jour_a(LocalDateTime dateTime) {
        when(localDateTimeSupplier.get())
                .thenReturn(dateTime);
    }
}
