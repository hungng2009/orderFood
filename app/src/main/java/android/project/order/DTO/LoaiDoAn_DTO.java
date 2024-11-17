package android.project.order.DTO;

public class LoaiDoAn_DTO {
    int maloai;
    String tenloai;

    public LoaiDoAn_DTO(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public LoaiDoAn_DTO() {
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
