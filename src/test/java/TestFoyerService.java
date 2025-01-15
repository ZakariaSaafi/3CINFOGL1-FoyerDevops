import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Entities.Universite;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IFoyerServiceTest {

    @Mock
    private IFoyerService foyerService; // Simule l'interface

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    @Test
    void testAddOrUpdate() {
        // Simuler un foyer
        Foyer foyer = new Foyer(1L, "Foyer1", "Adresse1");
        when(foyerService.addOrUpdate(any(Foyer.class))).thenReturn(foyer);

        // Appel de la méthode
        Foyer result = foyerService.addOrUpdate(foyer);

        // Vérifications
        assertNotNull(result);
        assertEquals("Foyer1", result.getNom());
        verify(foyerService, times(1)).addOrUpdate(foyer);
    }

    @Test
    void testFindAll() {
        // Simuler la liste des foyers
        List<Foyer> foyers = Arrays.asList(
                new Foyer(1L, "Foyer1", "Adresse1"),
                new Foyer(2L, "Foyer2", "Adresse2"));
        when(foyerService.findAll()).thenReturn(foyers);

        // Appel de la méthode
        List<Foyer> result = foyerService.findAll();

        // Vérifications
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Foyer1", result.get(0).getNom());
        verify(foyerService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Simuler un foyer
        Foyer foyer = new Foyer(1L, "Foyer1", "Adresse1");
        when(foyerService.findById(1L)).thenReturn(foyer);

        // Appel de la méthode
        Foyer result = foyerService.findById(1L);

        // Vérifications
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(foyerService, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        // Appel de la méthode
        foyerService.deleteById(1L);

        // Vérifications
        verify(foyerService, times(1)).deleteById(1L);
    }

    @Test
    void testDelete() {
        // Simuler un foyer
        Foyer foyer = new Foyer(1L, "Foyer1", "Adresse1");

        // Appel de la méthode
        foyerService.delete(foyer);

        // Vérifications
        verify(foyerService, times(1)).delete(foyer);
    }

    @Test
    void testAffecterFoyerAUniversite() {
        // Simuler une université
        Universite universite = new Universite(1L, "Universite1");
        when(foyerService.affecterFoyerAUniversite(1L, "Universite1")).thenReturn(universite);

        // Appel de la méthode
        Universite result = foyerService.affecterFoyerAUniversite(1L, "Universite1");

        // Vérifications
        assertNotNull(result);
        assertEquals("Universite1", result.getNom());
        verify(foyerService, times(1)).affecterFoyerAUniversite(1L, "Universite1");
    }

    @Test
    void testDesaffecterFoyerAUniversite() {
        // Simuler une université
        Universite universite = new Universite(1L, "Universite1");
        when(foyerService.desaffecterFoyerAUniversite(1L)).thenReturn(universite);

        // Appel de la méthode
        Universite result = foyerService.desaffecterFoyerAUniversite(1L);

        // Vérifications
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(foyerService, times(1)).desaffecterFoyerAUniversite(1L);
    }

    @Test
    void testAjouterFoyerEtAffecterAUniversite() {
        // Simuler un foyer et une université
        Foyer foyer = new Foyer(1L, "Foyer1", "Adresse1");
        when(foyerService.ajouterFoyerEtAffecterAUniversite(foyer, 1L)).thenReturn(foyer);

        // Appel de la méthode
        Foyer result = foyerService.ajouterFoyerEtAffecterAUniversite(foyer, 1L);

        // Vérifications
        assertNotNull(result);
        assertEquals("Foyer1", result.getNom());
        verify(foyerService, times(1)).ajouterFoyerEtAffecterAUniversite(foyer, 1L);
    }

    @Test
    void testAjoutFoyerEtBlocs() {
        // Simuler un foyer
        Foyer foyer = new Foyer(1L, "Foyer1", "Adresse1");
        when(foyerService.ajoutFoyerEtBlocs(foyer)).thenReturn(foyer);

        // Appel de la méthode
        Foyer result = foyerService.ajoutFoyerEtBlocs(foyer);

        // Vérifications
        assertNotNull(result);
        assertEquals("Foyer1", result.getNom());
        verify(foyerService, times(1)).ajoutFoyerEtBlocs(foyer);
    }
}
