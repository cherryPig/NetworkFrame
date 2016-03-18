package best.networkframe;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by BG207369 on 2016/3/15.
 */
public class OkHttpRequestStack extends HurlStack {
    private OkUrlFactory okUrlFactory;

    public OkHttpRequestStack() {
        this(new OkUrlFactory(new OkHttpClient()));
    }

    public OkHttpRequestStack(OkUrlFactory okUrlFactory) {
        if (okUrlFactory == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.okUrlFactory = okUrlFactory;
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return okUrlFactory.open(url);
    }
}
