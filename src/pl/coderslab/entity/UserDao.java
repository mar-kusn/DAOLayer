package pl.coderslab.entity;

import java.sql.*;
//import org.mindrot.jbcrypt.BCrypt;

public class UserDao {
    // zapytanie dodawania użytkownika
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    // zapytanie zmiany danych użytkownika
    private static final String UPDATE_USER_QUERY =
            "UPDATE users set email=?, username=?, password=? WHERE id=?";

    // zapytanie pobierania danych użytkownika po id
    private static final String READ_USER_QUERY =
            "SELECT email, username, password from users WHERE id=?";

    // zapytanie usuwania użytkownika po id
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id=?";

    // zapytanie pobierania wszystkich użytkowników
    private static final String SHOW_ALL_USERS_QUERY =
            "SELECT id, email, username, password FROM users";

    public String hashPassword(String password) {
        //return BCrypt.hashpw(password, BCrypt.gensalt());
        return password;
    }

    // zapis nowego użytkownika do bazy,
    public User create(User user) {
        /*
        W ramach metody należy:
            zapisać do bazy danych informacje z obiektu
            pobrać id nowo zapisanego użytkownika
            uzupełnić id w obiekcie
            zwrócić uzupełniony obiekt
         */
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // wczytanie użytkownika po jego id,
    public User read(int userId) {
        /*
        W ramach metody należy wykonać:
            pobrać z bazy danych wiersz dla zadanego identy katora
            utworzyć nowy obiekt klasy User
            uzupełnić obiekt danymi z bazy
            zwrócić uzupełniony obiekt
         */

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            if (resultSet.next()) {
                // twozymy nowy obiekt który zostanie zwrócony
                User user = new User();
                user.setId(userId);
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;    // nie zanalazło takiego użytkownika
    }
    // edycja danych użytkownika
    public void update(User user) {
        /*
        Metoda przyjmuje obiekt klasy User, który powinien posiadać wypełnione atrybuty, (userName,
            email, password, id),
        Metoda nic nie zwraca.
        W ramach metody należy zmienić dane w bazie na podstawie danych z obiektu.
         */
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // usunięcia użytkownika
    public void delete(int userId) {
        /*
        Metoda przyjmuje identy kator na podstawie, którego należy w bazie danych pobrać wiersz.
        Metoda nic nie zwraca.
        W ramach metody należy usunąć wiersz z bazy danych na podstawie przekazanego identy katora.
         */
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // wczytanie wszystkich użytkowników
    public User[] findAll() {
        /*
        W ramach metody należy wykonać:
            pobrać z bazy danych wszystkie wiersze z tabeli users
            na podstawie każdego wiersza utworzyć obiekt klasy User
            obiekty umieścić w tablicy
            zwrócić tablicę obiektów
            Będziemy również potrzebować mechanizmu, który pozwoli nam automatycznie powiększać tablicę.
         */
        return null;
    }
}
