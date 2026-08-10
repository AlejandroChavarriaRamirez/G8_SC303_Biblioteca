/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author aleja
 */
public class SentenciasMulta extends Conexion {

    public boolean registrar(Multa mul) {
        String sql = "INSERT INTO multa (id_multa, monto, id_devolucion) VALUES (?,?,?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mul.getIdMulta());
            ps.setInt(2, mul.getMonto());
            ps.setInt(3, mul.getIdDevolucion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar multa: " + e);
            return false;
        }
    }

    public boolean modificar(Multa mul) {
        String sql = "UPDATE multa SET monto=?, id_devolucion=? WHERE id_multa=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mul.getMonto());
            ps.setInt(2, mul.getIdDevolucion());
            ps.setInt(3, mul.getIdMulta());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar multa: " + e);
            return false;
        }
    }

    public boolean eliminar(Multa mul) {
        String sql = "DELETE FROM multa WHERE id_multa=?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mul.getIdMulta());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar multa: " + e);
            return false;
        }
    }

    public boolean buscar(Multa mul) {
        String sql = "SELECT * FROM multa WHERE id_multa= ?";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mul.getIdMulta());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mul.setIdMulta(rs.getInt("id_multa"));
                    mul.setMonto(rs.getInt("monto"));
                    mul.setIdDevolucion(rs.getInt("id_devolucion"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al buscar la multa: " + e);
            return false;
        }
    }

    public ArrayList<Multa> todos() {
        ArrayList<Multa> lista = new ArrayList<>();
        String sql = "SELECT * FROM multa";
        try (Connection con = getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Multa mul = new Multa();
                mul.setIdMulta(rs.getInt("id_multa"));
                mul.setMonto(rs.getInt("monto"));
                mul.setIdDevolucion(rs.getInt("id_devolucion"));
                lista.add(mul);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las multas: " + e);
        }
        return lista;
    }
}
