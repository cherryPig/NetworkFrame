package best.networkframe;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by BG207369 on 2016/3/15.
 */
public class HttpUtil {
    public static final String TAG = HttpUtil.class.getSimpleName();
    private static HttpUtil instance;
    private RequestQueue mRequestQueue;

    private HttpUtil(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context, new OkHttpRequestStack());
            mRequestQueue.start();
        }
    }

    public static HttpUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (HttpUtil.class) {
                if (instance == null)
                    instance = new HttpUtil(context);
            }
        }
        return instance;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        if (mRequestQueue != null) {
            req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
            mRequestQueue.add(req);
        }
    }

    public <T> void addToRequestQueue(Request<T> req) {
        if (mRequestQueue == null)
            new Throwable("please init request queue");
        req.setTag(TAG);
        mRequestQueue.add(req);
    }

    public void cancelAllRequest(String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}
