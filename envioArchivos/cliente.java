import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class cliente {
  public static void main(String[] args) {
    try {
      // Flujo de entrada para obtener los datos del servidor
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Escriba la direccion del servidor: ");
      String host = br.readLine();
      System.out.println("Escriba el puerto: ");
      int pto = Integer.parseInt(br.readLine());
      // Se usa un jfilechooser para elegir los archivos a enviar
      JFileChooser jf = new JFileChooser();
      // Seleccion de multiples archivos
      jf.setMultiSelectionEnabled(true);
      int r = jf.showOpenDialog(null);

      
      if ( r == JFileChooser.APPROVE_OPTION ) {
        // Debido a que se tiene activada la opción de selección multiple de archivos, se debe guardar esto en un arreglo de tipo File
        File[] f = jf.getSelectedFiles();
        // Se recorre el arreglo con el fin de envía archivo por archivo
        for ( File file: f) {
          // Definicion del socket a usar
          Socket cl = new Socket(host, pto);
          // Obtención de los datos principales de cada archivo
          String archivo = file.getAbsolutePath();
          String nombre = file.getName();
          long tam = file.length();

          System.out.println("Enviando archivo: " + nombre);
          // Flujos orientados a bytes
          // uno para enviar los datos por el socket
          DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
          // y el otro para leer el archivo.
          DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
          // Se envían los datos generales del archivo por el socket.
          dos.writeUTF(nombre);
          dos.flush();
          dos.writeLong(tam);
          dos.flush();

          // Lectura del contenido del archivo en paquetes de 1024 bytes y se envian por el socket
          byte[] b = new byte[1024];
          long enviados = 0;
          int porcentaje, n;
          while ( enviados < tam ) {
            n = dis.read(b);
            dos.write(b, 0, n);
            dos.flush();
            enviados = enviados + n;
            porcentaje = (int)(enviados*100/tam);
            System.out.println("Enviado: " + porcentaje + "%\r");
          }

          System.out.println("Archivo enviado");
          // Se cierran los flujos, el socket.
          dos.close();
          dis.close();
          cl.close();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }  
}