package io.github.gabbloquet.todolist.domain.features;

import io.cucumber.java.fr.Etantdonné;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static org.mockito.Mockito.when;

public class TimeTrackingSpec {

    @Etantdonné("la date du jour {dateTime}")
    public void la_date_du_jour_a(LocalDateTime dateTime) {
        when(localDateTimeSupplier.get())
                .thenReturn(dateTime);
    }
}
