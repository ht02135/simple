package simple.packages.stream;

import java.util.Arrays;
import java.util.List;

import simple.packages.Instructor;
import simple.packages.Instructors;

public class EnhancedInstructors extends Instructors {

	public static List<Instructor> getAll(){
		EnhancedInstructor instructor1 = new EnhancedInstructor("Mike", 10, "Software Developer"
         , "M", true, Arrays.asList("Java Programming", "C++ Programming", "Python Programming"));

		EnhancedInstructor instructor2 = new EnhancedInstructor("Jenny", 5, "Engineer"
                , "F", false, Arrays.asList("Multi-Threaded Programming", "CI/CD", "Unit Testing"));

		EnhancedInstructor instructor4 = new EnhancedInstructor("Anthony", 15, "Senior Developer"
                , "M", true, Arrays.asList("Java Programming", "Angular Programming", "React Native"));

		EnhancedInstructor instructor5 = new EnhancedInstructor("Syed", 15, "Principal Engineer"
                , "M", true, Arrays.asList("Java Programming", "Java Multi-Threaded Programming", "React Native"));

        List<Instructor> list = Arrays.asList(instructor1,instructor2,instructor4,instructor5);
        return list;
    }
	
}
