package nikVl.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor

public class Product {
    private int ID;
    private String Title;
    private String ProductType;
    private String Description;
    private String Image;
    private int Cost;
    private Date RegisterDate;

    public Product(String title, String ProductType, String description, String image, int cost, Date registerDate) {
        this(-1,title,ProductType,description,image,cost,registerDate);
    }
}
