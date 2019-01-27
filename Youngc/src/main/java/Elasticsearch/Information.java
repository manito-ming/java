package Elasticsearch;


import org.apache.log4j.Logger;

import java.util.HashMap;

public class Information {
    private static Logger logger = Logger.getLogger(Information.class);
    private transient final String indexName = "information";
    private transient String type;
    private transient String id;
//    private final String index = "not_analyzed";
    private String tag;
    private String mainType;
    private String subType;
    private String title;
    private String content;
    private int score ;

    public Information(HashMap<String,String> informationMap){
        this.type = informationMap.get("type");
        this.title = informationMap.get("title");
        this.content = informationMap.get("content");
        this.id = informationMap.get("id");
        this.tag = informationMap.get("tag");
        this.mainType = informationMap.get("mainType");
        this.subType = informationMap.get("subType");
        if(informationMap.get("score") == null){
            this.score = 0;
        }else {
            try {
                this.score = Integer.parseInt(informationMap.get("score"));
            } catch (Exception e) {
                logger.error("cast number error:  informationMap.get(\"score\"):\t" + informationMap.get("score"), e);
            }
        }
    }

//    public String getIndex() {
//        return index;
//    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIndexName() {
        return indexName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Information{" +
                "indexName='" + indexName + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
