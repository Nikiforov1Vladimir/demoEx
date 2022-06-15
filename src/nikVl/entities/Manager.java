package nikVl.entities;

import nikVl.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manager{

    public static void insertIntoTable(Product product) throws SQLException {
        try(Connection connection = Main.getConnection()){
            String sql = "INSERT INTO product(Title,ProductType,Description,Image,Cost,RegisterDate) VALUES(?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,product.getTitle());
            preparedStatement.setString(2,product.getProductType());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setString(4,product.getImage());
            preparedStatement.setInt(5,product.getCost());
            preparedStatement.setTimestamp(6,new Timestamp(product.getRegisterDate().getTime()));

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                product.setID(resultSet.getInt(1));
            }
        }
    }

    public static void updateProduct(Product product) throws SQLException {
        try(Connection connection = Main.getConnection()){
            String sql = "UPDATE product SET Title=?,ProductType=?,Description=?,Image=?,Cost=?,RegisterDate=? WHERE ID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,product.getTitle());
            preparedStatement.setString(2,product.getProductType());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setString(4,product.getImage());
            preparedStatement.setInt(5,product.getCost());
            preparedStatement.setTimestamp(6,new Timestamp(product.getRegisterDate().getTime()));
            preparedStatement.setInt(7,product.getID());

            preparedStatement.executeUpdate();

        }
    }

    public static void deleteProduct(Product product) throws SQLException{
        try(Connection connection = Main.getConnection()){
            String sql = "DELETE FROM product WHERE ID=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,product.getID());
            preparedStatement.executeUpdate();
        }
    }

    public static Product selectById(int id) throws SQLException {
        try(Connection connection = Main.getConnection()){
            String sql = "SELECT * FROM product WHERE ID=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                return new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getTimestamp(7)
                );
            }
            return null;
        }
    }

    public static List<Product> selectAllFromProduct() throws SQLException {
       try(Connection connection = Main.getConnection()){
           String sql = "SELECT * FROM product";

           Statement statement = connection.createStatement();
           statement.executeQuery(sql);

           List<Product> returningList = new ArrayList<>();

           ResultSet resultSet = statement.getResultSet();

           while (resultSet.next()){
               returningList.add(
                       new Product(
                               resultSet.getInt(1),
                               resultSet.getString(2),
                               resultSet.getString(3),
                               resultSet.getString(4),
                               resultSet.getString(5),
                               resultSet.getInt(6),
                               resultSet.getTimestamp(7)
                       )
               );
           }
           return returningList;
       }
    }

}
