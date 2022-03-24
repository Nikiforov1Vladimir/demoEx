package nikVl.demoexPractice;

import nikVl.demoexPractice.entities.Manager;
import nikVl.demoexPractice.entities.Product;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println(Manager.selectById(2));
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/demoex","root","rootAdmin");
    }
}
