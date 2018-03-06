package addressPlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.StringBuilder;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class addressPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
       if (action.equals("address")) {
            String key = args.getString(0);
            String lat= args.getString(1);
            String lon = args.getString(2);
            this.address(key,lat,lon, callbackContext);
            return true;
        }
        return false;
    }

    private void address(String key,String lat,String lon, CallbackContext callbackContext) {
        //这是构建url的过程
        StringBuilder url = new StringBuilder();
        url.append("http://api.map.baidu.com/geocoder/v2/?ak=");
        url.append(key);
        url.append("&location=");
        url.append(lat);
        url.append(",");
        url.append(lon);
        url.append("&output=json&pois=0");
        Log.v("Key",key);
        Log.v("lat",lat);
        Log.v("lon",lon);
        Log.v("url",url.toString());
        //发送Http请求
        HttpClient h = new DefaultHttpClient();
        HttpGet httpG = new HttpGet(url.toString());
        try {
            HttpResponse httpResponse = h.execute(httpG);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                //如果请求成功，取出服务返回的具体内容
                //response里为返回的Json数据
                HttpEntity entity = httpResponse.getEntity();
                String response = EntityUtils.toString(entity);
                //使用Json方法解析json数据
                JSONObject jsonObject = new JSONObject(response);
                JSONObject result = jsonObject.getJSONObject("result");
                JSONObject addressComponent = result.getJSONObject("addressComponent");
                String province = (String) addressComponent.get("province");
                String city = (String) addressComponent.get("city");
                String district = (String) addressComponent.get("district");
                String street = (String) addressComponent.get("street");
                String back = province + city + district + street;
                callbackContext.success(back);
            }
        } catch (Exception e) {

        }
    }
}
