package service;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.Test;

//import static junit.framework.TestCase.assertEquals;

public class StudentTest {
    Service service;
    @BeforeEach
    void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    void addStudentIdEmpty() {
        var x=service.saveStudent("", "nume", 934);
        assertEquals(1,x);
    }
    @Test
    void addStudentIdNull() {
        var x=service.saveStudent(null, "nume", 934);
        assertEquals(1,x);
    }
    @Test
    void addStudentNumeEmpty() {
        var x=service.saveStudent("321", "", 934);
        assertEquals(1,x);
    }
    @Test
    void addStudentNumeNull() {
        var x=service.saveStudent("321", null, 934);
        assertEquals(1,x);
    }
    @Test
    void addStudentGrupaMin() {
        var x=service.saveStudent("321", "nume", 84);
        assertEquals(1,x);
    }
    @Test
    void addStudentGrupaMax() {
        var x=service.saveStudent("", "nume", 950);
        assertEquals(1,x);
    }
}
