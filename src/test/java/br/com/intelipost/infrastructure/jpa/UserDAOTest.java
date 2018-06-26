package br.com.intelipost.infrastructure.jpa;

import br.com.intelipost.domain.*;
import br.com.intelipost.infrastructure.dao.UserDAO;
import br.com.intelipost.infrastructure.jpa.entities.UserEntity;
import br.com.intelipost.infrastructure.redis.UserCacheRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
        Mockito.verify(userCacheRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }
}
