package tn.esprit.devops_project.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.Iservices.IOperatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorServiceImplTest {

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl service;


    @Test
    public void shouldSaveOperatorWithSuccess() {
        Long id = 1L;
        Operator expectedOperator = Operator.builder()
                .idOperateur(id) // Définir un ID existant
                .fname("esprit")
                .lname("espritttt")
                .password("azzouz")
                .build();
        when(operatorRepository.save(expectedOperator)).thenReturn(expectedOperator);

        // When
        Operator savedOperator = service.addOperator(expectedOperator);

        // Then
        assertNotNull(savedOperator);
        assertNotNull(savedOperator.getIdOperateur());
        assertEquals(expectedOperator.getFname(), savedOperator.getFname());
        assertEquals(expectedOperator.getLname(), savedOperator.getLname());
        assertEquals(expectedOperator.getPassword(), savedOperator.getPassword());
    }

    @Test
    public void shouldUpdateOperatorWithSuccess() {
        // Given
        Long id = 1L;
        Operator expectedOperator = Operator.builder()
                .idOperateur(id) // Définir un ID existant
                .fname("esprit")
                .lname("espritttt")
                .password("azzouz")
                .build();
        when(operatorRepository.save(expectedOperator)).thenReturn(expectedOperator);

        // When
        Operator updatedOperator = service.updateOperator(expectedOperator);

        // Then
        assertNotNull(updatedOperator);
        assertEquals(expectedOperator.getIdOperateur(), updatedOperator.getIdOperateur());
        assertEquals(expectedOperator.getFname(), updatedOperator.getFname());
        assertEquals(expectedOperator.getLname(), updatedOperator.getLname());
        assertEquals(expectedOperator.getPassword(), updatedOperator.getPassword());
    }


    @Test(expected = Exception.class)
    public void shouldThrowEntityNotFoundException2() {

        Long operatorId = 4L;


        when(service.retrieveOperator(operatorId)).thenThrow(Exception.class);


        service.retrieveOperator(operatorId);


    }

    @Test
    public void shouldDeleteOperatorWithSuccess() {
        // Given
        Operator operator = new Operator();
        operator.setFname("Alice");
        operator.setLname("Wonderland");
        operator.setPassword("password");
        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator savedOperator = service.addOperator(operator);
        assertNotNull(savedOperator);

        // When
        service.deleteOperator(savedOperator.getIdOperateur());

        // Then
        assertThrows(NullPointerException.class, () -> service.retrieveOperator(savedOperator.getIdOperateur()));
    }

    @Test
    public void testRetrieveOperator() {
        // Given
        Long id = 1L;
        Operator expectedOperator = new Operator();
        expectedOperator.setIdOperateur(id);
        when(operatorRepository.findById(id)).thenReturn(Optional.of(expectedOperator));

        // When
        Operator retrievedOperator = service.retrieveOperator(id);

        // Then
        assertNotNull(retrievedOperator);
        assertEquals(expectedOperator, retrievedOperator);
    }

    @Test
    public void testRetrieveAllOperators() {
        // Given
        List<Operator> expectedOperators = new ArrayList<>();
        expectedOperators.add(new Operator());
        expectedOperators.add(new Operator());
        when(operatorRepository.findAll()).thenReturn(expectedOperators);

        // When
        List<Operator> retrievedOperators = service.retrieveAllOperators();

        // Then
        assertNotNull(retrievedOperators);
        assertEquals(expectedOperators.size(), retrievedOperators.size());
    }




}


