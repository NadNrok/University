package com.fm.University.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<CourseStudent> courses = new ArrayList<>();
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<ClassroomStudent> classrooms = new ArrayList<>();
	
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CourseStudent> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseStudent> courses) {
		this.courses = courses;
	}

	public List<ClassroomStudent> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<ClassroomStudent> classrooms) {
		this.classrooms = classrooms;
	}
	
}
