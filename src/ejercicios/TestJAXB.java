package ejercicios;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class TestJAXB {
	public static void main(String[] args) {
		try {
            JAXBContext context = JAXBContext.newInstance(String.class);
            System.out.println("✅ JAXB está funcionando correctamente");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
