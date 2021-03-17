package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.Productos;
import com.example.demo.model.services.InProductosServices;


@RestController
@RequestMapping("/pb")
public class ProductosRestController {
	
	@Autowired
	private InProductosServices inProductosServices;
	private final Logger log =  LoggerFactory.getLogger(ProductosRestController.class); 
	@GetMapping("/ProductosPB")
	public List<Productos> index(){
		return inProductosServices.findAll();
		
	}
	@GetMapping("/ProductosPB/{id}")
	public ResponseEntity<?>mostrar(@PathVariable Long id){
		Productos p=null;
		Map<String, Object> response=new HashMap<>();
		
		try {
			p=inProductosServices.findById(id);
			
		} catch (DataAccessException e) {
		 response.put("Error al Mostrar", "Realiza de nuevo la consulta");
		 response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		 
		}
		if(p==null) {
			 response.put("Error, ", "Producto con Id: ".concat(id.toString()).concat(" No existe en la BD")); 
			 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		 }
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
	@PostMapping("/ProductosPB")
	public ResponseEntity<?>crear(@Valid @RequestBody Productos productoC, BindingResult br){
		Productos p=null;
		Map<String, Object> response=new HashMap<>();
		if (br.hasErrors()) {
			
			List<String> errors = br.getFieldErrors()
					.stream()
					.map(err->"El campo '"+err.getField()+"'  "+err.getDefaultMessage())
					.collect(Collectors.toList());
			 response.put("Error al Crear: ", errors);
			
			 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}

		try {
			p=inProductosServices.save(productoC);
			
		} catch (DataAccessException e) {
		 response.put("Error al Mostrar", "Error al insertar Registro");
		 response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		 
		}
		response.put("Message:", "Se creo con exito");
		response.put("Producto",p);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	}
	@PutMapping("/ProductosPB/{id}")
	public ResponseEntity<?>edita(@Valid @RequestBody Productos productosE,BindingResult br,@PathVariable Long id ){
		Productos pA=inProductosServices.findById(id);
		Productos p=null;
		Map<String, Object> response=new HashMap<>();
		if (br.hasErrors()) {
			
			List<String> errors = br.getFieldErrors()
					.stream()
					.map(err->"El campo '"+err.getField()+"'  "+err.getDefaultMessage())
					.collect(Collectors.toList());
			 response.put("Error al Crear: ", errors);
			
			 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		if(pA==null){
			response.put("Error, ", "Producto con Id: ".concat(id.toString()).concat(" No se puede editar ya que no existe en la BD")); 
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			pA.setsKU(productosE.getsKU());
			pA.setfERT(productosE.getfERT());
			pA.setModelo(productosE.getModelo());
			pA.setnSerie(productosE.getnSerie());
			pA.setFecha(productosE.getFecha());
			p=inProductosServices.save(pA);
		} catch (DataAccessException e) {
			 response.put("Error al Mostrar", "Error al Actualizar Registro");
			 response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		response.put("Message:", "Se Actualizo con exito");
		response.put("Producto",p);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/ProductosPB/{id}")
	
	public ResponseEntity<?>elimina(@PathVariable Long id ){
		
		
		Map<String, Object> response=new HashMap<>();
		
		
		try {
			
		Productos p=inProductosServices.findById(id);
		inProductosServices.delete(id);
		} catch (DataAccessException e) {
			 response.put("Error al Mostrar", "Error al eliminar Registro");
			 response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		response.put("Message:", "Se elimino con exito");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
	
	
}
