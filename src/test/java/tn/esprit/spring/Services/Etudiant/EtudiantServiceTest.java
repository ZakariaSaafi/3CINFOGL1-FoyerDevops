package tn.esprit.spring.Services.Etudiant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceTest {

    @InjectMocks
    private EtudiantService etudiantService; // Service à tester

    @Mock
    private EtudiantRepository etudiantRepository; // Dépôt simulé

    private Etudiant mockEtudiant; // Objet simulé pour les tests

    @BeforeEach
    void setUp() {
        // Initialisation des mocks
        MockitoAnnotations.openMocks(this);

        // Création d'un étudiant simulé
        mockEtudiant = new Etudiant();
        mockEtudiant.setIdEtudiant(1L);  // Assurez-vous que setIdEtudiant existe
        mockEtudiant.setNomEt("Doe");
        mockEtudiant.setPrenomEt("John");
    }

    @Test
    void testAddOrUpdate() {
        // Configurer le comportement du mock pour save
        when(etudiantRepository.save(mockEtudiant)).thenReturn(mockEtudiant);

        // Appeler la méthode à tester
        Etudiant result = etudiantService.addOrUpdate(mockEtudiant);

        // Vérifications
        assertNotNull(result);
        assertEquals(mockEtudiant.getIdEtudiant(), result.getIdEtudiant());
        assertEquals(mockEtudiant.getNomEt(), result.getNomEt());
        assertEquals(mockEtudiant.getPrenomEt(), result.getPrenomEt());

        // Vérifier si la méthode save() a été appelée une seule fois
        verify(etudiantRepository, times(1)).save(mockEtudiant);
    }

    @Test
    void testFindAll() {
        // Préparer des données simulées
        List<Etudiant> mockList = Arrays.asList(mockEtudiant);

        // Configurer le comportement du mock pour findAll
        when(etudiantRepository.findAll()).thenReturn(mockList);

        // Appeler la méthode à tester
        List<Etudiant> result = etudiantService.findAll();

        // Vérifications
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockEtudiant, result.get(0));

        // Vérifier si la méthode findAll() a été appelée une seule fois
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Configurer le comportement du mock pour findById
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(mockEtudiant));

        // Appeler la méthode à tester
        Etudiant result = etudiantService.findById(1L);

        // Vérifications
        assertNotNull(result);
        assertEquals(mockEtudiant.getIdEtudiant(), result.getIdEtudiant());

        // Vérifier si la méthode findById() a été appelée une seule fois
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        // Appeler la méthode à tester
        etudiantService.deleteById(1L);

        // Vérifier si la méthode deleteById() a été appelée une seule fois
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete() {
        // Appeler la méthode à tester
        etudiantService.delete(mockEtudiant);

        // Vérifier si la méthode delete() a été appelée une seule fois
        verify(etudiantRepository, times(1)).delete(mockEtudiant);
    }
}
