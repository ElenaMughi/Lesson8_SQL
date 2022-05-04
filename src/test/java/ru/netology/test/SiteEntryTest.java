package ru.netology.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteEntryTest {

    @BeforeEach
    void openSite() {
            open("http://localhost:9999");
    }

    @Test
    void loginOldTest() throws SQLException {
        String dataSQL = "SELECT login, password FROM users where login = 'vasya'";
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                Statement usersStmt = conn.createStatement();

        ) {
           try( ResultSet rs = usersStmt.executeQuery(dataSQL)) {
               rs.next();
               String login = rs.getString("login");
//            String password = rs.getString("password");
               String password = "qwerty123";

               $("[data-test-id=login] input").setValue(login);
               $("[data-test-id=password] input").setValue(password);
               $("[data-test-id=action-login]").click();

               assertTrue($("[data-test-id=code]").exists());
           }
        }
    }

    @Test
    void loginNewTest() throws SQLException {
        Faker faker = new Faker();
        String login = faker.name().username();
        String password = "password";
        String dataSQL = "INSERT INTO users(id, login, password, status) VALUES (80, ?, ?, 'active');";

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                PreparedStatement preparedStatement = conn.prepareStatement(dataSQL);
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

        }
        $("[data-test-id=login] input").setValue(login);
        $("[data-test-id=password] input").setValue(password);
        $("[data-test-id=action-login]").click();

        assertTrue($("[data-test-id=code]").exists());
    }

}
