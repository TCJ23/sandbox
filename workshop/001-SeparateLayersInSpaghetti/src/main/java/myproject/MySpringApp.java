package myproject;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class MySpringApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MySpringApp.class);
        JFrame frame = context.getBean(JFrame.class);
        frame.setVisible(true);
    }
}
//https://stackoverflow.com/questions/36160353/why-does-swing-think-its-headless-under-spring-boot-but-not-under-spring-or-pl