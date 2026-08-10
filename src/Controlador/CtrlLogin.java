/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
 
import Modelo.Usuario;
import Modelo.SentenciasUsuario;
import Vista.frmLogin;
import Biblioteca.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
 
/**
 *
 * @author aleja
 */

public class CtrlLogin implements ActionListener {
 
    private final Usuario modelo;
    private final SentenciasUsuario consultas;
    private final frmLogin vista;
 
    public CtrlLogin(Usuario modelo, SentenciasUsuario consultas, frmLogin vista) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnIngresar.addActionListener(this);
    }
 
    public void inicio() {
        vista.setTitle("Inicio de Sesion");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnIngresar) {
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setContrasena(new String(vista.txtContrasena.getPassword()));
            if (consultas.login(modelo)) {
                JOptionPane.showMessageDialog(null, "Bienvenido " + modelo.getNombre());
                Menu m = new Menu();
                m.setVisible(true);
                m.setLocationRelativeTo(null);
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Correo o contrasena incorrectos");
            }
        }
    }
 
}
