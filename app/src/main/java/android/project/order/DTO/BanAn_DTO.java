package android.project.order.DTO;

public class BanAn_DTO {
    int MaBan;
    String Tenban;

    public BanAn_DTO(int maBan, String tenban) {
        MaBan = maBan;
        Tenban = tenban;
    }

    public BanAn_DTO() {
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public String getTenban() {
        return Tenban;
    }

    public void setTenban(String tenban) {
        Tenban = tenban;
    }
}
