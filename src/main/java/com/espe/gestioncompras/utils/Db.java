package com.espe.gestioncompras.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Klever Jami
 */
public final class Db {
  
    private static final String URL =
            "jdbc:mysql://localhost:3306/gestioncompras?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Klever2025@";

    private Db() {}

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL Connector/J [web:19]
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver MySQL en el classpath", e);
        }
        return DriverManager.getConnection(URL, USER, PASS); // JDBC DriverManager [web:19]
    }
    
    
}
