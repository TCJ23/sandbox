package myproject.logic;

import myproject.model.AppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
// can include multiple DAO's
public class Manager {
    @Autowired
    private AppointmentDAO dao;

    public Integer create(AppointmentModel ap) {
        return dao.create(ap);//get ID
    }

    public void delete(Integer id) {
        dao.delete(id);// CRUD repo meth
    }

    public void update(AppointmentModel ap) {
        dao.update(ap);      // CRUD repo method
    }

    public Iterator<AppointmentModel> getall() {
        return dao.getall();
    }

}
