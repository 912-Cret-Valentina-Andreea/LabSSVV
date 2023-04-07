package service;

import org.example.console.UI;
import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssignmentTest {
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
    void addTemaIdEmpty() {
        var x=service.saveTema("", "descriere", 10, 2);
        assertEquals(1,x);
    }

    @Test
    void addTemaIdNull() {
        var x=service.saveTema(null, "descriere", 10, 2);
        assertEquals(1,x);
    }

    @Test
    void addTemaDescriereEmpty() {
        var x=service.saveTema("103", "", 10, 2);
        assertEquals(1,x);
    }

    @Test
    void addTemaWrongDeadline()
    {
        var x=service.saveTema("100", "asc", 0, 2);
        assertEquals(1,x);
        var y=service.saveTema("100", "asc", -3, 2);
        assertEquals(1,y);
        var z=service.saveTema("100", "asc", 16, 2);
        assertEquals(1,z);
    }

    @Test
    void addTemaWrongStartline()
    {
        var x=service.saveTema("100", "asc", 0, 16);
        assertEquals(1,x);
        var y=service.saveTema("100", "asc", 2, -3);
        assertEquals(1,y);
        var z=service.saveTema("100", "asc", 1, 2);
        assertEquals(1,z);
    }


}
