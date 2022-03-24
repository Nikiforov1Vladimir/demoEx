package nikVl.demoexPractice.entities;

import nikVl.demoexPractice.Main;

import java.sql.*;

public class Manager {
    public static void insertIntoTable(Product product) throws SQLException {
        try(Connection connection = Main.getConnection()) {
            String sql = "INSERT INTO product(Title,ProductTypeId,ArticleNumber,Description,image,ProductionPersonCount,ProductionWorkshopNumber,MinCostForAgent) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,product.getTitle());
            preparedStatement.setInt(2,product.getProductTypeId());
            preparedStatement.setString(3,product.getArticleNumber());
            preparedStatement.setString(4,product.getDescription());
            preparedStatement.setString(5,product.getImage());
            preparedStatement.setInt(6,product.getProductionPersonCount());
            preparedStatement.setInt(7,product.getProductionWorkshopNumber());
            preparedStatement.setDouble(8,product.getMinCostForAgent());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                product.setID(resultSet.getInt(1));
                return;
            }
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
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getDouble(9)
                );
            }
            return null;
        }
    }
}
