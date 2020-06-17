package kz.gamma.my.project.security;

import kz.gamma.my.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthLogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final HttpSession session = request.getSession();
        if (session != null) {
            String ip = "no info";
            session.removeAttribute("user");
            if (authentication != null) {
                String userLogin = "";
                Long userId = null;
                if (authentication.getDetails() instanceof AuthDetails) {
                    AuthDetails details = (AuthDetails) authentication.getDetails();
                    ip = details.getRemoteAddress();
                    User user = details.getUser();
                    if (user != null) {
                        userLogin = user.cmpUserId != null ? user.cmpUserId : user.name;
                        userId = user.id;
                    }
                }
            }
        }

        response.sendRedirect("/auth");
    }
}