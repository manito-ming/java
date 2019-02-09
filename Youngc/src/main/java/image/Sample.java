package image;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "15055096";
    public static final String API_KEY = "46EAxoyeRdK6o8l1VUREmdc3";
     public static final String SECRET_KEY = "0ijUzrFgSBGhteXhKK0RNIBoeKfzSbZm";

     //通用文字识别
    public static void sample(AipOcr client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地路径
        String image = "/home/mzh/图片/timg1.jpeg";
        JSONObject res = client.basicGeneral(image, options);
        System.out.println(res.toString(2));

//        // 参数为二进制数组
//        byte[] file = readFile("test.jpg");
//        res = client.basicGeneral(file, options);
//        System.out.println(res.toString(2));
//
//        // 通用文字识别, 图片参数为远程url图片
//        JSONObject res = client.basicGeneralUrl(url, options);
//        System.out.println(res.toString(2));

    }

    public  static AipOcr init() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "/home/mzh/图片/timg1.jpeg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
        return client;

    }
    //通用文字识别
    public static void sample1(AipOcr client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "true");


        // 参数为本地路径
        String image = "/home/mzh/图片/wenzi.jpeg";
        String image1 = "/home/mzh/图片/sp.jpeg";

        JSONObject res = client.basicAccurateGeneral(image1, options);
        System.out.println(res.toString(2));

////        // 参数为二进制数组
//        byte[] file = readFile("test.jpg");
//        res = client.basicAccurateGeneral(file, options);
//        System.out.println(res.toString(2));
    }
    //识别网络图片
    public void sample2(AipOcr client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_language", "true");


        // 参数为本地路径
        String image = "/home/mzh/图片/sp.jpeg";
        JSONObject res = client.webImage(image, options);
        System.out.println(res.toString(2));
    }

    //身份证
    public static void sample3(AipOcr client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");

        String idCardSide = "back";

        // 参数为本地路径
        String image = "/home/mzh/图片/timg sf.jpeg";
        JSONObject res = client.idcard(image, idCardSide, options);
        System.out.println(res.toString(2));

    }


    //手写文字识别
    public static void sample4(){
        String filepath = "sp.jpeg";
        FileInputStream ips = null;
        byte[] imgbyte = null;
        try {
             ips = new FileInputStream(filepath);
            int img_size = ips.available();
            imgbyte = new byte[img_size];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
        AipOcr client= init();
        sample(client);
        System.out.println("=========================");
        sample1(client);
            System.out.println("=========================");
        sample3(client);
    }
}