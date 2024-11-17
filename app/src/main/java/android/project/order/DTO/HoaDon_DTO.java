package android.project.order.DTO;

public class HoaDon_DTO {
    private int mahoadon;
    private String email;
    private String hoten;
    private String SDT;
    private String diachinhan;
    private String thucdon;
    private int tongtien;
    private String ngaydathang;
    private String trangthai;
    private String thanhtoan;

    public HoaDon_DTO() {
    }

    public HoaDon_DTO(int mahoadon, String email, String hoten, String SDT, String diachinhan, String thucdon, int tongtien, String ngaydathang, String trangthai, String thanhtoan) {
        this.mahoadon = mahoadon;
        this.email = email;
        this.hoten = hoten;
        this.SDT = SDT;
        this.diachinhan = diachinhan;
        this.thucdon = thucdon;
        this.tongtien = tongtien;
        this.ngaydathang = ngaydathang;
        this.trangthai = trangthai;
        this.thanhtoan = thanhtoan;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiachinhan() {
        return diachinhan;
    }

    public void setDiachinhan(String diachinhan) {
        this.diachinhan = diachinhan;
    }

    public String getThucdon() {
        return thucdon;
    }

    public void setThucdon(String thucdon) {
        this.thucdon = thucdon;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgaydathang() {
        return ngaydathang;
    }

    public void setNgaydathang(String ngaydathang) {
        this.ngaydathang = ngaydathang;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(String thanhtoan) {
        this.thanhtoan = thanhtoan;
    }
}
