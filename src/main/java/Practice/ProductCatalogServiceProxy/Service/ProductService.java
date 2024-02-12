package Practice.ProductCatalogServiceProxy.Service;

import Practice.ProductCatalogServiceProxy.DTO.ProductDto;
import Practice.ProductCatalogServiceProxy.Models.Category;
import Practice.ProductCatalogServiceProxy.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

// tell spring that this the service and we need to autowire with controller
@Service
public class ProductService implements IProductService {

    // it is the library provided by spring and internally invokes the http client
    // which will call API's for us similarly how I implemented in front end.

    // when I hit /products endpoint from front end, then that will hit controller
    // then inside controller we a ProductService to call and inside product service
    // we are calling a third party API's to fetch data for now i.e why we named the project as proxy
    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getProducts(){
        //return "Returning all products.";
        RestTemplate restTemplate = restTemplateBuilder.build();

        // since List<ProductDTO> is generic spring is unable to identify the when we write List<ProductDTO>.class type,
        // use array instead and json allow array in them
        // generics like list are unable to identify determine the type of class at run time, so we are using array.
        ProductDto[] productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class).getBody();

        List<Product> products = new ArrayList<>();
        for(ProductDto productDto: productDtos){
            products.add(getProduct(productDto));
        }
        return products;
    }

    // if {id} and productId are same then we can directly give @PathVariable() else we have to give
    // @PathVariable("id") to help in mapping

    // jackson takes care of conversion of json into object and object to json, as we see some null in
    // fields jackson will take care of mapping the keys with objects
    @Override
    public Product getProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        // (url to hit, response type of class, external variables like product id where it will replace {id})
        // getForEntity is returning response entity so we did getBody to get only the body
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productId).getBody();
        return getProduct(productDto);
    }

    @Override
    public Product createProducts(ProductDto productDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);
        return getProduct(responseEntity.getBody());
    }

    @Override
    public String updateProduct(ProductDto productDto){
        return "Updating the product -- " + productDto;
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
}
