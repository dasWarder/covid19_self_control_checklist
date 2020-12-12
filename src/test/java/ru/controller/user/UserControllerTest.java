package ru.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.controller.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.testData.UserTestData.ADMIN_ID;
import static ru.testData.UserTestData.USER_ID;

class UserControllerTest extends AbstractControllerTest {


    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/profile/" + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/profile/" + ADMIN_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}