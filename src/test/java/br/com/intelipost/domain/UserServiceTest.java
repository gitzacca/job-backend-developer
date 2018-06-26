package br.com.intelipost.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void mustSaveAnUser() {
        //given
        User user = new User("User Test",
                new Email("test@intelipost.com"),
                new Credentials("test@intelipost.com", new Password("intelispost123")));
        //when
        userService.save(user);

        //then
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }
}
