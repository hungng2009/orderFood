package android.project.order.DTO;

public class DoAnPhu_DTO {
    int MaDoAnPhu;
    String TenDoAnPhu;

    public DoAnPhu_DTO(int maDoAnPhu, String tenDoAnPhu) {
        MaDoAnPhu = maDoAnPhu;
        TenDoAnPhu = tenDoAnPhu;
    }

    public DoAnPhu_DTO() {
    }

    public int getMaDoAnPhu() {
        return MaDoAnPhu;
    }

    public void setMaDoAnPhu(int maDoAnPhu) {
        MaDoAnPhu = maDoAnPhu;
    }

    public String getTenDoAnPhu() {
        return TenDoAnPhu;
    }

    public void setTenDoAnPhu(String tenDoAnPhu) {
        TenDoAnPhu = tenDoAnPhu;
    }
}
