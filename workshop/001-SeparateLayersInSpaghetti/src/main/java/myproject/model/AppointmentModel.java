package myproject.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter - bez setterów DTO po bożemu, nie można modyfikować po stworzeniu
@Builder
@EqualsAndHashCode
public class AppointmentModel {
    private Integer id;

    private String termin;
    private String opis;
    private String miejsce;
        /*
        Zawsze hascody i equalsy
         */
}
