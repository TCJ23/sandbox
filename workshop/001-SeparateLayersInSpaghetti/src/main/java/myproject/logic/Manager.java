package myproject.logic;

import myproject.model.AppointmentModel;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

// can include multiple DAO's
public class Manager {

    private AppointmentDAO dao;

    public Integer create(AppointmentModel ap) {
        return dao.create(ap);//get ID
    }

    public void delete(AppointmentModel ap) {
        dao.delete(ap);// CRUD repo meth
    }

    public void update(AppointmentModel ap) {
        dao.update(ap);      // CRUD repo method
    }

    public Iterator<AppointmentModel> getall() {
        return dao.getall();
    }

}
