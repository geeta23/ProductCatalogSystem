package Practice.ProductCatalogServiceProxy.Controller;

import Practice.ProductCatalogServiceProxy.DTO.ProductDto;
import Practice.ProductCatalogServiceProxy.Models.Product;
import Practice.ProductCatalogServiceProxy.Service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlowTest {
    @Autowired
    private ProductController productController;

    // we have implementation of product service stub so we use @autowired instead of @mockbean
    @Autowired
    private IProductService productService;

    @Test
    public void Test_CreateFetchUpdate_RunsSuccessfully(){
        //Arrange
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("ABC");
        productDto.setDescription("XYZ");

        //Act
        productController.createProducts(productDto);
        ResponseEntity<Product>productResponseEntity =  productController.getProduct(1L);

        //Asset
        assertEquals("ABC" , productResponseEntity.getBody().getTitle());
        assertEquals("XYZ", productResponseEntity.getBody().getDescription());


    }
}
