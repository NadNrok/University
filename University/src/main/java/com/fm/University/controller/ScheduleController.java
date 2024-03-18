package com.fm.University.controller;

import com.fm.University.model.Classroom;
import com.fm.University.model.Schedule;
import com.fm.University.model.Teacher;
import com.fm.University.service.ClassroomService;
import com.fm.University.service.ScheduleService;
import com.fm.University.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final TeacherService teacherService;
    private final ClassroomService classroomService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, TeacherService teacherService, ClassroomService classroomService) {
        this.scheduleService = scheduleService;
        this.teacherService = teacherService;
        this.classroomService = classroomService;
    }

    @GetMapping("")
    public String getAllSchedules(Model model) {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<Classroom> classrooms = classroomService.getAllClassrooms();

        model.addAttribute("schedules", schedules);
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("teachers", teachers);
        model.addAttribute("classrooms", classrooms);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
        model.addAttribute("role", role);

        return "schedule";
	}

	@GetMapping("/{id}")
	public String getScheduleById(@PathVariable Long id, Model model) {
		Schedule schedule = scheduleService.getScheduleById(id)
				.orElseThrow(() -> new NoSuchElementException("Schedule not found with ID: " + id));
		model.addAttribute("schedule", schedule);
		return "schedule";
	}

	@PostMapping("/")
	public String createSchedule(@ModelAttribute Schedule schedule, @RequestParam("classroomId") Long classroomId, @RequestParam("teacherId") Long teacherId) {
	    Classroom classroom = classroomService.getClassroomById(classroomId).orElseThrow(() -> new NoSuchElementException("Classroom not found with ID: " + classroomId));
	    Teacher teacher = teacherService.getTeacherById(teacherId).orElseThrow(() -> new NoSuchElementException("Teacher not found with ID: " + teacherId));

	    schedule.setClassroom(classroom);
	    schedule.setTeacher(teacher);

	    scheduleService.createSchedule(schedule);
	    return "redirect:/schedules";
	}

	@PostMapping("/{id}")
	public String deleteSchedule(@PathVariable Long id) {
		scheduleService.deleteScheduleById(id);
		return "redirect:/schedules";
	}
}
