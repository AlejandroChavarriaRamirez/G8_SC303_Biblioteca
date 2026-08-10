/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aleja
 */

import java.sql.*;

public class SentenciasPrestamo extends Conexion {

    public boolean registrar(Prestamo pre) {
        String sql = "INSERT INTO prestamo (id_prestamo, fecha_entrega, fecha_limite, id_libro, id_usuario) VALUES (?,?,?,?,?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pre.getIdPrestamo());
            ps.setString(2, pre.getFechaEntrega());
            ps.setString(3, pre.getFechaLimite());
            ps.setInt(4, pre.getIdLibro());
            ps.setInt(5, pre.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar prestamo: " + e);
            return false;
        }
    }

    public boolean modificar(Prestamo pre) {
        String sql = "UPDATE prestamo SET fecha_entrega=?, fecha_limite=?, id_libro=?, id_usuario=? WHERE id_prestamo=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pre.getFechaEntrega());
            ps.setString(2, pre.getFechaLimite());
            ps.setInt(3, pre.getIdLibro());
            ps.setInt(4, pre.getIdUsuario());
            ps.setInt(5, pre.getIdPrestamo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar prestamo: " + e);
            return false;
        }
    }

    public boolean eliminar(Prestamo pre) {
        String sql = "DELETE FROM prestamo WHERE id_prestamo=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pre.getIdPrestamo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar prestamo: " + e);
            return false;
        }
    }

    public boolean buscar(Prestamo pre) {
        String sql = "SELECT * FROM prestamo WHERE id_prestamo= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pre.getIdPrestamo());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pre.setIdPrestamo(rs.getInt("id_prestamo"));
                    pre.setFechaEntrega(rs.getString("fecha_entrega"));
                    pre.setFechaLimite(rs.getString("fecha_limite"));
                    pre.setIdLibro(rs.getInt("id_libro"));
                    pre.setIdUsuario(rs.getInt("id_usuario"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al buscar el prestamo: " + e);
            return false;
        }
    }
}