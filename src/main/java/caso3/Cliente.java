/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso3;
/**
 *
 * @author eidan
 */
public class Cliente {
    private String nombre;
    private String email;

    public Cliente(String nombre, String email){
        this.nombre = nombre;
        this.email = email;
    }

    // getters
    
    public String getNombre(){
        return nombre;
    }
    
    public String getEmail(){
        return email;
    }
}
