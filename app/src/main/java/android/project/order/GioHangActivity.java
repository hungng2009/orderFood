package android.project.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import android.project.order.Adapter.GiohangAdapter;
import android.project.order.DAO.GioHangDAo;
import android.project.order.DAO.User_DAO;
import android.project.order.DTO.GioHangDTO;
import android.project.order.DTO.User_DTO;

public class GioHangActivity extends AppCompatActivity {
    TextView gh_tongtien;
    RecyclerView rc_giohang;
    Button gh_dathang;
    GioHangDAo gioHangDAo;
    List<GioHangDTO> list;
    GiohangAdapter giohangAdapter;
    ImageView giohangback;
    String anh;
    String tenMon;
    int donGia;
    String thongTin;
    StringBuilder invoice;
    StringBuilder doanphu;
    long tongtiengh = 0;
    float tongtienmoi=0;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        rc_giohang = findViewById(R.id.rc_giohang);
        giohangback = findViewById(R.id.giohangback);
        gh_tongtien = findViewById(R.id.gh_tongtien);
        gh_dathang = findViewById(R.id.gh_dathang);
//        tongtienmoi=TT(list);
        gioHangDAo = new GioHangDAo(this);
        list = gioHangDAo.getAll();
        giohangAdapter = new GiohangAdapter(GioHangActivity.this,list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GioHangActivity.this,
                LinearLayoutManager.VERTICAL,false);
        rc_giohang.setLayoutManager(linearLayoutManager);
        rc_giohang.setAdapter(giohangAdapter);
        capNhatTongTien();
        Tensp();

//        TenDoanphu();

        gh_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (list.size()==0){
                   Toast.makeText(GioHangActivity.this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
               }else {
                   User_DAO userDAO = new User_DAO(GioHangActivity.this);
                   User_DTO loggedInUser = userDAO.getCurrentLoggedInUser();
                   Intent intent = new Intent(GioHangActivity.this, DienThongTinTuGHActivity.class);
                   Bundle bundle = new Bundle();
                   bundle.putString("TenMon", String.valueOf(invoice));
                   bundle.putString("Email", loggedInUser.getEmail());
                   bundle.putString("HoTen", loggedInUser.getHoTen());
                   bundle.putString("SDT", loggedInUser.getSDT());
                   bundle.putString("TongTien", String.valueOf(tongtiengh));
                   intent.putExtras(bundle);
                   startActivity(intent);
               }

            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            anh = intent.getStringExtra("anh");
            tenMon = intent.getStringExtra("tenmon");
            donGia = intent.getIntExtra("giadoan", 0);
            thongTin = intent.getStringExtra("thongtin");

        }

        giohangback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GioHangActivity.this, ChiTietSPActivity.class);

                i.putExtra("anh",anh);
                i.putExtra("tenmon",tenMon);
                i.putExtra("giadoan",donGia);
                i.putExtra("thongtin",thongTin);
                startActivity(i);
            }
        });
        giohangAdapter.setOnItemClickListener(new GiohangAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                // Xóa sản phẩm khỏi giỏ hàng khi nhấn nút xóa trong Adapter
                xoaSanPhamKhoiGioHang(position);
            }
        });

    }
//    private void TT(){
//
//        for (int i = 0; i< this.list.size(); i++){
//            tongtiengh = tongtiengh+(this.list.get(i).getGiasp() * this.list.get(i).getSoluongsp());
//
//        }
//        gh_tongtien.setText(String.valueOf("Tổng tiền: "+tongtiengh));
//    }
    // Xử lý sự kiện xóa sản phẩm khỏi danh sách (trong Activity hoặc Adapter)
    private void xoaSanPhamKhoiGioHang(int position) {
        // Xóa sản phẩm khỏi danh sách (list)
//        list.remove(position);
        GioHangDTO gioHangDTO = list.get(position);
        gioHangDAo = new GioHangDAo(GioHangActivity.this);

        int kq = gioHangDAo.DeleteGH(gioHangDTO);
        if(kq>0){
            Toast.makeText(GioHangActivity.this, "Xóa Thành công", Toast.LENGTH_SHORT).show();
            list.clear();
            list.addAll(gioHangDAo.getAll());
            giohangAdapter.notifyDataSetChanged();

        }else {
            Toast.makeText(GioHangActivity.this, "ko xóa được", Toast.LENGTH_SHORT).show();
        }


        // Cập nhật lại tổng giá tiền sau khi xóa sản phẩm
        capNhatTongTien();
        Tensp();

        // Cập nhật giao diện hiển thị
        giohangAdapter.notifyDataSetChanged();
    }

    // Phương thức để tính toán lại tổng giá tiền
    private void capNhatTongTien() {
        tongtiengh = 0;

        // Tính tổng giá tiền dựa trên danh sách sản phẩm trong giỏ hàng (list)
        for (GioHangDTO gioHangDTO : list) {
            tongtiengh += gioHangDTO.getGiasp() * gioHangDTO.getSoluongsp();
        }

        // Hiển thị lại tổng giá tiền trên giao diện
        gh_tongtien.setText("Tổng tiền: " + decimalFormat.format(tongtiengh));
    }
    public void Tensp() {

        invoice = new StringBuilder();

        for (GioHangDTO gioHangDTO : list) {
            invoice.append( gioHangDTO.getTensp()+" và " +gioHangDTO.getTendoanphu()+ "  SL: "+gioHangDTO.getSoluongsp()+"\n");
        }
    }

}