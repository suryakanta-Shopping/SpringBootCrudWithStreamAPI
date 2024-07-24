package com.nt.surya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.surya.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
