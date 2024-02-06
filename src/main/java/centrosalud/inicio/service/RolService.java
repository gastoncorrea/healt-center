/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package centrosalud.inicio.service;

import centrosalud.inicio.model.Rol;
import centrosalud.inicio.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService{
    
    @Autowired
    private RolRepository rolRepo;

    @Override
    public void nuevoRol(Rol nuevoRol) {
        rolRepo.save(nuevoRol); 
    }

    @Override
    public Rol encontrarUnRol(Long id) {
        Rol unRol = rolRepo.findById(id).orElse(null);
        return unRol;
    }

    @Override
    public List<Rol> listaDeRoles() {
        List<Rol> listaDeRoles = rolRepo.findAll();
        return listaDeRoles;
    }

    @Override
    public void eliminarRol(Long id) {
        rolRepo.deleteById(id);
    }
    
}
