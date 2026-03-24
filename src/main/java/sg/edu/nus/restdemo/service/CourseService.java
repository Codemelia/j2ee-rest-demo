package sg.edu.nus.restdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.restdemo.model.Course;
import sg.edu.nus.restdemo.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository cRepo;	
	
	// retrieve list of active courses
	public List<Course> getActiveCourses() {
		return cRepo.findByActiveTrue();
	}
	
	// retrieve course by id
	public Optional<Course> findCourseById(Long id) {
		return cRepo.findById(id);
	}
	
	// save course
	public Course saveCourse(Course c) {
		return cRepo.save(c);
	}
	
	// delete course by id
	public void deleteCourseById(Long id) {
		cRepo.deleteById(id);
	}
	
}
