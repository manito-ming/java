package createEla;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Elasticsearch {
    private TransportClient client;
    private IndexRequest source;
    private Logger logger = LoggerFactory.getLogger(Elasticsearch.class);

    public final static String HOST = "127.0.0.1";

    public final static int PORT = 9300;//http请求的端口是9200，客户端是9300
    @Before
    public void before() throws Exception {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();
        client = new TransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
                9300));
        client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
                9300));
        System.out.println("success connect");
    }
    public void createUser(){
        List<String> jsonUser= DataFactory.getJsonUser();
        for (int i = 0; i < jsonUser.size(); i++) {
            System.out.println(jsonUser.get(i));
            IndexResponse response = client.prepareIndex("user", "Userinfo",String.valueOf(i)).setSource(jsonUser.get(i)).get();
            if (response.isCreated()) {
                System.out.println("创建成功!");
            }
        }
        client.close();
    }
    public void createShop(){
        List<String> jsonShop= DataFactory.getJsonShop();
        for (int i = 0; i < jsonShop.size(); i++) {
            System.out.println(jsonShop.get(i));
            IndexResponse response = client.prepareIndex("product", "product",String.valueOf(i)).setSource(jsonShop.get(i)).get();
            if (response.isCreated()) {
                System.out.println("创建成功!");
            }
        }
        client.close();
    }
    public void createCourse(){
        List<String> jsonCourse= DataFactory.getJsonCourse();
        for (int i = 0; i < jsonCourse.size(); i++) {
            System.out.println(jsonCourse.get(i));
            IndexResponse response = client.prepareIndex("course", "course",String.valueOf(i)).setSource(jsonCourse.get(i)).get();
            if (response.isCreated()) {
                System.out.println("创建成功!");
            }
        }
        client.close();
    }
    public void createGift(){
        List<String> jsonGift= DataFactory.getJsonGift();
        for (int i = 0; i < jsonGift.size(); i++) {
            System.out.println(jsonGift.get(i));
            IndexResponse response = client.prepareIndex("gift", "Gift",String.valueOf(i)).setSource(jsonGift.get(i)).get();
            if (response.isCreated()) {
                System.out.println("创建成功!");
            }
        }
        client.close();
    }
    @Test
    public void test1(){
//        createUser();
//        createShop();
//        createCourse();
        createGift();
    }
    //更新
    @Test
    public void testUpdate() throws IOException {
        XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
        //每一个filed里面存的就是列名和列值
        jsonBuild.startObject().field("cid", "0004").field("pt",230 )
                .endObject();

        UpdateResponse response = client.prepareUpdate("course", "course", "AWcdQKpSchA3hQVg6Vqw").setDoc(jsonBuild).get();
        System.out.println("索引名称："+response.getIndex());
        System.out.println("类型："+response.getType());
        System.out.println("文档ID："+response.getId()); // 第一次使用是1
    }

    @Test
    public void testSearch()
    {
        String index="user";
        String type="Userinfo";
        SearchResponse searchResponse = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.matchAllQuery()) //查询所有
//                .setQuery(QueryBuilders.boolQuery()
//                        .must(QueryBuilders.matchQuery("uquestion", uquestion))//查询uquestion为139的
//                        .must(QueryBuilders.matchQuery("province", province)))//查询省份为江苏的
//                .setQuery(QueryBuilders.matchQuery("uquestion", "12599").operator(Operator.AND)) //根据tom分词查询name,默认or
//                .setQuery(QueryBuilders.matchQuery("province", "江苏").operator(Operator.AND)) //根据tom分词查询name,默认or
//                .setQuery(QueryBuilders.multiMatchQuery("tom", "name", "age")) //指定查询的字段
//                //.setQuery(QueryBuilders.queryString("name:to* AND age:[0 TO 19]")) //根据条件查询,支持通配符大于等于0小于等于19
//                //.setQuery(QueryBuilders.termQuery("name", "tom"))//查询时不分词
//                .setSearchType(SearchType.QUERY_THEN_FETCH)
//                .setFrom(1).setSize(1)//分页
                //.addSort("age", SortOrder.DESC)//排序
                .get();

        SearchHits hits = searchResponse.getHits();
        long total = hits.getTotalHits();
        System.out.println(total);
        SearchHit[] searchHits = hits.hits();
        for(SearchHit s : searchHits)
        {
            System.out.println(s.getSourceAsString());
            JSONObject jsonObject = JSON.parseObject(s.getSourceAsString());
            System.out.println("uid: "+jsonObject.getString("uid"));
            String []logindex=s.getSourceAsString().split(",");

        }
    }
    //商品销量降序查询
    @Test
    public void testShop()
    {
        String index="product";
        String type="product";
        String pt="20";
        SearchResponse searchResponse = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.matchAllQuery())
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setFrom(1).setSize(1)//分页
                .addSort("snum", SortOrder.DESC)//排序
                .get();

        SearchHits hits = searchResponse.getHits();
        long total = hits.getTotalHits();
        System.out.println(total);
        SearchHit[] searchHits = hits.hits();
        for(SearchHit s : searchHits)
        {
            System.out.println(s.getSourceAsString());
            String []logindex=s.getSourceAsString().split(",");

        }
    }
    //商品价格降序查询
    @Test
    public void testjShop()
    {
        String index="product";
        String type="product";

        SearchResponse searchResponse = client.prepareSearch(index)
                .setTypes(type)
//                .setQuery(QueryBuilders.matchAllQuery()) //查询所有
                .setSearchType(SearchType.QUERY_THEN_FETCH)
//                .setFrom(1).setSize(1)//分页
                .addSort("pt", SortOrder.DESC)//排序
                .get();

        SearchHits hits = searchResponse.getHits();
        long total = hits.getTotalHits();
        System.out.println("查询总数据： "+total);
        SearchHit[] searchHits = hits.hits();
        for(SearchHit s : searchHits)
        {
            System.out.println(s.getSourceAsString());
        }
    }
    //删除数据
    @Test
    public void testDelete() {
        DeleteResponse response = client.prepareDelete("user", "Userinfo","AWcdTuLFchA3hQVg6V9F")
                .get();
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        System.out.println(index + " : " + type + ": " + id + ": " + version);
    }
}
