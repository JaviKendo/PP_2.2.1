package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Javi", "Kendo", "javi@mail.ru",
                new Car("Toyota", 100)));
        userService.add(new User("Lionel", "Messi", "lionel@mail.ru",
                new Car("Porsche", 200)));
        userService.add(new User("Elon", "Musk", "elon@mail.ru",
                new Car("Lamborghini", 300)));
        userService.add(new User("Mark", "Zuckerberg", "mark@mail.ru",
                new Car("Ferrari", 400)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car id = " + user.getEpmCar().getId());
            System.out.println("Car model = " + user.getEpmCar().getModel());
            System.out.println("Car series = " + user.getEpmCar().getSeries());
            System.out.println();
        }

        System.out.println(userService.getAllUsersByCar("Ferrari", 400));
        System.out.println(userService.getAllUsersByCar("nonameUser", 0));

        context.close();
    }
}
