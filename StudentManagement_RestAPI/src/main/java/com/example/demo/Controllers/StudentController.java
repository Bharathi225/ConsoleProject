package com.example.demo.Controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.serviceImplement.StudentServiceImplement;
import com.example.demo.studentClass.Students;

/* @RestController annotation is applied to a class to mark it as a request handler. 
 * This annotation itself annotated with @Controller and @ResponseBody. 
 * @Controller is used for mapping
 * @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON 
 * and passed back into the HttpResponse object. */
@RestController
@RequestMapping("/studentManagement")
public class StudentController {

		public StudentServiceImplement studentServ;
		
		public StudentController(StudentServiceImplement studentServ) {
			this.studentServ = studentServ;
		}
		
		/* By using post mapping annotation, transfer data from client to server in HTTP protocol.
		 * POST carries request parameter in message body which makes it more secure
		 * way of transferring data from client to server.*/
		@PostMapping("/saveStudent")
		public ResponseEntity<Students> saveStudent(@RequestBody Students student){
			return new ResponseEntity<Students>(studentServ.saveStudent(student), HttpStatus.CREATED);
		}
		
		/* This method is used to update/modify the resource 
		 * so @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.*/
		@PutMapping("/updateStudent/{studentId}")
		public ResponseEntity<Students> updateStudentDetailsById(@PathVariable("studentId") int id,
				@RequestBody Students student){
			return new ResponseEntity<Students>(studentServ.updateStudentDetails(student, id), HttpStatus.OK);
			
		}
		

		/* By using get mapping annotation, transfer data from client to server in HTTP protocol.
		 * It is used to get a single or multiple resources
		 * It carries request parameter appended in URL string */
		@GetMapping("/readAllStudents")
		public List<Students> getAllStudents(){
			return studentServ.getStudentsFromDb();
		}
		@GetMapping("/readAStudent/{studentId}")
		public ResponseEntity<Students>  getStudentById(@PathVariable("studentId")  int id){
			return new ResponseEntity<Students>(studentServ.getStudentById(id), HttpStatus.OK);
		}
		
		/* The Delete HTTP method is used to delete the resource and @DeleteMapping annotation for mapping 
		 * HTTP DELETE requests onto specific handler methods.*/
		@DeleteMapping("/deleteStudent/{studentId}")
		public ResponseEntity<String> deleteStudentById(@PathVariable("studentId")  int id){
			studentServ.deleteStudentById(id);
			return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
		}
}
