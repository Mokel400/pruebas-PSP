package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculadora {
    public static void main (String[] args) {
        try {
            while (true) {
                ServerSocket servidor = new ServerSocket(5000);

                System.out.println("Servidor iniciado");
                System.out.println("Esperando ClienteCalculadora");

                Socket cliente = servidor.accept();

                System.out.println("Cliente conectado correctamente");

                PrintWriter salida = new PrintWriter(
                        cliente.getOutputStream(),
                        true
                );

                salida.println("Hola ClienteCalculadora, envie 3 mensajes, los dos primeros deberán ser números enteros y el último una de las operaciones disponibles (sumar, restar o multiplicar)");

                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(
                                cliente.getInputStream()
                        )
                );

                String numero1String = entrada.readLine();
                String numero2String = entrada.readLine();
                String operacion = entrada.readLine();

                int n1 = 0;
                int n2 = 0;

                try {
                    n1 = Integer.parseInt(numero1String);
                    n2 = Integer.parseInt(numero2String);
                } catch (Exception e) {
                    salida.println("Mensajes no válidos");
                    cliente.close();
                    servidor.close();
                    return;
                }
                int resultado = 0;
                switch(operacion.toLowerCase()){
                    case "sumar": resultado = n1 + n2;break;
                    case "restar": resultado = n1 - n2;break;
                    case "multiplicar": resultado = n1 * n2;break;
                    case "salir": cliente.close(); servidor.close(); break;
                    default: salida.println("Operación no válida");
                }
                System.out.println(resultado);
                salida.println(resultado);

                cliente.close();
                servidor.close();}


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}