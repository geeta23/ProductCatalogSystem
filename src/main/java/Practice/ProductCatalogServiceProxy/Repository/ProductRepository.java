package Practice.ProductCatalogServiceProxy.Repository;

import Practice.ProductCatalogServiceProxy.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Product findProductById(Long id);

    // Instead of List use Page to get page number
    Page<Product> findByTitleEquals(String query, Pageable pageable);
}
