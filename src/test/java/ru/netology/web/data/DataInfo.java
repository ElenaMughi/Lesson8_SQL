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

    public static VerificationCode getVerificationCodeFromBase(String name) {
        String code = "";
        String dataSQL = "SELECT auth_codes.code " +
                "FROM users, auth_codes where users.id=auth_codes.user_id and users.login = '" + name + "' " +
                "ORDER BY auth_codes.id DESC;";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            Statement usersStmt = conn.createStatement();
            try (ResultSet rs = usersStmt.executeQuery(dataSQL)) {
                rs.next();
                code = rs.getString("auth_codes.code");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new VerificationCode(code);
    }

    public static void clearBase() {
        String dataSQL = "delete from users;";
        String dataSQL2 = "delete from auth_codes;";
        String dataSQL3 =  "delete from cards;";
        int count = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            Statement usersStmt = conn.createStatement();
            count = count + usersStmt.executeUpdate(dataSQL3);
            count = count + usersStmt.executeUpdate(dataSQL2);
            count = count + usersStmt.executeUpdate(dataSQL);
            System.out.println(count);
            conn.setAutoCommit(true);
            usersStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
