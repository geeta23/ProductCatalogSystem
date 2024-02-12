package Practice.ProductCatalogServiceProxy.DTO;

import Practice.ProductCatalogServiceProxy.Models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private String title;
    private String description;
    private String imageUrl;
    private double price;
    private Category category;
}
