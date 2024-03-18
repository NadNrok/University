package com.fm.University.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.University.model.Classroom;
import com.fm.University.model.ClassroomStudent;
import com.fm.University.model.ClassroomTeacher;
import com.fm.University.model.Student;
import com.fm.University.model.Teacher;
import com.fm.University.repository.ClassroomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

	private final ClassroomRepository classroomRepository;

	@Autowired
	public ClassroomService(ClassroomRepository classroomRepository) {
		this.classroomRepository = classroomRepository;
	}

	public List<Classroom> getAllClassrooms() {
		return classroomRepository.findAll();
	}

	public Optional<Classroom> getClassroomById(Long id) {
		return classroomRepository.findById(id);
	}

	public Classroom createClassroom(Classroom classroom) {
		return classroomRepository.save(classroom);
	}

	public Classroom updateClassroom(Long id, Classroom classroom) {
		classroom.setClassroomId(id);
		return classroomRepository.save(classroom);
	}

	public void deleteClassroomById(Long id) {
		classroomRepository.deleteById(id);
	}

	public Classroom addStudentToClassroom(Long classroomId, Student student) {
		Classroom classroom = classroomRepository.findById(classroomId).orElse(null);
		if (classroom != null) {
			ClassroomStudent classroomStudent = new ClassroomStudent(classroom, student);
			classroom.getStudents().add(classroomStudent);
			return classroomRepository.save(classroom);
		}
		return null;
	}

	public void removeStudentFromClassroom(Long classroomId, Long studentId) {
		Classroom classroom = classroomRepository.findById(classroomId).orElse(null);
		if (classroom != null) {
			classroom.getStudents().removeIf(cs -> cs.getStudent().getStudentId().equals(studentId));
			classroomRepository.save(classroom);
		}
	}

	public Classroom addTeacherToClassroom(Long classroomId, Teacher teacher) {
		Classroom classroom = classroomRepository.findById(classroomId).orElse(null);
		if (classroom != null) {
			ClassroomTeacher classroomTeacher = new ClassroomTeacher(classroom, teacher);
			classroom.getTeachers().add(classroomTeacher);
			return classroomRepository.save(classroom);
		}
		return null;
	}

	public void removeTeacherFromClassroom(Long classroomId, Long teacherId) {
		Classroom classroom = classroomRepository.findById(classroomId).orElse(null);
		if (classroom != null) {
			classroom.getTeachers().removeIf(ct -> ct.getTeacher().getTeacherId().equals(teacherId));
			classroomRepository.save(classroom);
		}
	}

	public Classroom saveClassroom(Classroom classroom) {
		return classroomRepository.save(classroom);
	}
}
