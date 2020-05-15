package com.visme.app.data.services;

import com.visme.app.data.domain.User;
import com.visme.app.data.dto.UserDataDto;
import com.visme.app.data.repositories.UserRepository;
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
public class UserDataService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapperHelper modelMapperHelper;

    /**
     * Create user
     *
     * @param dto - user data transfer model
     * @return - user identifier
     */
    public UserDataDto create(UserDataDto dto) {

        log.info("Creating user from data [{}]", dto);

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        user = repository.save(user);

        log.debug("...created user with ID:[{}]", user.getId());

        return modelMapperHelper.buildUserDataDto(user);
    }

    /**
     * Edit user
     *
     * @param dto - user data transfer model
     */
    public UserDataDto edit(UserDataDto dto) {

        log.info("Editing user [$dto.id] from data:[{}]", dto);

        User user = this.findById(dto.getId());

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        repository.save(user);

        log.debug("...successfully edited user");

        return modelMapperHelper.buildUserDataDto(user);
    }

    /**
     * Delete user
     *
     * @param id - user identifier
     */
    public void delete(Long id) {

        log.info("Deleting user by ID:[{}]", id);

        repository.deleteById(id);
    }

    /**
     * Find all users
     *
     * @return - all users from data base
     */
    public List<UserDataDto> findAllUsers() {

        log.info("Finding all users");

        List<User> users = repository.findAll();

        log.debug("...found [{}] users in database", users.size());

        return users.stream()
                .map(modelMapperHelper::buildUserDataDto)
                .collect(Collectors.toList());

    }

    public User findById(Long id) throws EntityNotFoundException {

        log.info("Find user by ID:[{}]", id);

        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found by ID" + id));

        log.debug("...found user [{}]", user);

        return user;
    }
}
