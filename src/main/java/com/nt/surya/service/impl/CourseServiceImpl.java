package com.nt.surya.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.surya.entity.Course;
import com.nt.surya.repository.CourseRepository;
import com.nt.surya.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> findAllCourse() {
		return courseRepository.findAll().stream()
				.filter(c->c.getCname() !=null)
				.collect(Collectors.toList());
	}

	@Override
	public Course saveAllCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Optional<Course> findByCourseId(Long cid) {
		return courseRepository.findById(cid).stream()
				.filter(c->c.getCid().equals(cid)).findFirst();
	}

	@Override
	public void deleteByCourseId(Long cid) {
		List<Course> cs = courseRepository.findAll().stream()
		.filter(c->c.getCid().equals(cid))
		.collect(Collectors.toList());
		courseRepository.deleteById(cid);

	}

}
