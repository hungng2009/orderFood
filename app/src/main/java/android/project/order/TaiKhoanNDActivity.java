package android.project.order;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import android.project.order.DAO.User_DAO;
import android.project.order.DTO.User_DTO;

public class TaiKhoanNDActivity extends AppCompatActivity {
    TextView tennguoidung,emailnguoidung,sdtnguoidung,namsinhnguoidung;
    ImageView taikhoanback;
    User_DAO user_dao ;

    User_DTO user;

    String maND;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_ndactivity);
        taikhoanback = findViewById(R.id.taikhoanback);
        tennguoidung=findViewById(R.id.tennguoidung);
        emailnguoidung =findViewById(R.id.emailnguoidung);
        sdtnguoidung=findViewById(R.id.sdtnguoidung);
        namsinhnguoidung=findViewById(R.id.namsinhnguoidung);


        Bundle bundle = getIntent().getExtras();
         maND = bundle.getString("ma");
        user_dao = new User_DAO(this);
        user = (user_dao.getID(maND));
        tennguoidung.setText(user.getHoTen());
        emailnguoidung.setText(user.getEmail());
        sdtnguoidung.setText(user.getSDT());
        namsinhnguoidung.setText(user.getNamSinh());

        findViewById(R.id.btnSua).setOnClickListener(v->{

            UpdateTK(user);

        });

        taikhoanback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaiKhoanNDActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user",maND);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    public void UpdateTK(User_DTO userDto){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_updatetknd,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        TextInputEditText suatennguoidung = v.findViewById(R.id.suatennguoidung);
        TextInputEditText suaemailnguoidung = v.findViewById(R.id.suaemailnguoidung);
        TextInputEditText suasdtnguoidung = v.findViewById(R.id.suasdtnguoidung);
        TextInputEditText suanamsinhnguoidung = v.findViewById(R.id.suanamsinhnguoidung);
        Button updatetknd = v.findViewById(R.id.updatetknd);
        Button huyupdatetknd = v.findViewById(R.id.huyupdatetknd);

        suatennguoidung.setText(userDto.getHoTen());
        suaemailnguoidung.setText(userDto.getEmail());
        suasdtnguoidung.setText(userDto.getSDT());
        suanamsinhnguoidung.setText(userDto.getNamSinh());

        updatetknd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = suatennguoidung.getText().toString();
                String email = suaemailnguoidung.getText().toString();
                String sdt = suasdtnguoidung.getText().toString();
                String nam = suanamsinhnguoidung.getText().toString();
                String yearregex="\\d{4}";
String emailregex="^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$";
String phoneregex="0\\d{9}";
                String regex=".*\\s";
                String numregex=".*\\d.*";
                if(ten.isEmpty() || email.isEmpty() || sdt.isEmpty() || nam.isEmpty()){
                    Toast.makeText(TaiKhoanNDActivity.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (ten.matches(regex) || ten.startsWith(" ") || email.matches(regex) || email.startsWith(" ") || sdt.matches(regex) || sdt.startsWith(" ") || nam.matches(regex) || nam.startsWith(" ")) {
                    Toast.makeText(TaiKhoanNDActivity.this, "Không được để khoảng trắng", Toast.LENGTH_SHORT).show();
                } else if (ten.matches(numregex)) {
                    Toast.makeText(TaiKhoanNDActivity.this, "Không được chứa số", Toast.LENGTH_SHORT).show();
                } else if (!sdt.matches(phoneregex)) {
                    Toast.makeText(TaiKhoanNDActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (!nam.matches(yearregex)) {
                    Toast.makeText(TaiKhoanNDActivity.this, "Năm sinh không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (!email.matches(emailregex)) {
                    Toast.makeText(TaiKhoanNDActivity.this, "email không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {

                    user_dao = new User_DAO(TaiKhoanNDActivity.this);
                    userDto.setHoTen(ten);
                    userDto.setEmail(email);
                    userDto.setSDT(sdt);
                    userDto.setNamSinh(nam);


                    int kq = user_dao.Update_User(userDto);
                    if (kq > 0) {
                        Toast.makeText(TaiKhoanNDActivity.this, "update thành công", Toast.LENGTH_SHORT).show();

                        suatennguoidung.setText("");
                        suaemailnguoidung.setText("");
                        suasdtnguoidung.setText("");
                        suanamsinhnguoidung.setText("");


                        tennguoidung.setText(userDto.getHoTen());
                        emailnguoidung.setText(userDto.getEmail());
                        sdtnguoidung.setText(userDto.getSDT());
                        namsinhnguoidung.setText(userDto.getNamSinh());

                        dialog.dismiss();


                    } else {
                        Toast.makeText(TaiKhoanNDActivity.this, "update ko thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                }
            }

        });
        huyupdatetknd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaiKhoanNDActivity.this, "hủy insert ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}