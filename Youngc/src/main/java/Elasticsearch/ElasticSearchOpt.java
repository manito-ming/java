package Elasticsearch;


import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearchOpt {

    private TransportClient client = null;
    public ElasticSearchOpt() {
        // 设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        // 创建client
        try {
            client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
    public  void createMapping() throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("course")
                .startObject("properties")
                .startObject("title").field("index", "not_analyzed").field("type", "text").field("analyzer", "ik").field("searchAnalyzer", "ik").endObject()
                .startObject("score").field("index", "not_analyzed").field("type", "text").field("analyzer", "ik").field("searchAnalyzer", "ik").endObject()
                .startObject("url").field("index", "not_analyzed").field("type", "text").field("analyzer", "ik").field("searchAnalyzer", "ik").endObject()
                .startObject("content").field("index", "not_analyzed").field("type", "text").field("analyzer", "ik").field("searchAnalyzer", "ik").endObject()
                .endObject()
                .endObject()
                .endObject();
        System.out.println(builder.string());
        PutMappingRequest mapping = Requests.putMappingRequest("course").type("course").source(builder);
        client.admin().indices().putMapping(mapping).actionGet();
        client.close();
    }

    public static void main(String[] args) throws Exception {
        ElasticSearchOpt elasticSearchOpt = new ElasticSearchOpt();
        elasticSearchOpt.createMapping();
    }


}


