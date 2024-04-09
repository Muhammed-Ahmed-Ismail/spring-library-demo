package com.library.demo.repository;

import com.library.demo.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatronRepository extends JpaRepository<Patron,Long> {
    public Boolean existsByEmail(String email);
    public Boolean existsByPhone(String phone);
    public Boolean existsByEmailOrPhoneAndIdNot(String email,String phone,Long id);
}
