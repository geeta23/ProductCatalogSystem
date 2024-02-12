package Practice.ProductCatalogServiceProxy.Service;

import Practice.ProductCatalogServiceProxy.DTO.ProductDto;

public interface IProductService {
    String getProducts();

    String getProduct(Long productId);

    String createProducts(ProductDto productDto);

    String updateProduct(ProductDto productDto);
}
