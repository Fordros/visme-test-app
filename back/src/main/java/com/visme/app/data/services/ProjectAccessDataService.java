package com.visme.app.data.services;

import com.visme.app.data.domain.Access;
import com.visme.app.data.domain.Project;
import com.visme.app.data.repositories.ProjectAccessRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Data
public class ProjectAccessDataService {

    @Autowired
    private ProjectAccessRepository accessRepository;

    /**
     * Add access time by project
     * @param project
     * @param time
     */
    public void addAccessTime(Project project, Long time) {

        log.info("Add access time for project [{}]", project.getId());

        Access access = new Access();
        access.setProject(project);
        access.setTime(time);

        accessRepository.save(access);
    }

    /**
     * Find all accesses by project
     * @param projectId
     * @return
     */
    public List<Long> findAccessesByProjectId(Long projectId){

        log.debug("Finding accesses by project ID:[{}]", projectId);

        List<Access> accesses = accessRepository.findAllByProjectId(projectId);

        log.debug("...found [{}] accesses", accesses.size());

        return accesses.stream().map(Access::getTime).collect(Collectors.toList());
    }
}
