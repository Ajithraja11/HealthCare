package com.healthcare.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entity.Dependent;

public interface DependentDao extends JpaRepository<Dependent,Integer>{

}
