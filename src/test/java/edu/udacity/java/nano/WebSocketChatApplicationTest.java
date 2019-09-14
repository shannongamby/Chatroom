package edu.udacity.java.nano;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(WebSocketChatApplication.class)

public class WebSocketChatApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldVisitLoginPage() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Chat Room")))
                .andExpect(content().string(containsString("Login")))
                .andExpect(view().name("/login"));
    }

    @Test
    public void shouldVisitChatPage() throws Exception {
        this.mockMvc.perform(get("/index")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome")))
                .andExpect(content().string(containsString("SEND")))
                .andExpect(content().string(containsString("CLEAR")))
                .andExpect(view().name("/chat"));
    }
}