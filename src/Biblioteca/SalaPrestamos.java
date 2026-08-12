/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Biblioteca;

import Modelo.SentenciasLibro;

/**
 *
 * @author aleja
 */
public class SalaPrestamos {

    SentenciasLibro consultasLibro = new SentenciasLibro();

    public void intentarPrestamo(int idCliente, int idLibro) {
        //genera el tiempo que dura el cliente haciendo el tramite
        long tiempoProceso = (long) (Math.random() * 3000);
        try {
            System.out.println("El cliente " + idCliente + " intenta prestar el libro " + idLibro);
            Thread.sleep(tiempoProceso);
            String estado = consultasLibro.consultarEstado(idLibro);
            if (estado != null && estado.equals("disponible")) {
                consultasLibro.actualizarEstado(idLibro, "prestado");
                System.out.println("El cliente " + idCliente + " logro prestar el libro " + idLibro + " en un tiempo de " + tiempoProceso);
            } else {
                System.out.println("El cliente " + idCliente + " NO pudo prestar el libro " + idLibro + ", no estaba disponible");
            }
        } catch (InterruptedException e) {
            System.out.println("Hubo un problema al procesar el prestamo...");
        }
    }
}
