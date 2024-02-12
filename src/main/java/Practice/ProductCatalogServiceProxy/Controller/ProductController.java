package Practice.ProductCatalogServiceProxy.Controller;

import Practice.ProductCatalogServiceProxy.DTO.ProductDto;
import org.springframework.web.bind.annotation.*;

// this will add /products before all paths below, so we can remove the /products below
@RequestMapping("/products")
// including this annotation will tell that this is a rest controller
@RestController
public class ProductController {
    //creating the api is like function we give something as argument and get something in return

    // GetMapping annotation tell that /products api is to get data, basically give the route
    @GetMapping("")
    public String getProducts(){
        return "Returning all products.";
    }

    // if {id} and productId are same then we can directly give @PathVariable() else we have to give
    // @PathVariable("id") to help in mapping
    @GetMapping("{id}")
    public String getProduct(@PathVariable("id") Long productId){
        return "Returning product with id = " + productId;
    }

    @PostMapping("")
    public String createProducts(@RequestBody ProductDto productDto){
        return "Creating the product -- " + productDto;
    }

    @PatchMapping("")
    public String updateProduct(@RequestBody ProductDto productDto){
        return "Updating the product -- " + productDto;
    }
}
