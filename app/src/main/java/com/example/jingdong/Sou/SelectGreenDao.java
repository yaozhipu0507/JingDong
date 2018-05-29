package com.example.jingdong.Sou;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SelectGreenDao {
    @Id(autoincrement = true)
    private Long id;
    private String uid;
    private String uname;
    private String selectGoods;
    @Generated(hash = 1062801631)
    public SelectGreenDao(Long id, String uid, String uname, String selectGoods) {
        this.id = id;
        this.uid = uid;
        this.uname = uname;
        this.selectGoods = selectGoods;
    }
    @Generated(hash = 1503253500)
    public SelectGreenDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUname() {
        return this.uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getSelectGoods() {
        return this.selectGoods;
    }
    public void setSelectGoods(String selectGoods) {
        this.selectGoods = selectGoods;
    }
}
