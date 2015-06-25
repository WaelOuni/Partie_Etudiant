
/**
 * Created by Wael on 26/03/2015.
 */
package rnu.iit.waelgroup.student.Models;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class JsonParserUpdateBD {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    final String TAG = "JsonParser.java";

    public void setUrlFromJson(String url, ArrayList<NameValuePair> nameValuePairs) {
        // make HTTP request
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            Log.i(nameValuePairs.get(0).getName(), nameValuePairs.get(0).getValue());

         //   Log.i(nameValuePairs.get(9).getName(), nameValuePairs.get(9).getValue());

            httpClient.execute(httpPost);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}