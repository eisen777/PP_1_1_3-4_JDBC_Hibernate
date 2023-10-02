package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        List<User> users = new ArrayList<>();
        users.add(new User("Mithum", "Chakraborty", (byte) 45));
        users.add(new User("Walter", "White", (byte) 44));
        users.add(new User("Hector", "Salamanka", (byte) 65));
        users.add(new User("Jesse", "Pinkman", (byte) 24));

        for (User us : users) {
            userService.saveUser(us.getName(), us.getLastName(), us.getAge());
            System.out.println("User с именем – " + us.getName() + " добавлен в базу данных");
        }

        List<User> usersTable = userService.getAllUsers();
        for (User us : usersTable) {
            System.out.println(us);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
