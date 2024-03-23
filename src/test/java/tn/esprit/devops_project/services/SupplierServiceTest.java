package tn.esprit.devops_project.services;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllSuppliers() {
        // Créer une liste de fournisseurs simulée
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(1L, "001", "Fournisseur 1", null));
        suppliers.add(new Supplier(2L, "002", "Fournisseur 2", null));

        // Définir le comportement simulé du repository
        when(supplierRepository.findAll()).thenReturn(suppliers);

        // Appeler la méthode du service
        List<Supplier> result = supplierService.retrieveAllSuppliers();

        // Vérifier le résultat
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Fournisseur 1", result.get(0).getLabel());
        Assertions.assertEquals("Fournisseur 2", result.get(1).getLabel());
    }

    @Test
    public void testRetrieveSupplier() {
        // Créer un fournisseur simulé
        Supplier supplier = new Supplier(1L, "001", "Fournisseur 1", null);

        // Définir le comportement simulé du repository
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        // Appeler la méthode du service
        Supplier result = supplierService.retrieveSupplier(1L);

        // Vérifier le résultat
        Assertions.assertEquals("Fournisseur 1", result.getLabel());
    }

    // Ajoutez des tests similaires pour les autres méthodes de service (addSupplier, updateSupplier, deleteSupplier) en utilisant Mockito pour simuler le comportement du repository.

}
