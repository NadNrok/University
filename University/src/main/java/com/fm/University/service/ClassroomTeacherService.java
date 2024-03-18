package com.fm.University.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.University.model.ClassroomTeacher;
import com.fm.University.repository.ClassroomTeacherRepository;

@Service
public class ClassroomTeacherService {

	private final ClassroomTeacherRepository classroomTeacherRepository;

	@Autowired
	public ClassroomTeacherService(ClassroomTeacherRepository classroomTeacherRepository) {
		this.classroomTeacherRepository = classroomTeacherRepository;
	}

	public List<ClassroomTeacher> getAllClassroomTeachers() {
		return classroomTeacherRepository.findAll();
	}

	public Optional<ClassroomTeacher> getClassroomTeacherById(Long id) {
		return classroomTeacherRepository.findById(id);
	}

	public ClassroomTeacher createClassroomTeacher(ClassroomTeacher classroomTeacher) {
		return classroomTeacherRepository.save(classroomTeacher);
	}

	public ClassroomTeacher updateClassroomTeacher(Long id, ClassroomTeacher classroomTeacher) {
		classroomTeacher.setId(id);
		return classroomTeacherRepository.save(classroomTeacher);
	}

	public void deleteClassroomTeacherById(Long id) {
		classroomTeacherRepository.deleteById(id);
	}
}
