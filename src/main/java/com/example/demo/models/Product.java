package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name = "ProductTable")
@Entity
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @NotBlank
    @Pattern(regexp = "[A-Z]{1}[a-z ]+", message = "Only letters allowed")
    @Column(name = "Title")
    private String title;

    @Min(0)
	@Max(10000)
    @Column(name = "Price")
    private double price;

    @NotBlank
    @Column(name = "Description")
    @Size(min = 4, max = 200, message = "Description must be between 4 and 200 characters")
    @Pattern(regexp = "[A-Za-z .:!]+", message = "Description can only contain letters, spaces, and punctuation")
    private String description;

    @Min(0)
	@Max(100)
    @Column(name = "Quantity")
    private int quantity;

    @NotBlank
    @Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀŽČŅ]{1}[a-zēūīļķģšāžčņ]{2,20}")
    @Column(name = "Surname")
    private String surname;

    @Min(1)
    @Max(100)
    @Column(name = "Age")
    private int age;

    
/*
    public Product(){
        this.setId();
        this.title = "----";
    }*/

    public Product(@NotBlank @Pattern(regexp = "[A-Z]{1}[a-z ]+", message = "Only letters allowed")String title, 
    @Min(0)@Max(10000) double price, @NotBlank
    @Size(min = 4, max = 200, message = "Description must be between 4 and 200 characters")
    @Pattern(regexp = "[A-Za-z .:!]+", message = "Description can only contain letters, spaces, and punctuation")String description,
    @Min(0) @Max(100)int quantity, @NotBlank @Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀŽČŅ]{1}[a-zēūīļķģšāžčņ]{2,20}")String surname, 
    @Min(1) @Max(100)int age){
    	
    	this.title = title;
    	this.price = price;
    	this.description = description;
    	this.quantity = quantity;
    	this.surname = surname;
    	this.age = age;
    	/*
        this.setTitle(title);
        this.setPrice(price);
        this.setDescription(description);
        this.setQuantity(quantity);
        this.setSurname(surname);
        this.setAge(age);*/
    }

    /*
    public void setId() {
        this.id = counter;
        counter++;
    }*/

   
}
