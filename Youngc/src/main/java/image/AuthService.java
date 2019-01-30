package image;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

//获取token类
public class AuthService {

        /**
         * 获取权限token
         * @return 返回示例：
         * {
         * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
         * "expires_in": 2592000
         * }
         */


        /**
         * 获取API访问token
         * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
        */
        public static JSONObject getAuth() {
            // 官网获取的 API Key 更新为你注册的
            String clientId = "46EAxoyeRdK6o8l1VUREmdc3";
            // 官网获取的 Secret Key 更新为你注册的
            String clientSecret = "0ijUzrFgSBGhteXhKK0RNIBoeKfzSbZm";
            // 获取token地址
            String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
            String getAccessTokenUrl = authHost
                    // 1. grant_type为固定参数
                    + "grant_type=client_credentials"
                    // 2. 官网获取的 API Key
                    + "&client_id=" + clientId
                    // 3. 官网获取的 Secret Key
                    + "&client_secret=" + clientSecret;
            JSONObject jsonObject = ReqAndResp(getAccessTokenUrl,"POST");
            return jsonObject;

        }
        public static JSONObject ReqAndResp(String url,String method){
            JSONObject jsonObject = null;
            String contentType = "application/x-www-form-urlencoded";
            URL realUrl = null;
            try {
                realUrl = new URL(url);
                // 打开和URL之间的连接
                HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
                connection.setRequestMethod(method);
                connection.setRequestProperty("Content-Type", contentType);
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String result = "";
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                jsonObject =JSON.parseObject(result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }

    public static void main(String[] args) {
        getAuth();
    }

}
