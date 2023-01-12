package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller 
public class AuthController {

    //   @GetMapping("/")
//   public String Logn() {
//	   return "home";
//   }
//    @PostMapping("/")
//    public String home() {
//        return "login";
//    }
//
//}
//    @GetMapping("/")
//    public String Logn() {
//        return "home";
//    }

    @GetMapping("/")
    public String process(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);

        return "home";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", defaultValue = "false") boolean loginError,
                        @RequestParam(value = "invalid-session", defaultValue = "false") boolean invalidSession,
                        final Model model, HttpSession session) {

                    if (invalidSession) {
                        model.addAttribute("invalidSession", "You already have an active session. We do not allow multiple active sessions");
                    }
        return "login";
    }

    @GetMapping("/logout")
    public String Logo() {
        return "login";
    }

}

