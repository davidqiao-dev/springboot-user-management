package com.qiao.usermanagement.repository;

import com.qiao.usermanagement.entity.UserEntity;
import jakarta.persistence.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class UserRepositoryIntegrationTest {
    @Autowired
    private UserRepository repository;

    @Test
    void shouldPersistAndLoadUser() {
        UserEntity user = new UserEntity();

        user.setName("David");
        user.setEmail("david@example.com");
        user.setAge(45);

        UserEntity savedUser = repository.save(user);

        Long id = savedUser.getId();

        UserEntity loadedUser = repository.findById(id).orElseThrow();

        assertThat(loadedUser.getId()).isEqualTo(id);

        assertThat(loadedUser.getName()).isEqualTo("David");

        assertThat(loadedUser.getEmail()).isEqualTo("david@example.com");

        assertThat(loadedUser.getAge()).isEqualTo(45);
    }

    @Test
    void shouldGenerateIdAutomatically() {
        UserEntity user = new UserEntity();

        user.setName("Tom");
        user.setEmail("tom@example.com");
        user.setAge(30);

        UserEntity savedUser = repository.save(user);
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void shouldRejectDuplicateEmail() {
        UserEntity user1 = new UserEntity();
        user1.setName("David");
        user1.setEmail("duplicate@example.com");
        user1.setAge(45);
        UserEntity user2 = new UserEntity();
        user2.setName("Tom");
        user2.setEmail("duplicate@example.com");
        user2.setAge(30);

        repository.save(user1);

        assertThrows(Exception.class, () -> repository.save(user2));
    }

    @Test
    void shouldVerifyNameColumnLength() {
        UserEntity user = new UserEntity();
        user.setName("A".repeat(100));
        user.setEmail("length@example.com");
        user.setAge(20);
        UserEntity savedUser = repository.save(user);
        assertThat(savedUser).isNotNull();

    }

    @Test
    void shouldVerifyEntityMapping() throws NoSuchFieldException {

        Field emailField = UserEntity.class.getDeclaredField("email");

        Column column = emailField.getAnnotation(Column.class);

        assertThat(column.unique()).isTrue();
        assertThat(column.nullable()).isFalse();
    }
}
