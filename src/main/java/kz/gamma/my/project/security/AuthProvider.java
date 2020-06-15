package kz.gamma.my.project.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;

import kz.gamma.my.project.model.*;

import kz.gamma.my.project.model.User;

import kz.gamma.my.project.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.CommunicationException;
import org.springframework.ldap.ServiceUnavailableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.cert.Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class AuthProvider implements AuthenticationProvider {

    private UserService userService;

    private static final Logger log = Logger.getLogger(AuthProvider.class);

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        //  TODO: рефакторинг после продакшна

        // Базовая аутентификация для фронтендеров
        // TODO: удалить закомментировать на продакшне
        String login = auth.getName();
        String password = auth.getCredentials().toString();
        return new UsernamePasswordAuthenticationToken(login, password);
    }

    /**
     * Get elapsed time in minutes
     *
     * @param userDate
     * @return
     */
    private long getElapsedMinutes(Date userDate) {
        Date cur = new Date();
        long diffInMillies = cur.getTime() - userDate.getTime();
        return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Get elapsed time in hours
     *
     * @param userDate
     * @return
     */
    private long getElapsedHours(Date userDate) {
        Date cur = new Date();
        long diffInMillies = cur.getTime() - userDate.getTime();
        return TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }


    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
