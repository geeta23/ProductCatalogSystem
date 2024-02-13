package Practice.ProductCatalogServiceProxy.Service;

import Practice.ProductCatalogServiceProxy.Clients.FakeStore.Client.FakeStoreApiClient;
import Practice.ProductCatalogServiceProxy.Clients.FakeStore.Dtos.FakeStoreProductDto;
import Practice.ProductCatalogServiceProxy.Clients.FakeStore.Dtos.FakeStoreRatingDto;
import Practice.ProductCatalogServiceProxy.Models.Category;
import Practice.ProductCatalogServiceProxy.Models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.ArrayList;
import java.util.List;

// tell spring that this the service and we need to autowire with controller
@Service
public class FakeStoreProductService implements IProductService {
    private FakeStoreApiClient fakeStoreApiClient;

    public FakeStoreProductService(FakeStoreApiClient fakeStoreApiClient){
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    @Override
    public List<Product> getProducts(){
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreApiClient.getProducts();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreproductDto: fakeStoreProductDtos){
            products.add(getProduct(fakeStoreproductDto));
        }
        return products;
    }

    @Override
    public Product getProduct(Long productId){
        return getProduct(fakeStoreApiClient.getProduct(productId));
    }

    @Override
    public Product createProducts(Product product){
        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProduct(product);
        return getProduct(fakeStoreApiClient.createProducts(fakeStoreProductDto));
    }

    @Override
    public Product updateProduct(Long id, Product product){
        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProduct(product);
        return getProduct(fakeStoreApiClient.updateProduct(id, fakeStoreProductDto));
    }


    private Product getProduct(FakeStoreProductDto fakeStoreproductDto){
        Product product = new Product();
        product.setId(fakeStoreproductDto.getId());
        product.setTitle(fakeStoreproductDto.getTitle());
        product.setPrice(fakeStoreproductDto.getPrice());
        product.setDescription(fakeStoreproductDto.getDescription());
        product.setImageUrl(fakeStoreproductDto.getImage());

        Category category = new Category();
        category.setName(fakeStoreproductDto.getCategory());
        category.setDescription(fakeStoreproductDto.getDescription());

        product.setCategory(category);

        return product;
    }

    private FakeStoreProductDto getFakeStoreProduct(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getDescription());

        return fakeStoreProductDto;
    }

}
