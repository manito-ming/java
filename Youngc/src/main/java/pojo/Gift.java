package pojo;

public class Gift {
    private String gid;
    private String col;
    private String mod;
    private int pt;
//    private String
    private String size;
    private String g_stock;
    private String g_sold;


    public Gift(  String col, String mod, int pt, String size, String g_stock, String g_sold) {

        this.col = col;
        this.mod = mod;
        this.pt = pt;
        this.size = size;
        this.g_stock = g_stock;
        this.g_sold = g_sold;
    }

    public String getG_stock() {
        return g_stock;
    }

    public void setG_stock(String g_stock) {
        this.g_stock = g_stock;
    }

    public String getG_sold() {
        return g_sold;
    }

    public void setG_sold(String g_sold) {
        this.g_sold = g_sold;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }



    public int getPt() {
        return pt;
    }

    public void setPt(int pt) {
        this.pt = pt;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
