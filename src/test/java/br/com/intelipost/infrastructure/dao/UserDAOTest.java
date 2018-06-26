package br.com.intelipost.infrastructure.dao;

import br.com.intelipost.domain.*;
import br.com.intelipost.domain.exceptions.UserNotFoundException;
import br.com.intelipost.infrastructure.jpa.UserDataRepository;
import br.com.intelipost.infrastructure.jpa.entities.UserEntity;
import br.com.intelipost.infrastructure.redis.UserCache;
import br.com.intelipost.infrastructure.redis.UserCacheRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

public class UserDAOTest {

    @Mock
    private UserDataRepository dataRepository;

    @Mock
    private UserCacheRepository userCacheRepository;

    private UserDAO userDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userDAO = new UserDAO(dataRepository, userCacheRepository);
    }

    @Test
    public void mustPersistAnUser() {
        //given
        User user = new User("User Test",
                new Email("test@intelipost.com"),
                new Credentials("user.intelipost", new Password("intelispost123")));
        //when
        userDAO.save(user);

        //then
        Mockito.verify(dataRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
        Mockito.verify(userCacheRepository, Mockito.times(1)).save(Mockito.any(UserCache.class));
    }

    @Test
    public void mustReturnAnUserFromCacheRepository() {
        //given
        Email email = new Email("test@intelipost.com");
        User user = new User("User intelipost", email);
        UserCache userCache = new UserCache(user);

        Mockito.when(userCacheRepository.findByEmail(email.getValue())).thenReturn(userCache);

        //when
        User result = userDAO.findBy(email);

        //then
        Mockito.verify(dataRepository, Mockito.times(0)).save(Mockito.any(UserEntity.class));
        Assert.assertEquals(email.getValue(), result.getEmail());
        Assert.assertEquals(user.getName(), result.getName());
    }

    @Test(expected = UserNotFoundException.class)
    public void mustThrowExceptionWhenUserNotFound() {
        //given
        Email email = new Email("test@intelipost.com");
        Mockito.when(userCacheRepository.findByEmail(email.getValue())).thenReturn(null);
        Mockito.when(dataRepository.findByEmail(email.getValue())).thenReturn(null);

        //when
        userDAO.findBy(email);
    }

}
