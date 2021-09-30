package dao;

import model.User;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public int registerEmployee(User employee) throws ClassNotFoundException{
        String INSERT_USER_SQL = "INSERT INTO users" +
                "(first_name, last_name, username, password) " +
                "VALUES (?, ?, ?, ?);";

        int result = 0;
        try(Connection connection = JDBCUtils.getConnection();
            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)
        ){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUsername());
            preparedStatement.setString(4, employee.getPassword());

            System.out.println(preparedStatement);
            // Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return result;
    }
}
