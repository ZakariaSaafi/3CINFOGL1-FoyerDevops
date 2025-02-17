package test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.BlocRepository;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;
import tn.esprit.spring.Services.Bloc.BlocService;
import tn.esprit.spring.Services.Universite.UniversiteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class TestBloc {
    @Mock
    private BlocRepository blocRepository;

    @Mock
    private ChambreRepository chambreRepository;

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private BlocService blocService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrUpdateBloc() {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");
        Chambre chambre1 = new Chambre();
        Chambre chambre2 = new Chambre();
        bloc.setChambres(Arrays.asList(chambre1, chambre2));

        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre1);

        // Act
        Bloc savedBloc = blocService.addOrUpdate(bloc);

        // Assert
        assertNotNull(savedBloc);
        assertEquals("Bloc A", savedBloc.getNomBloc());
        verify(blocRepository).save(any(Bloc.class));
        verify(chambreRepository, times(2)).save(any(Chambre.class));
    }

    @Test
    void testFindAllBlocs() {
        // Arrange
        List<Bloc> blocs = new ArrayList<>();
        blocs.add(new Bloc());
        blocs.add(new Bloc());
        when(blocRepository.findAll()).thenReturn(blocs);

        // Act
        List<Bloc> foundBlocs = blocService.findAll();

        // Assert
        assertNotNull(foundBlocs);
        assertEquals(2, foundBlocs.size());
        verify(blocRepository).findAll();
    }

    @Test
    void testFindBlocById() {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

        // Act
        Bloc foundBloc = blocService.findById(1L);

        // Assert
        assertNotNull(foundBloc);
        assertEquals(1L, foundBloc.getIdBloc());
        verify(blocRepository).findById(1L);
    }

    @Test
    void testDeleteBlocById() {
        // Arrange
        Long blocId = 1L;

        // Act
        blocService.deleteById(blocId);

        // Assert
        verify(blocRepository).deleteById(blocId);
    }

    @Test
    void testAffecterBlocAFoyer() {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");
        Foyer foyer = new Foyer();
        foyer.setNomFoyer("Foyer A");

        when(blocRepository.findByNomBloc("Bloc A")).thenReturn(bloc);
        when(foyerRepository.findByNomFoyer("Foyer A")).thenReturn(foyer);
        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);

        // Act
        Bloc updatedBloc = blocService.affecterBlocAFoyer("Bloc A", "Foyer A");

        // Assert
        assertNotNull(updatedBloc);
        assertEquals("Foyer A", updatedBloc.getFoyer().getNomFoyer());
        verify(blocRepository).findByNomBloc("Bloc A");
        verify(foyerRepository).findByNomFoyer("Foyer A");
        verify(blocRepository).save(any(Bloc.class));
    }

    @Test
    void testAffecterChambresABloc() {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");

        Chambre chambre1 = new Chambre();
        chambre1.setNumeroChambre(101L);
        Chambre chambre2 = new Chambre();
        chambre2.setNumeroChambre(102L);

        List<Long> numChambres = Arrays.asList(101L, 102L);
        when(blocRepository.findByNomBloc("Bloc A")).thenReturn(bloc);
        when(chambreRepository.findByNumeroChambre(101L)).thenReturn(chambre1);
        when(chambreRepository.findByNumeroChambre(102L)).thenReturn(chambre2);
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre1);

        // Act
        Bloc updatedBloc = blocService.affecterChambresABloc(numChambres, "Bloc A");

        // Assert
        assertNotNull(updatedBloc);
        assertEquals("Bloc A", chambre1.getBloc().getNomBloc());
        assertEquals("Bloc A", chambre2.getBloc().getNomBloc());
        verify(chambreRepository, times(2)).save(any(Chambre.class));
    }
}

