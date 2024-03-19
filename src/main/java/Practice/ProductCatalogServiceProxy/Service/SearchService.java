package Practice.ProductCatalogServiceProxy.Service;

import Practice.ProductCatalogServiceProxy.Models.Product;
import Practice.ProductCatalogServiceProxy.Repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Page<Product> searchProducts(String query, int pageNumber, int pageSize){
        Sort sort = Sort.by("price").and(Sort.by("id").descending());
        return productRepository.findByTitleEquals(query, PageRequest.of(pageNumber, pageSize, sort));
    }
}
