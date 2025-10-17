package com.example.springboot.backend.fullstack_backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.backend.fullstack_backend.entity.Producto;


public interface ProductoRepository extends CrudRepository<Producto, Long> {

}