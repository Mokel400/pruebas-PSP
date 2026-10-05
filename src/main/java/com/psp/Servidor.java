package main.java.com.psp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Servidor{

    public static void main (String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Esperando cliente...");
            Socket cliente = server.accept();
            System.out.println("Cliente conectado");
            int random = Math.max((int)(Math.random()*100), 1);
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
            salida.println("Adivina el numero entre 1 y 100");
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            while (true) {
                String intentoString = entrada.readLine();
                int intento;
                try {
                    intento = Integer.parseInt(intentoString);
                } catch (Exception e){
                    salida.println("Intento invalido"); continue;
                }
                if (intento < random) {
                    salida.println("Más alto");
                } else if (intento > random) {
                    salida.println("Más bajo");
                } else if (intento == random) {
                    salida.println("ENHORABUENA"); break;
                }
            }
            server.close();
            cliente.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}