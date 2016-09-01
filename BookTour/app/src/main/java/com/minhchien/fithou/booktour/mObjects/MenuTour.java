package com.minhchien.fithou.booktour.mObjects;

import java.io.Serializable;

/**
 * Created by Admin on 25-Jun-16.
 */
public class MenuTour implements Serializable {
    String HinhAnh;
    String TenTour;
    int Gia;
    String ThoiGian;
    int PK_TourID;
    String NgayKhoiHanh;
    String DiemXuatPhat;
    String LichTrinh;
    String TTGiaTour;
    String DiemDen;
    String PhuongTien;
    String Tour;

    public int getPK_TourID() {
        return PK_TourID;
    }

    public void setPK_TourID(int PK_TourID) {
        this.PK_TourID = PK_TourID;
    }

    public String getNgayKhoiHanh() {
        return NgayKhoiHanh;
    }

    public void setNgayKhoiHanh(String ngayKhoiHanh) {
        NgayKhoiHanh = ngayKhoiHanh;
    }

    public String getDiemXuatPhat() {
        return DiemXuatPhat;
    }

    public void setDiemXuatPhat(String diemXuatPhat) {
        DiemXuatPhat = diemXuatPhat;
    }

    public String getLichTrinh() {
        return LichTrinh;
    }

    public void setLichTrinh(String lichTrinh) {
        LichTrinh = lichTrinh;
    }

    public String getTTGiaTour() {
        return TTGiaTour;
    }

    public void setTTGiaTour(String TTGiaTour) {
        this.TTGiaTour = TTGiaTour;
    }

    public String getDiemDen() {
        return DiemDen;
    }

    public void setDiemDen(String diemDen) {
        DiemDen = diemDen;
    }

    public String getPhuongTien() {
        return PhuongTien;
    }

    public void setPhuongTien(String phuongTien) {
        PhuongTien = phuongTien;
    }

    public String getTour() {
        return Tour;
    }

    public void setTour(String tour) {
        Tour = tour;
    }

    public MenuTour() {

    }

    public MenuTour(String hinhAnh, String tenTour, int gia, String thoiGian) {
        HinhAnh = hinhAnh;
        TenTour = tenTour;
        Gia = gia;
        ThoiGian = thoiGian;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getTenTour() {
        return TenTour;
    }

    public void setTenTour(String tenTour) {
        TenTour = tenTour;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }
}
