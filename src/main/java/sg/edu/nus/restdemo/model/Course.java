package sg.edu.nus.restdemo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;
	
	@NotBlank(message="Course name is required")
	@Size(min=3, max=25, message="Course name must be between 3 and 25 characters")
	private String courseName;
	
	@NotBlank(message="Instructor name is required")
	private String instructor;
	
	@NotNull(message="Launch year is required")
	@Min(value=2000, message="Launch year must be 2000 or later")
	@Max(value=2099, message="Launch year must be 2099 or earlier")
	private Long launchYear;
	
	@NotBlank(message="Company name is required")
	private String companyName;
	
	@NotNull(message="Course fees is required")
	@DecimalMin(value="200.0", message="Course fees must be at least $200")
	@DecimalMax(value="3000.0", message="Course fees cannot exceed $3000")
	private Double courseFees;
	
	@Size(max=200, message="Course description cannot exceed 200 characters")
	private String courseDesc;
	
	@NotNull(message="Start date is required")
	@FutureOrPresent(message="Start date cannot be in the past")
	private LocalDate startDate;
	
	// h2 does not support DATETIME data type like mysql
	// so this will map to h2 as timestamp
	private LocalDateTime createdOn;
	
	@NotNull(message="Lead contact number is required")
	private String leadContact;
	
	private Boolean active;

	public Long getCourseId() { return courseId; }
	public void setCourseId(Long courseId) { this.courseId = courseId; }
	public String getCourseName() { return courseName; }
	public void setCourseName(String courseName) { this.courseName = courseName; }
	public String getInstructor() { return instructor; }
	public void setInstructor(String instructor) { this.instructor = instructor; }
	public Long getLaunchYear() { return launchYear; }
	public void setLaunchYear(Long launchYear) { this.launchYear = launchYear; }
	public String getCompanyName() { return companyName; }
	public void setCompanyName(String companyName) { this.companyName = companyName; }
	public Double getCourseFees() { return courseFees; }
	public void setCourseFees(Double courseFees) { this.courseFees = courseFees; }
	public String getCourseDesc() { return courseDesc; }
	public void setCourseDesc(String courseDesc) { this.courseDesc = courseDesc; }
	public LocalDate getStartDate() { return startDate; }
	public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
	public LocalDateTime getCreatedOn() { return createdOn; }
	public void setCreatedOn(LocalDateTime createdOn) { this.createdOn = createdOn; }	
	public String getLeadContact() { return leadContact; }
	public void setLeadContact(String leadContact) { this.leadContact = leadContact; }
	public Boolean getActive() { return active; }
	public void setActive(Boolean active) { this.active = active; }
	
	public Course() { super(); }
	public Course(String courseName, String instructor, Long launchYear, String companyName,
			String leadContact, Double courseFees, String courseDesc, LocalDate startDate, LocalDateTime createdOn, Boolean active) {
		super();
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
	}
	
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", instructor=" + instructor
				+ ", launchYear=" + launchYear + ", companyName=" + companyName + ", courseFees=" + courseFees
				+ ", courseDesc=" + courseDesc + ", startDate=" + startDate + ", createdOn=" + createdOn
				+ ", leadContact=" + leadContact + ", active=" + active + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(courseId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Course other = (Course) obj;
		return Objects.equals(courseId, other.courseId);
	}
	
}
