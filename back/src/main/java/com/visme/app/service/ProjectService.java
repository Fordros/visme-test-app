package com.visme.app.service;

import com.visme.app.data.domain.Project;
import com.visme.app.data.domain.User;
import com.visme.app.data.dto.ProjectDataDto;
import com.visme.app.data.services.ProjectAccessDataService;
import com.visme.app.data.services.ProjectDataService;
import com.visme.app.data.services.UserDataService;
import com.visme.app.model.request.ProjectRequestDto;
import com.visme.app.model.response.ProjectResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private ProjectDataService projectDataService;

    @Autowired
    private ProjectAccessDataService accessDataService;

    @Autowired
    private ModelMapperHelper modelMapperHelper;

    /**
     * Get all projects for user
     * @return
     */
    public List<ProjectResponseDto> getAllByUserId(Long userId) {

        List<ProjectDataDto> projects = projectDataService.findAllProjectsByUserId(userId);

        return projects.stream()
                .map(modelMapperHelper::buildProjectResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Get by id
     * @param id
     * @return
     */
    public ProjectResponseDto findById(Long id){

        Project project = projectDataService.findById(id);

        accessDataService.addAccessTime(project, System.currentTimeMillis());

        return modelMapperHelper.buildProjectResponseDto(project);
    }

    /**
     * Create project
     * @param userId
     * @param dto
     * @return
     */
    public ProjectResponseDto create(Long userId, ProjectRequestDto dto){

        ProjectDataDto dataDto = modelMapperHelper.buildProjectDataDto(dto);

        User user = userDataService.findById(userId);

        dataDto = projectDataService.create(user, dataDto);

        return modelMapperHelper.buildProjectResponseDto(dataDto);
    }

    /**
     * Edit project
     * @param dto
     */
    public ProjectResponseDto edit(ProjectRequestDto dto){

        ProjectDataDto dataDto = modelMapperHelper.buildProjectDataDto(dto);

        dataDto = projectDataService.edit(dataDto);

        return modelMapperHelper.buildProjectResponseDto(dataDto);
    }

    /**
     * Delete project
     * @param id
     */
    public void delete(Long id){

        projectDataService.delete(id);
    }

    /**
     * Get all access times of project
     * @param projectId
     * @return
     */
    public List<Long> findAllAccessTimesByProjectId(Long projectId){

        return accessDataService.findAccessesByProjectId(projectId);
    }

    /**
     * Change favorite state
     * @param id
     */
    public boolean changeFavoriteState(Long id){

        Project project = projectDataService.findById(id);

        projectDataService.setFavoriteState(project, !project.getFavorite());

        return project.getFavorite();
    }
}
