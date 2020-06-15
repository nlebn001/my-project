package kz.gamma.my.project.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultResponse {

    private Object data;
    private Long timestamp;

    public DefaultResponse(Object data) {
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public DefaultResponse(Object list, int count) {
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("count", count);
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public DefaultResponse(String key, Object value) {
        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put(key, value);
        this.data = map;
        this.timestamp = System.currentTimeMillis();
    }
}