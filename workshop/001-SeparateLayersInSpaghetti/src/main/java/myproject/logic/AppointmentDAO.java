package myproject.logic;

import myproject.database.DBConnector;
import myproject.model.AppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    private void setup() {
        Integer maxID = maxID();
        ID_SEQ.addAndGet(maxID);
    }

    public Integer create(AppointmentModel ap) {
        ap.setId(ID_SEQ.incrementAndGet());
        dbc.executeQuery(UtilsORM.insertSQL(ap));
        return ap.getId();
    }

    public void delete(Integer id) {

        dbc.executeQuery(UtilsORM.deleteSQL(id));
    }

    public void update(AppointmentModel ap) {
        dbc.executeQuery(UtilsORM.updateSQL(ap));
    }

    public Iterator<AppointmentModel> getall() {
//        ResultSet >> Iterator - po to jest metoda convert2model
        ResultSet resultSet = dbc.executeSearchQuery(GET_ALL);
        List<AppointmentModel> models = new ArrayList<>();
        try {
            while (resultSet.next()) {
                models.add(UtilsORM.convert2Model(resultSet));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return new ArrayList<AppointmentModel>().iterator();
        }
        return models.iterator();
    }

    /*helper method for MAX ID SELECT MAX(column_name)
    FROM table_name*/
    private Integer maxID() {
        try {
            ResultSet resultSetID = dbc.executeSearchQuery("SELECT MAX(Id) FROM Kalendarz");
            if (resultSetID.next()) {
                return resultSetID.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.MAX_VALUE;
    }
}
