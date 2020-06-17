package kz.gamma.my.project.security;

import kz.gamma.my.project.model.DefaultResponse;
import kz.gamma.my.project.service.UserService;
import kz.gamma.my.project.utils.gson.GsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
//        response.getWriter().append(e.getMessage());
//        response.setStatus(401);
        PrintWriter out = null;
        System.out.println("response error message: " + e.getMessage() + "");
        try {
            Map<String, Object> props = new HashMap<>();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");

            response.setStatus(HttpStatus.BAD_REQUEST.value());
            out = response.getWriter();
            DefaultResponse resp = new DefaultResponse(e.getMessage());
            out.println(GsonHelper.toJson(resp));
            out.close();
        } catch (Exception ex) {
            if (out != null)
                out.close();
        }
    }
}

