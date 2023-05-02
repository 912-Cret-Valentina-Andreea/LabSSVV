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

public class IntegratingTest {
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
    void addStudent() {
        var x=service.saveStudent("12", "Alex", 932);
        assertEquals(1,x);
    }
    @Test
    void addAssignment() {
        var x=service.saveTema("12", "descriere", 10, 5);
        assertEquals(1,x);
    }
    @Test
    void addNota() {
        var x=service.saveNota("12", "12", 9, 7, "e bine");
        assertEquals(-1,x);
    }

    @Test
    void addAll(){
        var x=service.saveStudent("13", "Alex", 932);
        var y=service.saveTema("13", "descriere", 10, 5);
        var z=service.saveNota("13", "13", 9, 7, "e bine");
        assertEquals(1,z);
    }

}
