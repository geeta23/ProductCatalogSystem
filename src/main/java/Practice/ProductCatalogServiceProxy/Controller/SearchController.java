package Practice.ProductCatalogServiceProxy.Controller;

import Practice.ProductCatalogServiceProxy.DTO.SearchRequestDto;
import Practice.ProductCatalogServiceProxy.DTO.ProductDto;
import Practice.ProductCatalogServiceProxy.Models.Product;
import Practice.ProductCatalogServiceProxy.Service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    // instead of using @autowire use constructor
    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto){
        Page<Product> products = searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getPageSize());
//        List<ProductDto>searchResults =  new ArrayList<>();
//
//        for(Product product : products){
//            searchResults.add(getProductDTO(product));
//        }
//
//        return searchResults;
        return products;
   }

    private ProductDto getProductDTO(Product product){
        ProductDto productDto =  new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getName());

        return productDto;
    }

}
