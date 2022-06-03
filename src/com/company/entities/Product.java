package com.company.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public
class Product {
    private int ID;
    private String Title;
    private int ProductTypeId;
    private String ArticleNumber;
    private String Description;
    private String Image;
    private int ProductionPersonCount;
    private int ProductionWorkshopNumber;
    private double MinCostForAgent;
}
