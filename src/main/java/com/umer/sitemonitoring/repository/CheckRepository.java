package com.umer.sitemonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umer.sitemonitoring.entity.Check;

public interface CheckRepository extends JpaRepository<Check, Integer>{

}
