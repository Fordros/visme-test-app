package com.visme.app.data.services;

import com.visme.app.data.domain.Project;
import com.visme.app.data.domain.User;
import com.visme.app.data.dto.ProjectDataDto;
import com.visme.app.data.repositories.UserProjectRepository;
import com.visme.app.service.ModelMapperHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Data
public class ProjectDataService {

    @Autowired
    private UserProjectRepository projectRepository;

    @Autowired
    private ProjectAccessDataService accessDataService;

    @Autowired
    private ModelMapperHelper modelMapperHelper;

    /**
     * Create project
     *
     * @param dto - project data transfer model
     * @return - project identifier
     */
    public ProjectDataDto create(User user, ProjectDataDto dto) {

        log.info("Creating project for user [{}] from data [{}]", user.getId(), dto);

        Project project = modelMapperHelper.buildProject(dto);

        project.setUser(user);

        project = projectRepository.save(project);

        log.debug("...created project with ID:[{}]", project.getId());

        return modelMapperHelper.buildProjectDataDto(project);
    }

    /**
     * Edit project
     *
     * @param dto - project data transfer model
     */
    public ProjectDataDto edit(ProjectDataDto dto) {

        log.info("Editing project [{}] from data:[{}]", dto.getId(), dto);

        Project project = this.findById(dto.getId());

        project.setTitle(dto.getTitle());
        project.setType(dto.getType());
        project.setFavorite(dto.getFavorite());

        projectRepository.save(project);

        accessDataService.addAccessTime(project, System.currentTimeMillis());

        log.debug("...successfully edited project");

        return modelMapperHelper.buildProjectDataDto(project);
    }

    /**
     * Delete project
     *
     * @param id - project identifier
     */
    public void delete(Long id) {

        log.info("Deleting project by ID:[{}]", id);

        projectRepository.deleteById(id);
    }

    /**
     * Find all projects for user
     *
     * @return - all projects of user
     */
    public List<ProjectDataDto> findAllProjectsByUserId(Long userId) {

        log.info("Finding all projects of user [{}]", userId);

        List<Project> projects = projectRepository.findAllByUserId(userId);

        log.debug("...found [{}] projects for user [{}]", projects.size(), userId);

        return projects.stream()
                .map(modelMapperHelper::buildProjectDataDto)
                .collect(Collectors.toList());

    }

    /**
     * Find by ID
     *
     * @param id
     * @return
     */
    public Project findById(Long id) throws EntityNotFoundException {

        log.info("Find project by ID:[{}}]", id);

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No project found by ID" + id));

        log.debug("...found project [{}]", project);

        return project;
    }

    /**
     * Set favorite state
     *
     * @param project
     * @param favorite
     */
    public void setFavoriteState(Project project, boolean favorite) {

        log.debug("Set favorite state to [{}] for project [{}]", favorite, project.getId());

        project.setFavorite(favorite);

        projectRepository.save(project);
    }
}
