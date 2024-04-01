/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guido.api.biblioteca.APiBiblioteca.Services;

import com.guido.api.biblioteca.APiBiblioteca.DAO.EditorialRepository;
import com.guido.api.biblioteca.APiBiblioteca.entidades.Editorial;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class EditorialService {
    
    @Autowired
    EditorialRepository editorialRepository;
    
    
   @Transactional
   public Editorial save (Editorial editorial) {
   
   return    editorialRepository.save(editorial);
   } 
   
   @Transactional
   public void delete (Integer id) {
       
        Optional<Editorial> editorialEncontrada = editorialRepository.findById(id);
        
        if (editorialEncontrada.isPresent()) {
        editorialRepository.deleteById(id);
        }   
   }
   
   
   @Transactional 
   public Editorial upGrade (Editorial editorial) {
       return editorialRepository.save(editorial);
   }
   
   public Editorial obtenerEditorialPorId (Integer id) {
   return editorialRepository.findById(id).orElse(null);
   }
   
   public List <Editorial> obtenerEditoriales () {
   return (List<Editorial>) editorialRepository.findAll();
   }
}
