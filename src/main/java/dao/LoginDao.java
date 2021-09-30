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

        try(Connection connection = JDBCUtils.getConnection();
            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? and password = ?")
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
}
