import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;
import tn.esprit.spring.Services.Universite.UniversiteService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestUniversiteService {

    private static final Logger logger = LogManager.getLogger(TestUniversiteService.class);

    @Mock
    private UniversiteRepository repo;

    @InjectMocks
    private UniversiteService universiteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddOrUpdate() {
        Universite u = new Universite();

        when(repo.save(u)).thenReturn(u);

        Universite result = universiteService.addOrUpdate(u);

        assertNotNull(result);
        logger.info("testAddOrUpdate passed");
    }

    @Test
    public void testFindAll() {
        Universite u1 = new Universite();
        Universite u2 = new Universite();

        when(repo.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Universite> universites = universiteService.findAll();

        assertNotNull(universites);
        assertEquals(2, universites.size());
        logger.info("testFindAll passed");
    }

    @Test
    public void testFindById() {
        Universite u = new Universite();

        when(repo.findById(1L)).thenReturn(Optional.of(u));

        Universite result = universiteService.findById(1L);

        assertNotNull(result);
        logger.info("testFindById passed");
    }

    @Test
    public void testDeleteById() {
        doNothing().when(repo).deleteById(1L);

        universiteService.deleteById(1L);

        verify(repo, times(1)).deleteById(1L);
        logger.info("testDeleteById passed");
    }

    @Test
    public void testDelete() {
        Universite u = new Universite();

        doNothing().when(repo).delete(u);

        universiteService.delete(u);

        verify(repo, times(1)).delete(u);
        logger.info("testDelete passed");
    }
}
