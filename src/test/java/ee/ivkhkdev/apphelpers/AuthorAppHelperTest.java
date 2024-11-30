package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthorAppHelperTest {
    @Mock
    private Input input;

    @InjectMocks
    private AuthorAppHelper authorAppHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldCreateAuthorSuccessfully() {
        // Arrange
        when(input.getString()).thenReturn("John", "Doe");

        // Act
        Optional<Author> result = authorAppHelper.create();

        // Assert
        assertTrue(result.isPresent());
        Author author = result.get();
        assertEquals("John", author.getAuthorName());
        assertEquals("Doe", author.getAuthorSurname());
    }

    @Test
    void create_shouldReturnEmptyOptionalOnException() {
        // Arrange
        when(input.getString()).thenThrow(new RuntimeException("Input error"));

        // Act
        Optional<Author> result = authorAppHelper.create();

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void update_shouldUpdateAuthorNameAndSurname() {
        // Arrange
        Author author = new Author();
        author.setAuthorName("Old Name");
        author.setAuthorSurname("Old Surname");

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        when(input.getString()).thenReturn("1", "y", "New Name", "y", "New Surname");

        // Act
        List<Author> updatedAuthors = authorAppHelper.update(authors);

        // Assert
        assertNotNull(updatedAuthors);
        assertEquals(1, updatedAuthors.size());
        assertEquals("New Name", updatedAuthors.get(0).getAuthorName());
        assertEquals("New Surname", updatedAuthors.get(0).getAuthorSurname());
    }

    @Test
    void update_shouldNotUpdateAuthorIfUserDeclinesChanges() {
        // Arrange
        Author author = new Author();
        author.setAuthorName("Old Name");
        author.setAuthorSurname("Old Surname");

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        when(input.getString()).thenReturn("1", "n", "n");

        // Act
        List<Author> updatedAuthors = authorAppHelper.update(authors);

        // Assert
        assertNotNull(updatedAuthors);
        assertEquals(1, updatedAuthors.size());
        assertEquals("Old Name", updatedAuthors.get(0).getAuthorName());
        assertEquals("Old Surname", updatedAuthors.get(0).getAuthorSurname());
    }

    @Test
    void update_shouldReturnNullOnException() {
        // Arrange
        when(input.getString()).thenThrow(new RuntimeException("Input error"));

        // Act
        List<Author> result = authorAppHelper.update(new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void printList_shouldPrintAllAuthors() {
        // Arrange
        Author author1 = new Author();
        author1.setAuthorName("John");
        author1.setAuthorSurname("Doe");

        Author author2 = new Author();
        author2.setAuthorName("Jane");
        author2.setAuthorSurname("Smith");

        List<Author> authors = List.of(author1, author2);

        // Act
        boolean result = authorAppHelper.printList(authors);

        // Assert
        assertTrue(result);
        // Verify that it iterates through the list (manual verification of printed output might be required).
    }

}