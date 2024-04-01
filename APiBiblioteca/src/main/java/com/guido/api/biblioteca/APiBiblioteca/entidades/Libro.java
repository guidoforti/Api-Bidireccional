/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guido.api.biblioteca.APiBiblioteca.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

/**
 *
 * @author Usuario
 */
@Entity
//aca estamos indicando QUE EL NOMBRE DEL LIBRO SEA UNICO Y NO SE PUEDA REPETIR
@Table(name = "libro", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nombre;

    //el fetch es el tipo de carga, esta es una carga perezosa, eager trae TODA LA INFO, trae el libro y la INFO DE CADA EDITORIAL, en cambio lazy, no
    // si soloi indicamos la relacion en esta entidad, seria UNIDIRECCIONAL, en cambio en ambos ya es bidireccional y podemos acceder a la otra entidad desde cada entidad
    @ManyToOne
    @JoinColumn(name = "editorial_id")  // esta anotacion nos permite tener una columna que une con la otra entidad y va a tener una editorial para poder acceder a ella
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //lo que hace es que a la no no de una excepcion de lazy, cuando usamos lazy, la sesion de la bd se cierra antes de mostrar la editorial , lo que hace esto es evitar qeu nos tire una excepcion que se daria por queder acceder ala editorial luego de cerrar la sesion
     private Editorial editorial;

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Libro() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
