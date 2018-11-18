package pojo;

public class Vm {
    private String uid;
    private String type;
    private String cpu;
    private String mem;
    private String disk ;
    private String paydate;

    public Vm(String uid, String type, String cpu, String mem, String disk, String paydate) {
        this.uid = uid;
        this.type = type;
        this.cpu = cpu;
        this.mem = mem;
        this.disk = disk;
        this.paydate = paydate;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String pid) {
        this.uid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }
}
