package com.fpt.fsa.employee_management.controllers;

import com.fpt.fsa.employee_management.dtos.request.EmployeeCreateDto;
import com.fpt.fsa.employee_management.dtos.request.EmployeeUpdateDto;
import com.fpt.fsa.employee_management.enums.EGender;
import com.fpt.fsa.employee_management.enums.EStatus;
import com.fpt.fsa.employee_management.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees", employeeService.getAllEmployees());
        modelAndView.setViewName("employee/index");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("genders", EGender.values());
        modelAndView.addObject("status", EStatus.values());
        modelAndView.addObject("employeeCreate", new EmployeeCreateDto());
        modelAndView.setViewName("employee/add");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getEmployee(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("genders", EGender.values());
        modelAndView.addObject("status", EStatus.values());
        modelAndView.addObject("employee", employeeService.getEmployee(id));
        modelAndView.addObject("employeeUpdate", new EmployeeUpdateDto());
        modelAndView.setViewName("employee/info");
        System.out.println(employeeService.getEmployee(id));
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addEmployee(@ModelAttribute("employeeCreate")
                                    @Valid EmployeeCreateDto employeeCreateDto,
                                    BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee/add");
        if (result.hasErrors()) {
            modelAndView.addObject("genders", EGender.values());
            modelAndView.addObject("status", EStatus.values());
            return modelAndView;
        }
        employeeService.createEmployee(employeeCreateDto);
        modelAndView.setViewName("redirect:/employee");
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView updateEmployee (@PathVariable("id") Long id, @ModelAttribute("employeeUpdate")
                                        @Valid EmployeeUpdateDto employeeUpdateDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee/info");
        if (result.hasErrors()) {
            modelAndView.addObject("genders", EGender.values());
            modelAndView.addObject("status", EStatus.values());
            modelAndView.addObject("employee", employeeService.getEmployee(id));
            return modelAndView;
        }
        employeeService.updateEmployee(id, employeeUpdateDto);
        modelAndView.setViewName("redirect:/employee");
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteEmployee (@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        employeeService.deleteEmployee(id);
        modelAndView.setViewName("redirect:/employee");
        return modelAndView;
    }
}
