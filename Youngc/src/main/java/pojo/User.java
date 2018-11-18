package pojo;

public class User {
    private String uid;//id
    private String N;//姓名
    private String B;//生日
    private String E;//邮箱
    private String BPH;//备用电话
    private String FC;//一完成课时
    private String ADD;//地址
    private int CM;//当前课时数
    private int GM;//当前积分数
    private String BC;//已购课程
    private String PWD;//密码
    private String HD;//头像地址

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public User(String n, String b, String e, String BPH, String FC, String ADD, int CM, int GM, String BC, String PWD, String HD) {

        N = n;
        B = b;
        E = e;
        this.BPH = BPH;
        this.FC = FC;
        this.ADD = ADD;
        this.CM = CM;
        this.GM = GM;
        this.BC = BC;
        this.PWD = PWD;
        this.HD = HD;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getN() {
        return N;
    }

    public void setN(String n) {
        N = n;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getBPH() {
        return BPH;
    }

    public void setBPH(String BPH) {
        this.BPH = BPH;
    }

    public String getFC() {
        return FC;
    }

    public void setFC(String FC) {
        this.FC = FC;
    }

    public String getADD() {
        return ADD;
    }

    public void setADD(String ADD) {
        this.ADD = ADD;
    }

    public int getCM() {
        return CM;
    }

    public void setCM(int CM) {
        this.CM = CM;
    }

    public int getGM() {
        return GM;
    }

    public void setGM(int GM) {
        this.GM = GM;
    }

    public String getBC() {
        return BC;
    }

    public void setBC(String BC) {
        this.BC = BC;
    }
}
