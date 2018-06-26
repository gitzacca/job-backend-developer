package br.com.intelipost.domain;

import br.com.intelipost.domain.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

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

    @Test
    public void mustFindAnUserByEmail() {
        //given
        Email email = new Email("test@intelipost.com");
        User user = new User("User intelipost", email);

        Mockito.when(userRepository.findBy(email)).thenReturn(user);
        //when
        User result = userService.findBy(email);

        //then
        Assert.assertEquals(email.getValue(), result.getEmail());
        Assert.assertEquals(user.getName(), result.getName());
    }

    @Test(expected = UserNotFoundException.class)
    public void mustThrowExceptionWhenUserNotFound() {
        //given
        Email email = new Email("test@intelipost.com");
        Mockito.when(userRepository.findBy(email)).thenThrow(UserNotFoundException.class);

        //when
        userService.findBy(email);
    }

    @Test
    public void mustReturnAListOfUsers() {
        //given
        Pageable pageable = Pageable.unpaged();
        Page<User> users = Mockito.mock(Page.class);
        Mockito.when(users.getContent()).thenReturn(Arrays.asList(new User("User intelipost", new Email("test@intelipost.com"))));

        Mockito.when(userRepository.findAll(pageable)).thenReturn(users);

        //when
        Page<User> result = userService.findAll(pageable);

        //then
        Assert.assertEquals(1, result.getContent().size());
    }
}
