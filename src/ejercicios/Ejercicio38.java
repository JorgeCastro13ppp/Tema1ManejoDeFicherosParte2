package ejercicios;

// Importaciones necesarias para leer y procesar documentos XML
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio38 {

    public static void main(String[] args) {
        try {
            // 1️⃣ Cargar el fichero XML en un objeto DOM
            // Creamos un objeto File que apunta al archivo XML
            File archivo = new File("concesionario.xml");

            // Obtenemos una instancia de DocumentBuilderFactory, que se usa para construir objetos DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Creamos un DocumentBuilder, encargado de parsear el archivo XML y construir el DOM
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parseamos el archivo XML y lo cargamos en un objeto Document
            Document doc = builder.parse(archivo);

            // 2️⃣ Normalizar el documento
            // El método normalize() agrupa los nodos de texto adyacentes y limpia espacios innecesarios.
            doc.getDocumentElement().normalize();

            // 3️⃣ Obtener la lista de nodos <coche> del documento
            // Esto devuelve una lista con todos los elementos "coche"
            NodeList listaCoches = doc.getElementsByTagName("coche");

            // 4️⃣ Declarar variables para almacenar el coche con el precio más barato
            String marcaBarata = "";
            String modeloBarato = "";
            double precioMinimo = Double.MAX_VALUE; // empezamos con el valor más alto posible

            // 5️⃣ Recorrer todos los nodos <coche>
            for (int i = 0; i < listaCoches.getLength(); i++) {
                Node nodoCoche = listaCoches.item(i);

                // Comprobamos que el nodo sea un ELEMENT_NODE (para evitar nodos de texto)
                if (nodoCoche.getNodeType() == Node.ELEMENT_NODE) {
                    // Convertimos el nodo genérico en un Element para acceder a sus etiquetas hijas
                    Element coche = (Element) nodoCoche;

                    // 6️⃣ Obtener los valores de las etiquetas hijas
                    // Usamos getElementsByTagName("nombreEtiqueta").item(0).getTextContent()
                    String marca = coche.getElementsByTagName("marca").item(0).getTextContent();
                    String modelo = coche.getElementsByTagName("modelo").item(0).getTextContent();
                    double precio = Double.parseDouble(
                            coche.getElementsByTagName("precio").item(0).getTextContent()
                    );

                    // 7️⃣ Comparar precios para encontrar el más bajo
                    if (precio < precioMinimo) {
                        precioMinimo = precio;
                        marcaBarata = marca;
                        modeloBarato = modelo;
                    }
                }
            }

            // 8️⃣ Mostrar el resultado por consola
            System.out.println("🚗 El coche más barato es:");
            System.out.println("Marca: " + marcaBarata);
            System.out.println("Modelo: " + modeloBarato);
            System.out.println("Precio: " + precioMinimo);

        } catch (Exception e) {
            // Capturamos cualquier error de lectura, parseo o conversión de datos
            e.printStackTrace();
        }
    }
}
