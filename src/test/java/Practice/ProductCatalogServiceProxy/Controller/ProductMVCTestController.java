package Practice.ProductCatalogServiceProxy.Controller;

import Practice.ProductCatalogServiceProxy.Models.Product;
import Practice.ProductCatalogServiceProxy.Service.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

// when client enter "/products" then client doesn't know that the product controller will response as
// that will be taken care by dispatcher servlet
// here we have to check if client give "/products then productController should respond"
@WebMvcTest(ProductController.class)
public class ProductMVCTestController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    // jackson library to convert object into string
    private ObjectMapper objectMapper;

    @Test
    public void Test_getProducts_ReceivedSuccessfulResponse() throws Exception {
        // Arrange
        List<Product>products = new ArrayList<>();
        Product product = new Product();
        product.setTitle("Iphone5");
        product.setPrice(1000D);
        products.add(product);

        Product product2 =  new Product();
        product2.setPrice(2000D);
        product2.setTitle("Samsung");
        products.add(product2);

        when(productService.getProducts()).thenReturn(products);
        // perform a get request on "/products" and resultant status as ok
        // response is converted from json to string
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(products)));

        //object -> json -> string
    }

    @Test
    public void Test_CreateProduct_ReceiveSuccessfulResponse() throws Exception{
        Product productToCreate = new Product();
        productToCreate.setTitle("Iphone");
        productToCreate.setPrice(1000D);

        Product productExcepted = new Product();
        productExcepted.setId(10L);
        productExcepted.setTitle("Iphone");
        productExcepted.setPrice(1000D);

        when(productService.createProducts(any(Product.class))).thenReturn(productExcepted);
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productExcepted)));
    }
}
