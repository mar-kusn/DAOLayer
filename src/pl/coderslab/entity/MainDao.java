package pl.coderslab.entity;

import java.util.Arrays;

public class MainDao {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();


        User user = new User();
        user.setEmail("user6@gmail.com");
        user.setUserName("user6");
        user.setPassword("user6pass");
        userDao.create(user);
        System.out.println(user.toString());
/**/

        // test UserDao.read(id)
        User userToRead2 = userDao.read(2);
        if (userToRead2 != null) {
            System.out.println(userToRead2);
        } else {
            System.out.println("Brak użytkownika o podanym id(2) w bazie!");
        }

        User userToRead12 = userDao.read(12);
        if (userToRead12 != null) {
            System.out.println(userToRead12);
        } else {
            System.out.println("Brak użytkownika o podanym id(12) w bazie!");
        }

        // test UserDao.update(User)
        User userToUpd = userDao.read(3);
        if (userToUpd != null) {
            System.out.println(userToUpd);
            userToUpd.setPassword("user3NewPassword");
            userToUpd.setEmail("user3New@gmail.com");
            System.out.println("Aktualizacja użytkownika...");
            userDao.update(userToUpd);
        } else {
            System.out.println("Brak użytkownika o podanym id(3) w bazie!");
        }
        // sprawdzamy poprawność edycji danych użytkownika o id=3
        userToUpd = userDao.read(3);
        if (userToUpd != null) {
            System.out.println("Nowe dane użytkownika o id=3");
            System.out.println(userToUpd);
        } else {
            System.out.println("Brak użytkownika o podanym id(3) w bazie!");
        }

        // test metody UserDao.delete(id)
/*
        User userToDelete = new User();
        userToDelete.setEmail("userToDelete2@gmail.com");
        userToDelete.setUserName("userToDelete2");
        userToDelete.setPassword("userToDelete2Pass");
        userDao.create(userToDelete);
        System.out.println(userToDelete.toString());

        userDao.delete(6);
        User user6 = userDao.read(6);
        if (user6 != null) {
            System.out.println(user6);
        } else {
            System.out.println("Brak użytkownika o podanym id(6) w bazie!");
        }
*/
        User[] allUsers = userDao.findAll();
        if (allUsers != null && allUsers.length != 0);
        {
            System.out.println("Lista wszystkich użytkowników");
            for (User u : allUsers) {
                System.out.println(u);
            }
        }

    }
}
