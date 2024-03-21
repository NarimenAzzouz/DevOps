package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct() {
        Product product = new Product();
        product.setIdProduct(1L);
        product.setTitle("Test Product");
        product.setPrice(100.0f);
        product.setQuantity(10);
        product.setCategory(ProductCategory.ELECTRONICS);

        when(productRepository.save(any())).thenReturn(product);

        Product savedProduct = productService.addProduct(product);

        assertEquals(product.getIdProduct(), savedProduct.getIdProduct());
        assertEquals(product.getTitle(), savedProduct.getTitle());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
        assertEquals(product.getCategory(), savedProduct.getCategory());

        verify(productRepository, times(1)).save(any());
    }

    @Test
    void retrieveProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);
        product.setTitle("Test Product");
        product.setPrice(100.0f);
        product.setQuantity(10);
        product.setCategory(ProductCategory.ELECTRONICS);

        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

        Product retrievedProduct = productService.retrieveProduct(productId);

        assertEquals(productId, retrievedProduct.getIdProduct());
        assertEquals(product.getTitle(), retrievedProduct.getTitle());
        assertEquals(product.getPrice(), retrievedProduct.getPrice());
        assertEquals(product.getQuantity(), retrievedProduct.getQuantity());
        assertEquals(product.getCategory(), retrievedProduct.getCategory());

        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void retreiveAllProduct() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setTitle("Test Product 1");
        product1.setPrice(100.0f);
        product1.setQuantity(10);
        product1.setCategory(ProductCategory.ELECTRONICS);
        productList.add(product1);

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setTitle("Test Product 2");
        product2.setPrice(200.0f);
        product2.setQuantity(20);
        product2.setCategory(ProductCategory.BOOKS);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveAllProduct();

        assertEquals(2, retrievedProducts.size());
        assertEquals(productList, retrievedProducts);

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void retrieveProductByCategory() {
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setTitle("Test Product 1");
        product1.setPrice(100.0f);
        product1.setQuantity(10);
        product1.setCategory(category);
        productList.add(product1);

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setTitle("Test Product 2");
        product2.setPrice(200.0f);
        product2.setQuantity(20);
        product2.setCategory(category);
        productList.add(product2);

        when(productRepository.findByCategory(category)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retrieveProductByCategory(category);

        assertEquals(2, retrievedProducts.size());
        assertEquals(productList, retrievedProducts);

        verify(productRepository, times(1)).findByCategory(category);
    }

    @Test
    void deleteProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);
        product.setTitle("Test Product");
        product.setPrice(100.0f);
        product.setQuantity(10);
        product.setCategory(ProductCategory.ELECTRONICS);

        doNothing().when(productRepository).deleteById(productId);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}

