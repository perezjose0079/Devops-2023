package mx.tecnm.itlp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.itlp.dao.PlanJDBC;
import mx.tecnm.itlp.models.Cuentas;
import mx.tecnm.itlp.models.Planes;

@RestController
@RequestMapping("/api/planes")


public class PlanREST {
	@Autowired
	PlanJDBC repository;

	
	
	@GetMapping
	public ResponseEntity<?> consultar(){
	List<Planes> resultado = repository.consultar();			
	return new ResponseEntity<List<Planes>>(resultado, HttpStatus.OK);
	}

	@GetMapping("/{plan-id}")
	public ResponseEntity<?> buscar(@PathVariable ("plan-id") int id){
		try {
			Planes resultado = repository.buscar(id);
			return new ResponseEntity<Planes>(resultado, HttpStatus.OK);				
			} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
	}

	@PutMapping("{plan-id}")
	public ResponseEntity<?> modificarPlan (@RequestBody Planes plan, @PathVariable ("plan-id")int planid){
		try {
			repository.modificar(plan,planid);
			return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (DataAccessException e) {
    		System.out.println(e.getMessage());
    		return new ResponseEntity<>(HttpStatus.CONFLICT);
    	
    	}
	}
	
    @PostMapping()
    public ResponseEntity<?> insertar(@RequestBody Planes plan){
    	try {
    		repository.insertar(plan);
    		return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (DataAccessException e) {
    		System.out.println(e.getMessage());
    		return new ResponseEntity<>(HttpStatus.CONFLICT);
    	
    	}
    }

    @GetMapping("/cuentas/{plan-id}")
    public ResponseEntity<?>cuentas(@PathVariable("plan-id")int id){
    	try {
			List <Cuentas> resultado=repository.buscarCuentas(id);
			return new ResponseEntity<List<Cuentas>>(resultado,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
    }
   
}
