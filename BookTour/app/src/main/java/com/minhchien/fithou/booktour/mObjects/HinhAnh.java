package com.minhchien.fithou.booktour.mObjects;

/**
 * Created by Admin on 27-Jul-16.
 */
public class HinhAnh {
    int PK_HinhAnhID;
    String TenAnh;
    String LinkAnh;

    public int getPK_HinhAnhID() {
        return PK_HinhAnhID;
    }

    public void setPK_HinhAnhID(int PK_HinhAnhID) {
        this.PK_HinhAnhID = PK_HinhAnhID;
    }

    public String getTenAnh() {
        return TenAnh;
    }

    public void setTenAnh(String tenAnh) {
        TenAnh = tenAnh;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }
}
