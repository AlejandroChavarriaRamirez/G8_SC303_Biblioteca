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
public class SentenciasReporte extends Conexion {

    public boolean registrar(Reporte rep) {
        String sql = "INSERT INTO reporte (id_reporte, tipo, fecha_generacion, id_usuario) VALUES (?,?,?,?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, rep.getIdReporte());
            ps.setString(2, rep.getTipo());
            ps.setString(3, rep.getFechaGeneracion());
            ps.setInt(4, rep.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar reporte: " + e);
            return false;
        }
    }

    public boolean modificar(Reporte rep) {
        String sql = "UPDATE reporte SET tipo=?, fecha_generacion=?, id_usuario=? WHERE id_reporte=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rep.getTipo());
            ps.setString(2, rep.getFechaGeneracion());
            ps.setInt(3, rep.getIdUsuario());
            ps.setInt(4, rep.getIdReporte());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar reporte: " + e);
            return false;
        }
    }

    public boolean eliminar(Reporte rep) {
        String sql = "DELETE FROM reporte WHERE id_reporte=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, rep.getIdReporte());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar reporte: " + e);
            return false;
        }
    }

    public boolean buscar(Reporte rep) {
        String sql = "SELECT * FROM reporte WHERE id_reporte= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, rep.getIdReporte());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rep.setIdReporte(rs.getInt("id_reporte"));
                    rep.setTipo(rs.getString("tipo"));
                    rep.setFechaGeneracion(rs.getString("fecha_generacion"));
                    rep.setIdUsuario(rs.getInt("id_usuario"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al buscar el reporte: " + e);
            return false;
        }
    }
}