package com.visme.app.data.repositories;


import com.visme.app.data.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectRepository extends JpaRepository<Project, Long> {


    List<Project> findAllByUserId(Long userId);
}
