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
public class SentenciasLibro extends Conexion {

    public boolean registrar(Libro lib) {
        String sql = "INSERT INTO libro (id_libro, titulo, autor, categoria, editorial) VALUES (?,?,?,?,?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lib.getIdLibro());
            ps.setString(2, lib.getTitulo());
            ps.setString(3, lib.getAutor());
            ps.setString(4, lib.getCategoria());
            ps.setString(5, lib.getEditorial());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar libro: " + e);
            return false;
        }
    }

    public boolean modificar(Libro lib) {
        String sql = "UPDATE libro SET titulo=?, autor=?, categoria=?, editorial=? WHERE id_libro=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lib.getTitulo());
            ps.setString(2, lib.getAutor());
            ps.setString(3, lib.getCategoria());
            ps.setString(4, lib.getEditorial());
            ps.setInt(5, lib.getIdLibro());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar libro: " + e);
            return false;
        }
    }

    public boolean eliminar(Libro lib) {
        String sql = "DELETE FROM libro WHERE id_libro=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lib.getIdLibro());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar libro: " + e);
            return false;
        }
    }

    public boolean buscar(Libro lib) {
        String sql = "SELECT * FROM libro WHERE id_libro= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lib.getIdLibro());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lib.setIdLibro(rs.getInt("id_libro"));
                    lib.setTitulo(rs.getString("titulo"));
                    lib.setAutor(rs.getString("autor"));
                    lib.setCategoria(rs.getString("categoria"));
                    lib.setEditorial(rs.getString("editorial"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al buscar el libro: " + e);
            return false;
        }
    }

    public String consultarEstado(int idLibro) {
        String sql = "SELECT estado FROM libro WHERE id_libro= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("estado");
                }
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error al consultar el estado del libro: " + e);
            return null;
        }
    }

    public boolean actualizarEstado(int idLibro, String estado) {
        String sql = "UPDATE libro SET estado=? WHERE id_libro=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, idLibro);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado del libro: " + e);
            return false;
        }
    }
}
