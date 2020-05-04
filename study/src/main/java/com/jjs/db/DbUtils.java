package com.jjs.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * @author jjs
 * @Version 1.0 2020/5/4
 */
public class DbUtils {
    private static Properties prop;

    static {
        try {
            InputStream in = DbUtils.class.getResourceAsStream("db.properties");
            if (in == null) {
                in = DbUtils.class.getClassLoader().getResourceAsStream("db.properties");
            }
            prop = new Properties();
            prop.load(in);
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws Exception {
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        String driver = prop.getProperty("driver");
        Class.forName(driver);
        return DriverManager.getConnection(url,username,password);
    }


    public static void main(String[] args) throws Exception {
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement("insert into test_tb(c1, c2) values(?,?)");

        for (int i = 0; i < 10; i++) {
            statement.setInt(1, i);
            statement.setString(2, "jjs" + i);
            statement.addBatch();
        }
        statement.executeBatch();
        statement.close();
        connection.close();

        System.out.println("===");
    }
}
