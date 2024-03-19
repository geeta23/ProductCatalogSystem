package Practice.ProductCatalogServiceProxy.Stubs;

import Practice.ProductCatalogServiceProxy.Models.Product;
import Practice.ProductCatalogServiceProxy.Service.IProductService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceStub implements IProductService {

    HashMap<Long, Product> products = new HashMap<>();
    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long productId) {
        return products.get(productId);
    }

    @Override
    public Product createProducts(Product product) {
        products.put(product.getId(), product);
        return products.get(product.getId());
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        products.put(id, product);
        return products.get(id);
    }
}
