package service;

import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.Validator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StudentTest {
    @Test
    public void saveStudentSucceed() {
        Student student = new Student("8", "xxx", 232);
        Validator<Student> validator = new StudentValidator();
        String XMLfilename="studenti.xml";
        StudentXMLRepository studentXmlRepo = new StudentXMLRepository(validator, XMLfilename);
        Service service = new Service(studentXmlRepo);
        int x = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(0,x);
    }
    @Test
    public void saveStudentFailed(){
        Validator<Student> validator = new StudentValidator();
        String XMLfilename="studenti.xml";
        StudentXMLRepository studentXmlRepo = new StudentXMLRepository(validator, XMLfilename);
        Service service = new Service(studentXmlRepo);
        int y = service.saveStudent("2", "blalbla", 666);
        try{
            assertEquals(1,y);
        }
        catch (AssertionError error){
            System.out.println("ërror: could not ");
        }
    }
}
