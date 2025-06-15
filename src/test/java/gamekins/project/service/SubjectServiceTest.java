package gamekins.project.service;

import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.SubjectDTO;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private SubjectService subjectService;

    private Subject subject1;
    private Subject subject2;

    @BeforeEach
    void setUp() {
        subject1 = new Subject();
        subject1.setId(1L);
        subject1.setName("Matemática");

        subject2 = new Subject();
        subject2.setId(2L);
        subject2.setName("História");
    }

    @Test
    @DisplayName("Deve retornar uma lista de SubjectDTOs quando houver disciplinas")
    void findAll_ShouldReturnListOfSubjectDTOs_WhenSubjectsExist() {
        // Arranjo (Arrange)
        List<Subject> subjects = Arrays.asList(subject1, subject2);
        when(subjectRepository.findAll()).thenReturn(subjects);

        // Ação (Act)
        List<SubjectDTO> result = subjectService.findAll();

        // Assertiva (Assert)
        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(subject1.getId(), result.get(0).getId());
        assertEquals(subject1.getName(), result.get(0).getName());

        assertEquals(subject2.getId(), result.get(1).getId());
        assertEquals(subject2.getName(), result.get(1).getName());

        verify(subjectRepository, times(1)).findAll();
    }

}