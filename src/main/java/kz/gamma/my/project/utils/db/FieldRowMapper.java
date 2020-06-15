package kz.gamma.my.project.utils.db;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FieldRowMapper<T> extends BeanPropertyRowMapper<T> {

    private Map<String, Field> mappedFields;
    private Class<T> mappedClass;
    private Set<String> mappedProperties;

    public FieldRowMapper(Class<T> dealerGroupClass) {
        super(dealerGroupClass);
    }

    @Override
    protected void initialize(Class<T> mappedClass) {
        this.mappedClass = mappedClass;
        this.mappedFields = new HashMap<>();
        this.mappedProperties = new HashSet<>();
        Field[] declaredFields = mappedClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            this.mappedFields.put(declaredField.getName().toLowerCase(), declaredField);
            String underscoredName = underscoreName(declaredField.getName()).toLowerCase();
            if (!declaredField.getName().toLowerCase().equals(underscoredName))
                this.mappedFields.put(underscoredName, declaredField);

            this.mappedProperties.add(declaredField.getName());
        }
    }

    protected String underscoreName(String name) {
        if (!StringUtils.hasLength(name)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(name.substring(0, 1).toLowerCase());
        for (int i = 1; i < name.length(); i++) {
            String s = name.substring(i, i + 1);
            String slc = s.toLowerCase();
            if (!s.equals(slc)) {
                result.append("_").append(slc);
            } else {
                result.append(s);
            }
        }
        return result.toString();
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        Assert.state(this.mappedClass != null, "Mapped class was not specified");
        T mappedObject = BeanUtils.instantiate(this.mappedClass);
        ConfigurablePropertyAccessor bw = PropertyAccessorFactory.forDirectFieldAccess(mappedObject);

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Set<String> populatedProperties = (isCheckFullyPopulated() ? new HashSet<>() : null);

        for (int index = 1; index <= columnCount; index++) {
            String column = JdbcUtils.lookupColumnName(rsmd, index);
            column = column.replaceAll("_", "");
            Field f = this.mappedFields.get(column.replaceAll(" ", "").toLowerCase());
            if (f != null) {
                try {
                    Object value = getColumnValue(rs, index, f);
                    if (f.getType().isAssignableFrom(LocalDate.class)) {
                        Date sqlDate = (Date) value;
                        if (sqlDate != null) {
                            bw.setPropertyValue(f.getName(), sqlDate.toLocalDate());
                        }

                    } else {
                        bw.setPropertyValue(f.getName(), value);
                    }
                    if (populatedProperties != null) {
                        populatedProperties.add(f.getName());
                    }
                } catch (NotWritablePropertyException ex) {
                    throw new DataRetrievalFailureException(
                            "Unable to map column " + column + " to property " + f.getName(), ex);
                }
            }
        }

        if (populatedProperties != null && !populatedProperties.equals(this.mappedProperties)) {
            throw new InvalidDataAccessApiUsageException("Given ResultSet does not contain all fields " +
                    "necessary to populate object of class [" + this.mappedClass + "]: " + this.mappedProperties);
        }

        return mappedObject;
    }

    private Object getColumnValue(ResultSet rs, int index, Field f) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index, f.getType());
    }

    public static <T> FieldRowMapper<T> newInstance(Class<T> mappedClass) {
        return new FieldRowMapper(mappedClass);
    }
}