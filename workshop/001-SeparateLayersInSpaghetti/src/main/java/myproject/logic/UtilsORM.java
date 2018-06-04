package myproject.logic;

import myproject.model.AppointmentModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilsORM {

//    Utilsy nie mają konstruktora (default jako private) stąd statyczność


    private UtilsORM() {
    }

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

    public static String insertSQL(AppointmentModel appointmentModel) {
        return "INSERT INTO Kalendarz (Id, Termin, Opis, Miejsce) " +
                "VALUES (" + appointmentModel.getId() + ",#" + appointmentModel.getTermin() + "#, '" + appointmentModel.getOpis() + "', '" + appointmentModel.getMiejsce() + "')";
    }

    public static String deleteSQL(AppointmentModel appointmentModel) {
        return "DELETE FROM Kalendarz WHERE Id=" + appointmentModel.getId();
    }

    public static String updateSQL(AppointmentModel appointmentModel) {
        return "UPDATE Kalendarz SET Termin=#" + appointmentModel.getTermin() + "#, Opis='" + appointmentModel.getOpis() + "', Miejsce='" + appointmentModel.getMiejsce() + "' WHERE Id=" + appointmentModel.getId();
    }

}
