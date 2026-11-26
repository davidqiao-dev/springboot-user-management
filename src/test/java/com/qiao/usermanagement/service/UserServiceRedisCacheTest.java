package com.qiao.usermanagement.service;

import com.qiao.usermanagement.entity.UserEntity;
import com.qiao.usermanagement.model.User;
import com.qiao.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceRedisCacheTest {

    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @MockitoSpyBean
    private UserRepository repository;

    private Long userId;

    @BeforeEach
    void clearCache() {
        Cache cache = cacheManager.getCache("user");
        if (cache != null) {
            cache.clear();
        }

        UserEntity saved = repository.save(
                new UserEntity(
                        "Alice",
                        "alice" + System.nanoTime() + "@test.com",
                        18
                )
        );
        userId = saved.getId();
    }

    @AfterEach
    void cleanDB() {
        repository.deleteById(userId);
    }

    @Test
    void shouldPutUserIntoCache() {

        User user = service.getUser(userId);
        assertThat(user).isNotNull();

        Cache.ValueWrapper wrapper = cacheManager
                .getCache("users")
                .get(userId);

        assertThat(wrapper).isNotNull();
        assertThat(wrapper.get()).isEqualTo(user);
    }

    @Test
    void shouldUpdateCacheAfterUpdate() {

        service.getUser(userId);

        User updatedUser = new User(
                userId,
                "Updated Name",
                "updated@test.com",
                29
        );

        service.updateUser(userId, updatedUser);

        User cachedUser = (User) cacheManager
                .getCache("users")
                .get(userId)
                .get();

        assertThat(cachedUser.name())
                .isEqualTo("Updated Name")
        ;

        assertThat(cachedUser.email())
                .isEqualTo("updated@test.com")
        ;
    }

    @Test
    void shouldRemoveCacheAfterDelete() {

        service.getUser(userId);

        assertThat(cacheManager.getCache("users").get(userId))
                .isNotNull();

        service.deleteUser(userId);

        assertThat(cacheManager.getCache("users").get(userId))
                .isNull();

    }

    @Test
    void shouldHitDatabaseOnlyOnce() {
        service.getUser(userId);
        service.getUser(userId);

        verify(
                repository,
                times(1)
        ).findById(userId);
    }
}