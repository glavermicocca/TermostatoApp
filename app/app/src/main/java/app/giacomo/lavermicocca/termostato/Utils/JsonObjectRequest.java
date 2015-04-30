package app.giacomo.lavermicocca.termostato.Utils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Giacomo on 30/11/2014.
 */
public class JsonObjectRequest extends JsonRequest<JSONObject> {

    /**
     * 36.     * Creates a new request.
     * 37.     * @param url URL to fetch the JSON from
     * 38.     * @param listener Listener to receive the JSON response
     * 39.     * @param errorListener Error listener, or null to ignore errors.
     * 40.
     */
    public JsonObjectRequest(String url, String bodyPost, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, bodyPost, listener, errorListener);
    }

    public JsonObjectRequest(int methos, String url, String bodyPost, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        super(methos, url, bodyPost, listener, errorListener);
    }

    /**
     * Ricotruisce la risposta e restituisce un oggetto json
     * @param response
     * @return
     */
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    //In your extended request class
    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
            volleyError = error;
        }

        return volleyError;
    }

    @Override
    public String getBodyContentType()
    {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }
}
