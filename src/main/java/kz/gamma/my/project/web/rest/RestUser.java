package kz.gamma.my.project.web.rest;

import kz.gamma.my.project.model.CaOrganization;
import kz.gamma.my.project.model.DefaultResponse;

import kz.gamma.my.project.model.User;
import kz.gamma.my.project.service.AllRandomMethods;
import kz.gamma.my.project.service.RandomOrganizationCreator;
import kz.gamma.my.project.service.ThreadCreator;
import kz.gamma.my.project.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/user")
public class RestUser extends BaseRest {
    private static final Logger log = Logger.getLogger(RestUser.class);
    private static final int DN_MAX_LENGTH = 1023;

    @Autowired
    private UserService userService;

//    @Autowired
    private AllRandomMethods allRandomMethods;

    //@Autowired
    protected TransactionTemplate transactionTemplate;
    private Map<String, String> forgotTokens = new HashMap<>();

    // @PreAuthorize("hasAnyAuthority('SPEC_USERS, USERS')")
    @RequestMapping(value = "/list", method = GET)
    public void list(HttpServletResponse response, @RequestParam(value = "specUser", required = false) Boolean specUser) {
        // set default value for specUser. If not specified then set to false
        // and hence return just users
        //User curUser = getUser();
        //List<Map<String, Object>> userDetails = userService.userDetails();

        ThreadCreator threadCreator = new ThreadCreator("ThreadCreator");
        threadCreator.start();



                returnResponse(response, new DefaultResponse(RandomOrganizationCreator.randomCreate().toString()));      // {"data":"Hello, I\u0027m USER","timestamp":1591448151696}

    }

    @RequestMapping(value = "/create", method = POST)
    public void create(HttpServletResponse response, HttpServletRequest request) {
        User curUser = getUser();
    }



    @PreAuthorize("hasAuthority('USERS')")
    @RequestMapping(value = "/get", method = GET)
    public void getUser(HttpServletResponse response, @RequestParam(value = "id", required = false) Long id) {
        User user = getUser();
        Map<String, Object> result = new HashMap<>();

        returnResponse(response, new DefaultResponse(result));
    }



}
