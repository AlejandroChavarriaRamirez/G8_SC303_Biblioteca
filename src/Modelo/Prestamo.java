/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aleja
 */

public class Prestamo {

    private int idPrestamo;
    private String fechaEntrega;
    private String fechaLimite;
    private int idLibro;
    private int idUsuario;

    public Prestamo() {
        this.idPrestamo = 0;
        this.fechaEntrega = "";
        this.fechaLimite = "";
        this.idLibro = 0;
        this.idUsuario = 0;
    }

    public Prestamo(int idPrestamo, String fechaEntrega, String fechaLimite, int idLibro, int idUsuario) {
        this.idPrestamo = idPrestamo;
        this.fechaEntrega = fechaEntrega;
        this.fechaLimite = fechaLimite;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
