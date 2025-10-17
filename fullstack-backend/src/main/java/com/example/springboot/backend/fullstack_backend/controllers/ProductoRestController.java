package com.example.springboot.backend.fullstack_backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.springboot.backend.fullstack_backend.entity.Producto;
import com.example.springboot.backend.fullstack_backend.service.ProductoService;


@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoService productoservice;

    @GetMapping
    public List<Producto> mostrarProducto() {
        return productoservice.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verProducto(@PathVariable Long id) {
        Optional<Producto> optionalProducto = productoservice.findById(id);
        if (optionalProducto.isPresent()) {
            return ResponseEntity.ok(optionalProducto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto unProducto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoservice.save(unProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable Long id, @RequestBody Producto unProducto) {
        Optional<Producto> optionalProducto = productoservice.findById(id);
        if (optionalProducto.isPresent()) {
            Producto productoExiste = optionalProducto.get();
            productoExiste.setNombre(unProducto.getNombre());
            productoExiste.setPrecio(unProducto.getPrecio());
            productoExiste.setStock(unProducto.getStock());
            productoExiste.setDescripcion(unProducto.getDescripcion());
            productoExiste.setCategoria(unProducto.getCategoria());
            Producto productoModificado = productoservice.save(productoExiste);
            return ResponseEntity.ok(productoModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoservice.findById(id);
        if (productoOptional.isPresent()) {
            productoservice.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}