package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.Iservices.ISupplierService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Test
    void retrieveAllSuppliers() {
        // Given
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(1L, "Supplier 1", "Address 1", "Email 1"));
        suppliers.add(new Supplier(2L, "Supplier 2", "Address 2", "Email 2"));

        when(supplierRepository.findAll()).thenReturn(suppliers);

        // When
        List<Supplier> result = supplierService.retrieveAllSuppliers();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void addSupplier() {
        // Given
        Supplier supplier = new Supplier(1L, "Supplier 1", "Address 1", "Email 1");

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // When
        Supplier result = supplierService.addSupplier(supplier);

        // Then
        assertEquals(supplier, result);
    }

    @Test
    void updateSupplier() {
        // Given
        Supplier supplier = new Supplier(1L, "Supplier 1", "Address 1", "Email 1");

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // When
        Supplier result = supplierService.updateSupplier(supplier);

        // Then
        assertEquals(supplier, result);
    }

    @Test
    void deleteSupplier() {
        // Given
        Long supplierId = 1L;

        // When
        supplierService.deleteSupplier(supplierId);

        // Then
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Test
    void retrieveSupplier() {
        // Given
        Long supplierId = 1L;
        Supplier supplier = new Supplier(supplierId, "Supplier 1", "Address 1", "Email 1");

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        // When
        Supplier result = supplierService.retrieveSupplier(supplierId);

        // Then
        assertEquals(supplier, result);
    }
}