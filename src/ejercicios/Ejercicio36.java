package ejercicios;

// Librerías necesarias para construir y guardar un documento XML
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio36 {

    public static void main(String[] args) {
        try {
            // 1️⃣ Crear una instancia del DocumentBuilderFactory
            //    Esta clase es la entrada al API DOM y permite obtener constructores de documentos.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            // 2️⃣ Crear un objeto DocumentBuilder a partir de la fábrica
            //    El DocumentBuilder nos permitirá construir un documento XML o leer uno existente.
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // 3️⃣ Crear un documento DOM vacío (sin ningún nodo aún)
            Document doc = builder.newDocument();
            
            // 4️⃣ Crear el nodo raíz <profesores>
            Element raiz = doc.createElement("profesores");
            
            // 5️⃣ Añadir la raíz al documento
            doc.appendChild(raiz);
            
            // 6️⃣ Crear el nodo hijo <profesor>
            Element profesor = doc.createElement("profesor");
            
            // 7️⃣ Añadir un atributo al nodo <profesor>: grupo="2DAM"
            profesor.setAttribute("grupo", "2DAM");
            
            // 8️⃣ Crear un nodo de texto "Juan Pérez" y añadirlo dentro del elemento <profesor>
            profesor.appendChild(doc.createTextNode("Juan Pérez"));
            
            // 9️⃣ Insertar el nodo <profesor> dentro del nodo raíz <profesores>
            raiz.appendChild(profesor);
            
            // 🟢 Hasta este punto, en memoria el árbol DOM tiene esta estructura:
            // <profesores>
            //     <profesor grupo="2DAM">Juan Pérez</profesor>
            // </profesores>
            
            // 🔸 Ahora se va a escribir (guardar) el contenido del DOM en un archivo físico.
            
            // 🔟 Crear una fábrica de transformadores
            //    El TransformerFactory permite crear objetos que convierten DOM → XML de texto.
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            
            // 1️⃣ Formatear la salida con sangrías y establecer propiedades de salida
            t.setOutputProperty(OutputKeys.INDENT, "yes"); // activa la indentación
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // número de espacios por nivel
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // aseguramos codificación correcta
            
            // 2️⃣ Crear el origen (el documento DOM) y el destino (archivo XML)
            DOMSource source = new DOMSource(doc); // fuente: el documento en memoria
            StreamResult result = new StreamResult(new File("profesores.xml")); // destino: el archivo en disco
            
            // 3️⃣ Transformar el DOM en texto XML y guardarlo en el archivo indicado
            t.transform(source, result);
            
            // 4️⃣ Confirmar la creación del archivo
            System.out.println("✅ Archivo 'profesores.xml' creado correctamente.");
            
        } catch (Exception e) {
            // Captura cualquier excepción (errores de parser, escritura, permisos, etc.)
            e.printStackTrace();
        }
    }
}
