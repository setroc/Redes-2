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
      Socket cl = new Socket(host, pto);

      BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
      String mensaje = br2.readLine();
      System.out.println("Recibimos un mensaje desde el servidor");
      System.out.println("Mensaje: "+mensaje);

      System.out.println("Enviado el mensaje al servidor");
      PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
      pw.println(mensaje);
      pw.flush();

      br1.close();
      br2.close();
      cl.close();

    } catch (Exception e) {
      //TODO: handle exception
      e.printStackTrace();
    }    
  }
}