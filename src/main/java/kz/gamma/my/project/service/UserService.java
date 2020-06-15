package kz.gamma.my.project.service;

import kz.gamma.my.project.model.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

import static kz.gamma.my.project.utils.db.FieldRowMapper.newInstance;

@Service
public class UserService extends AbstractService {

    public User getUser(String login) {                     //return -> "user with id=" + id + " and name=" + name;
        try {
            return db.queryForObject("SELECT * FROM USERS " +
                            "WHERE (name = ? OR cmp_user_id = ?) " +
                            "AND is_removed = FALSE and is_blocked = FALSE",
                    newInstance(User.class), login, login);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }


    public List<Map<String, Object>> userDetails() {
            return db.queryForList("SELECT * FROM user_details WHERE TYPE = 'USER' AND caOrganizationId=?  ORDER BY created DESC");
    }



}


