package Practice.ProductCatalogServiceProxy.Controller;

import Practice.ProductCatalogServiceProxy.DTO.ProductDto;
import Practice.ProductCatalogServiceProxy.Models.Product;
import Practice.ProductCatalogServiceProxy.Service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// this will add /products before all paths below, so we can remove the /products below
@RequestMapping("/products")
// including this annotation will tell that this is a rest controller
@RestController
public class ProductController {
    private IProductService productService;
    //creating the api is like function we give something as argument and get something in return

    // GetMapping annotation tell that /products api is to get data, basically give the route

    public ProductController(IProductService productService){
        this.productService = productService;
    }
    @GetMapping("")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    // if {id} and productId are same then we can directly give @PathVariable() else we have to give
    // @PathVariable("id") to help in mapping

    // by using response entity we can send body, status code, headers by using response entity
    // we can give our own status code, else dispatcher servlet will handle all these
    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId){
        try {
            if(productId < 1){
                throw new IllegalArgumentException("product Id is less than one, recheck");
            }
            // we can add our own headers in both response and request and send it
            // now adding the headers in response

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("called by", "pagal");

            Product product = productService.getProduct(productId);

            // we can add our custom headers to our response
            return new ResponseEntity<>(product, headers, HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public Product createProducts(@RequestBody ProductDto productDto){
        return productService.createProducts(productDto);
    }

    @PatchMapping("")
    public String updateProduct(@RequestBody ProductDto productDto){
        return "Updating the product -- " + productDto;
    }
}
