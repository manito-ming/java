package createEla;

/**
 * Created by andilyliao on 16-8-9.
 */
import pojo.Course;
import pojo.Gift;
import pojo.Shop;
import pojo.User;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {
    public static DataFactory dataFactory = new DataFactory();

    public static List<String> getJsonUser() {
        List<String> list = new ArrayList<String>();
        String data1 = Json.UserJson(new User( "xiaowang", "1997-02-05 15:54:51", "13159452@qq.com","13515234567","50%","山西",1000,200,"1","123456","/home/mzh/tupian"));
        String data2 = Json.UserJson(new User( "xiaoli", "1997-03-05 15:54:51", "13259452@qq.com","13515232367","50%","太远",900,200,"1","123456","/home/mzh/tupian"));
        String data3 = Json.UserJson(new User( "xiaozhao", "1997-04-05 15:54:51", "13359452@qq.com","13515454567","50%","天津",600,500,"1","123456","/home/mzh/tupian"));
        list.add(data1);
        list.add(data2);
        list.add(data3);
        return list;
    }

    public static List<String> getJsonShop() {
        List<String> list = new ArrayList<String>();
        String data1 = Json.ShopJson(new Shop( 50, "U盘", "金士顿",100));
        String data2 = Json.ShopJson(new Shop( 20, "U盘", "三星",200));
        String data3 = Json.ShopJson(new Shop( 10, "U盘", "索尼",150));
        list.add(data1);
        list.add(data2);
        list.add(data3);
        return list;
    }

    public static List<String> getJsonGift() {
        List<String> list = new ArrayList<String>();
        String data1 = Json.GiftJson(new Gift( "红色", "型号1", 10,"1","1000","200"));
        String data2 = Json.GiftJson(new Gift( "蓝色", "型号2", 10,"1","300","232"));
        String data3 = Json.GiftJson(new Gift("黑色", "型号3", 10,"1","1200","213"));
        list.add(data1);
        list.add(data2);
        list.add(data3);
        return list;
    }

    public static List<String> getJsonCourse() {
        List<String> list = new ArrayList<String>();
        String data1 = Json.CourseJson(new Course(200,"/home/mzh"));
        String data2 = Json.CourseJson(new Course(300,"/home/mzh"));
        String data3 = Json.CourseJson(new Course( 514,"/home/mzh"));
        list.add(data1);
        list.add(data2);
        list.add(data3);
        return list;
    }

}
