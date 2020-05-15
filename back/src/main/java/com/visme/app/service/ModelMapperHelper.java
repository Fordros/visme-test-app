package com.visme.app.service;

import com.visme.app.data.domain.Project;
import com.visme.app.data.domain.User;
import com.visme.app.data.dto.ProjectDataDto;
import com.visme.app.data.dto.UserDataDto;
import com.visme.app.model.request.ProjectRequestDto;
import com.visme.app.model.request.UserRequestDto;
import com.visme.app.model.response.ProjectResponseDto;
import com.visme.app.model.response.UserResponseDto;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

@Component
public class ModelMapperHelper {

    public ProjectResponseDto buildProjectResponseDto(ProjectDataDto dto) {

        return new ProjectResponseDto(
                dto.getId(),
                dto.getTitle(),
                dto.getType(),
                dto.getFavorite(),
                dto.getCreatedAt(),
                dto.getModifiedAt());
    }

    public ProjectResponseDto buildProjectResponseDto(Project dto) {

        return new ProjectResponseDto(
                dto.getId(),
                dto.getTitle(),
                dto.getType(),
                dto.getFavorite(),
                dto.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli(),
                dto.getModifiedAt().toInstant(ZoneOffset.UTC).toEpochMilli());
    }

    public ProjectDataDto buildProjectDataDto(ProjectRequestDto dto) {

        ProjectDataDto dataDto = new ProjectDataDto();
        dataDto.setId(dto.getId());
        dataDto.setTitle(dto.getTitle());
        dataDto.setType(dto.getType());
        dataDto.setFavorite(dto.getFavorite());
        return dataDto;
    }

    public ProjectDataDto buildProjectDataDto(Project domain) {

        ProjectDataDto dataDto = new ProjectDataDto();
        dataDto.setId(domain.getId());
        dataDto.setTitle(domain.getTitle());
        dataDto.setType(domain.getType());
        dataDto.setFavorite(domain.getFavorite());
        dataDto.setCreatedAt(domain.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli());
        dataDto.setModifiedAt(domain.getModifiedAt().toInstant(ZoneOffset.UTC).toEpochMilli());

        return dataDto;
    }

    public Project buildProject(ProjectDataDto dto) {

        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setType(dto.getType());

        return project;
    }

    public UserDataDto buildUserDataDto(User user) {

        UserDataDto dto = new UserDataDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());

        return dto;
    }

    public UserResponseDto buildUserResponseDto(UserDataDto u) {

        UserResponseDto dto = new UserResponseDto();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setEmail(u.getEmail());
        dto.setPassword(u.getPassword());

        return dto;
    }

    public UserDataDto buildUserDataDto(UserRequestDto dto) {
        return new UserDataDto(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword());
    }
}
