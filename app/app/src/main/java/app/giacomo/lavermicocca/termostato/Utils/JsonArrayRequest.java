package app.giacomo.lavermicocca.termostato.Utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Giacomo on 30/11/2014.
 */
public class JsonArrayRequest extends JsonRequest<JSONArray> {

    /**
     * 36.     * Creates a new request.
     * 37.     * @param url URL to fetch the JSON from
     * 38.     * @param listener Listener to receive the JSON response
     * 39.     * @param errorListener Error listener, or null to ignore errors.
     * 40.
     */
    public JsonArrayRequest(int method, String url, String bodyData, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(method, url, bodyData, listener, errorListener);
    }

    /**
     * Passing some request headers
     * */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        return headers;
    }


    /**
     * Ricotruisce la risposta e restituisce un oggetto json
     * @param response
     * @return
     */
    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONArray ja = new JSONArray(jsonString);
            Response<JSONArray> responseArray = Response.success(ja, HttpHeaderParser.parseCacheHeaders(response));
            return responseArray;
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    //In your extended request class
    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        Log.e("", this.getBodyContentType());
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
            volleyError = error;
        }

        return volleyError;
    }
}
