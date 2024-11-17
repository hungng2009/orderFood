package android.project.order;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import android.project.order.DAO.GioHangDAo;
import android.project.order.DAO.HoaDon_DAO;
import android.project.order.DAO.User_DAO;
import android.project.order.DTO.HoaDon_DTO;
import android.project.order.DTO.User_DTO;
import android.project.order.DbHelper.MyDbHelper;

public class DienThongTinTuGHActivity extends AppCompatActivity {
    Spinner spn_banan1;
    Button mua,back;
    String tenMon;
    String donGia;
    String doanPhu;
    String email;
    String hoTen;
    String sdt;
    User_DAO userDao;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thong_tin_tugh);
        mua = findViewById(R.id.muagh);
        GioHangDAo gioHangDAo = new GioHangDAo(DienThongTinTuGHActivity.this);
        spn_banan1=findViewById(R.id.spn_banan1);
        back = findViewById(R.id.backgh);
        Bundle bundle = getIntent().getExtras();
         tenMon = bundle.getString("TenMon");
         donGia = bundle.getString("TongTien");
         email = bundle.getString("Email");
         hoTen = bundle.getString("HoTen");
        sdt = bundle.getString("SDT");
        TextInputEditText txtTenMon = findViewById(R.id.txt_thucdon3);
        TextInputEditText txtDonGia = findViewById(R.id.txt_tongtien3);


        txtTenMon.setText(tenMon);
        txtDonGia.setText(donGia);

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
        spn_banan1.setAdapter(adapter);
        mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenMon = bundle.getString("TenMon");
                donGia = bundle.getString("TongTien");
                email = bundle.getString("Email");
                hoTen = bundle.getString("HoTen");
                sdt = bundle.getString("SDT");
                String diachi = spn_banan1.getSelectedItem().toString();
                Date ate = new Date();
                CharSequence h = DateFormat.format("d/MM /yyyy",ate.getTime());


                HoaDon_DAO hoaDonDao = new HoaDon_DAO(DienThongTinTuGHActivity.this);
                HoaDon_DTO hoaDonDto = new HoaDon_DTO();
                hoaDonDto.setHoten(hoTen);
                hoaDonDto.setEmail(email);
                hoaDonDto.setSDT(sdt);
                hoaDonDto.setDiachinhan(diachi);
                hoaDonDto.setThucdon(tenMon);
                hoaDonDto.setTongtien(Integer.parseInt(donGia));
                hoaDonDto.setNgaydathang(String.valueOf(h));
                hoaDonDto.setThanhtoan("Chưa thanh toán");
                hoaDonDto.setTrangthai("Đang chế biến món ăn");

                long kq = hoaDonDao.InsertHD(hoaDonDto);
                if(kq>0){
                    Toast.makeText(DienThongTinTuGHActivity.this, "Đặt món thành công", Toast.LENGTH_SHORT).show();
                    gioHangDAo.xoaToanBoSanPhamTrongGioHang();
                    Intent intent = new Intent(DienThongTinTuGHActivity.this,HoaDonUserActivity.class);
                    userDao = new User_DAO(DienThongTinTuGHActivity.this);
                    User_DTO loggedInUser = userDao.getCurrentLoggedInUser();
                    Bundle bundle = new Bundle();
                    bundle.putString("user",loggedInUser.getMaND());
                    intent.putExtras(bundle);
                    startActivity(intent);


                }else {
                    Toast.makeText(DienThongTinTuGHActivity.this, "Đặt món thất bại", Toast.LENGTH_SHORT).show();

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DienThongTinTuGHActivity.this, HomeActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                String ma = sharedPreferences.getString("USERNAME","");
                Bundle bundle = new Bundle();
                bundle.putString("user",ma);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private ArrayList<String> getBananData() {
        ArrayList<String> banan = new ArrayList<>();

        return banan;
    }
}