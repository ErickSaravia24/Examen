package com.example.demo.model.services;

import java.util.List;

import com.example.demo.model.entity.Productos;

public interface InProductosServices {
	public List<Productos> findAll();
	//Busqueda
	public Productos findById(Long id);
	//Guardar
	public Productos save(Productos productos);
	//Eliminar 
	public void delete(Long id);
	
}
