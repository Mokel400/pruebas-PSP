package main.java.com.psp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(entrada.readLine());
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Intento: ");
                String intento = sc.nextLine();
                salida.println(intento);
                String resultado = entrada.readLine();
                System.out.println(resultado);
                if (resultado.contains("ENHORABUENA")){
                    break;
                }
            }
            socket.close();
            sc.close();
        } catch (Exception e) {
            System.out.println("Conexion erronea");;
        }
    }
}
