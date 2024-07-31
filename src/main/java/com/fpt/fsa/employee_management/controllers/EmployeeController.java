package com.fpt.fsa.employee_management.controllers;

import com.fpt.fsa.employee_management.dtos.request.EmployeeCreateDto;
import com.fpt.fsa.employee_management.dtos.request.EmployeeUpdateDto;
import com.fpt.fsa.employee_management.dtos.response.EmployeeResponseDto;
import com.fpt.fsa.employee_management.enums.EGender;
import com.fpt.fsa.employee_management.enums.EStatus;
import com.fpt.fsa.employee_management.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private static final String ACTIVE_TAB = "activeTab";
    private static final String EMPLOYEE = "employee";
    private static final String MESSAGE = "message";
    private static final String GENDER = "genders";
    private static final String STATUS = "status";
    private static final String CURR_SEARCH = "currentSearch";
    private static final String CURR_FILTER = "currentFilter";
    private static final String REDIRECT_EMPLOYEE = "redirect:/employee";

    @GetMapping
    public ModelAndView index (
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String accountName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, value = "sort", defaultValue = "asc") String sort,
            @AuthenticationPrincipal User user
    ) {

        Sort orders = Sort.by(sort.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC : Sort.Direction.ASC, "id");
        Page<EmployeeResponseDto> employeeResponses = employeeService.getEmployeePageable(firstName, lastName, email, phone, accountName, PageRequest.of(page, size, orders));
        List<String> filters = new ArrayList<>(List.of("firstName", "lastName", "email", "phone", "accountName"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees", employeeResponses);
        modelAndView.addObject("user", user);
        modelAndView.addObject(ACTIVE_TAB, EMPLOYEE);
        modelAndView.addObject("filters", filters);

        // Add the current filter and search values to the model
        if (firstName != null) {
            modelAndView.addObject(CURR_SEARCH, "firstName");
            modelAndView.addObject(CURR_FILTER, firstName);
        }

        if (lastName != null) {
            modelAndView.addObject(CURR_SEARCH, "lastName");
            modelAndView.addObject(CURR_FILTER, lastName);
        }

        if (email != null) {
            modelAndView.addObject(CURR_SEARCH, "email");
            modelAndView.addObject(CURR_FILTER, email);
        }

        if (phone != null) {
            modelAndView.addObject(CURR_SEARCH, "phone");
            modelAndView.addObject(CURR_FILTER, phone);
        }

        if (accountName != null) {
            modelAndView.addObject(CURR_SEARCH, "accountName");
            modelAndView.addObject(CURR_FILTER, accountName);
        }

        modelAndView.setViewName("employee/index");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(GENDER, EGender.values());
        modelAndView.addObject(STATUS, EStatus.values());
        modelAndView.addObject("employeeCreate", new EmployeeCreateDto());
        modelAndView.setViewName("employee/add");
        modelAndView.addObject(ACTIVE_TAB, "add");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getEmployee(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(GENDER, EGender.values());
        modelAndView.addObject(STATUS, EStatus.values());
        modelAndView.addObject(EMPLOYEE, employeeService.getEmployee(id));
        modelAndView.addObject("employeeUpdate", new EmployeeUpdateDto());
        modelAndView.setViewName("employee/info");
        System.out.println(employeeService.getEmployee(id));
        modelAndView.addObject(ACTIVE_TAB, "edit");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addEmployee(@ModelAttribute("employeeCreate")
                                    @Valid EmployeeCreateDto employeeCreateDto,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee/add");
        if (result.hasErrors()) {
            modelAndView.addObject(GENDER, EGender.values());
            modelAndView.addObject(STATUS, EStatus.values());
            return modelAndView;
        }
        employeeService.createEmployee(employeeCreateDto);
        redirectAttributes.addFlashAttribute(MESSAGE, "Employee added successfully");
        modelAndView.setViewName(REDIRECT_EMPLOYEE);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView updateEmployee (@PathVariable("id") Long id, @ModelAttribute("employeeUpdate")
                                        @Valid EmployeeUpdateDto employeeUpdateDto, BindingResult result,
                                        RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee/info");
        if (result.hasErrors()) {
            modelAndView.addObject(GENDER, EGender.values());
            modelAndView.addObject(STATUS, EStatus.values());
            modelAndView.addObject(EMPLOYEE, employeeService.getEmployee(id));
            return modelAndView;
        }
        employeeService.updateEmployee(id, employeeUpdateDto);
        redirectAttributes.addFlashAttribute(MESSAGE, "Employee updated successfully");
        modelAndView.setViewName(REDIRECT_EMPLOYEE);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteEmployee (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        employeeService.deleteEmployee(id);
        redirectAttributes.addFlashAttribute(MESSAGE, "Employee deleted successfully");
        modelAndView.setViewName(REDIRECT_EMPLOYEE);
        return modelAndView;
    }
}
