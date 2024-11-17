package android.project.order.DTO;

public class User_DTO {
    int MaTV;
    String MaND;
    String HoTen;
    String MatKhau;
    String Email;
    String NamSinh;
    String SDT;
    String typeAcc;

    public User_DTO(String maTV, String maND, String hoTen, String matKhau, String email, String namSinh, String SDT, String typeAcc) {
        MaTV = Integer.parseInt(maTV);
        MaND = maND;
        HoTen = hoTen;
        MatKhau = matKhau;
        Email = email;
        NamSinh = namSinh;
        this.SDT = SDT;
        this.typeAcc = typeAcc;
    }

    public User_DTO(String SDT, String hoTen, String email){
    this.HoTen = hoTen;
   this.Email = email;
    this.SDT = SDT;
}
    public String getTypeAcc() {
        return typeAcc;
    }

    public void setTypeAcc(String typeAcc) {
        this.typeAcc = typeAcc;
    }

    public User_DTO() {
    }

    public int getMaTV() {
        return MaTV;
    }

    public void setMaTV(int maTV) {
        MaTV = maTV;
    }

    public String getMaND() {
        return MaND;
    }

    public void setMaND(String maND) {
        MaND = maND;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
