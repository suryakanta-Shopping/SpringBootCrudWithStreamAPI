package com.nt.surya.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.nt.surya.entity.Course;
import com.nt.surya.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	
	@GetMapping("/all")
	public	List<Course> getAllCourses(){
		return courseService.findAllCourse().stream()
				.sorted((c1,c2)->c1.getCname().compareTo(c2.getCname())) //Example to sorting
				.collect(Collectors.toList());
	}
	
	
	@GetMapping("/{cid}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long cid) {
		Optional<Course> course=courseService.findByCourseId(cid);
		return course.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build()) ;
	}
	
	@PostMapping("/save")
	public Course  createCourse(@RequestBody Course course) {
		return courseService.saveAllCourse(course);
	}
	
	
	@PutMapping("/{cid}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long cid, @RequestBody Course courseDetails) {
        Optional<Course> course = courseService.findByCourseId(cid);

        if (course.isPresent()) {
            Course courseToUpdate = course.get();
            courseToUpdate.setCname((courseDetails.getCname()));
            courseToUpdate.setCdescrip((courseDetails.getCdescrip()));
            return ResponseEntity.ok(courseService.saveAllCourse(courseToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cid}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long cid) {
        if (courseService.findByCourseId(cid).isPresent()) {
            courseService.deleteByCourseId(cid);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
