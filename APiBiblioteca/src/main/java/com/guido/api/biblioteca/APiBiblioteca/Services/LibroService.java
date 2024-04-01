/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guido.api.biblioteca.APiBiblioteca.Services;

import com.guido.api.biblioteca.APiBiblioteca.DAO.LibroRepository;
import com.guido.api.biblioteca.APiBiblioteca.entidades.Libro;
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
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    @Transactional
    public Libro save(Libro libro) {

        return libroRepository.save(libro);
    }

    @Transactional
    public void deleteById(Integer Id) {
        Optional<Libro> libroEncontrado = libroRepository.findById(Id);

        if (libroEncontrado.isPresent()) {
            libroRepository.deleteById(Id);
        }

    }

    @Transactional
    public void delete(Libro libro) {

        libroRepository.delete(libro);

    }

    @Transactional
    public Libro upGrade(Libro libroNuevo) {

        return libroRepository.save(libroNuevo);
    }

    public List<Libro> listarLibros() {

        return (List<Libro>) libroRepository.findAll();
    }

    public Libro obtenerLibroPorId(Integer id) {

        return libroRepository.findById(id).orElse(null);
    }
}
