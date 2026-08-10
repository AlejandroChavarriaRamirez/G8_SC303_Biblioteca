/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aleja
 */
public class Libro {
 
    private int idLibro;
    private String titulo;
    private String autor;
    private String categoria;
    private String editorial;
 
    public Libro() {
        this.idLibro = 0;
        this.titulo = "";
        this.autor = "";
        this.categoria = "";
        this.editorial = "";
    }
 
    public Libro(int idLibro, String titulo, String autor, String categoria, String editorial) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.editorial = editorial;
    }
 
    public int getIdLibro() {
        return idLibro;
    }
 
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
 
    public String getTitulo() {
        return titulo;
    }
 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
 
    public String getAutor() {
        return autor;
    }
 
    public void setAutor(String autor) {
        this.autor = autor;
    }
 
    public String getCategoria() {
        return categoria;
    }
 
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
 
    public String getEditorial() {
        return editorial;
    }
 
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
 
}