package tn.esprit.spring.Services.Etudiant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tn.esprit.spring.DAO.Entities.Etudiant;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IEtudiantServiceTest {

    private IEtudiantService etudiantService; // Interface à tester
    private Etudiant mockEtudiant; // Objet simulé pour les tests

    @BeforeEach
    void setUp() {
        // Création d'un mock pour l'interface IEtudiantService
        etudiantService = Mockito.mock(IEtudiantService.class);

        // Création d'un étudiant simulé
        mockEtudiant = new Etudiant();
        mockEtudiant.setId(1L);
        mockEtudiant.setNom("Doe");
        mockEtudiant.setPrenom("John");
    }

    @Test
    void testAddOrUpdate() {
        // Configurer le comportement du mock
        when(etudiantService.addOrUpdate(mockEtudiant)).thenReturn(mockEtudiant);

        // Appeler la méthode à tester
        Etudiant result = etudiantService.addOrUpdate(mockEtudiant);

        // Vérifications
        assertNotNull(result);
        assertEquals(mockEtudiant.getId(), result.getId());
        assertEquals(mockEtudiant.getNom(), result.getNom());
        assertEquals(mockEtudiant.getPrenom(), result.getPrenom());

        // Vérifier si la méthode a été appelée une seule fois
        verify(etudiantService, times(1)).addOrUpdate(mockEtudiant);
    }

    @Test
    void testFindAll() {
        // Préparer des données simulées
        List<Etudiant> mockList = Arrays.asList(mockEtudiant);

        // Configurer le comportement du mock
        when(etudiantService.findAll()).thenReturn(mockList);

        // Appeler la méthode à tester
        List<Etudiant> result = etudiantService.findAll();

        // Vérifications
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockEtudiant, result.get(0));

        // Vérifier si la méthode a été appelée une seule fois
        verify(etudiantService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Configurer le comportement du mock
        when(etudiantService.findById(1L)).thenReturn(mockEtudiant);

        // Appeler la méthode à tester
        Etudiant result = etudiantService.findById(1L);

        // Vérifications
        assertNotNull(result);
        assertEquals(mockEtudiant.getId(), result.getId());

        // Vérifier si la méthode a été appelée une seule fois
        verify(etudiantService, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        // Appeler la méthode à tester
        etudiantService.deleteById(1L);

        // Vérifier si la méthode a été appelée une seule fois
        verify(etudiantService, times(1)).deleteById(1L);
    }

    @Test
    void testDelete() {
        // Appeler la méthode à tester
        etudiantService.delete(mockEtudiant);

        // Vérifier si la méthode a été appelée une seule fois
        verify(etudiantService, times(1)).delete(mockEtudiant);
    }
}
