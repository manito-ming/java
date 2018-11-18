package createEla;

import pojo.Course;
import pojo.Gift;
import pojo.Shop;
import pojo.User;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

public class Json {

    // Java个人信息对象转json对象
    public static String UserJson(User user) {
        String jsonData = null;
        try {
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
            //每一个filed里面存的就是列名和列值
            jsonBuild.startObject().field("N", user.getN()).field("B", user.getB()).field("E",user.getE())
                    .field("BPH",user.getBPH()).field("FC",user.getFC()).field("ADD", user.getADD())
                    .field("CM", user.getCM()).field("GM", user.getGM()).field("BC", user.getBC())
            .field("PWD", user.getPWD()).field("HD", user.getHD()).endObject();

            jsonData = jsonBuild.string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonData;//此时存储的应该是一个大json文件
    }

    // Java商品对象转json对象
    public static String ShopJson(Shop shop) {
        String jsonData = null;
        try {
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
            //每一个filed里面存的就是列名和列值
            jsonBuild.startObject().field("pt", shop.getPt())
                    .field("type",shop.getType()).field("brand",shop.getBrand()).field("snum",shop.getSnum()).endObject();

            jsonData = jsonBuild.string();
            //System.out.println(jsonData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonData;//此时存储的应该是一个大json文件
    }

    // Java礼物对象转json对象
    public static String GiftJson(Gift gift) {
        String jsonData = null;
        try {
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
            //每一个filed里面存的就是列名和列值
            jsonBuild.startObject().field("pt", gift.getPt()).field("col",gift.getCol())
                    .field("mod",gift.getMod()).field("size",gift.getSize())
                    .field("g_stock", gift.getG_stock()).field("g_sold", gift.getG_sold()).endObject();

            jsonData = jsonBuild.string();
            //System.out.println(jsonData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonData;//此时存储的应该是一个大json文件
    }

    // Java课程对象转json对象
    public static String CourseJson(Course course) {
        String jsonData = null;
        try {
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
            //每一个filed里面存的就是列名和列值
            jsonBuild.startObject().field("pt", course.getPt()).field("cls_json", course.getCls_json())
                   .endObject();

            jsonData = jsonBuild.string();
            //System.out.println(jsonData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonData;//此时存储的应该是一个大json文件
    }
}
