package android.project.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.project.order.DAO.GioHangDAo;
import android.project.order.DAO.User_DAO;
import android.project.order.DTO.GioHangDTO;
import android.project.order.DTO.User_DTO;
import android.project.order.DbHelper.MyDbHelper;

public class ChiTietSPActivity extends AppCompatActivity {
private TextView txt_ten, txt_gia,txt_thongtin;
private Button btn_muahang,btn_themvaogiohang;
private ImageView img_anhchitiet,back,giohang;
private Spinner spn_doanphu;
private EditText edt_soluong;
    private static final int MAX_QUANTITY = 99;

    String anh;
    String tenMon;
    int donGia;
    String thongTin;
    String soLuong;
    String ma;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");

private int tongtien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sp);
         back=findViewById(R.id.back);
        giohang = findViewById(R.id.giohang);
        img_anhchitiet= findViewById(R.id.img_anhchitiet);
        btn_muahang=findViewById(R.id.btn_muahang);
        txt_gia=findViewById(R.id.txt_gia);
        txt_ten=findViewById(R.id.txt_ten);
        txt_thongtin=findViewById(R.id.txt_thongtin);
        spn_doanphu=findViewById(R.id.spn_doanphu);
        edt_soluong=findViewById(R.id.edt_soluong);
btn_muahang=findViewById(R.id.btn_muahang);
        btn_themvaogiohang = findViewById(R.id.btn_themvaogiohang);
edt_soluong.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
validateQuantityInput(s.toString());
    }
});

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietSPActivity.this, HomeActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                ma = sharedPreferences.getString("USERNAME","");
                Bundle bundle = new Bundle();
                bundle.putString("user",ma);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
             anh = intent.getStringExtra("anh");
             tenMon = intent.getStringExtra("tenmon");
             donGia = intent.getIntExtra("giadoan", 0);
             thongTin = intent.getStringExtra("thongtin");
             soLuong = edt_soluong.getText().toString();
            if (!soLuong.isEmpty()) {
                int soluong = Integer.parseInt(soLuong);
                tongtien= soluong*donGia;
            } else {
                     }



            txt_ten.setText(tenMon);
            txt_gia.setText(decimalFormat.format(donGia)+"VND");
            txt_thongtin.setText(thongTin);
            Picasso.get().load(anh).into(img_anhchitiet);

            ArrayList<String> doanPhuList = getDoanPhuData();

            MyDbHelper dbHelper = new MyDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM dt_doanphu", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String tenDoanPhu = cursor.getString(1);
                    doanPhuList.add(tenDoanPhu);
                } while (cursor.moveToNext());
                cursor.close();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doanPhuList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_doanphu.setAdapter(adapter);

        }

        btn_muahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User_DAO userDAO = new User_DAO(ChiTietSPActivity.this);
                User_DTO loggedInUser = userDAO.getCurrentLoggedInUser();

                try {
                    int sl1 = Integer.parseInt(edt_soluong.getText().toString());
                    if(sl1>0){
                        Intent intent = new Intent(ChiTietSPActivity.this, DienThongTinActivity.class);
                        String tenMon = txt_ten.getText().toString();
                        int tongtien = Integer.parseInt(String.valueOf(donGia)) * Integer.parseInt(edt_soluong.getText().toString());
                        String thongTin = txt_thongtin.getText().toString();
                        String doanPhu = spn_doanphu.getSelectedItem().toString();

                        // mon an
                        intent.putExtra("TenMon", tenMon);
                        intent.putExtra("TongTien", tongtien);
                        intent.putExtra("ThongTin", thongTin);
                        intent.putExtra("DoanPhu", doanPhu);
                        intent.putExtra("sl",edt_soluong.getText().toString());

                        // nguoi dung
                        intent.putExtra("Email", loggedInUser.getEmail());
                        intent.putExtra("HoTen", loggedInUser.getHoTen());
                        intent.putExtra("SDT", loggedInUser.getSDT());

                        intent.putExtra("anh",anh);
                        intent.putExtra("tenmon",tenMon);
                        intent.putExtra("giadoan",donGia);
                        intent.putExtra("thongtin",thongTin);


                        startActivity(intent);
                    }else {
                        Toast.makeText(ChiTietSPActivity.this, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ChiTietSPActivity.this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_themvaogiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    GioHangDAo gioHangDAo = new GioHangDAo(ChiTietSPActivity.this);
                    String tenMon = txt_ten.getText().toString();
                    int gia = Integer.parseInt(String.valueOf(donGia));
                    int sl = Integer.parseInt(edt_soluong.getText().toString());
                    String doanPhu = spn_doanphu.getSelectedItem().toString();
if(sl<=0){
    Toast.makeText(ChiTietSPActivity.this, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
}else {
    GioHangDTO gioHangDTO = new GioHangDTO();
    gioHangDTO.setAnhsp(anh);
    gioHangDTO.setTensp(tenMon);
    gioHangDTO.setGiasp(gia);
    gioHangDTO.setSoluongsp(sl);
    gioHangDTO.setTendoanphu(doanPhu);

    long kq = gioHangDAo.InsertGH(gioHangDTO);
    if (kq > 0) {
        Toast.makeText(ChiTietSPActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();

    } else {
        Toast.makeText(ChiTietSPActivity.this, "Thêm vào giỏ hàng không thành công", Toast.LENGTH_SHORT).show();

    }
}
                }catch (Exception e){
                    Toast.makeText(ChiTietSPActivity.this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChiTietSPActivity.this, GioHangActivity.class);
                i.putExtra("anh",anh);
                i.putExtra("tenmon",tenMon);
                i.putExtra("giadoan",donGia);
                i.putExtra("thongtin",thongTin);
                startActivity(i);
            }
        });

    }

    private ArrayList<String> getDoanPhuData() {
        ArrayList<String> doanPhuList = new ArrayList<>();

        return doanPhuList;
    }

    private void validateQuantityInput(String input) {
        // Xử lý khi người dùng nhập giá trị
        try {
            int quantity = Integer.parseInt(input);

            // Giới hạn giá trị tối đa là 99
            if (quantity > MAX_QUANTITY) {
                // Nếu nhập quá giới hạn, đặt giá trị là 99
                edt_soluong.setText(String.valueOf(MAX_QUANTITY));
            }
        } catch (NumberFormatException e) {
            // Xử lý khi người dùng nhập không phải là số
            // Có thể hiển thị thông báo lỗi hoặc thực hiện hành động phù hợp
        }
    }
}