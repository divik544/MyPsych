package com.psych.game.repository;

import com.psych.game.model.Employee;
import com.psych.game.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
