/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;

/**
 *
 * @author aleja
 */

public class Conexion {
 
    Connection con = null;
    String base = "biblioteca";
    String url = "jdbc:mysql://localhost:3306/" + base;
    String user = "root";
    String password = "";
 
    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexion: " + e);
        }
        return con;
    }
}