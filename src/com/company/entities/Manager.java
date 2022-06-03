package com.company.entities;

import com.company.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manager{
    public static void insertIntoTable(Product product) throws SQLException {
        try(Connection connection = Main.getConnection()){
            String sql = "INSERT INTO product(Title,ProductTypeId,ArticleNumber,Description,Image,ProductionPersonCount,ProductionWorkshopNumber,MinCostForAgent) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,product.getTitle());
            preparedStatement.setInt(2,product.getProductTypeId());
            preparedStatement.setString(3,product.getArticleNumber());
            preparedStatement.setString(4,product.getDescription());
            preparedStatement.setString(5,product.getImage());
            preparedStatement.setInt(6,product.getProductionPersonCount());
            preparedStatement.setInt(7,product.getProductionWorkshopNumber());
            preparedStatement.setDouble(8,product.getMinCostForAgent());
            preparedStatement.executeUpdate();

//            ResultSet resultSet = preparedStatement.getResultSet();
//
//            if(resultSet.next()){
//                product.setID(resultSet.getInt(1)
//                );
//            }
        }
    }

    public static void updateProduct(Product product) throws SQLException {
        try(Connection connection = Main.getConnection()){
            String sql = "UPDATE product SET Title = ?,ProductTypeId = ?, ArticleNumber = ?, Description = ?, Image = ?, ProductionPersonCount = ?, ProductionWorkshopNumber = ?, MinCostForAgent = ? WHERE ID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,product.getTitle());
            preparedStatement.setInt(2,product.getProductTypeId());
            preparedStatement.setString(3,product.getArticleNumber());
            preparedStatement.setString(4,product.getDescription());
            preparedStatement.setString(5,product.getImage());
            preparedStatement.setInt(6,product.getProductionPersonCount());
            preparedStatement.setInt(7,product.getProductionWorkshopNumber());
            preparedStatement.setDouble(8,product.getMinCostForAgent());
            preparedStatement.setInt(9,product.getID());
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteById(int id) throws SQLException {
        try(Connection connection = Main.getConnection()){
            String sql = "DELETE FROM product WHERE ID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        }
    }

    public static Product selectById(int id) throws SQLException{
        try(Connection connection = Main.getConnection()){
            String sql = "SELECT FROM products WHERE ID = ?";

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
            }return null;
        }
    }

    public static List<Product> selectAll() throws SQLException
    {
        try(Connection connection = Main.getConnection())
        {
            String sql = "SELECT * FROM product";
            Statement statement = connection.createStatement();

            statement.executeQuery(sql);

            ResultSet resultSet = statement.getResultSet();

            List<Product> returnableList = new ArrayList<>();

            while(resultSet.next()){
                returnableList.add(
                        new Product(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getInt(7),
                                resultSet.getInt(8),
                                resultSet.getDouble(9)
                        )
                );
            }
            return returnableList;

        }
    }
}
