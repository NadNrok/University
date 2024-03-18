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
@RequestMapping("/schedule-details")
public class ScheduleDetailsController {

    private final ScheduleService scheduleService;
    private final TeacherService teacherService;
    private final ClassroomService classroomService;

    @Autowired
    public ScheduleDetailsController(ScheduleService scheduleService, TeacherService teacherService, ClassroomService classroomService) {
        this.scheduleService = scheduleService;
        this.teacherService = teacherService;
        this.classroomService = classroomService;
    }

    @GetMapping("/{id}")
    public String getScheduleDetails(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
        model.addAttribute("role", role);

        Schedule schedule = scheduleService.getScheduleById(id).orElse(null);
        model.addAttribute("schedule", schedule);

        List<Teacher> teachers = teacherService.getAllTeachers();
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        model.addAttribute("teachers", teachers);
        model.addAttribute("classrooms", classrooms);

        return "schedule-details";
    }

    @PostMapping("/{id}/update")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute Schedule updatedSchedule, @RequestParam("classroomId") Long classroomId, @RequestParam("teacherId") Long teacherId) {
        try {
            Classroom classroom = classroomService.getClassroomById(classroomId).orElseThrow(() -> new NoSuchElementException("Classroom not found with ID: " + classroomId));
            Teacher teacher = teacherService.getTeacherById(teacherId).orElseThrow(() -> new NoSuchElementException("Teacher not found with ID: " + teacherId));

            updatedSchedule.setClassroom(classroom);
            updatedSchedule.setTeacher(teacher);

            scheduleService.updateSchedule(id, updatedSchedule);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return "redirect:/schedules";
    }
}
