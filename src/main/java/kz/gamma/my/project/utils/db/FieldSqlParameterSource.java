package kz.gamma.my.project.utils.db;

import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FieldSqlParameterSource extends AbstractSqlParameterSource {

    private Map<String, Object> props = new HashMap<>();

    public FieldSqlParameterSource(Object object) {
        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
            String name = field.getName();
            try {
                Object value = field.get(object);
                props.put(name, value);
            } catch (IllegalAccessException ignored) {
            }
        }
    }

    @Override
    public boolean hasValue(String paramName) {
        return props.containsKey(paramName);
    }

    @Override
    public Object getValue(String paramName) throws IllegalArgumentException {
        Object result = props.get(paramName);
        if (result != null) {
            if (result instanceof LocalDate) {
                return Date.valueOf((LocalDate) result);
            } else {
                return result;
            }
        } else {
            return null;
        }
    }
}