package com.visme.app.data.repositories;


import com.visme.app.data.domain.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectAccessRepository extends JpaRepository<Access, Long> {

    List<Access> findAllByProjectId(Long projectId);
}
