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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.itlp.dao.ListaJDBC;
import mx.tecnm.itlp.models.Lista;
@RestController
@RequestMapping("/api/lista")
public class ListaREST {
		@Autowired
		ListaJDBC repository;
		
		@GetMapping
		public ResponseEntity<?> consultarList(){
		List<Lista> resultado = repository.consultarList();			
		return new ResponseEntity<List<Lista>>(resultado, HttpStatus.OK);
		}
		
		@PostMapping
	    public ResponseEntity<?> insertList(@RequestBody Lista lista){
	    	try {
	    		repository.insertpeli(lista);
	    		return new ResponseEntity<>(HttpStatus.CREATED);
	    	} catch (DataAccessException e) {
	    		System.out.println(e.getMessage());
	        	return new ResponseEntity<>(HttpStatus.CONFLICT);    	
	    	}
	    }
		@DeleteMapping("{lista-id}")
		public ResponseEntity<?> desactivaList(@PathVariable ("lista-id")int listaid){
			repository.desactivaList(listaid);
			return new ResponseEntity<>(HttpStatus.OK);
		}
}
