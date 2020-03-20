package ua.lviv.lgs.magazineShop;


import ua.lviv.lgs.magazineShop.dao.DAOException;
import ua.lviv.lgs.magazineShop.dao.MagazineDAO;
import ua.lviv.lgs.magazineShop.dao.SubscribeDAO;
import ua.lviv.lgs.magazineShop.dao.UserDAO;
import ua.lviv.lgs.magazineShop.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws DAOException {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Іван", "Петренко", "petrenko@gmail.com", "123456", "USER"));
        userList.add(new User("Василь", "Дубовий", "vas_dyb@gmail.com", "123456", "USER"));

        UserDAO userDAO = new UserDAO();
        userList.forEach(user -> {
            try {
                System.out.println(userDAO.insert(user.getFirstName(), user.getLastName(), user.getEmail(),
                        user.getPassword(), user.getAccessLevel()));
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });

        System.out.println(userDAO.readByID(2));
        System.out.println(userDAO.readByEmail("petrenko@gmail.com"));
        userDAO.updateByID(1, "Джон", "Питерс", "petrenko@gmail.com", "123456", "АDMIN");
        userDAO.updateByEmail("Вася", "Дуб", "vas_dyb@gmail.com", "123456", "USER");
        userDAO.delete(1);
        userDAO.readAll().forEach(System.out::println);

        MagazineDAO magazineDAO = new MagazineDAO();
        System.out.println(
                magazineDAO.insert("Playboy", "Маша покоряет шоу-бизнес и продает экзотические острова!",
                        LocalDate.parse("2019-04-01"), 6005));
        magazineDAO.readAll().forEach(System.out::println);

        SubscribeDAO subscribeDAO = new SubscribeDAO();
        System.out.println(subscribeDAO.insert(2, 1, true, LocalDate.parse("2019-04-26"), 12));
        subscribeDAO.readAll().forEach(System.out::println);
    }
}