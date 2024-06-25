package com.example.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.staff.entity.Staff;
import com.example.staff.service.StaffService;

import java.util.List;

@Controller
//@RequestMapping("api/v1")
public class StaffController {
    @Autowired
    private StaffService staffService;

    //Start view page
    @GetMapping("/")
    public String viewPage(Model model){
        String keyword=null;
        return findPaginated(1,"firstName","asc", keyword,model);

    }
    //Stop view page


    //start show new staff from
    @GetMapping("showNewStaffForm")
    public String showNewStaffForm(Model model){
        Staff staff=new Staff();
        model.addAttribute("staff",staff);
        return "add_new_staff";
    }
    //then you can see new staff from

    //start creating new staff
    @PostMapping("savedStaff")
    public String savedStaff(@ModelAttribute("staff")Staff staff) {
        staffService.savedStaff(staff);
        return "redirect:/";
    }
    //end of new staff

    //start updating staff with id
    @GetMapping("update_staff/{id}")
    public String updateStaff(@PathVariable("id")Long id,Model model){

        Staff staff=staffService.getStaffById(id);
        model.addAttribute("staff",staff);
        return "update_staff";

    }
    //end of updated staff with id


    //start deleting staff
    @GetMapping("deleteStaff/{id}")
    public String deleteStaff(@PathVariable("id")Long id){
        //call delete staff method
        staffService.deleteStaff(id);
        return "redirect:/";
    }

    //creating page
    @GetMapping("page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo")int pageNo,
                                @RequestParam(value = "sortField",required = false,defaultValue = "firstName")String sortField,
                                @RequestParam(value = "sortDirection",required = false,defaultValue = "asc")String sortDirection,
                                @RequestParam(value = "keyword",required = false)String keyword,
                                Model model){
        int pageSize=5;

        Page<Staff> page=staffService.findPaginated(pageNo,pageSize,sortField,sortDirection,keyword);
        List<Staff> staffList=page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        model.addAttribute("reverseSortDirection",sortDirection.equals("asc")?"desc":"asc");
        model.addAttribute("staffLists",staffList);
        model.addAttribute("keyword",keyword);

        return "staff_list";
    }
}
