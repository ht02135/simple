package simple.packages.stream;

import java.util.List;
import java.util.stream.Collectors;

import simple.packages.Instructor;

public class EnhancedInstructor extends Instructor {
	
	// Source >> Generate Constructor from SuerClass
	
	public EnhancedInstructor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnhancedInstructor(Instructor instructor) {
		super(instructor);
		// TODO Auto-generated constructor stub
	}

	public EnhancedInstructor(String name, int yearsOfExperience, String title, String gender, boolean onlineCourses,
			List<String> courses) {
		super(name, yearsOfExperience, title, gender, onlineCourses, courses);
		// TODO Auto-generated constructor stub
	}
	
	/*
	create this class using New >> Class
    Package=simplePackage.stream
    Name=EnhancedInstructor
    SuperClass=simplePackage.Instructor <- you type Instructor to show dropdown
	*/
	
	//try Source >> Overrid/Implement Method
	/*
	steps
	1>select EnhancedInstructor
	2>Source >> Overrid/Implement Method
	3>select getCourses() and below is generated....
	*/

	@Override
	public List<String> getCourses() {
		// TODO Auto-generated method stub
		return super.getCourses().stream().map((s) -> { return s+" is enhenced"; }).collect(Collectors.toList());
	}
	
}
