package com.example.demo.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.InProductos;
import com.example.demo.model.entity.Productos;
@Service
public class ProductosServices implements InProductosServices{
	@Autowired
	private InProductos productosDao;
	
	@Override
	@Transactional(readOnly = true)
	public Productos findById(Long id) {
		// TODO Auto-generated method stub
		return productosDao.findById(id).orElse(null);
	}
	
	@Override
	public Productos save(Productos productos) {
		// TODO Auto-generated method stub
		return productosDao.save(productos);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		productosDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Productos> findAll() {
		// TODO Auto-generated method stub
		return (List<Productos>) productosDao.findAll();
	}

}
