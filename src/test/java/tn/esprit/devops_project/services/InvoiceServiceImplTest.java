package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class InvoiceServiceImplTest {
    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private OperatorRepository operatorRepository;
    @Mock
    private SupplierRepository supplierRepository;
    @InjectMocks
    private InvoiceServiceImpl invoiceService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllInvoices() {
        // Mocking repository response
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice());
        when(invoiceRepository.findAll()).thenReturn(invoices);

        // Call service method
        List<Invoice> result = invoiceService.retrieveAllInvoices();

        // Verify expected behavior
        assertEquals(invoices, result);
    }
    /*
    @Test
    void retrieveAllInvoices_WrongReturnType() {
        // Define a list of strings
        List<String> unexpectedData = Arrays.asList("Invoice1", "Invoice2", "Invoice3");

        // Mock the behavior of findAll() to throw an exception
        when(invoiceRepository.findAll()).thenThrow(new RuntimeException("Unexpected data type"));

        // Call service method
        List<Invoice> result = invoiceService.retrieveAllInvoices();

        // Verify unexpected behavior (expecting a failure)
        assertNotEquals(unexpectedData, result);
    }*/

    // Add more test cases for other methods as needed
    // For example, test cancelInvoice(), retrieveInvoice(), etc.
}
