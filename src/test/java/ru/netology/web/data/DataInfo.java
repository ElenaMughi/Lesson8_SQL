package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.sql.*;

public class DataInfo {
    private DataInfo() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {
        return new AuthInfo("petya", "123qwerty");
    }

    public static AuthInfo getAuthInfoFromBase(String name) throws SQLException {
        String login = name;
        String password = "";
        String dataSQL = "SELECT login, password FROM users where login = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            PreparedStatement usersStmt = conn.prepareStatement(dataSQL);
            usersStmt.setString(1, name);
            try (ResultSet rs = usersStmt.executeQuery(dataSQL)) {
                rs.next();
                password = rs.getString("password");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Запрос " + dataSQL + " не выполнен!");
            }
        }
        return new AuthInfo(login, password);
    }

    public static VerificationCode getVerificationCodeFromBase(String name) throws SQLException {
        String code = "";
        String dataSQL = "SELECT auth_codes.code FROM users, auth_codes where users.id=auth_codes.user_id and users.login = '" + name + "';";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            Statement usersStmt = conn.createStatement();
            try (ResultSet rs = usersStmt.executeQuery(dataSQL)) {
                rs.next();
                System.out.println(rs.getString("auth_codes.code"));
                code = rs.getString("auth_codes.code");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Запрос " + dataSQL + " не выполнен!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Соединение не установлено!");
        }
        return new VerificationCode(code);
    }

    public static void closeConnect() throws SQLException {
        String dataSQL = "delete from users;";
        String dataSQL2 = "delete from auth_codes;";
        String dataSQL3 = "delete from cards;";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            Statement usersStmt = conn.createStatement();
            usersStmt.executeQuery(dataSQL2);
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка удаления записей!");
        }
    }

    public static AuthInfo addUser() throws SQLException {
        Faker faker = new Faker();
        String login = faker.name().firstName();
        String password = "password";
        String dataSQL = "INSERT INTO users(id, login, password, status) VALUES (82, ?, ?, 'active');";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
             PreparedStatement preparedStatement = conn.prepareStatement(dataSQL);
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        }
        return new AuthInfo(login, password);
    }

}
