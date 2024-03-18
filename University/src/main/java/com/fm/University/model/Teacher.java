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
@Table(name = "teacher")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacher_id")
	private Long teacherId;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	private List<CourseTeacher> courses = new ArrayList<>();

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	private List<ClassroomTeacher> classroom = new ArrayList<>();

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CourseTeacher> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseTeacher> courses) {
		this.courses = courses;
	}

	public List<ClassroomTeacher> getClassroom() {
		return classroom;
	}

	public void setClassroom(List<ClassroomTeacher> classroom) {
		this.classroom = classroom;
	}
}
