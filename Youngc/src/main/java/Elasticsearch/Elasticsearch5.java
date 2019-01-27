package Elasticsearch;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import createEla.DataFactory;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;



public class Elasticsearch5 {
    public static TransportClient client = null;
    public final static String HOST = "127.0.0.1";
    public final static int PORT = 9300;
     InetSocketTransportAddress node =null;
     //创建连接
     @Before
     public void getClient() {
         {
             try {
                 node = new InetSocketTransportAddress(InetAddress.getByName(HOST), PORT);
                 Settings settings = Settings.builder()
                         .put("cluster.name", "elasticsearch")
                         .build();
                 client = new PreBuiltTransportClient(settings);
                 client.addTransportAddress(node);
                 System.out.println("创建成功");
             } catch (UnknownHostException e) {
                 System.out.println("创建数据库中出现错误");
                 e.printStackTrace();
             }
         }

     }
    @Test
        public   void createUser(){
        List<XContentBuilder> jsonUser= DataFactory.getJsonUser();
        XContentBuilder jsonBuild = null;
        try {
            jsonBuild = XContentFactory.jsonBuilder();
            //每一个filed里面存的就是列名和列值
            jsonBuild.startObject().field("N", "asd").field("B", "asd").field("E","asda")
                    .field("BPH","asd").field("FC","asd").field("ADD", "asda")
                    .field("CM", "Asdasd").field("GM", "asdas").field("BC", "asdas")
                    .field("PWD", "asda").field("HD", "asasd").endObject();


        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonUser.size(); i++) {
            System.out.println(jsonUser.get(i).toString());
            IndexResponse response = client.prepareIndex("user", "Userinfo",String.valueOf(i)).setSource(jsonBuild).get();

        }
        client.close();
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
                .setFrom(1).setSize(1)//分页
//                .addSort("age", SortOrder.DESC)//排序
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
    @Test
    public void addIndex1() throws IOException {
        IndexResponse response = client.prepareIndex("msg", "tweet", "1").setSource(XContentFactory.jsonBuilder()
                .startObject().field("userName", "张三")
                .field("sendDate", new Date())
                .field("msg", "你好李四")
                .endObject()).get();

    }

    private XContentBuilder getIndexMapping() throws IOException {
        XContentBuilder mapping = XContentFactory.jsonBuilder();
        mapping.startObject()
                .startObject("index")
                .startObject("analyzer")
                .startObject("pinyin_analyzer")
                .field("tokenizer", "my_pinyin")
                .endObject()
                .startObject("default")
                .field("tokenizer", "ik_max_word")
                .endObject()
                .endObject()
                .startObject("tokenizer")
                .startObject("my_pinyin")
                .field("type", "pinyin")
                // 拼音首字母单独开一个
                .field("keep_separate_first_letter", true)
                .field("keep_full_pinyin", true)
                .field("keep_original", true)
                .field("limit_first_letter_length", 16)
                .field("lowercase", true)
                .field("remove_duplicated_term", true)
                .endObject().endObject().endObject().endObject().endObject();
        return mapping;
    }



    public static void main(String[] args) {
//        createUser();
    }

}
