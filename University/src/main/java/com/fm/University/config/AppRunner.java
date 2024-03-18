package com.fm.University.config;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fm.University.model.AppUser;
import com.fm.University.model.Classroom;
import com.fm.University.model.ClassroomStudent;
import com.fm.University.model.ClassroomTeacher;
import com.fm.University.model.Course;
import com.fm.University.model.CourseStudent;
import com.fm.University.model.CourseTeacher;
import com.fm.University.model.Role;
import com.fm.University.model.Schedule;
import com.fm.University.model.Student;
import com.fm.University.model.Teacher;
import com.fm.University.repository.AppUserRepository;
import com.fm.University.repository.ClassroomRepository;
import com.fm.University.repository.ClassroomStudentRepository;
import com.fm.University.repository.ClassroomTeacherRepository;
import com.fm.University.repository.CourseRepository;
import com.fm.University.repository.CourseStudentRepository;
import com.fm.University.repository.CourseTeacherRepository;
import com.fm.University.repository.RoleRepository;
import com.fm.University.repository.ScheduleRepository;
import com.fm.University.repository.StudentRepository;
import com.fm.University.repository.TeacherRepository;
import com.fm.University.service.AppUserService;

import javax.annotation.PostConstruct;

@Component
public class AppRunner {

	private final AppUserService appUserService;
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	private final CourseStudentRepository courseStudentRepository;
	private final CourseTeacherRepository courseTeacherRepository;
	private final ClassroomRepository classroomRepository;
	private final ClassroomStudentRepository classroomStudentRepository;
	private final ClassroomTeacherRepository classroomTeacherRepository;
	private final ScheduleRepository scheduleRepository;
	private final DataSource dataSource;

	@Autowired
	public AppRunner(AppUserService appUserService, AppUserRepository appUserRepository, RoleRepository roleRepository,
			CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository,
			CourseStudentRepository courseStudentRepository, CourseTeacherRepository courseTeacherRepository,
			ClassroomRepository classroomRepository, ClassroomStudentRepository classroomStudentRepository,
			ClassroomTeacherRepository classroomTeacherRepository, ScheduleRepository scheduleRepository,
			DataSource dataSource) {
		this.appUserService = appUserService;
		this.appUserRepository = appUserRepository;
		this.roleRepository = roleRepository;
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		this.courseStudentRepository = courseStudentRepository;
		this.courseTeacherRepository = courseTeacherRepository;
		this.classroomRepository = classroomRepository;
		this.classroomStudentRepository = classroomStudentRepository;
		this.classroomTeacherRepository = classroomTeacherRepository;
		this.scheduleRepository = scheduleRepository;
		this.dataSource = dataSource;
	}

	@PostConstruct
	private void migrateAndGenerateData() {
		migrateDatabase();
		generateTestDataIfNeeded();
	}

	private void migrateDatabase() {
		Flyway flyway = Flyway.configure().dataSource(dataSource).load();
		flyway.migrate();
	}

	private void generateTestDataIfNeeded() {
		if (!databaseHasData()) {
			createRolesAndAdmin();
		}
	}

	private boolean databaseHasData() {
		Long totalUser = appUserRepository.getCount();
		long totalCount = totalUser;
		return totalCount > 0;
	}

	private void createRolesAndAdmin() {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Classroom classroom = new Classroom();
		classroom.setName("4D");
		classroomRepository.save(classroom);

		Course course = new Course();
		course.setName("Introduction to Programming");
		course.setCode("CS101");
		courseRepository.save(course);

		Student student = new Student();
		student.setName("John Doe");
		studentRepository.save(student);

		Teacher teacher = new Teacher();
		teacher.setName("Jack Roy");
		teacherRepository.save(teacher);

		ClassroomStudent classroomStudent = new ClassroomStudent();
		classroomStudent.setClassroom(classroom);
		classroomStudent.setStudent(student);
		classroomStudentRepository.save(classroomStudent);

		CourseStudent courseStudent = new CourseStudent();
		courseStudent.setCourse(course);
		courseStudent.setStudent(student);
		courseStudentRepository.save(courseStudent);

		CourseTeacher courseTeacher = new CourseTeacher();
		courseTeacher.setCourse(course);
		courseTeacher.setTeacher(teacher);
		courseTeacherRepository.save(courseTeacher);

		ClassroomTeacher classroomTeacher = new ClassroomTeacher();
		classroomTeacher.setClassroom(classroom);
		classroomTeacher.setTeacher(teacher);
		classroomTeacherRepository.save(classroomTeacher);

		Schedule schedule = new Schedule();
		schedule.setDate(Date.valueOf(LocalDate.now()));
		schedule.setTime(LocalTime.now().withSecond(0));
		schedule.setClassroom(classroom);
		schedule.setTeacher(teacher);
		scheduleRepository.save(schedule);

		Role adminRole = new Role();
		adminRole.setName("ADMIN");
		roleRepository.save(adminRole);

		Role studentRole = new Role();
		studentRole.setName("STUDENT");
		roleRepository.save(studentRole);

		Role teacherRole = new Role();
		teacherRole.setName("TEACHER");
		roleRepository.save(teacherRole);

		AppUser admin = new AppUser();
		admin.setUsername("admin");
		String adminHashedPassword = passwordEncoder.encode("1234");
		admin.setPassword(adminHashedPassword);
		admin.setRoles(Collections.singleton(adminRole));
		appUserService.createUser(admin);

		AppUser studentUser = new AppUser();
		studentUser.setUsername("student");
		String studentHashedPassword = passwordEncoder.encode("1111");
		studentUser.setPassword(studentHashedPassword);
		studentUser.setRoles(Collections.singleton(studentRole));
		appUserService.createUser(studentUser);

		AppUser teacherUser = new AppUser();
		teacherUser.setUsername("teacher");
		String teacherHashedPassword = passwordEncoder.encode("4444");
		teacherUser.setPassword(teacherHashedPassword);
		teacherUser.setRoles(Collections.singleton(teacherRole));
		appUserService.createUser(teacherUser);
	}
}
