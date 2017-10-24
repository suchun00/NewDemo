package com.sc.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by suchun on 2017/7/24.
 */
@Entity
public class NetAddress {
    @Id
    private Long id;
    private String netName;
    private String ipAddress;
    public String getIpAddress() {
        return this.ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getNetName() {
        return this.netName;
    }
    public void setNetName(String netName) {
        this.netName = netName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 726917341)
    public NetAddress(Long id, String netName, String ipAddress) {
        this.id = id;
        this.netName = netName;
        this.ipAddress = ipAddress;
    }
    @Generated(hash = 528811687)
    public NetAddress() {
    }

}
