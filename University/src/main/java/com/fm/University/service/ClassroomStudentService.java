package com.fm.University.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.University.model.ClassroomStudent;
import com.fm.University.repository.ClassroomStudentRepository;

@Service
public class ClassroomStudentService {

	private final ClassroomStudentRepository classroomStudentRepository;

	@Autowired
	public ClassroomStudentService(ClassroomStudentRepository classroomStudentRepository) {
		this.classroomStudentRepository = classroomStudentRepository;
	}

	public List<ClassroomStudent> getAllClassroomStudents() {
		return classroomStudentRepository.findAll();
	}

	public Optional<ClassroomStudent> getClassroomStudentById(Long id) {
		return classroomStudentRepository.findById(id);
	}

	public ClassroomStudent createClassroomStudent(ClassroomStudent classroomStudent) {
		return classroomStudentRepository.save(classroomStudent);
	}

	public ClassroomStudent updateClassroomStudent(Long id, ClassroomStudent classroomStudent) {
		classroomStudent.setId(id);
		return classroomStudentRepository.save(classroomStudent);
	}

	public void deleteClassroomStudentById(Long id) {
		classroomStudentRepository.deleteById(id);
	}
}
