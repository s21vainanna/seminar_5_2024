package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

    @Setter(value = AccessLevel.NONE)
    @Id
    private int id;

    private String title;

    private double price;

    private String description;

    private int quantity;

    private String surname;

    private int age;

    private static int counter = 1;

    public Product(){
        this.setId();
        this.title = "----";
    }

    public Product(String title, double price, String description, int quantity, String surname, int age){
        this.setId();
        this.setTitle(title);
        this.setPrice(price);
        this.setDescription(description);
        this.setQuantity(quantity);
        this.setSurname(surname);
        this.setAge(age);
    }

    public void setId() {
        this.id = counter;
        counter++;
    }

    public String toString(){
        return id + ", " + title + ", " + price + ", " + description + ", " + quantity +
            ", " + surname + ", " + age;

    }
}
