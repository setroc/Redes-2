import java.net.*;
import java.io.*;

public class cliente {
  public static void main(String[] args) {
    try {

      BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Escriba la direccion del servidor: ");
      String host = br1.readLine();
      System.out.println("Escriba el puerto");
      int pto = Integer.parseInt(br1.readLine());
      // Creacion de un socket de flujo conectandose a un no. de puerto en una ip definida.
      Socket cl = new Socket(host, pto);
      // Definicion de un flujo de lectura ligado a un socket para recibir el mensaje
      BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
      String mensaje = br2.readLine();
      System.out.println("Recibimos un mensaje desde el servidor");
      System.out.println("Mensaje: "+mensaje);
      // ligamos un printwriter a un flujo de salida de caracter.
      System.out.println("Enviado el mensaje al servidor");
      PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
      pw.println(mensaje);
      pw.flush();
      // Cerramos los flujos, el socket.
      br1.close();
      br2.close();
      cl.close();

    } catch (Exception e) {
      //TODO: handle exception
      e.printStackTrace();
    }    
  }
}