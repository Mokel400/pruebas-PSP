package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteCalculadora {
    public static void main(String[] args) {
        try {
            while (true) {
                Socket socket = new Socket("localhost", 5000);

                System.out.println("Conexión establecida correctamente");

                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()
                        )
                );

                String mensaje = entrada.readLine();
                System.out.println(mensaje);

                PrintWriter salida = new PrintWriter(
                        socket.getOutputStream(),
                        true
                );
                Scanner sc = new Scanner(System.in);
                System.out.print("Primer número: ");
                String num1 = sc.nextLine();
                System.out.print("Segundo número: ");
                String num2 = sc.nextLine();
                System.out.print("Operación/salir: ");
                String operacion = sc.nextLine();


                salida.println(num1);
                salida.println(num2);
                salida.println(operacion);


                String resultado = entrada.readLine();

                System.out.println(resultado);

            }


        } catch (Exception e) {
            System.out.println("No se ha podido establecer la conexión");
        }
    }
}
