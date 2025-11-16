package ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Ejercicio 44 - Gestión de empresas con JAXB
 * 
 * Permite:
 * 1. Ver datos de la empresa
 * 2. Ver trabajadores
 * 3. Añadir trabajador (sin NIFs duplicados)
 * 4. Modificar trabajador
 * 5. Borrar trabajador
 */

public class Ejercicio44 {
	
	private static final String FILE_PATH = "empresa.xml"; 
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
	     EmpresaEj44 empresa = cargarEmpresa();
	     int opcion;

	     do {
	         System.out.println("\n=== GESTIÓN DE EMPRESA ===");
	         System.out.println("1. Ver datos de la empresa");
	         System.out.println("2. Ver trabajadores");
	         System.out.println("3. Añadir trabajador");
	         System.out.println("4. Modificar trabajador");
	         System.out.println("5. Borrar trabajador");
	         System.out.println("0. Salir");
	         System.out.print("Opción: ");
	         opcion = Integer.parseInt(sc.nextLine());

	         switch (opcion) {
	             case 1 -> mostrarEmpresa(empresa);
	             case 2 -> mostrarTrabajadores(empresa);
	             case 3 -> anadirTrabajador(empresa, sc);
	             case 4 -> modificarTrabajador(empresa, sc);
	             case 5 -> borrarTrabajador(empresa, sc);
	             case 0 -> guardarEmpresa(empresa);
	             default -> System.out.println("⚠️ Opción no válida.");
	         }

	     } while (opcion != 0);

	     sc.close();
		
	}
	

 // --- Métodos de gestión ---

 private static void mostrarEmpresa(EmpresaEj44 e) {
     System.out.println("\nEmpresa: " + e.getNombre() + " (NIE: " + e.getNie() + ")");
     DireccionEj44 d = e.getDireccion();
     if (d != null) {
         System.out.println("Dirección: " + d.getVia() + " " + d.getNumero() + ", " + d.getPoblacion() + " " + d.getCp());
     }
 }

 private static void mostrarTrabajadores(EmpresaEj44 e) {
     if (e.getTrabajadores().isEmpty()) {
         System.out.println("⚠️ No hay trabajadores registrados.");
         return;
     }
     System.out.println("\n--- Lista de trabajadores ---");
     for (TrabajadorEj44 t : e.getTrabajadores()) {
         System.out.println("NIF: " + t.getNif() + " | Nombre: " + t.getNombre() + " | Cargo: " + t.getCargo());
     }
 }

 private static void anadirTrabajador(EmpresaEj44 e, Scanner sc) {
     System.out.print("NIF: ");
     String nif = sc.nextLine();
     if (e.buscarPorNif(nif) != null) {
         System.out.println("⚠️ Ya existe un trabajador con ese NIF.");
         return;
     }
     System.out.print("Nombre: ");
     String nombre = sc.nextLine();
     System.out.print("Cargo: ");
     String cargo = sc.nextLine();
     e.addTrabajador(new TrabajadorEj44(nif, nombre, cargo));
     System.out.println("✅ Trabajador añadido correctamente.");
 }

 private static void modificarTrabajador(EmpresaEj44 e, Scanner sc) {
     System.out.print("NIF del trabajador a modificar: ");
     String nif = sc.nextLine();
     TrabajadorEj44 t = e.buscarPorNif(nif);
     if (t == null) {
         System.out.println("⚠️ No existe un trabajador con ese NIF.");
         return;
     }
     System.out.print("Nuevo nombre (deja vacío para mantener): ");
     String nombre = sc.nextLine();
     if (!nombre.isEmpty()) t.setNombre(nombre);

     System.out.print("Nuevo cargo (deja vacío para mantener): ");
     String cargo = sc.nextLine();
     if (!cargo.isEmpty()) t.setCargo(cargo);

     System.out.println("✅ Trabajador modificado correctamente.");
 }

 private static void borrarTrabajador(EmpresaEj44 e, Scanner sc) {
     System.out.print("NIF del trabajador a eliminar: ");
     String nif = sc.nextLine();
     if (e.eliminarPorNif(nif))
         System.out.println("🗑️ Trabajador eliminado.");
     else
         System.out.println("⚠️ No se encontró ese NIF.");
 }

 // --- Métodos JAXB para persistencia ---

 private static EmpresaEj44 cargarEmpresa() {
     try {
         File f = new File(FILE_PATH);
         if (!f.exists()) {
        	// Crear lista vacía de trabajadores al principio
             List<TrabajadorEj44> listaTrabajadores = new ArrayList<>();

             // Crear dirección
             DireccionEj44 d = new DireccionEj44("Avenida de la Energía", 10, "Madrid", "28001");

             // Usar el constructor completo
             EmpresaEj44 nueva = new EmpresaEj44("E12345678", "IberPower", d, listaTrabajadores);

             // Guardar
             guardarEmpresa(nueva);

             return nueva;
         }
         JAXBContext context = JAXBContext.newInstance(EmpresaEj44.class);
         Unmarshaller um = context.createUnmarshaller();
         return (EmpresaEj44) um.unmarshal(f);
         
     } catch (Exception ex) {
         ex.printStackTrace();
         return new EmpresaEj44();
     }
 }

 private static void guardarEmpresa(EmpresaEj44 e) {
     try {
         JAXBContext context = JAXBContext.newInstance(EmpresaEj44.class);
         Marshaller m = context.createMarshaller();
         m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         m.marshal(e, new File(FILE_PATH));
         System.out.println("💾 Datos guardados en 'empresa.xml'.");
     } catch (Exception ex) {
         ex.printStackTrace();
     }
 }
}


