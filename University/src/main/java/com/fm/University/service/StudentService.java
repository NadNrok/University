package com.fm.University.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.University.model.Student;
import com.fm.University.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	public Student updateStudent(Long id, Student student) {
		student.setStudentId(id);
		return studentRepository.save(student);
	}

	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);
	}

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public List<Student> getStudentsEnrolledInCourses() {
		return studentRepository.findStudentsEnrolledInCourses();
	}
}
