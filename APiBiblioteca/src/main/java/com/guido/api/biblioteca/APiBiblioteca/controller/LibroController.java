/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guido.api.biblioteca.APiBiblioteca.controller;

import com.guido.api.biblioteca.APiBiblioteca.Services.EditorialService;
import com.guido.api.biblioteca.APiBiblioteca.Services.LibroService;
import com.guido.api.biblioteca.APiBiblioteca.entidades.Editorial;
import com.guido.api.biblioteca.APiBiblioteca.entidades.Libro;
import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
public class LibroController {
    
    @Autowired
    LibroService libroService;
    @Autowired
    EditorialService editorialService;
    
    //para guardar y actualizar un libro, TAMBIEN obtengo la editorial por id y se la seteo al libro que me lo mandan para que siempre este actualizada y seteada si la ed o el libro son null, retorno una unprocessable
    // si ninguno de los condicionales entra, seteo la editorial y en el caso de actualizar, seteo el id, y los mantengo asi siemrpe actualizados
    @PostMapping("/libro")
    public ResponseEntity <?> save ( @Valid @RequestBody Libro libro) {
        Editorial editorial =  editorialService.obtenerEditorialPorId(libro.getEditorial().getId());
        
        if (editorial == null) {
        return ResponseEntity.unprocessableEntity().build();
        }
        
        libro.setEditorial(editorial);
       Libro libroGuardado =  libroService.save(libro);
       //
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(libroGuardado.getId()).toUri();
        // aca retorno el response entity, con el estado de la peticion created (que va a devolver toda la info de URI) y el body le envio el objeto
        return ResponseEntity.created(ubicacion).body(libroGuardado);
    }
    
    @PutMapping("/libro/{id}")
    public ResponseEntity<?> edit (@Valid @RequestBody Libro libro,  @PathVariable Integer id) {
        Editorial editorial =  editorialService.obtenerEditorialPorId(libro.getEditorial().getId());
        Libro libroEncontrado = libroService.obtenerLibroPorId(id);
      
        if (editorial == null) {
        return ResponseEntity.unprocessableEntity().build();
        }

        if ( libroEncontrado == null) {
        return ResponseEntity.unprocessableEntity().build();
        }
        
         libro.setEditorial(editorial);
        libro.setId(libroEncontrado.getId());
        
        libroService.upGrade(libro);
        
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/libro/{id}") 
    public ResponseEntity<?> delete (@Valid @PathVariable Integer id) {
    Libro libroEcontrado = libroService.obtenerLibroPorId(id);
    
    if (libroEcontrado == null) {
    return ResponseEntity.unprocessableEntity().build();
    }
    
    libroService.delete(libroEcontrado);
    return ResponseEntity.ok().build();
        
    }
    
    @GetMapping("/libro/{id}")
    public ResponseEntity<?> obtenerLibro (@PathVariable Integer id) {
    Libro libroEncontrado = libroService.obtenerLibroPorId(id);
    
    if (libroEncontrado == null) {
    return ResponseEntity.unprocessableEntity().build();
    }
    
    return ResponseEntity.ok(libroEncontrado);
    }
    
    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> listarLibros () {
        return  ResponseEntity.ok(libroService.listarLibros());
    }
}
