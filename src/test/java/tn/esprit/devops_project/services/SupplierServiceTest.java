package tn.esprit.devops_project.services;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Test
    public void testRetrieveAllSuppliers() {
        // Créer une liste de fournisseurs simulée
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(1L, "code1", "label1", null));
        suppliers.add(new Supplier(2L, "code2", "label2", null));

        // Simuler le comportement du repository
        when(supplierRepository.findAll()).thenReturn(suppliers);

        // Appeler la méthode du service à tester
        List<Supplier> retrievedSuppliers = supplierService.retrieveAllSuppliers();

        // Vérifier si la liste retournée par le service correspond à la liste simulée
        Assertions.assertEquals(suppliers, retrievedSuppliers);
    }

    @Test
    public void testAddSupplier() {
        // Créer un fournisseur simulé
        Supplier supplier = new Supplier(1L, "code1", "label1", null);

        // Simuler le comportement du repository
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Appeler la méthode du service à tester
        Supplier addedSupplier = supplierService.addSupplier(supplier);

        // Vérifier si le fournisseur retourné par le service correspond au fournisseur simulé
        Assertions.assertEquals(supplier, addedSupplier);
    }

}

