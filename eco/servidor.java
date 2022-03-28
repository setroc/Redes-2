import java.net.*;
import java.io.*;

public class servidor {
  public static void main(String[] args) {
    try { 
      // Crear server socket de flujo ligado al puerto 5000
      ServerSocket s = new ServerSocket(5000);
      System.out.println("Esperando cliente...");
      
      for (;;) {
        // socket para conexión de cliente en la red
        Socket cl = s.accept();
        System.out.println("Conexion establecida desde " +cl.getInetAddress()+":"+cl.getPort());

        // Definicion del mensaje a enviar y ligamos un printwriter a un flujo de salida de caracter.
        String mensaje = "Isaac Godínez Cortés";
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
        pw.println(mensaje);
        pw.flush();
        // Definicion de un flujo de lectura ligado a un socket para recibir el mensaje
        BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
        String mensajeRecibido = br.readLine();
        System.out.println("Mensaje recibido: " + mensajeRecibido);

        // Cerramos el printwriter, el socket y el bufferedreader
        pw.close();
        cl.close();
        br.close();
      }
    } catch (Exception e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }
  
}