package simple.packages;

import java.util.List;

@FunctionalInterface
public interface InstructorFactory {
    Instructor getInstructor(String name, int yearsOfExperience, String title,
    		String gender, boolean onlineCourse, List<String> courses);
}
