package tn.esprit.devops_project.services.Iservices;

import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;

import java.util.List;

public interface IProductService {

    Product addProduct(Product product);
    Product retrieveProduct(Long id);
    List<Product> retreiveAllProduct();
    List<Product> retrieveProductByCategory(ProductCategory category);
    void deleteProduct(Long id);



}
