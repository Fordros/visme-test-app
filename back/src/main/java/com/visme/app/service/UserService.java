package com.visme.app.service;

import com.visme.app.data.dto.UserDataDto;
import com.visme.app.data.services.UserDataService;
import com.visme.app.model.request.UserRequestDto;
import com.visme.app.model.response.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private ModelMapperHelper modelMapperHelper;

    /**
     * Get all users
     * @return
     */
    public List<UserResponseDto> getAllUsers() {

        List<UserDataDto> users = userDataService.findAllUsers();

        return users.stream()
                .map(modelMapperHelper::buildUserResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Create user
     * @param dto
     * @return
     */
    public UserResponseDto create(UserRequestDto dto){

        UserDataDto dataDto = modelMapperHelper.buildUserDataDto(dto);

        dataDto =  userDataService.create(dataDto);

        return modelMapperHelper.buildUserResponseDto(dataDto);
    }

    /**
     * Edit user
     * @param dto
     * @return
     */
    public UserResponseDto edit(UserRequestDto dto){

        UserDataDto dataDto = modelMapperHelper.buildUserDataDto(dto);

        dataDto = userDataService.edit(dataDto);

        return modelMapperHelper.buildUserResponseDto(dataDto);
    }

    /**
     * Delete user
     * @param id
     */
    public void delete(Long id){

        userDataService.delete(id);
    }
}
