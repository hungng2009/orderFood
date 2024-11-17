package android.project.order.DTO;

public class GioHangDTO {
    int masp;
    String anhsp;
    String tensp;
    String tendoanphu;
    int giasp;
    int soluongsp;

    public GioHangDTO(int masp, String anhsp, String tensp, String tendoanphu, int giasp, int soluongsp) {
        this.masp = masp;
        this.anhsp = anhsp;
        this.tensp = tensp;
        this.tendoanphu = tendoanphu;
        this.giasp = giasp;
        this.soluongsp = soluongsp;
    }

    public GioHangDTO() {
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getTendoanphu() {
        return tendoanphu;
    }

    public void setTendoanphu(String tendoanphu) {
        this.tendoanphu = tendoanphu;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
}
