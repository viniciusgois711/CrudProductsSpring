package com.projeto.crudProducts.controllers;


import com.projeto.crudProducts.dtos.ProductRecordDto;
import com.projeto.crudProducts.models.ProductModel;
import com.projeto.crudProducts.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts(){
        var allProducts = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneProduct(@PathVariable(value= "id") UUID id){
        Optional<ProductModel> product = repository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody @Valid ProductRecordDto data){
        var newProduct = new ProductModel(data);
        repository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateProduct(@PathVariable(value= "id") UUID id,
                                        @RequestBody @Valid ProductRecordDto data){

        Optional<ProductModel> product = repository.findById(id);

        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found. ");
        }
        ProductModel productX = product.get();
        productX.setName(data.name());
        productX.setPrice(data.price());
        productX.setType(data.type());
        return ResponseEntity.status(HttpStatus.OK).body(productX);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> product = repository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found. ");
        }
        ProductModel productX = product.get();
        repository.delete(productX);
        return ResponseEntity.status(HttpStatus.OK).body("Product Removed Successfully. ");
    }
}
