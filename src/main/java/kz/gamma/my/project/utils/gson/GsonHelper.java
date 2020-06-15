package kz.gamma.my.project.utils.gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import kz.gamma.my.project.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GsonHelper {

    public static String toJson(Object object) {
        GsonBuilder gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer())
                .registerTypeAdapter(java.sql.Date.class, new SqlDateSerializer())
                .registerTypeAdapter(java.sql.Time.class, new SqlTimeSerializer());
        return gson.create().toJson(object);
    }

    public static <T> T fromJson(HttpServletRequest request, Class<T> classe) {
        return fromJson(Utils.toString(request), classe);
    }

    public static <T> T fromJson(String bodyString, Class<T> classe) {
        return gsonBuilder().create().fromJson(bodyString, classe);
    }

    public static <T> List<T> fromJsonList(JsonElement element, Class<T> classe) {
        return gsonBuilder().create().fromJson(element, TypeToken.getParameterized(ArrayList.class, classe).getType());
    }

    private static GsonBuilder gsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
    }
}
