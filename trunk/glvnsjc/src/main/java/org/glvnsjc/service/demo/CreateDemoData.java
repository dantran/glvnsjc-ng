package org.glvnsjc.service.demo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.glvnsjc.model.domain.Branch;
import org.glvnsjc.model.domain.Course;
import org.glvnsjc.model.domain.Instructor;
import org.glvnsjc.model.domain.School;
import org.glvnsjc.model.domain.Term;
import org.glvnsjc.model.domain.User;
import org.glvnsjc.service.GenericNameManager;
import org.glvnsjc.service.NameExistsException;
import org.glvnsjc.service.RoleManager;
import org.glvnsjc.service.UserManager;
import org.springframework.stereotype.Component;

@Component
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
    
    @PostConstruct
    public void init()
        throws NameExistsException
    {
        //Setup school infrastructure
        
        School school = new School();
        school.setName( "GLVNSJC" );
        school.setDescription( "Giao Ly Viet Ngu at Sanjose" );
        school = schoolManager.save( school );

        Instructor instructor = new Instructor();
        instructor.setName( "MeMe" );
        instructor = (Instructor) userManager.saveUser( instructor );
        

        Branch branch = new Branch();
        branch.setName( "PC" );
        branch.setDescription( "St. Patrick PM" );
        branch.setParentSchool( school );
        branch.addInstructor( instructor ); 
        branch = branchManager.save( branch );
        school.addBranch( branch );

        Course course = new Course();
        course.setName( "GL1" );
        course.setSchool( school );

        school.addCourse( this.courseManager.save( course ) );

        //another course
        course.setName( "VN1" );
        school.addCourse( this.courseManager.save( course ) );

        school = schoolManager.save( school );
        
        //now deal with a new academic term
        Term term = new Term();
        term.setName( "term1" );
        term = termManager.save( term );
        
        school.addTerm( term );
        school = schoolManager.save( school );
        
    }
}
