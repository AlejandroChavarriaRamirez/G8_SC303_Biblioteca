/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Biblioteca;

import java.util.concurrent.Semaphore;

/**
 *
 * @author aleja
 */
public class ClientePrestamo implements Runnable {

    int idCliente;
    int idLibro;
    SalaPrestamos sala;
    static Semaphore semaforo = new Semaphore(1);

    public ClientePrestamo(int idCliente, int idLibro, SalaPrestamos sala) {
        this.idCliente = idCliente;
        this.idLibro = idLibro;
        this.sala = sala;
    }

    public void run() {
        try {
            //solo un cliente a la vez puede intentar prestar el mismo libro
            semaforo.acquire();
            this.sala.intentarPrestamo(idCliente, idLibro);
        } catch (InterruptedException e) {
            System.out.println("El hilo se ha interrumpido intentando prestar el libro");
        }
        semaforo.release();
    }
}
