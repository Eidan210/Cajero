/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso4;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
/**
 *
 * @author eidan
 */
public class Agenda {
    private ArrayList<Contacto> Contactos = new ArrayList<>();
    private int contactosGuardados;
    
    
    public void guardarEnArchivo(){
        try(PrintWriter pw = new PrintWriter(new FileWriter("Contactos.txt"))){
            for(Contacto c : Contactos){
                pw.println(c.getNombre() + ";" + c.getTelefono() + ";" + c.getEmail() + ";" + c.getGrupo());
            }
            System.out.println("Contactos guardados");
        }catch (IOException e){
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    
    public void cargarDesdeArchivo(){
        File archivo = new File("Contactos.txt");
            if(!archivo.exists()){
            return;
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea = br.readLine()) != null){
                if (linea.isBlank()) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length != 4) {
                    System.out.println("Linea ignorada: " + linea);
                    continue;
                }
                Contactos.add(new Contacto(partes[0], partes[1], partes[2], partes[3]));
            }
            System.out.println("Contactos cargados");
        } catch (IOException e){
            System.out.println("Error al cargar "+ e.getMessage());
        }
    }
    
    public void agregarContacto(Contacto c){
        if(existeContacto(c)){
            System.out.println("Ya existe ese nombre o ese telefono registrado");
        }else if(!esTelefonoValido(c.getTelefono())){
            System.out.println("el telefono es erroneo");
        }else if(!esEmailValido(c.getEmail())){
            System.out.println("El email es erroneo");
        }else{
            // 1. guardar c en contacto
            Contactos.add(c);
            // 2. mensaje de éxito
            System.out.println("Se agrego el contacto correctamente ");
        }
    }
    
    private boolean existeContacto(Contacto c){
        for(int i = 0; i < Contactos.size(); i++){
            boolean pieza1 = Contactos.get(i).getNombre().equalsIgnoreCase(c.getNombre());
            boolean pieza2 =Contactos.get(i).getTelefono().equals(c.getTelefono());
            if(pieza1 || pieza2 ){
                return true;
            }
        } 
        return false;
    }
    
    public boolean esTelefonoValido(String telefono){
        if(telefono.length() != 10){
            return false;
        }
        for(int i = 0; i < telefono.length(); i++){
            if(!Character.isDigit(telefono.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    public boolean esEmailValido(String email){
        if(!email.contains("@") || !email.contains(".")){
            return false;
        }else{
            return true;
        }
    }
    
    public void mostrarContactosPorGrupo(String grupo){
        boolean hayContactos = false;
        
        for(Contacto c : Contactos){
            if(c.getGrupo().equalsIgnoreCase(grupo)){
                c.mostrarInfo();
                hayContactos = true;
            }
        }
        if(!hayContactos){
            System.out.println("No se encontro contactos por ese grupo");
        }
    }
    
    public void mostrarTodos(){
        if(Contactos.isEmpty()){
            System.out.println("No tienes contactos Guardados");
        }else{
        for (int i = 0; i < Contactos.size(); i++){
            Contactos.get(i).mostrarInfo();
            }
        }
    }
    
    public ArrayList<Contacto> buscarContacto(String nombre) {
        
        ArrayList<Contacto> encontrados = new ArrayList<>();
        
        for (int i = 0; i<Contactos.size(); i++) {
            if (Contactos.get(i).getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                encontrados.add(Contactos.get(i));
            }
        }
        return encontrados;
    }
    
    public int buscarPosicion(String nombre){
        for(int i = 0; i<Contactos.size(); i++){
            if(Contactos.get(i).getNombre().equalsIgnoreCase(nombre)){
                return i;
            }
        }
        return -1;
    }
    
    public void eliminarContacto(String nombre){
        int posicion = buscarPosicion(nombre);
        if(posicion == -1){
            System.out.println("No existe ese contacto...");
        }else{
            Contactos.remove(posicion);
            System.out.println("Contacto eliminado correctamente");
        }
    }
}