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
@Table(name = "classroom")
public class Classroom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "classroom_id")
	private Long classroomId;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
	private List<ClassroomStudent> students = new ArrayList<>();

	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
	private List<ClassroomTeacher> teachers = new ArrayList<>();

	public Long getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(Long classroomId) {
		this.classroomId = classroomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ClassroomStudent> getStudents() {
		return students;
	}

	public void setStudents(List<ClassroomStudent> students) {
		this.students = students;
	}

	public List<ClassroomTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<ClassroomTeacher> teachers) {
		this.teachers = teachers;
	}
}
