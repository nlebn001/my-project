package kz.gamma.my.project.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/principal")
    public
    @ResponseBody
    Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/pages/index.html";
    }


    @RequestMapping(value = "auth", method = RequestMethod.GET)
    public String login() {
        return "/pages/login.html";
    }

    @RequestMapping(value = "restore-pass", method = RequestMethod.GET)
    public String restorePass(@RequestParam("token") String token) {
        return "/pages/login.html";
    }

    @RequestMapping(value = "subscribe-user", method = RequestMethod.GET)
    public String subscribeUser(@RequestParam("token") String token) {
        return "/pages/login.html";
    }

    @RequestMapping(value = "confirm-user", method = RequestMethod.GET)
    public String confirmUser(@RequestParam("token") String token) {
        return "/pages/login.html";
    }

    @RequestMapping(value = "confirm-email", method = RequestMethod.GET)
    public String confirmEmail(@RequestParam("token") String token) {
        return "/pages/login.html";
    }

    @RequestMapping(value = "confirm-authentication-email", method = RequestMethod.GET)
    public String subscribeUser(@RequestParam("token") String token,
                                @RequestParam("login") String login) {
        return "/pages/login.html";
    }

    @RequestMapping(value = "session", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String invalidSession() {
        return "/pages/login.html";
    }

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }

}
