package nikVl.demoexPractice.entities;

import nikVl.demoexPractice.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    public static void insertIntoTable(Product product) throws SQLException {
        try(Connection connection = Main.getConnection()) {
            String sql = "INSERT INTO product(Title,ArticleNumber,MinCostForAgent,image,ProductionPersonCount,ProductionWorkshopNumber,ProductTypeId,Description) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getArticleNumber());
            preparedStatement.setDouble(3,product.getMinCostForAgent());
            preparedStatement.setString(4,product.getImage());
            preparedStatement.setInt(5,product.getProductionPersonCount());
            preparedStatement.setInt(6,product.getProductionWorkshopNumber());
            preparedStatement.setInt(7,product.getProductionPersonCount());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                product.setID(resultSet.getInt(1));
                return;
            }
        }
    }
    public static void update(Product product) throws SQLException {
        try(Connection connection = Main.getConnection()){
            String sql = "UPDATE product SET Title = ?,ArticleNumber = ?,MinCostForAgent = ?,image = ?,ProductionPersonCount = ?,ProductionWorkshopNumber = ?,ProductTypeId =?,Description = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getArticleNumber());
            preparedStatement.setDouble(3,product.getMinCostForAgent());
            preparedStatement.setString(4,product.getImage());
            preparedStatement.setInt(5,product.getProductionPersonCount());
            preparedStatement.setInt(6,product.getProductionWorkshopNumber());
            preparedStatement.setInt(7,product.getProductionPersonCount());
            preparedStatement.setString(8,product.getDescription());
            preparedStatement.setInt(9,product.getID());

            preparedStatement.executeUpdate();

        }
    }
    public static void deleteById(int id) throws SQLException {
        try(Connection connection = Main.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE ID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
    }

    public static Product selectById(int id) throws SQLException {
        try(Connection connection = Main.getConnection()){
            String sql = "SELECT * FROM product WHERE ID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                return new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getString(9)
                );
            }
            return null;
        }
    }
    public static List<Product> selectAll() throws SQLException {
        try(Connection connection = Main.getConnection()){
            String sql = "SELECT * FROM product";

            Statement statement = connection.createStatement();
            statement.executeQuery(sql);

            ResultSet resultSet = statement.getResultSet();

            List<Product> mainList = new ArrayList<>();

            while(resultSet.next()){
                mainList.add(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getString(9)
                )
                );
            }
            return mainList;
        }
    }
}
