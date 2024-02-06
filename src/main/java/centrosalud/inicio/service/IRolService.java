/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package centrosalud.inicio.service;

import centrosalud.inicio.model.Rol;
import java.util.List;


public interface IRolService {
    
    public void nuevoRol(Rol nuevoRol);
    
    public Rol encontrarUnRol(Long id);
    
    public List<Rol> listaDeRoles();
    
    public void eliminarRol(Long id);
}
