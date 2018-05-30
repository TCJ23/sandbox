package myproject.logic;

import myproject.model.AppointmentModel;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

// can include multiple DAO's
public class Manager {

    private static final AtomicInteger ID_SEQ = new AtomicInteger(0); // good practice nie chcemy zmian i duplikacji per klasa Manager
    private AppointmentDAO dao;

    public Integer create(AppointmentModel ap) {
        return dao.create(ap);//get ID
    }

    public void delete(AppointmentModel ap) {
    dao.delete(ap);// CRUD repo meth
    }

    public void update(AppointmentModel ap) {
      dao.update(ap);      // CRUD repo meth
    }

    public Iterator<AppointmentModel> getall() {
        return dao.getall();
    }

}
