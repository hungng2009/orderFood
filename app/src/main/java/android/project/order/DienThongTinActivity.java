package android.project.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import android.project.order.DAO.HoaDon_DAO;
import android.project.order.DAO.User_DAO;
import android.project.order.DTO.HoaDon_DTO;
import android.project.order.DTO.User_DTO;
import android.project.order.DbHelper.MyDbHelper;

public class DienThongTinActivity extends AppCompatActivity {
    Spinner spn_banan;
    Button mua,trolai;
    String tenMon;
    int donGia;
    String doanPhu;
    String email;
    String hoTen;
    String sdt;
    String anh;
    String tenMon1;
    int donGia1;
    String thongTin;
    String sl;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thong_tin);
        mua = findViewById(R.id.mua);
        trolai = findViewById(R.id.trolai);
        spn_banan = findViewById(R.id.spn_banan);
        Intent intent = getIntent();
         tenMon = intent.getStringExtra("TenMon");
         donGia = intent.getIntExtra("TongTien", 0);
         doanPhu = intent.getStringExtra("DoanPhu");
         email = intent.getStringExtra("Email");
         hoTen = intent.getStringExtra("HoTen");
        sdt = intent.getStringExtra("SDT");
        sl = intent.getStringExtra("sl");


        TextView txtTenMon = findViewById(R.id.txt_thucdon1);
        TextView txtDonGia = findViewById(R.id.txt_tongtien1);
      // TextView txtDoanPhu = findViewById(R.id.);
//        TextView txtSoLuong = findViewById(R.id.txt_tongtien);
//        TextView txtEmail = findViewById(R.id.txt_email1);
//        TextView txtHoTen = findViewById(R.id.txt_hoten1);
//        TextView txtSDT = findViewById(R.id.txt_SDT1);
//        EditText edt_diachi=findViewById(R.id.edt_diachi1);


        txtTenMon.setText(tenMon+" va "+doanPhu+"  SL: "+sl);
        txtDonGia.setText(String.valueOf(donGia));
     //   txtDoanPhu.setText(doanPhu);
//        txtEmail.setText(email);
//        txtHoTen.setText(hoTen);
//        txtSDT.setText(sdt);

        ArrayList<String> bananlist = getBananData();

        MyDbHelper dbHelper = new MyDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM dt_banan", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String tenban = cursor.getString(1);
                bananlist.add(tenban);
            } while (cursor.moveToNext());
            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bananlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_banan.setAdapter(adapter);
        mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(edt_diachi.getText().toString().isEmpty()){
//                    Toast.makeText(DienThongTinActivity.this, "Địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
//                }else {
                    tenMon = intent.getStringExtra("TenMon");
                    donGia = intent.getIntExtra("TongTien", 0);
                    doanPhu = intent.getStringExtra("DoanPhu");
                    email = intent.getStringExtra("Email");
                    hoTen = intent.getStringExtra("HoTen");
                    sdt = intent.getStringExtra("SDT");
                    sl = intent.getStringExtra("sl");
                    String diachi = spn_banan.getSelectedItem().toString();
                    Date ate = new Date();
                    CharSequence h = DateFormat.format("d/MM /yyyy",ate.getTime());


                    HoaDon_DAO hoaDonDao = new HoaDon_DAO(DienThongTinActivity.this);
                    HoaDon_DTO hoaDonDto = new HoaDon_DTO();
                    hoaDonDto.setHoten(hoTen);
                    hoaDonDto.setEmail(email);
                    hoaDonDto.setSDT(sdt);
                    hoaDonDto.setDiachinhan(diachi);
                    hoaDonDto.setThucdon(tenMon+"va"+doanPhu+" SL:"+sl);
                    hoaDonDto.setTongtien(donGia);
                    hoaDonDto.setNgaydathang(String.valueOf(h));
                    hoaDonDto.setThanhtoan("Chưa thanh toán");
                    hoaDonDto.setTrangthai("Đang chế biến món ăn");

                    long kq = hoaDonDao.InsertHD(hoaDonDto);
                    if(kq>0){
                        Toast.makeText(DienThongTinActivity.this, "Đặt món thành công", Toast.LENGTH_SHORT).show();
                        Intent intent1= new Intent(DienThongTinActivity.this,HoaDonUserActivity.class);
                        User_DAO userDao = new User_DAO(DienThongTinActivity.this);
                        User_DTO loggedInUser = userDao.getCurrentLoggedInUser();
                        Bundle bundle = new Bundle();
                        bundle.putString("user",loggedInUser.getMaND());
                        intent1.putExtras(bundle);
                        startActivity(intent1);
                    }else {
                        Toast.makeText(DienThongTinActivity.this, "Đặt món thất bại", Toast.LENGTH_SHORT).show();

                    }
//                }
            }
        });

        Intent intent1 = getIntent();
        if (intent1 != null) {
            anh = intent1.getStringExtra("anh");
            tenMon1 = intent1.getStringExtra("tenmon");
            donGia1 = intent1.getIntExtra("giadoan", 0);
            thongTin = intent1.getStringExtra("thongtin");

        }

        trolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DienThongTinActivity.this, ChiTietSPActivity.class);

                i.putExtra("anh",anh);
                i.putExtra("tenmon",tenMon1);
                i.putExtra("giadoan",donGia1);
                i.putExtra("thongtin",thongTin);
                startActivity(i);
            }
        });

    }
    private ArrayList<String> getBananData() {
        ArrayList<String> banan = new ArrayList<>();

        return banan;
    }
}