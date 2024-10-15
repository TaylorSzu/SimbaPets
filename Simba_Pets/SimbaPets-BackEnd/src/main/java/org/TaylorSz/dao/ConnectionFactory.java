package org.TaylorSz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory {
    private ConnectionFactory(){}

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/simba_pets","TaylorSz", "tos200689");
        } catch (SQLException | ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }

}