import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;
import tn.esprit.spring.Services.Universite.UniversiteService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestUniversiteService {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteService universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUniversite() {
        // Arrange
        Universite universite = Universite.builder()
                .nomUniversite("ESPRIT")
                .adresse("Ghazela")
                .build();

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        // Act
        Universite saved = universiteService.addOrUpdate(universite);

        // Assert
        assertNotNull(saved);
        assertEquals("ESPRIT", saved.getNomUniversite());
        assertEquals("Ghazela", saved.getAdresse());
        verify(universiteRepository).save(any(Universite.class));
    }

    @Test
    void testGetUniversiteById() {
        // Arrange
        Universite universite = Universite.builder()
                .idUniversite(1L)
                .nomUniversite("ESPRIT")
                .adresse("Ghazela")
                .build();

        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        // Act
        Universite found = universiteService.findById(1L);

        // Assert
        assertNotNull(found);
        assertEquals(1L, found.getIdUniversite());
        assertEquals("ESPRIT", found.getNomUniversite());
        assertEquals("Ghazela", found.getAdresse());
    }

    @Test
    void testDeleteUniversite() {
        // Arrange
        Long universiteId = 1L;

        // Act
        universiteService.deleteById(universiteId);

        // Assert
        verify(universiteRepository).deleteById(universiteId);
    }

    @Test
    void testUpdateUniversite() {
        // Arrange
        Universite universite = Universite.builder()
                .idUniversite(1L)
                .nomUniversite("ESPRIT Updated")
                .adresse("Ghazela Updated")
                .build();

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        // Act
        Universite updated = universiteService.addOrUpdate(universite);

        // Assert
        assertNotNull(updated);
        assertEquals("ESPRIT Updated", updated.getNomUniversite());
        assertEquals("Ghazela Updated", updated.getAdresse());
        verify(universiteRepository).save(any(Universite.class));
    }

    @Test
    void testAddUniversiteWithFoyer() {
        // Arrange
        Foyer foyer = Foyer.builder()
                .nomFoyer("Foyer ESPRIT")
                .capaciteFoyer(500)
                .build();

        Universite universite = Universite.builder()
                .nomUniversite("ESPRIT")
                .adresse("Ghazela")
                .foyer(foyer)
                .build();

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        // Act
        Universite saved = universiteService.addOrUpdate(universite);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getFoyer());
        assertEquals("Foyer ESPRIT", saved.getFoyer().getNomFoyer());
        verify(universiteRepository).save(any(Universite.class));
    }
}