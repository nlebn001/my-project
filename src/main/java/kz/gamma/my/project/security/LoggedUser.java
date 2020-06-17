package kz.gamma.my.project.security;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LoggedUser implements HttpSessionBindingListener {

    private String username;
    private AuthDetails details;

    public LoggedUser(String username,  AuthDetails details) {
        this.username = username;
        this.details = details;
    }

    public LoggedUser() {
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
       // List<LoggedUserResponse> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        
        boolean putValue = false;

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String lastRequestDate = dateFormat.format(now);
    }
    
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}