package mx.tecnm.itlp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import mx.tecnm.itlp.dao.PeliculasJDBC;
import mx.tecnm.itlp.models.Peliculas;
import mx.tecnm.itlp.models.Planes;
@RestController
@RequestMapping("/peliculas")
public class PeliculasREST {
	@Autowired
	PeliculasJDBC repository;
	
	@GetMapping
	public ResponseEntity<?> consultarpeli(){
	List<Peliculas> resultado = repository.consultarpeli();			
	return new ResponseEntity<List<Peliculas>>(resultado, HttpStatus.OK);
	}
	
    @GetMapping("/cantidad")
    public ResponseEntity<?> pelicula(@PathVariable("id") int id){
    	try {
			List<Peliculas> respuesta = repository.buscartotal(id);
			return new ResponseEntity<List<Peliculas>>(respuesta,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
    }
	@PostMapping
    public ResponseEntity<?> insertpeli(@RequestBody Peliculas pelicula){
    	try {
    		repository.insertpeli(pelicula);
    		return new ResponseEntity<>(HttpStatus.CREATED);
    	} catch (DataAccessException e) {
    		System.out.println(e.getMessage());
        	return new ResponseEntity<>(HttpStatus.CONFLICT);    	
    	}
    }
	@DeleteMapping("{pelicula-id}")
	public ResponseEntity<?> desactivapeli(@PathVariable ("pelicula-id")int peliculaid){
		repository.desactivapeli(peliculaid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
