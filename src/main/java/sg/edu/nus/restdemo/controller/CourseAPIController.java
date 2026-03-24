package sg.edu.nus.restdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.restdemo.model.Course;
import sg.edu.nus.restdemo.service.CourseService;

@CrossOrigin(origins = "http://localhost:4200") // for angular
@RequestMapping("/api/courses")
@RestController
public class CourseAPIController {
	
	@Autowired
	private CourseService cSvc;
	
	@GetMapping("/active")
	public ResponseEntity<List<Course>> displayActiveCourses() {
		System.out.println(">>> HIT GET /api/courses/active");
		List<Course> courses = cSvc.getActiveCourses();
		// System.out.println(">>> Active courses: " + courses);
		if (courses == null || courses.size() <= 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(courses);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> findCourseById(@PathVariable Long id) {
		System.out.println(">>> HIT GET /api/courses/" + id);
		if (id == null || id <= 0)
			return ResponseEntity.badRequest().build(); // 404
		return ResponseEntity.of(cSvc.findCourseById(id)); // 404 if empty
	}

	@PostMapping("/add")
	public ResponseEntity<Course> addCourse(@ModelAttribute Course course) { // @ModelAttribute for form data
		System.out.println(">>> HIT POST /api/courses/add");
		if (course == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.status(HttpStatus.CREATED).body(cSvc.saveCourse(course));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id,
		@RequestBody Course newCourse) { // @RequestBody for JSON data
		System.out.println(">>> HIT PUT /api/courses/update/" + id);
		System.out.println(">>> New course: " + newCourse);
		
		// if data received is invalid, send 400 status
		if (newCourse == null || id == null || id <= 0)
			return ResponseEntity.badRequest().build();
		
		// retrieve optional and check whether empty
		Optional<Course> optCourse = cSvc.findCourseById(id);
		if (cSvc.findCourseById(id).isEmpty())
			return ResponseEntity.notFound().build();
		
		// find existing course obj
		Course existCourse = optCourse.get();
		
		// set variables
		// skip default vars like ID and created date
		/*
			this.courseName = courseName;
			this.instructor = instructor;
			this.launchYear = launchYear;
			this.companyName = companyName;
			this.leadContact = leadContact;
			this.courseFees = courseFees;
			this.courseDesc = courseDesc;
			this.startDate = startDate;
			this.createdOn = createdOn;
			this.active=active;
		*/
		if (newCourse.getCourseName() == null)
			existCourse.setCourseName(newCourse.getCourseName());
		if (newCourse.getInstructor() == null)
			existCourse.setInstructor(newCourse.getInstructor());
		if (newCourse.getLaunchYear() == null)
			existCourse.setLaunchYear(newCourse.getLaunchYear()); 
		if (newCourse.getCompanyName() == null)
			existCourse.setCompanyName(newCourse.getCompanyName()); 
		if (newCourse.getLeadContact() == null)
			existCourse.setLeadContact(newCourse.getLeadContact()); 
		if (newCourse.getCourseFees() == null)
			existCourse.setCourseFees(newCourse.getCourseFees()); 
		if (newCourse.getCourseDesc() == null)
			existCourse.setCourseDesc(newCourse.getCourseDesc()); 
		if (newCourse.getStartDate() == null)
			existCourse.setStartDate(newCourse.getStartDate()); 
		if (newCourse.getActive() == null)
			existCourse.setActive(newCourse.getActive()); 
		
		return ResponseEntity.ok(cSvc.saveCourse(existCourse));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Course> deleteCourseById(@PathVariable Long id) {
		System.out.println(">>> HIT DELETE /api/courses/delete/" + id);
		
		if (id == null || id <= 0)
			return ResponseEntity.badRequest().build();
		else if (cSvc.findCourseById(id).isEmpty())
			return ResponseEntity.notFound().build();
		
		cSvc.deleteCourseById(id);
		if (cSvc.findCourseById(id).isPresent()) // should be empty if not existent
			return ResponseEntity.badRequest().build();
		return ResponseEntity.noContent().build();
	}
	
}
