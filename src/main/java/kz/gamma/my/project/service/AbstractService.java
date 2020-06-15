package kz.gamma.my.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

@Service
public abstract class AbstractService {

    public static final long SYSTEM_USER_ID = 1;
    protected JdbcTemplate db;                  //  
    protected NamedParameterJdbcTemplate npDb;
//    protected TransactionTemplate transactionTemplate;
    //@Autowired
    //protected TransactionTemplate transactionTemplate;

    /*
     * Метод возвращает строку поиска по параметрам.
     * Упрощенный аналог метода {@code getFilterScript(List<?>, String, boolean)}
     * Подразумевается, что переменная по которому будет производиться поиск будет маппиться по названию :filter
     *
     * @param {@code List<?>} filters список параметров, по которому производится поиск
     * @param isConditionBefore если равен true то строку поиска нужно подставлять после WHERE
     *
     * @return {@code String} строка для SQL подзапроса
     * */

    protected String getSimpleSqlWithFilters(List<String> filters, Boolean isConditionBefore) {

        if (filters == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        result.append((isConditionBefore ? " AND (" : " WHERE ("));

        String prefix = "";

        for (Object f : filters) {
            result.append(prefix).append(f).append(" LIKE :filter");
            prefix = " OR ";
        }

        result.append(")");

        return result.toString();
    }


    protected static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equalsIgnoreCase(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }

    protected static Long getLong(ResultSet rs, String strColName) throws SQLException {
        long nValue = rs.getLong(strColName);
        return rs.wasNull() ? null : nValue;
    }

    protected static Integer getInteger(ResultSet rs, String strColName) throws SQLException {
        int nValue = rs.getInt(strColName);
        return rs.wasNull() ? null : nValue;
    }

    protected static Double getDouble(ResultSet rs, String strColName) throws SQLException {
        double nValue = rs.getDouble(strColName);
        return rs.wasNull() ? null : nValue;
    }

    //@Autowired
    public void setScheduleDataSource(@Qualifier("webraDS") DataSource dataSource) {
        this.db = new JdbcTemplate(dataSource);
        this.npDb = new NamedParameterJdbcTemplate(dataSource);
//        transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
    }

    protected String getFilterScript(List<?> filters, String filterStr, boolean isConditionExist) {
        String result = null;
        if (filters != null && filterStr != null && filterStr != "") {
            for (Object _filter : filters) {
                if (result == null) {
                    result = isConditionExist ? " and (" : " WHERE (";
                } else {
                    result += " or ";
                }
               // result += ((Filter) _filter).getDbColumn() + " like :filter";
            }
        }
        if (result == null)
            return "";
        else
            return result + ")";
    }

    protected String getUpdatedFilter(String filter) {
        return "%" + filter + "%";
    }
}
