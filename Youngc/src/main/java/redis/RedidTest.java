package redis;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedidTest {
    private Jedis jedis;

    @Before
    public void setup() {
        //连接redis服务器，192.168.0.100:6379
        jedis = new Jedis("127.0.0.1", 6379);
    }
    @Test
    public void testcourse() {
        //-----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("courseid", "1");
        map.put("sales","50");
        map.put("type","python");
        map.put("bread","三星");
        map.put("courseprice", "22");
        jedis.hmset("course", map);
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("course", "courseid", "courseprice","type","sales", "bread");
        System.out.println(rsmap);
    }

    @Test
    public void testuser() {
        //-----添加数据----------
        Map<String, String> map = new HashMap<String, String>();

        map.put("N", "dsf"); //用户姓名
        map.put("B", "1997-2-5:12:52");//用户生日
        map.put("E", "213234@COM");//用户邮箱
        map.put("BPH", "13920510359");//备用电话
        map.put("FC", "220");//已完成课程数
        map.put("PWD", "123456");//密码
        map.put("ADDR", "山西省");//用户地址
        map.put("CM", "100");//当前课时数
        map.put("GM" , "1000");//当前积分数
        map.put("HD", "/home/mzh/图片/1.png");//头像地址
        map.put("YG", "1");//已购课程
        jedis.hmset("222", map);
        List<String> rsmap = jedis.hmget("111", "N", "B","E", "BPH","FC", "PWD", "ADDR","CM","GM","HD","YG");
        System.out.println(rsmap);
    }
    }
//public class RedidTest {
//    private static String HOST = "127.0.0.1";
//    private static int PORT = 6379;
//
//    private static JedisPool jedisPool = null;
//
//    /*
//     * 初始化redis连接池
//     * */
//    private static void initPool(){
//        try {
//            JedisPoolConfig config = new JedisPoolConfig();
//
//            jedisPool = new JedisPool(config, HOST, PORT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*
//     * 获取jedis实例
//     * */
//    public synchronized static Jedis getJedis() {
//        try {
//            if(jedisPool == null){
//                initPool();
//            }
//            Jedis jedis = jedisPool.getResource();
//
//            return jedis;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public static byte[] serizlize(Object object){
//        ObjectOutputStream oos = null;
//        ByteArrayOutputStream baos = null;
//        try {
//            baos = new ByteArrayOutputStream();
//            oos = new ObjectOutputStream(baos);
//            oos.writeObject(object);
//            byte[] bytes = baos.toByteArray();
//            return bytes;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if(baos != null){
//                    baos.close();
//                }
//                if (oos != null) {
//                    oos.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return null;
//    }
//    /*
//     * 反序列化
//     * */
//    public static Object deserialize(byte[] bytes){
//        ByteArrayInputStream bais = null;
//        ObjectInputStream ois = null;
//        try{
//            bais = new ByteArrayInputStream(bytes);
//            ois = new ObjectInputStream(bais);
//            return ois.readObject();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return null;
//    }
//    public static void set(String key,String value){
//        Jedis jedis = getJedis();
//        jedis.set(key, value);
//        jedis.close();
//    }
//    public static String get(String key){
//        Jedis jedis = getJedis();
//        String value = jedis.get(key);
//        jedis.close();
//        return value;
//    }
//    public static void setObject(String key,Object object){
//        Jedis jedis = getJedis();
//        jedis.set(key.getBytes(), serizlize(object));
//        jedis.close();
//    }
//    public static Object getObject(String key){
//        Jedis jedis = getJedis();
//        byte[] bytes = jedis.get(key.getBytes());
//        jedis.close();
//        return deserialize(bytes);
//    }
//
//
//
//    @Test
//    public void testString(){
//        set("user:1", "sisu");
//        String user = get("user:1");
//        Assert.assertEquals("sisu", user);
//    }
//
//    @Test
//    public void testObject(){
//        setObject("user:2",new User(2,"lumia"));
//        User user = (User)getObject("user:2");
//        Assert.assertEquals("lumia", user.getName());
//    }
//
//
//
//    public static void setJsonString(String key,Object object){
//        Jedis jedis = getJedis();
//        jedis.set(key, JSON.toJSONString(object));
//        jedis.close();
//    }
//    public static Object getJsonObject(String key,Class clazz){
//        Jedis jedis = getJedis();
//        String value = jedis.get(key);
//        jedis.close();
//        return JSON.parseObject(value,clazz);
//    }
//
//
//    @Test
//    public void testObject2(){
//        setJsonString("user:3", new User(3,"xiaoming"));
//        User user = (User)getJsonObject("user:3",User.class);
//        Assert.assertEquals("xiaoming", user.getName());
//    }
//
//}
