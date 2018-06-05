package myproject.view;

import myproject.logic.Manager;
import myproject.model.AppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

//https://stackoverflow.com/questions/36160353/why-does-swing-think-its-headless-under-spring-boot-but-not-under-spring-or-pl
@Configuration
public class Viewer {

    @Autowired
    private Manager manager;

    @Bean
    public JFrame jframe() {

        final JFrame frame = new JFrame("JTable Demo");

        DefaultTableModel tableView = new DefaultTableModel();
        tableView.addColumn("Id");
        tableView.addColumn("Termin");
        tableView.addColumn("Opis");
        tableView.addColumn("Miejsce");

        frame.getContentPane().setLayout(null);

        final JTable table = new JTable(tableView);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 422, 172);
        table.setFillsViewportHeight(true);
        frame.getContentPane().add(scrollPane);


        JButton jButton = createjButton("Usun Rekord", new Rectangle(432, 113, 130, 23), actionEvent -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow < 0) return;
            Integer appointmentID = (Integer) table.getValueAt(selectedRow, 0);
            tableView.removeRow(selectedRow);//widok only
            manager.delete(appointmentID);
        });
        frame.getContentPane().add(jButton);

        JButton jButton2 = createjButton("Dodaj Rekord", new Rectangle(432, 79, 130, 23), actionEvent -> {
            AppointmentModel ap = AppointmentModel.builder()
                    .miejsce("Miejsce")
                    .opis("Opis")
                    .termin(Date.valueOf(LocalDate.now()))
                    .build();
            Integer id = manager.create(ap);
            tableView.addRow(new Object[]{id, ap.getTermin(), ap.getOpis() + id, ap.getMiejsce()});
        });
        frame.getContentPane().add(jButton2);

        JButton jButton4 = createjButton("SQL-Reload", new Rectangle(432, 11, 130, 23), actionEvent -> {
            int rows = tableView.getRowCount();
            for (int i = 0; i < rows; i++) {
                tableView.removeRow(0);
            }
            manager.getall().forEachRemaining(appointmentModel -> tableView.addRow(new Object[]{appointmentModel.getId(),
                    appointmentModel.getTermin(),
                    appointmentModel.getOpis(),
                    appointmentModel.getMiejsce()}));

        });
        frame.getContentPane().add(jButton4);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(588, 272);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem menuItem_1 = new JMenuItem("Open");
        menu.add(menuItem_1);

        JMenuItem menuItem_2 = new JMenuItem("Save");
        menu.add(menuItem_2);

        JMenu menu_1 = new JMenu("Edit");
        menuBar.add(menu_1);
        return frame;
    }

    private JButton createjButton(String text, Rectangle rect, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(rect);
        button.addActionListener(listener);
        return button;
    }
}
