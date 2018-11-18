package pojo;

public class Course {
    private String cls_id;
    private int pt;
    private String cls_json;

    public String getCls_json() {
        return cls_json;
    }

    public void setCls_json(String cls_json) {
        this.cls_json = cls_json;
    }

    public Course( int pt, String cls_json) {
        this.pt = pt;
        this.cls_json = cls_json;
    }

    public String getCls_id() {
        return cls_id;
    }

    public void setCls_id(String cls_id) {
        this.cls_id = cls_id;
    }

    public int getPt() {
        return pt;
    }

    public void setPt(int pt) {
        this.pt = pt;
    }
}
