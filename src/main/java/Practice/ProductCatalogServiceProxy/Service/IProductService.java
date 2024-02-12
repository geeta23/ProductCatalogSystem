package Practice.ProductCatalogServiceProxy.Service;

import Practice.ProductCatalogServiceProxy.DTO.ProductDto;
import Practice.ProductCatalogServiceProxy.Models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product getProduct(Long productId);

    Product createProducts(ProductDto productDto);

    String updateProduct(ProductDto productDto);
}
