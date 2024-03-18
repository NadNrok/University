package com.fm.University.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fm.University.model.Teacher;
import com.fm.University.repository.TeacherRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
	private final TeacherRepository teacherRepository;

	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	public Optional<Teacher> getTeacherById(Long id) {
		return teacherRepository.findById(id);
	}

	public Teacher createTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public Teacher updateTeacher(Long id, Teacher teacher) {
		teacher.setTeacherId(id);
		return teacherRepository.save(teacher);
	}

	public void deleteTeacherById(Long id) {
		teacherRepository.deleteById(id);
	}

	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public List<Teacher> getTeachersEnrolledInCourses() {
		return teacherRepository.findTeachersEnrolledInCourses();
	}
}
