package android.project.order.DTO;

public class ThongkeTop_DTO {
    String Tenmonan;
    int soluong;

    public ThongkeTop_DTO(String tenmonan, int soluong) {
        Tenmonan = tenmonan;
        this.soluong = soluong;
    }

    public ThongkeTop_DTO() {
    }

    public String getTenmonan() {
        return Tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        Tenmonan = tenmonan;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
