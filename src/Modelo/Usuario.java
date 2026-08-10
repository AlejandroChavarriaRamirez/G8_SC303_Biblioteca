/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aleja
 */

public class Usuario {
 
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;
 
    public Usuario() {
        this.idUsuario = 0;
        this.nombre = "";
        this.correo = "";
        this.contrasena = "";
        this.rol = "";
    }
 
    public Usuario(int idUsuario, String nombre, String correo, String contrasena, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }
 
    public int getIdUsuario() {
        return idUsuario;
    }
 
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getCorreo() {
        return correo;
    }
 
    public void setCorreo(String correo) {
        this.correo = correo;
    }
 
    public String getContrasena() {
        return contrasena;
    }
 
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
 
    public String getRol() {
        return rol;
    }
 
    public void setRol(String rol) {
        this.rol = rol;
    }
 
}
