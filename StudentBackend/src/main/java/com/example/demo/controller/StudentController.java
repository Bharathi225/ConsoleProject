package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

/* @RestController annotation is applied to a class to mark it as a request handler. 
 * This annotation itself annotated with @Controller and @ResponseBody. 
 * @Controller is used for mapping
 * @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON 
 * and passed back into the HttpResponse object. */

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/studentDetails/v1/")
public class StudentController {

	@Autowired
	private StudentRepository studRepo;
	
	// get all Students
	@GetMapping("/getStudent")
	public List<Student> getAllStudents(){
		return studRepo.findAll();
	}		
	
	// create Student rest api
	@PostMapping("/saveStudent")
	public Student createStudent(@RequestBody Student student) {
		return studRepo.save(student);
	}
	
	// get Student by id rest api
	@GetMapping("/getOneStudent/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
		Student student = studRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
		return ResponseEntity.ok(student);
	}
	
	// update Student rest api
	
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student studentDetails){
		Student student = studRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
		
		student.setName(studentDetails.getName());
		student.setStandard(studentDetails.getStandard());
		student.setContact(studentDetails.getContact());
		
		Student updatedStudent = studRepo.save(student);
		return ResponseEntity.ok(updatedStudent);
	}
	
	// delete Student rest api
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Integer id){
		Student student = studRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
		
		studRepo.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}