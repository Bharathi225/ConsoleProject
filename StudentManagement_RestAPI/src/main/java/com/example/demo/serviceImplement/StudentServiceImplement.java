package com.example.demo.serviceImplement;

import java.util.List;
//import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.StudentServiceInterface.StudentService;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.jpaRepository.StudentRepo;
import com.example.demo.studentClass.Students;


/* @Service is used to denote a class to perform unimplemented interface method */
@Service
public class StudentServiceImplement implements StudentService{

	StudentRepo studentRepo;

	public StudentServiceImplement(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}

	public Students saveStudent(Students student) {
		return studentRepo.save(student);
	}

	/* This method is used for display all student details from db by using findall() method*/
	@Override
	public List<Students> getStudentsFromDb() {
		return studentRepo.findAll();
	}

	/* When we give studentId to findbyId method then it finds that particular id record is present or not
	 * If it is present then it will display that record, otherwise it will returns null */
	@Override
	public Students getStudentById(int studentId) {
		return studentRepo.findById(studentId).orElseThrow(() -> 
							new ResourceNotFoundException("Student", "Id", studentId));
	}


	/* When we give studentId to findbyId method then it finds that particular id record is present or not
	 * If it is present, it will get new value for update that then it will set that new value for that attribute,
	 *  otherwise it will returns null */
	@Override
	public Students updateStudentDetails(Students newVal, int studentId) {
		Students student = studentRepo.findById(studentId).orElseThrow(
				() -> new ResourceNotFoundException("Student", "Id", studentId));
			student.setStudentName(newVal.getStudentName());
			student.setContactNo(newVal.getContactNo());
			studentRepo.save(student);
			return student;
	}

	/* When we give studentId to findbyId method then it finds that particular id record is present or not
	 * If it is present then it will delete that record, otherwise it will print not found */
	@Override
	public void deleteStudentById(int studentId) {
		studentRepo.findById(studentId).orElseThrow(
				() -> new ResourceNotFoundException("Student", "Id", studentId));
		studentRepo.deleteById(studentId);
	}
}












//@Override
//public Students getStudentById(int studentId) {
//	Optional<Students> student = studentRepo.findById(studentId);   //Class obj / Object obj
//	if(student.isPresent()) {
//		return student.get();
//	}
//	else {
//		throw new ResourceNotFoundException("Employee", "Id", id);
//	}


//@Override
//public Students updateStudentDetails(Students newVal, int studentId) {
//Optional<Students> student = studentRepo.findById(studentId);
//if(student.isPresent()) {
//Students existingStudent = student.get();  //'2', 'mny@123', 'abc', 'xyz'
//existingStudent.setStudentName(newVal.getStudentName());
//existingStudent.setContactNo(newVal.getContactNo());
//studentRepo.save(existingStudent);
//return existingStudent;
//}
//}

//@Override
//public void deleteStudentById(int studentId) {
////	Optional<Students> student = studentRepo.findById(studentId);
////	if(student.isPresent()) {
////		studentRepo.deleteById(studentId);
////	}	
//}
