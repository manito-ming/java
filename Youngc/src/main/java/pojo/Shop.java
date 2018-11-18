package pojo;

public class Shop {
    private String sid;
    private int pt;

    private String type;
    private String brand;
    private int snum;//购买量

    private String col;//颜色
    private String  size;//大小


    public Shop( int pt, String type, String brand, int snum) {
        this.pt = pt;
        this.type = type;
        this.brand = brand;
        this.snum = snum;
    }

    public Shop(String sid, int pt, String type, String brand, int snum, String col, String size) {
        this.sid = sid;
        this.pt = pt;
        this.type = type;
        this.brand = brand;
        this.snum = snum;
        this.col = col;
        this.size = size;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getPt() {
        return pt;
    }

    public void setPt(int pt) {
        this.pt = pt;
    }

    public int getSnum() {
        return snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


}
