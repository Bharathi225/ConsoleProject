package com.example.demo.StudentServiceInterface;

import java.util.List;
import com.example.demo.studentClass.Students;

/* Student service interface have only method declaration and 
 * it implements in student service implementation class */
public interface StudentService {
	Students saveStudent(Students student);
	List<Students>  getStudentsFromDb();
	Students getStudentById(int studentId);
	Students updateStudentDetails(Students student, int studentId);
	void deleteStudentById(int studentId);
}
