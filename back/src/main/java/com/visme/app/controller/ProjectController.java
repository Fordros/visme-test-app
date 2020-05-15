package com.visme.app.controller;


import com.visme.app.model.request.ProjectRequestDto;
import com.visme.app.model.response.ProjectResponseDto;
import com.visme.app.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/users/{user_id}/projects")
    List<ProjectResponseDto> getProjectsOfUser(@PathVariable("user_id") Long userId) {

        log.info("Request for get all projects of user [{}]", userId);

        return projectService.getAllByUserId(userId);
    }

    @GetMapping("/projects/{id}")
    ProjectResponseDto getById(@PathVariable Long id) {

        log.info("Request project by ID [{}]", id);

        return projectService.findById(id);
    }

    @PostMapping("/users/{user_id}/projects")
    ProjectResponseDto create(@PathVariable("user_id") Long userId,
                              @RequestBody @Valid ProjectRequestDto dto) {

        log.info("Request for create project for user [{}] from data [{}] ", userId, dto);

        return projectService.create(userId, dto);
    }

    @PutMapping("/projects/{id}")
    ProjectResponseDto edit(@PathVariable Long id,
                            @RequestBody @Valid ProjectRequestDto dto) {

        log.info("Request for edit project [{}]", id);

        dto.setId(id);

        return projectService.edit(dto);
    }

    @DeleteMapping("/projects/{id}")
    void delete(@PathVariable Long id) {

        log.info("Request for delete project by ID:[{}]", id);

        projectService.delete(id);
    }

    @PutMapping("/projects/{id}/favorite")
    boolean changeFavoriteState(@PathVariable Long id) {

        log.info("Request for change favorite state of project [{}]", id);

        return projectService.changeFavoriteState(id);
    }

    @GetMapping("/projects/{id}/access-times")
    List<Long> getAccessTimes(@PathVariable Long id) {

        log.info("Request for get access times of project [{}]", id);

        return projectService.findAllAccessTimesByProjectId(id);
    }
}
