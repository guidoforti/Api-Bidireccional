/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.guido.api.biblioteca.APiBiblioteca.DAO;

import com.guido.api.biblioteca.APiBiblioteca.entidades.Editorial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface EditorialRepository extends CrudRepository<Editorial, Integer>{
    
}
