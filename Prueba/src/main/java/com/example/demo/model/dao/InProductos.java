package com.example.demo.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Productos;

public interface InProductos extends JpaRepository<Productos, Long> {

}
