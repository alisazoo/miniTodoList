package dao;

import model.LoginBean;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException{
        boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");

        try(Connection connection = JDBCUtils.getConnection();
            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? and password = ?;")
        ){
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();

        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return status;

    }

    public int returnID(LoginBean loginBean) throws ClassNotFoundException{
        int userId = 0;
        Class.forName("com.mysql.jdbc.Driver");

        try(Connection connection = JDBCUtils.getConnection();
            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT user_id FROM users WHERE username = ? and " +
                            "password = ?;")
        ){
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());
            //System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                userId = resultSet.getInt("user_id");

        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return userId;
    }
}
