package best.networkframe.util;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bl05498 on 2016/1/27.
 */
public class JsonUtil {
    private final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss:SSSZ";
    private ObjectMapper mObjectMapper;
    private final static JsonUtil instance = new JsonUtil();

    private JsonUtil() {
        mObjectMapper = new ObjectMapper();
//        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
//        dateFormat.setTimeZone(TimeZone.getDefault());
//        mObjectMapper.setDateFormat(dateFormat);
//        mObjectMapper.setTimeZone(DateTimeZone.getDefault().toTimeZone());
//        mObjectMapper.registerModule(new JodaModule());
//        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJson(Object value) {
        try {
            return instance.mObjectMapper.writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JsonUtil", "writeValueAsString:" + e.toString());
            return null;
        }
    }

    public static <T> T fromJson(String json, TypeReference valueTypeRef) {
        try {
            return instance.mObjectMapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JsonUtil", "readValue:" + e.toString());
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return instance.mObjectMapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JsonUtil", "readValue:" + e.toString());
            return null;
        }
    }

    public static <T> T fromJson(String json, JavaType valueType) {
        try {
            return instance.mObjectMapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JsonUtil", "readValue:" + e.toString());
            return null;
        }
    }

    /*
     * 对象List通过Json转成字符串List
     * add by zxl
     */
    public static <T> ArrayList<String> listToJson(List<T> list) {
        ArrayList<String> result = new ArrayList<String>();
        if (null == list || list.size() == 0) {
            return result;
        }
        try {
            for (T t : list) {
                String str = toJson(t);
                result.add(str);
            }
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
            Log.e("JsonUtil", "toJson:" + e.toString());
        }
        return result;
    }

    /*
     * 字符串List通过Json转成对象List
     * add by zxl
     */
    public static <T> List<T> listFromJson(List<String> list, Class<T> valueType) {
        List<T> result = new ArrayList<T>();
        if (null == list || list.size() == 0) {
            return result;
        }
        try {
            for (String str : list) {
                T t = fromJson(str, valueType);
                result.add(t);
            }
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
            Log.e("JsonUtil", "toJson:" + e.toString());
        }
        return result;
    }
}
