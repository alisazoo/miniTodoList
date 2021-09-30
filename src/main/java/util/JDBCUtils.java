package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {

    // USE PostgreSQL
    private static String jdbcURL = "jdbc:postgresql://localhost:5432/demo";
    private static String jdbcUsername = "postgres";
    private static String jdbcPassword = "arisa";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("connected");
        } catch (SQLException sqlEx){
            System.out.println("failed to connect DB");
            sqlEx.printStackTrace();
        }
        return connection;
    }

    public static void printSQLException(SQLException ex){
        for (Throwable e: ex){
            if(e instanceof SQLException){
                e.printStackTrace();
                System.err.println("SQLState: " +( (SQLException)e).getSQLState() );
                System.err.println("Error code: " +( (SQLException)e).getErrorCode() );
                System.err.println("Message: " + e.getMessage() );
                Throwable t = ex.getCause();
                while(t != null){
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static Date getSQLDate(LocalDate date){
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }

}
