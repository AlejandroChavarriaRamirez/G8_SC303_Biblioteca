/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Biblioteca;
 
import Modelo.Usuario;
import Modelo.SentenciasUsuario;
import Vista.frmLogin;
import Controlador.CtrlLogin;
 
/**
 *
 * @author aleja
 */
public class Principal {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Usuario modelo = new Usuario();
        SentenciasUsuario consultas = new SentenciasUsuario();
        frmLogin vista = new frmLogin();
        CtrlLogin controlador = new CtrlLogin(modelo, consultas, vista);
        controlador.inicio();
    }
 
}
 
