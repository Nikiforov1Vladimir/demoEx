package nikVl.demoexPractice.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor

public class Product {
    int ID;
    String Title;
    String ArticleNumber;
    double MinCostForAgent;
    String image;
    int ProductionPersonCount;
    int ProductionWorkshopNumber;
    int ProductTypeId;
    String Description;
}
