package myproject.view;

import myproject.logic.Manager;
import myproject.model.AppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

//https://stackoverflow.com/questions/36160353/why-does-swing-think-its-headless-under-spring-boot-but-not-under-spring-or-pl
@Configuration
public class Viewer {

    @Autowired
    private Manager mgr;

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

        // lambda action listener - wszystkie buttony do jednej metody, dodaj buttona, generyczna
        JButton button = new JButton("Usun Rekord");
        button.setBounds(432, 113, 130, 23);
        frame.getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow < 0) return;
                Integer appointmentID = (Integer) table.getValueAt(selectedRow, 0);
                tableView.removeRow(selectedRow);//widok only
                mgr.delete(appointmentID);
            }
        });

        JButton button_2 = new JButton("Dodaj Rekord");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AppointmentModel ap = AppointmentModel.builder()
                        .miejsce("Miejsce")
                        .opis("Opis")
                        .termin(Date.valueOf(LocalDate.now()))
                        .build();
                Integer id = mgr.create(ap);
                tableView.addRow(new Object[]{id, ap.getTermin(), ap.getOpis() + id, ap.getMiejsce()});
            }
        });
        button_2.setBounds(432, 79, 130, 23);
        frame.getContentPane().add(button_2);

        JButton button_4 = new JButton("SQL-Reload");
        button_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // usuwamy wszystkie rekordy aby uniknąć zduplikowania wczytywanioa
                int rows = tableView.getRowCount();
                for (int i = 0; i < rows; i++) {
                    tableView.removeRow(0);
                }
                mgr.getall().forEachRemaining(appointmentModel -> tableView.addRow(new Object[]{appointmentModel.getId(),
                        appointmentModel.getTermin(),
                        appointmentModel.getOpis(),
                        appointmentModel.getMiejsce()}));

            }
        });

        button_4.setBounds(432, 11, 130, 23);
        frame.getContentPane().add(button_4);


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

}
