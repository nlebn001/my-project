package kz.gamma.my.project.web.rest;


import kz.gamma.my.project.model.CaOrganization;
import kz.gamma.my.project.model.DefaultResponse;
import kz.gamma.my.project.model.Organization;
import kz.gamma.my.project.model.User;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/org")
public class RestOrganization extends BaseRest {

    private static final Logger log = Logger.getLogger(RestUser.class);
    private static final int DN_MAX_LENGTH = 1023;

    @RequestMapping(value = "/list", method = GET)
    public void list(HttpServletResponse response, @RequestParam(value = "specUser", required = false) Boolean specUser) {

        User user = null;
        ArrayList<User> users = null;
        Set<CaOrganization> orgs = new HashSet<>();
        for (int i = 0; i < (int) (10 * Math.random() + 1); i++) {
            users = new ArrayList<User>();
            for (int j = 0; j < (int) (10 * Math.random() + 1); j++) {
                user = new User();
                users.add(user);
            }
            orgs.add(new CaOrganization(users));
        }
        returnResponse(response, new DefaultResponse(orgs.toString()));      // {"data":"Hello, I\u0027m USER","timestamp":1591448151696}
    }


}
