package org.glvnsjc.service.demo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.glvnsjc.model.domain.Branch;
import org.glvnsjc.model.domain.Clazz;
import org.glvnsjc.model.domain.Course;
import org.glvnsjc.model.domain.Instructor;
import org.glvnsjc.model.domain.School;
import org.glvnsjc.model.domain.Student;
import org.glvnsjc.model.domain.Term;
import org.glvnsjc.service.GenericNameManager;
import org.glvnsjc.service.NameExistsException;
import org.glvnsjc.service.RoleManager;
import org.glvnsjc.service.UserManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope( "conversation.access")
public class CreateDemoData
{
    @Resource
    private UserManager userManager;

    @Resource
    private RoleManager roleManager;

    @Resource
    private GenericNameManager<School, Long> schoolManager;

    @Resource
    private GenericNameManager<Branch, Long> branchManager;

    @Resource
    private GenericNameManager<Course, Long> courseManager;

    @Resource
    private GenericNameManager<Term, Long> termManager;
    
    @Resource
    private GenericNameManager<Clazz, Long> classManager;

    @PostConstruct
    public void init()
        throws NameExistsException
    {
        //Setup school infrastructure
        
        School school = new School();
        school.setName( "GLVNSJC" );
        school.setDescription( "Giao Ly Viet Ngu at Sanjose" );
        school = schoolManager.save( school );

        Instructor instructor1 = new Instructor();
        instructor1.setName( "instructor 1" );
        instructor1 = (Instructor) userManager.saveUser( instructor1 );
        
        Instructor instructor2 = new Instructor();
        instructor2.setName( "instructor 2" );
        instructor2 = (Instructor) userManager.saveUser( instructor2 );

        //parent school has on instructor
        school.addInstructor( instructor1 );
        
        //branch PC has on instructor
        School branch = new School();
        branch.setName( "PC" );
        branch.setDescription( "St. Patrick PM" );
        branch.setParentSchool( school );
        branch.addInstructor( instructor1 ); 
        branch.setParentSchool( school );
        school.addBranch( schoolManager.save( branch ) );
        
        //branch PS has 2 instructors, with one belong to the other 2
        branch.setName( "PS" );
        branch.setDescription( "St. Patrick AM" );
        branch.addInstructor( instructor2 );
        school.addBranch( schoolManager.save( branch ) );
        
        Course course = new Course();
        course.setName( "GL1" );
        course.setSchool( school );

        school.addCourse( this.courseManager.save( course ) );
        
        //another course, note course itself is not managed by entity manager yet
        course.setName( "VN1" );
        school.addCourse( this.courseManager.save( course ) );

        school = schoolManager.save( school );
        
        //now deal with a new academic term
        
        Term term = new Term();
        term.setName( "term1" );
        term.setSchool( school );
        term = termManager.save( term );
        
        school.addTerm( term );
        school = schoolManager.save( school );
        
        Clazz clazz = new Clazz();
        clazz.setName( "A" );
        clazz.setDescription( "GL1 A at PC" );
        clazz.setTerm( school.getCurrentTerm() );
        clazz.setCourse( courseManager.getByName( "GL1" ) );
        clazz.setSchool( schoolManager.getByName( "PC" ) );
        clazz.addInstructor( instructor1 );
        
        classManager.save( clazz );
        
        clazz.setName( "B" );
        clazz.setDescription( "GL1 B at PC" );
        clazz.addInstructor( instructor2 );
        classManager.save( clazz );
        
        clazz.setName( "C" );
        clazz.setDescription( "GL1 C at PS" );
        clazz.setSchool( schoolManager.getByName( "PS" ) );
        classManager.save( clazz );
        
        //now assign students into class
        
        Student student = new Student();
        student.setName( "GL1A student" );
        student = (Student)userManager.saveUser( student );
        
        clazz = classManager.getByName( "A" );
        clazz.addStudent( student );
        classManager.save( clazz );
        
        student = new Student();
        student.setName( "GL1B student" );
        student = (Student) userManager.saveUser( student );
        
        
        clazz = classManager.getByName( "B" );
        clazz.addStudent( student );
        classManager.save( clazz );        
        
        
        school.validate();
        
    }
}
