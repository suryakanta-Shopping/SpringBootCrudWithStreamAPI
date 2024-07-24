package com.nt.surya.service;

import java.util.List;
import java.util.Optional;

import com.nt.surya.entity.Course;

public interface CourseService {

	List<Course> findAllCourse();

	Course saveAllCourse(Course course);

	Optional<Course> findByCourseId(Long cid);

	void deleteByCourseId(Long cid);

}
