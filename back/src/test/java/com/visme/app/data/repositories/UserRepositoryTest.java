package com.visme.app.data.repositories;

import com.visme.app.data.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        User alex = new User();
        alex.setName("Alex");
        alex.setEmail("Alex@mail.com");
        alex.setPassword("pass");

        entityManager.persist(alex);
        entityManager.flush();

        // when
        List<User> found = userRepository.findAll();

        // then
        assertThat(found.get(0).getName()).isEqualTo(alex.getName());
    }
}
