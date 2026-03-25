USE j2ee;

DROP TABLE IF EXISTS courses;

CREATE TABLE courses (
	course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(25) NOT NULL,
    instructor VARCHAR(50) NOT NULL,
    launch_year BIGINT NOT NULL,
    company_name VARCHAR(50) NOT NULL,
    lead_contact VARCHAR(15) NOT NULL,
    course_fees DECIMAL(4, 1) NOT NULL,
    course_desc VARCHAR(200),
    start_date DATE NOT NULL,
    created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    active BOOL DEFAULT TRUE
);

INSERT INTO courses
	(course_name, instructor, launch_year, company_name, 
		lead_contact, course_fees, course_desc, start_date, active) 
    VALUES
		('Java Programming', 'Vincent', 2000, 'ISS',
			'98765432', 450.0, null, '2025-08-01', true),
		('Web Programming', 'Chuk', 2010, 'ISS',
			'97489321', 550.0, null, '2026-08-01', false),
		('React Programming', 'Suria', 2020, 'ISS',
			'89745376', 850.0, null, '2027-08-01', true);
            
SELECT * FROM courses;