import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchivosZip {
    public static void main(String[] args) {
//_________________________________-Inicialización de variables-_____________________________________
        String fileName = "ejemmplo.txt";
        String zipFileName = "ejemplo.zip";
//__________________________________-Creación de archivo-____________________________________________

/*
 * Creación de archivo mediante BufferedWriter que contiene ->
 *      writer ==> Escribir de manera eficiente
 *      newLine ==> Inserta un salto de línea
 */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Este es un archivo de texto de ejemplo.");
            writer.newLine();
            writer.write("Hola mundo.");
            writer.newLine();
            writer.write("¿Por qué se extinguieron los mamuts?");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

//______________________________________-Compresión en ZIP-_________________________________________
/*
 * FileOutputStream fos = new FileOutputStream(zipFileName) ==> Crea un flujo de salida para el archivo ZIP.
 * ZipOutputStream zos = new ZipOutputStream(fos) ==> Envuelve el FileOutputStream en un ZipOutputStream para manejar la compresión.
 * FileInputStream fis = new FileInputStream(fileName) ==> Crea un flujo de entrada para leer el archivo de texto.
 */
        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(fileName)) {
/*
 * ZipEntry ==> Inicializa la entrada para el archivo de texto
 * PutNextEntry ==> Asigna la entrada al archivo de texto
 */
            ZipEntry zipEntry = new ZipEntry(fileName);
            zos.putNextEntry(zipEntry);
/*
 * byte[] buffer = new byte[1024] ==> Se usa para leer los caracteres del archivo
 */
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }
//___________________________________-Cerrar el flujo de entrada y salida-_______________________________________
            zos.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}