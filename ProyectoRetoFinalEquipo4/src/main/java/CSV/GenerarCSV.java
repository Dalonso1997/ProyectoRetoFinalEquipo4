/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CSV;

import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
 * Clase para transformar el archivo XML de inventario en un documento CSV.
 * Utiliza una plantilla XSLT para hacer la conversión de formato.
 *
 * * @author Sergio Camacho
 * * @author David Alonso
 */
public class GenerarCSV {

    /**
     * Método principal que ejecuta la transformación de XML a CSV.
     *
     * * @param args argumentos de la línea de comandos.
     */
    public static void main(String[] args) {

        try {

            TransformerFactory taller = TransformerFactory.newInstance();

            Source xslt = new StreamSource(new File("src/inventarioCSV.xsl"));
            Transformer transformar = taller.newTransformer(xslt);

            Source xml = new StreamSource(new File("src/inventario.xml"));

            Result resultado = new StreamResult(new File("src/inventario.csv"));

            transformar.transform(xml, resultado);
            System.out.println("Ruta actual: " + System.getProperty("user.dir"));

            System.out.println("CSV generado correctamente");

        } catch (TransformerException e) {
            e.getMessage();
        }
    }
}
