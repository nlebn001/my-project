package kz.gamma.my.project.web.rest;

import kz.gamma.my.project.model.DefaultResponse;
import kz.gamma.my.project.model.User;
import kz.gamma.my.project.service.UserService;
import kz.gamma.my.project.utils.gson.GsonHelper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class BaseRest {

    private static final Logger log = Logger.getLogger(BaseRest.class);
    protected static final String BAD_REQUEST_BODY_MESS = "Bad request body";

    @Autowired
    protected UserService userService;


    protected User getUser() {
        return userService.getUser(getUserName());
    }

    protected String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    protected List<String> getPermissions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    protected void returnResponse(HttpServletResponse response, DefaultResponse obj) {
        PrintWriter out = null;
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(GsonHelper.toJson(obj));
            out.close();
        } catch (Exception ex) {
            log.error("response error", ex);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            if (out != null)
                out.close();
        }
    }


    @ExceptionHandler(Exception.class)
    public void returnError(HttpServletResponse response, Exception ex) {
        PrintWriter out = null;
        try {
            printException(ex);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            out = response.getWriter();
//            DefaultResponse resp = new DefaultResponse("Internal server error");
            //todo remove on production
            DefaultResponse resp = new DefaultResponse(ExceptionUtils.getStackTrace(ex));
            out.println(GsonHelper.toJson(resp));
        } catch (Exception e) {
            log.error("error", ex);
            if (out != null)
                out.close();
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            out = response.getWriter();
            DefaultResponse resp = new DefaultResponse("Forbidden");
            out.println(GsonHelper.toJson(resp));
        } catch (Exception e) {
            log.error("accessDenied", e);
            if (out != null)
                out.close();
        }
    }


    //todo not logic errors with noraml statuses OK without panic

    protected void returnError(HttpServletResponse response, String error) {
        PrintWriter out = null;
        try {
            out = getErrorPrintWriter(response, error);
            out.println(GsonHelper.toJson(new DefaultResponse(error)));
        } catch (Exception e) {
            log.error("returnError", e);
            if (out != null)
                out.close();
        }
    }

    protected void returnError(HttpServletResponse response, Object error) {
        PrintWriter out = null;
        try {
            out = getErrorPrintWriter(response, error);
            out.println(GsonHelper.toJson(error));
        } catch (Exception e) {
            log.error("returnError", e);
            if (out != null)
                out.close();
        }
    }

    private PrintWriter getErrorPrintWriter(HttpServletResponse response, Object error) throws IOException {
        log.error(error);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return response.getWriter();
    }


    void printException(Exception e) {
        e.printStackTrace();
        log.error("", e);
    }
}
