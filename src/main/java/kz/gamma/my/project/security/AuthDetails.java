package kz.gamma.my.project.security;

import kz.gamma.my.project.model.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class AuthDetails extends WebAuthenticationDetails {

    final String authType;
    final String sign;
    final String data;
    final String token;
    private User user;
    private String userName;


    AuthDetails(HttpServletRequest request) {
        super(request);
        authType = request.getParameter("authType");
        sign = request.getParameter("sign");
        data = request.getParameter("data");
        token = request.getParameter("token");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthType() {
        return authType;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}