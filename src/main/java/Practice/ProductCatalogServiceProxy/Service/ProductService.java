package Practice.ProductCatalogServiceProxy.Service;

import Practice.ProductCatalogServiceProxy.DTO.ProductDto;
import org.springframework.web.bind.annotation.*;

public class ProductService implements IProductService {

    @Override
    public String getProducts(){
        return "Returning all products.";
    }

    // if {id} and productId are same then we can directly give @PathVariable() else we have to give
    // @PathVariable("id") to help in mapping

    @Override
    public String getProduct(Long productId){
        return "Returning product with id = " + productId;
    }

    @Override
    public String createProducts(ProductDto productDto){
        return "Creating the product -- " + productDto;
    }

    @Override
    public String updateProduct(ProductDto productDto){
        return "Updating the product -- " + productDto;
    }
}
