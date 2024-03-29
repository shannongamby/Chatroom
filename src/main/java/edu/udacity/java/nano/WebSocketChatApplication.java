package edu.udacity.java.nano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class WebSocketChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketChatApplication.class, args);
    }

    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("index")
    public ModelAndView index(String username, HttpServletRequest request) {
        if (StringUtils.isEmptyOrWhitespace(username)) { username = "Anon"; }
        return new ModelAndView("chat")
                .addObject("username", username)
                .addObject("webSocketUrl", "ws://" + request.getLocalName() + ":" + request.getServerPort() + "/chat");
    }
}
