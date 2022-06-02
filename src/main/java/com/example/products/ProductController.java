package com.example.products;

import DTO.converter.ProductoDTOConverter;
import exception.ProductoNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    //private  final ProductoDTOConverter productoDTOConverter;

    @GetMapping("/product")
    public ResponseEntity<?> getProducts() {

        List<Product> result = productRepository.findAll();

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productRepository.findById(id).orElseThrow(()-> new ProductoNotFound(id));

//        if (result == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(result);
//
//        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> setProduct(@RequestBody Product nuevo) {
        Product saved = productRepository.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> editProduct(@RequestBody Product editar, @PathVariable Long id) {
        return productRepository.findById(id).map(p -> {
            p.setNombre(editar.getNombre());
            p.setPrecio(editar.getPrecio());
            return ResponseEntity.ok(productRepository.save(p));

        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
