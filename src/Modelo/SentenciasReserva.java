/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;

/**
 *
 * @author Usuario
 */
public class SentenciasReserva extends Conexion {

    public boolean registrar(Reserva res) {
        String sql = "INSERT INTO reserva (id_reserva, fecha_reserva, estado, id_libro, id_usuario) VALUES (?,?,?,?,?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, res.getIdReserva());
            ps.setString(2, res.getFechaReserva());
            ps.setString(3, res.getEstado());
            ps.setInt(4, res.getIdLibro());
            ps.setInt(5, res.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar reserva: " + e);
            return false;
        }
    }

    public boolean modificar(Reserva res) {
        String sql = "UPDATE reserva SET fecha_reserva=?, estado=?, id_libro=?, id_usuario=? WHERE id_reserva=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, res.getFechaReserva());
            ps.setString(2, res.getEstado());
            ps.setInt(3, res.getIdLibro());
            ps.setInt(4, res.getIdUsuario());
            ps.setInt(5, res.getIdReserva());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar reserva: " + e);
            return false;
        }
    }

    public boolean eliminar(Reserva res) {
        String sql = "DELETE FROM reserva WHERE id_reserva=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, res.getIdReserva());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar reserva: " + e);
            return false;
        }
    }

    public boolean buscar(Reserva res) {
        String sql = "SELECT * FROM reserva WHERE id_reserva= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, res.getIdReserva());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    res.setIdReserva(rs.getInt("id_reserva"));
                    res.setFechaReserva(rs.getString("fecha_reserva"));
                    res.setEstado(rs.getString("estado"));
                    res.setIdLibro(rs.getInt("id_libro"));
                    res.setIdUsuario(rs.getInt("id_usuario"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al buscar la reserva: " + e);
            return false;
        }
    }

    public boolean buscarPendiente(Reserva res) {
        String sql = "SELECT * FROM reserva WHERE id_libro= ? AND estado='reservado'";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, res.getIdLibro());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    res.setIdReserva(rs.getInt("id_reserva"));
                    res.setFechaReserva(rs.getString("fecha_reserva"));
                    res.setEstado(rs.getString("estado"));
                    res.setIdLibro(rs.getInt("id_libro"));
                    res.setIdUsuario(rs.getInt("id_usuario"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al buscar reserva pendiente: " + e);
            return false;
        }
    }
}
