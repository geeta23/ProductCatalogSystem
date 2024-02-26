package Practice.ProductCatalogServiceProxy.Controller;

import Practice.ProductCatalogServiceProxy.Models.Product;
import Practice.ProductCatalogServiceProxy.Service.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

// we add @SpringBootTest will be added for test class @Test will be added for method
@SpringBootTest
class ProductControllerTest {
    // autowired works similar to constructor and it can be only used inside @service or @Component,
    // as it is neither so add @SpringBootTest
    @Autowired
    private ProductController productController;

    // this is external dependency, so we need to mock it, so we don't @Autowired use @MockBean
    //@Autowired
    @MockBean
    private IProductService productService;

    // the name of the test to display in console can be predefined
    // now instead of Test_GetProduct_ReturnsProduct, "Getting product successfully" will be displayed
    @Test
    @DisplayName("Getting product successfully")//explaing the behaviour
    public void Test_GetProduct_ReturnsProduct(){
        //Arrange
        // we are mocking when we find the call productService.getProduct then we need to mock the result
        // I'll get new product if I enter any long value. we are mocking as if for any long value sent as id
        // we get new product
//        when(productService.getProduct(any(Long.class))).thenReturn(new Product());

        Product product = new Product();
        product.setPrice(1000D);
        product.setTitle("Iphone15");

        // we will get the product pass instead of default product that was passed earlier
        when(productService.getProduct(any(Long.class))).thenReturn(product);

        //Act
        ResponseEntity<Product> productResponseEntity = productController.getProduct(1L);

        //Assert
        assertNotNull(productResponseEntity);
        assertEquals(1000D, productResponseEntity.getBody().getPrice());
        assertEquals("Iphone15", productResponseEntity.getBody().getTitle());
    }

    @Test
    public void Test_GetProduct_InternalDependencyThrowsException(){
        //Arrange
        when(productService.getProduct(any(Long.class))).thenThrow(new RuntimeException("Something went wrong!"));

        //Act and Assert
        assertThrows(RuntimeException.class, () -> productController.getProduct(1L));
    }

    @Test
    public void Test_GetProductInvalidId_ThrowsException(){
        // not need to mock because this if statement is encountered before product service call
        assertThrows(IllegalArgumentException.class, ()-> productController.getProduct(0L));
    }
}