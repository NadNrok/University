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
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Long courseId;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<CourseStudent> students = new ArrayList<>();

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<CourseTeacher> teachers = new ArrayList<>();
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CourseStudent> getStudents() {
		return students;
	}

	public void setStudents(List<CourseStudent> students) {
		this.students = students;
	}

	public List<CourseTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<CourseTeacher> teachers) {
		this.teachers = teachers;
	}

}
