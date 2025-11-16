package ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio48 {

    public static void main(String[] args) {
        try {
            // === 1. Cargar el XML en memoria ===
            File archivo = new File("ej48_cotizaciones2.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);

            doc.getDocumentElement().normalize();

            // === 2. Lista donde guardaremos las empresas ===
            List<EmpresaEj48> empresas = new ArrayList<>();

            // === 3. Obtenemos todos los nodos <Empresa> ===
            NodeList listaNodos = doc.getElementsByTagName("Empresa");

            for (int i = 0; i < listaNodos.getLength(); i++) {
                Node nodo = listaNodos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element empresa = (Element) nodo;

                    // Leer <Nombre> y <Precio>
                    String nombre = empresa.getElementsByTagName("Nombre")
                                           .item(0).getTextContent();

                    double precio = Double.parseDouble(
                            empresa.getElementsByTagName("Precio")
                                   .item(0).getTextContent()
                    );

                    empresas.add(new EmpresaEj48(nombre, precio));
                }
            }

            // === 4. Ordenar la lista por precio (ascendente) ===
            empresas.sort(Comparator.comparingDouble(EmpresaEj48::getPrecio));

            // === 5. Mostrar 5 más baratas ===
            System.out.println("\n=== 5 EMPRESAS CON MENOR PRECIO ===");
            empresas.stream().limit(5).forEach(System.out::println);

            // === 6. Mostrar 5 más caras ===
            System.out.println("\n=== 5 EMPRESAS CON MAYOR PRECIO ===");
            empresas.stream()
                    .sorted(Comparator.comparingDouble(EmpresaEj48::getPrecio).reversed())
                    .limit(5)
                    .forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
