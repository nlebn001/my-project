package kz.gamma.my.project.security;


import kz.gamma.my.project.model.DefaultResponse;
import kz.gamma.my.project.utils.gson.GsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            if (authentication.getDetails() instanceof AuthDetails) {
                AuthDetails details = (AuthDetails) authentication.getDetails();
                String ip = details.getRemoteAddress();

                LoggedUser user = new LoggedUser(authentication.getName(), details);
                session.setAttribute("user", user);
            }
        }

        PrintWriter out = null;
        try {

            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            AuthDetails details = (AuthDetails) authentication.getDetails();
            Map<String, Object> props = new HashMap<>();
            DefaultResponse resp;
            resp = new DefaultResponse("OK");

            out.println(GsonHelper.toJson(resp));
            out.close();
        } catch (Exception ex) {
            if (out != null)
                out.close();
        }
    }
}


