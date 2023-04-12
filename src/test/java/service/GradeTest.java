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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GradeTest {
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
    void addNotaIdEmpty1() {
        var x=service.saveNota("", "231", 10, 2, "ok");
        assertEquals(-1,x);
    }

    @Test
    void addNotaIdNull1() {
        boolean thrown = false;
        try {
            var x = service.saveNota(null, "231", 10, 2, "ok");
        }catch(IllegalArgumentException ex){
            thrown = true;
        }
        assertTrue(thrown);
    }


    @Test
    void addNotaIdEmpty2() {
        var x=service.saveNota("321", "", 10, 2, "ok");
        assertEquals(-1,x);
    }

    @Test
    void addNotaIdNull2() {
        var x=service.saveNota("321", null, 10, 2, "ok");
        assertEquals(-1,x);
    }

    @Test
    void addNotaMin(){
        var x=service.saveNota("321", "123", -4, 2, "ok");
        assertEquals(-1,x);
    }

    @Test
    void addNotaMax(){
        var x=service.saveNota("321", "123", 12, 2, "ok");
        assertEquals(-1,x);
    }

    @Test
    void saptPredareInvalida(){
        var x=service.saveNota("321", null, 12, -2, "ok");
        assertEquals(-1,x);
    }
}
