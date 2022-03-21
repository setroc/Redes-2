import java.net.*;
import java.io.*;

public class servidor {
  public static void main(String[] args) {
    try {
      // Crear server socket
      ServerSocket s = new ServerSocket(5000);
      System.out.println("Esperando cliente...");
      
      for (;;) {
        // socket para conexión de cliente
        Socket cl = s.accept();
        System.out.println("Conexion establecida desde " +cl.getInetAddress()+":"+cl.getPort());

        String mensaje = "Hola mundo";
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
        pw.println(mensaje);
        pw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
        String mensajeRecibido = br.readLine();
        System.out.println("Mensaje recibido: " + mensajeRecibido);

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