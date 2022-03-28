import java.net.*;
import java.io.*;

public class servidor {
  public static void main(String[] args) {
    try {
      ServerSocket s = new ServerSocket(7000);
      // Ciclo infino en espera de una conexion
      for (;;) {
        Socket cl = s.accept();
        System.out.println("Conexion establecida desde "+cl.getInetAddress()+":"+cl.getPort());
        // Flujo de nivel de bits de entrada correspondiente al socket creado
        DataInputStream dis = new DataInputStream(cl.getInputStream());
        // Se leen los datos principales del archivo y se crea un flujo para escribir el archivo de salirda
        byte[] b = new byte[1024];
        String nombre = dis.readUTF();
        System.out.println("Recibiendo el archivo: " + nombre);
        long tam = dis.readLong();
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));


        long recibidios = 0;
        int n, porcentaje;
        // Ciclo con el cual recibiremos los datos enviados por el cliente
        while ( recibidios < tam ) {
          n = dis.read(b);
          dos.write(b, 0, n);
          dos.flush();
          recibidios = recibidios + n;
          porcentaje = (int)(recibidios*100/tam);
          System.out.println("Recibiendo: " + porcentaje + "%\r");

        }
        System.out.println("Archivo recibido");
        // Se cierran los flujos y el soclet.
        dos.close();
        dis.close();
        cl.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }  
}
