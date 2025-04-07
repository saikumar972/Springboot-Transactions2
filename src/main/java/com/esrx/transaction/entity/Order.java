package com.esrx.transaction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="order_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonProperty("productNo")
    private int productId;
    @Column(name="product_cost")
    @JsonProperty("product_cost")
    private int price;
    private int quantity;
    @Column(name="total_amount")
    private int amount;
    private LocalDateTime purchaseDate;
}
