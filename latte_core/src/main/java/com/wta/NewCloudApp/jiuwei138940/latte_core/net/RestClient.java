package com.wta.NewCloudApp.jiuwei138940.latte_core.net;
import android.content.Context;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.IError;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.IFailure;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.IRequest;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.ISuccess;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.RequestCallback;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.download.DownloadHandler;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.LatteLoader;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.LoaderStyle;

import java.io.File;
import java.io.IOException;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.wta.NewCloudApp.jiuwei138940.latte_core.net.HttpMethod.GET;


public class RestClient {

    private final String URL;
    // private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    private final IRequest REQUEST;
    private final String DOWNLOAD_DTR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
    private LoaderStyle LOADER_STYLE;
    private Context CONTEXT;
    private Call<String> call = null;

    public static final String REQUEST_ERROR = "error";

    public RestClient(String URL,
                      WeakHashMap<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = URL;
        this.PARAMS = params;
        this.DOWNLOAD_DTR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                // call = RestCreator.getRestService().upload(URL,body);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());

        }
    }

    //同步请求方法
    public String requestSync(HttpMethod method) {
        final RestService service = RestCreator.getRestService();

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                // call = RestCreator.getRestService().upload(URL,body);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }
        if (call != null) {
            try {
                retrofit2.Response<String> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                }else {
                    return REQUEST_ERROR;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }



    public final void get() {
        request(GET);
    }

    public String getSync() {
        return requestSync(GET);
    }

    public final String postSync() {
        if (BODY == null) {
            return requestSync(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return requestSync(HttpMethod.POST_RAW);
        }
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DTR, EXTENSION, NAME, SUCCESS, FAILURE, ERROR, PARAMS)
                .handleDownload();
    }

    public Call<String> getCall() {
        return call;
    }
}
