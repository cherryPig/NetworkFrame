package best.networkframe;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import best.networkframe.util.JsonUtil;

/**
 * Created by BG207369 on 2016/3/15.
 */
public class JsonRequest<T> extends Request<T> {

    private Class<T> clazz;
    private Response.Listener mListener;

    public JsonRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    protected Map<String, String> getHeader() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json; charset=UTF-8");
        return headers;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            /**
             * 得到返回的数据
             */
            String jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            /**
             * 转化成对象
             */

            Response.success(JsonUtil.fromJson(jsonStr, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(T response) {

    }

    class SuccessResponse implements com.android.volley.Response.Listener<JSONObject> {

        @Override
        public void onResponse(JSONObject response) {

        }
    }

    class ErrorResponse implements com.android.volley.Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }
}
