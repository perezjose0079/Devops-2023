package mx.tecnm.itlp.controllers;

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

import mx.tecnm.itlp.dao.CuentaJDBC;
import mx.tecnm.itlp.models.Cuentas;
import mx.tecnm.itlp.models.Planes;

@RestController
@RequestMapping("/api/cuentas")

public class CuentaREST {
	@Autowired
	CuentaJDBC repository;
	
	@PutMapping("{cuenta-id}")
	public ResponseEntity<?> modificarCuenta(@RequestBody Cuentas cuenta,@PathVariable ("cuenta-id")int cuentaid){
    	try {
    		repository.modificar(cuenta,cuentaid);
    		return new ResponseEntity<>(HttpStatus.CREATED);
    	} catch (DataAccessException e) {
    		System.out.println(e.getMessage());
        	return new ResponseEntity<>(HttpStatus.CONFLICT);   	
    	}
	}
	
	@PostMapping
    public ResponseEntity<?> insertar(@RequestBody Cuentas cuenta){
    	try {
    		repository.insertar(cuenta);
    		return new ResponseEntity<>(HttpStatus.CREATED);
    	} catch (DataAccessException e) {
    		System.out.println(e.getMessage());
        	return new ResponseEntity<>(HttpStatus.CONFLICT);    	
    	}
    }
	
	@DeleteMapping("{cuenta-id}")
	public ResponseEntity<?> desactivaCuenta(@PathVariable ("cuenta-id")int cuentaid){
		repository.desactivaCuenta(cuentaid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/login")
		public ResponseEntity<?> login(@RequestBody Cuentas cuenta){
			try {
				
				Cuentas resultado = repository.login(cuenta);
				return new ResponseEntity<>(HttpStatus.OK);				
				} catch (DataAccessException e) {
					
				return new ResponseEntity<>( HttpStatus.NOT_FOUND);
				}
		}
    
    @GetMapping("/planes/{cuenta-id}")
    public ResponseEntity<?>plan(@PathVariable("cuenta-id")int id){
    	try {
			Planes resultado=repository.buscarPlan(id);
			return new ResponseEntity<Planes>(resultado,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
    }
}