package com.healthcare.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entity.Enrollee;

public interface EnrolleeDao extends JpaRepository<Enrollee,Integer> {

}
