package com.projeto.crudProducts.models;


import com.projeto.crudProducts.dtos.ProductRecordDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name= "Products")
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String name;
    private float price;
    private String type;

    public ProductModel(){

    }

    public ProductModel(ProductRecordDto recordDto){
        this.name = recordDto.name();
        this.price = recordDto.price();
        this.type = recordDto.type();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
