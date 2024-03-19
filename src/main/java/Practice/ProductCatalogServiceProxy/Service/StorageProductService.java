package Practice.ProductCatalogServiceProxy.Service;

import Practice.ProductCatalogServiceProxy.Models.Product;
import Practice.ProductCatalogServiceProxy.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageProductService implements IProductService{

    private ProductRepository productRepository;

    public StorageProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long productId) {
        Product product = productRepository.findProductById(productId);
        return product;
    }

    @Override
    public Product createProducts(Product product) {
        Product resultProduct = productRepository.save(product);
        return resultProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}
