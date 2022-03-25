package ru.geekbrains.winter.market.auth;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.geekbrains.winter.market.auth.entities.Role;
import ru.geekbrains.winter.market.auth.services.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    @Test
    public void createAuthTokenTest() throws Exception {
        String jsonRequest = "{\n" +
                "\t\"username\": \"username\",\n" +
                "\t\"password\": \"pass\"\n" +
                "}";
        Role role = new Role();
        role.setName("ROLE_ADMIN");

        UserDetails userDetails = new User("username",
                "$2a$04$1agcjUGWxT2WpNZVVYblZ.zZyMrT0kxKMHZptSEgp6DmuertrXulu",
                Collections.singleton(new SimpleGrantedAuthority(role.getName())));

        Mockito.doReturn(userDetails).when(userService).loadUserByUsername("username");
        mvc.perform(
                post("/auth")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();
        String jsonRequestUnAuth = "{\n" +
                "\t\"username\": \"username12\",\n" +
                "\t\"password\": \"pass\"\n" +
                "}";
        mvc.perform(
                post("/auth")
                        .content(jsonRequestUnAuth)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isUnauthorized());
    }
}
