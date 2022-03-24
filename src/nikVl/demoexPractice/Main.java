package nikVl.demoexPractice;

import nikVl.demoexPractice.entities.Manager;
import nikVl.demoexPractice.entities.Product;
import nikVl.demoexPractice.util.baseForm;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        new baseForm();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/demoex","root","rootAdmin");
    }
}
