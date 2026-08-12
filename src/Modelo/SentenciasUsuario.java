/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author aleja
 */
public class SentenciasUsuario extends Conexion {

    //cifra la contrasena con SHA-256, segun indicacion de la profesora
    private String encriptar(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(texto.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error al encriptar la contrasena: " + e);
            return texto;
        }
    }

    public boolean registrar(Usuario usu) {
        String sql = "INSERT INTO usuario (id_usuario, nombre, correo, contrasena, rol) VALUES (?,?,?,?,?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usu.getIdUsuario());
            ps.setString(2, usu.getNombre());
            ps.setString(3, usu.getCorreo());
            ps.setString(4, encriptar(usu.getContrasena()));
            ps.setString(5, usu.getRol());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e);
            return false;
        }
    }

    public boolean modificar(Usuario usu) {
        String sql = "UPDATE usuario SET nombre=?, correo=?, contrasena=?, rol=? WHERE id_usuario=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getCorreo());
            ps.setString(3, encriptar(usu.getContrasena()));
            ps.setString(4, usu.getRol());
            ps.setInt(5, usu.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar usuario: " + e);
            return false;
        }
    }

    public boolean eliminar(Usuario usu) {
        String sql = "DELETE FROM usuario WHERE id_usuario=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usu.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e);
            return false;
        }
    }

    public boolean buscar(Usuario usu) {
        String sql = "SELECT * FROM usuario WHERE id_usuario= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usu.getIdUsuario());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usu.setIdUsuario(rs.getInt("id_usuario"));
                    usu.setNombre(rs.getString("nombre"));
                    usu.setCorreo(rs.getString("correo"));
                    usu.setContrasena(rs.getString("contrasena"));
                    usu.setRol(rs.getString("rol"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al buscar el usuario: " + e);
            return false;
        }
    }

    public boolean login(Usuario usu) {
        String sql = "SELECT * FROM usuario WHERE correo= ? AND contrasena= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usu.getCorreo());
            ps.setString(2, encriptar(usu.getContrasena()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usu.setIdUsuario(rs.getInt("id_usuario"));
                    usu.setNombre(rs.getString("nombre"));
                    usu.setCorreo(rs.getString("correo"));
                    usu.setContrasena(rs.getString("contrasena"));
                    usu.setRol(rs.getString("rol"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al validar el login: " + e);
            return false;
        }
    }

    public ArrayList<Usuario> todos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection con = getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNombre(rs.getString("nombre"));
                usu.setCorreo(rs.getString("correo"));
                usu.setContrasena(rs.getString("contrasena"));
                usu.setRol(rs.getString("rol"));
                lista.add(usu);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los usuarios: " + e);
        }
        return lista;
    }
}
