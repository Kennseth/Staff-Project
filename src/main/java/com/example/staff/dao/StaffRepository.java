package com.example.staff.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.staff.entity.Staff;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {

    @Query("select s from Staff s where s.firstName LIKE %?1%"+
    "or s.lastName like %?1%")
    Page<Staff> findAll(String keyword, Pageable pageable);
}
