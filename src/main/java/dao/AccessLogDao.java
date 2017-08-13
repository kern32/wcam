package dao;

import entity.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccessLogDao {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    //put database url/username/password
    private static final String DB_URL = "";
    private static final String USER = "";
    private static final String PASS = "";

    private static final String selectSQL = "SELECT time, ip, os, country, city, browser from access_log order by time desc limit 15";
    private static final String insertSQL = "INSERT into access_log (time, ip, os, country, city, browser) values (?, ?, ?, ?, ?, ?)";
    private static Connection dbConnection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;

    public static List<Log> getLastAccess(){
        ArrayList<Log> list = new ArrayList();
        try {
            dbConnection = ConnectionManager.getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while(resultSet.next()){
                Timestamp timestamp = resultSet.getTimestamp(1);
                String ip = resultSet.getString(2);
                String os = resultSet.getString(3);
                String country = resultSet.getString(4);
                String city = resultSet.getString(5);
                String browser = resultSet.getString(6);
                Log logItem = new Log(timestamp, ip, country, city, browser, os);

                list.add(logItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return  list;
    }

    public static void addEntryIp(Log item){
        try {
            dbConnection = ConnectionManager.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertSQL);
            preparedStatement.setTimestamp(1, item.getTime());
            preparedStatement.setString(2, item.getIp());
            preparedStatement.setString(3, item.getOs());
            preparedStatement.setString(4, item.getCountry());
            preparedStatement.setString(5, item.getCity());
            preparedStatement.setString(6, item.getBrowser());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ConnectionManager {
        static Connection dbConnection = null;

        static Connection getDBConnection() {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
                return dbConnection;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return dbConnection;
        }
    }
}

