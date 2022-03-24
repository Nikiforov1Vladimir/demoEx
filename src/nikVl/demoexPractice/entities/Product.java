package nikVl.demoexPractice.entities;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Product {
    int ID;
    String Title;
    int ProductTypeId;
    String ArticleNumber;
    String Description;
    String image;
    int ProductionPersonCount;
    int ProductionWorkshopNumber;
    double MinCostForAgent;
}
