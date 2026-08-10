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
public class SentenciasDevolucion extends Conexion {

    public boolean registrar(Devolucion dev) {
        String sql = "INSERT INTO devolucion (id_devolucion, fecha_devolucion, id_prestamo) VALUES (?,?,?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dev.getIdDevolucion());
            ps.setString(2, dev.getFechaDevolucion());
            ps.setInt(3, dev.getIdPrestamo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar devolucion: " + e);
            return false;
        }
    }

    public boolean modificar(Devolucion dev) {
        String sql = "UPDATE devolucion SET fecha_devolucion=?, id_prestamo=? WHERE id_devolucion=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dev.getFechaDevolucion());
            ps.setInt(2, dev.getIdPrestamo());
            ps.setInt(3, dev.getIdDevolucion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar devolucion: " + e);
            return false;
        }
    }

    public boolean eliminar(Devolucion dev) {
        String sql = "DELETE FROM devolucion WHERE id_devolucion=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dev.getIdDevolucion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar devolucion: " + e);
            return false;
        }
    }

    public boolean buscar(Devolucion dev) {
        String sql = "SELECT * FROM devolucion WHERE id_devolucion= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dev.getIdDevolucion());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dev.setIdDevolucion(rs.getInt("id_devolucion"));
                    dev.setFechaDevolucion(rs.getString("fecha_devolucion"));
                    dev.setIdPrestamo(rs.getInt("id_prestamo"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al buscar la devolucion: " + e);
            return false;
        }
    }
}
