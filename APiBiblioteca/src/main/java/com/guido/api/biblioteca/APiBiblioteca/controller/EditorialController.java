/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guido.api.biblioteca.APiBiblioteca.controller;

import com.guido.api.biblioteca.APiBiblioteca.Services.EditorialService;
import com.guido.api.biblioteca.APiBiblioteca.entidades.Editorial;
import com.guido.api.biblioteca.APiBiblioteca.entidades.Libro;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("bibliotecaApi/v1")
public class EditorialController {
    
    @Autowired
    EditorialService editorialService;
    
    
    @PostMapping("/editorial")
    public ResponseEntity<?> save (@Valid @RequestBody Editorial editorial) {
    
        Editorial editorialGuardada = editorialService.save(editorial);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(editorialGuardada.getId()).toUri();
       
        return  ResponseEntity.created(ubicacion).body(editorialGuardada);
       
    }
    
    @PutMapping("/editorial/{id}")
    public ResponseEntity<?> edit (@Valid @RequestBody Editorial editorial , @PathVariable Integer id) {
   
        Editorial editorialEncontrada = editorialService.obtenerEditorialPorId(id);
    
        if (editorialEncontrada == null) {
        return ResponseEntity.unprocessableEntity().build();
        }
        
        editorial.setId(editorialEncontrada.getId());
        editorialService.upGrade(editorial);
        
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/editorial/{id}")
    public ResponseEntity<?> delete (@Valid @PathVariable Integer id) {
    Editorial editorialEncontrada = editorialService.obtenerEditorialPorId(id);
    
    if (editorialEncontrada == null) {
    return ResponseEntity.unprocessableEntity().build();
    }
    editorialService.delete(id);
    
    return  ResponseEntity.ok().build();
    }
    
    @GetMapping("editorial/{id}")
    public ResponseEntity <?> obtenerEditorialPorId (@Valid @PathVariable Integer id) {
    Editorial editorialEncontrada = editorialService.obtenerEditorialPorId(id);
    
    if (editorialEncontrada == null) {
    return ResponseEntity.notFound().build();
    }
     return ResponseEntity.ok(editorialEncontrada);
    }
    
    @GetMapping("/editoriales")
    public ResponseEntity<List<Editorial>> listarEditoriales() {
    return ResponseEntity.ok(editorialService.obtenerEditoriales());
    }
    }
