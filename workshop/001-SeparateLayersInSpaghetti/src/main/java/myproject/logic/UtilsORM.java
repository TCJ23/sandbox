package myproject.logic;

import com.sun.xml.internal.bind.v2.model.core.ID;
import myproject.model.AppointmentModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilsORM {

    private static final String ID = "Id";
    private static final String MIEJSCE = "Miejsce";
    private static final String OPIS = "Opis";
    private static final String TERMIN = "Termin";

    public static AppointmentModel convert2Model(ResultSet rs) {
    // przyda się w ....
        try {
            return AppointmentModel.builder()
                    .id(rs.getInt(ID))
                    .miejsce(rs.getString(MIEJSCE))
                    .opis(rs.getString(OPIS))
                    .termin(rs.getDate(TERMIN))
                    .build();

        } catch (SQLException e) { //TO DO
            e.printStackTrace();
        }
        return null;
    }
}
