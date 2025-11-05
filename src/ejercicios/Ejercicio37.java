package ejercicios;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio37 {

    public static void main(String[] args) {
        // Creamos un mapa ordenado (TreeMap) para almacenar los alumnos
        // La clave será el número de expediente (Integer) y el valor un objeto AlumnoEj37
        Map<Integer, AlumnoEj37> alumnos = new TreeMap<>();

        // Scanner para leer datos desde la consola
        Scanner sc = new Scanner(System.in);
        System.out.println("--------- Introducción de alumnos -------");

        // Bucle infinito que termina cuando el usuario escribe "salir"
        while (true) {
            System.out.println("Número de expediente o 'salir' para terminar:");
            String entrada = sc.nextLine();

            // Si el usuario escribe 'salir', salimos del bucle
            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Has terminado.");
                break;
            }

            try {
                // Convertimos la entrada a número entero
                int expediente = Integer.parseInt(entrada);

                // Comprobamos si ya existe un alumno con el mismo expediente
                if (alumnos.containsKey(expediente)) {
                    System.out.println("⚠️ Ya existe un alumno con ese expediente.");
                    continue;
                }

                // Pedimos el resto de los datos
                System.out.println("Introduce el nombre:");
                String nombreAlumno = sc.nextLine();

                System.out.println("Introduce la nota:");
                // Convertimos la nota, aceptando tanto coma como punto decimal
                double nota = Double.parseDouble(sc.nextLine().replace(",", "."));

                // Guardamos el alumno en el mapa ordenado
                alumnos.put(expediente, new AlumnoEj37(expediente, nombreAlumno, nota));
                System.out.println("✅ Alumno añadido correctamente.");

            } catch (NumberFormatException n) {
                // Si el usuario introduce texto no numérico donde debería ir un número
                System.out.println("⚠️ Error: formato numérico no válido.");
            }
        }

        // Cerramos el scanner para liberar recursos
        sc.close();

        // A partir de aquí creamos el documento XML con los datos introducidos
        try {
            // 1️⃣ Crear la fábrica y el constructor del documento DOM
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            // 2️⃣ Crear un documento vacío
            Document doc = db.newDocument();

            // 3️⃣ Crear el nodo raíz <alumnos>
            Element raiz = doc.createElement("alumnos");
            doc.appendChild(raiz);

            // 4️⃣ Recorrer el mapa de alumnos y crear nodos <alumno> para cada uno
            for (AlumnoEj37 a : alumnos.values()) {
                // Nodo <alumno>
                Element alumnoE = doc.createElement("alumno");

                // Nodo <numExpediente>
                Element expE = doc.createElement("numExpediente");
                expE.appendChild(doc.createTextNode(String.valueOf(a.getExpediente())));
                alumnoE.appendChild(expE);

                // Nodo <nombre>
                Element nombreE = doc.createElement("nombre");
                nombreE.appendChild(doc.createTextNode(a.getNombre()));
                alumnoE.appendChild(nombreE);

                // Nodo <nota>
                Element notaE = doc.createElement("nota");
                notaE.appendChild(doc.createTextNode(String.valueOf(a.getNota())));
                alumnoE.appendChild(notaE);

                // Añadimos el nodo <alumno> completo al nodo raíz <alumnos>
                raiz.appendChild(alumnoE);
            }

            // 🟢 Estructura del DOM en memoria:
            //
            // <alumnos>
            //     <alumno>
            //         <numExpediente>1003</numExpediente>
            //         <nombre>Ana López García</nombre>
            //         <nota>8.5</nota>
            //     </alumno>
            //     ...
            // </alumnos>

            // 5️⃣ Guardar el DOM en un fichero XML físico

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            // Propiedades de salida: formato legible e indentado
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            // Fuente: el DOM generado
            DOMSource source = new DOMSource(doc);

            // Destino: el archivo XML en disco
            StreamResult result = new StreamResult(new File("notasAlumno.xml"));

            // 6️⃣ Realizar la transformación (escritura del XML)
            t.transform(source, result);
            System.out.println("📁 Archivo 'notasAlumno.xml' creado correctamente.");

        } catch (ParserConfigurationException | TransformerException pt) {
            // Captura errores de configuración del parser o de escritura del XML
            pt.printStackTrace();
        }
    }
}
