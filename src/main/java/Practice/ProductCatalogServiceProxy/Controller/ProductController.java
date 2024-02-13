package Practice.ProductCatalogServiceProxy.Controller;

import Practice.ProductCatalogServiceProxy.DTO.ProductDto;
import Practice.ProductCatalogServiceProxy.Models.Category;
import Practice.ProductCatalogServiceProxy.Models.Product;
import Practice.ProductCatalogServiceProxy.Service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// this will add /products before all paths below, so we can remove the /products below

// when an exception occur it is controller responsibility to inform the client that exception has occurred
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
            //return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            // first it will check for @ExceptionHandler in same file if not found then @RestControllerAdviser
            // will handle all the exceptions that all controller throw.
            throw exception; // @ExceptionHandler will handle this
        }
    }

    @PostMapping("")
    public Product createProducts(@RequestBody ProductDto productDto){
        Product product = getProduct(productDto);
        return productService.createProducts(product);
    }

    @PatchMapping("{id}")
    public Product updateProduct(@PathVariable Long productId,@RequestBody ProductDto productDto){
        Product product = getProduct(productDto);
        return productService.updateProduct(productId, product);
    }
    private Product getProduct(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());

        Category category = new Category();
        category.setName(productDto.getCategory());
        category.setDescription(productDto.getDescription());

        product.setCategory(category);

        return product;
    }


//    // we can specify for which two error we can call this function and return what status
//    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
//    private ResponseEntity<String> handleException(){
//        return new ResponseEntity<String>("kuch tho phata h", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
