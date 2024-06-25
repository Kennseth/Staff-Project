package com.example.staff.service;

import java.util.List;

import com.example.staff.entity.Staff;
import org.springframework.data.domain.Page;

public interface StaffService {

    List<Staff> getAllSatff();


    void savedStaff(Staff staff);
    Staff getStaffById(Long id);
    void deleteStaff(Long id);

    Page<Staff> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection,String keyword);

}
