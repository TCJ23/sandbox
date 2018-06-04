package myproject.logic;

import myproject.database.DBConnector;
import myproject.model.AppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AppointmentDAO {

    private static final AtomicInteger ID_SEQ = new AtomicInteger(0); // good practice nie chcemy zmian i duplikacji per klasa Manager
    private static final String GET_ALL = "SELECT * FROM [Kalendarz]";
    @Autowired
    private DBConnector dbc;

    public Integer create(AppointmentModel ap) {
        ap.setId(ID_SEQ.incrementAndGet());
        dbc.executeQuery(UtilsORM.insertSQL(ap));
        return ap.getId();
    }

    public void delete(AppointmentModel ap) {
        dbc.executeQuery(UtilsORM.deleteSQL(ap));
    }

    public void update(AppointmentModel ap) {
        dbc.executeQuery(UtilsORM.updateSQL(ap));
    }

    public Iterator<AppointmentModel> getall() {
//        ResultSet >> Iterator - po to jest metoda convert2model
        ResultSet resultSet = dbc.executeQuery(GET_ALL);
        List<AppointmentModel> models = new ArrayList<>();
        try {
            while (resultSet.next()){
                models.add(UtilsORM.convert2Model(resultSet));
            }
        }
        catch (SQLException sqlEx){
            sqlEx.printStackTrace();
            return new ArrayList<AppointmentModel>().iterator();
        }
        return models.iterator();
    }
}
