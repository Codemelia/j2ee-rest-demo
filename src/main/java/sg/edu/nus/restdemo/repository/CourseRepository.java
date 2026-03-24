package sg.edu.nus.restdemo.repository;

import sg.edu.nus.restdemo.model.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findByActiveTrue();
	
}
