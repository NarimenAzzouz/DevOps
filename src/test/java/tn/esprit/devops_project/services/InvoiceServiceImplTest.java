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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the InvoiceServiceImpl class.
 */
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

    /**
     * Test case for retrieving all invoices.
     */
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

    /**
     * Test case for canceling an invoice.
     */
    @Test
    void cancelInvoice() {
        // Mock invoice
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(1L);
        invoice.setArchived(false); // Initialize as not archived

        // Mock repository response
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));

        // Call service method
        invoiceService.cancelInvoice(1L);

        // Verify expected behavior
        assertTrue(invoice.getArchived()); // Check if the invoice is archived after cancellation
    }

    /**
     * Test case for retrieving a specific invoice.
     */
    @Test
    void retrieveInvoice() {
        // Mock invoice
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(1L);

        // Mock repository response
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));

        // Call service method
        Invoice result = invoiceService.retrieveInvoice(1L);

        // Verify expected behavior
        assertNotNull(result);
        assertEquals(1L, result.getIdInvoice());
    }

    /**
     * Test case for retrieving invoices by supplier.
     */

    /*
    @Test
    void getInvoicesBySupplier() {
        // Mock supplier
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice());

        // Mock repository response
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(supplier.getInvoices()).thenAnswer(invocation -> invoices); // Return the list of invoices

        // Call service method
        List<Invoice> result = invoiceService.getInvoicesBySupplier(1L);

        // Verify expected behavior
        assertNotNull(result);
        assertEquals(invoices, result);
    }
*/
    /**
     * Test case for assigning an operator to an invoice.
     */
    @Test
    void assignOperatorToInvoice() {
        // Mock invoice and operator
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(1L);
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setInvoices(new HashSet<>()); // Initialize the invoices set

        // Mock repository response
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));
        when(operatorRepository.findById(1L)).thenReturn(Optional.of(operator));

        // Call service method
        invoiceService.assignOperatorToInvoice(1L, 1L);

        // Verify expected behavior
        assertTrue(operator.getInvoices().contains(invoice));
    }

    /**
     * Test case for calculating the total amount of invoices between two dates.
     */
    @Test
    void getTotalAmountInvoiceBetweenDates() {
        // Define start and end dates
        Date startDate = new Date(); // Replace this with your start date
        Date endDate = new Date(); // Replace this with your end date

        // Define the expected total amount
        float expectedTotalAmount = 100.0f; // Replace this with your expected total amount

        // Mock repository response
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedTotalAmount);

        // Call service method
        float actualTotalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Verify expected behavior
        assertEquals(expectedTotalAmount, actualTotalAmount);
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

}

