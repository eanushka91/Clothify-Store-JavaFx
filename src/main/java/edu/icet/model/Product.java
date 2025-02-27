package edu.icet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private String id;
    private String name;
    private String category;
    private String size;
    private String price;
    private String quantity;
}
