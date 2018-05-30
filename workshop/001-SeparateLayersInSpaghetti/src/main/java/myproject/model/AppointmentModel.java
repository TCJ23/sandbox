package myproject.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
//@Setter - bez setterów DTO po bożemu, nie można modyfikować po stworzeniu
@Builder
@EqualsAndHashCode
public class AppointmentModel {
    private Integer id;

    private Date termin;
    private String opis;
    private String miejsce;
        /*
        Zawsze hascody i equalsy
         */
}
