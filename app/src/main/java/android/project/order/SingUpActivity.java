package android.project.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import android.project.order.DAO.User_DAO;
import android.project.order.DTO.User_DTO;

public class SingUpActivity extends AppCompatActivity {
    TextInputEditText sg_tenDN,sg_tenND,sg_email,sg_Mk,sg_Mk1,sg_phone,sg_year;
    Button sg_Singup,sg_back;
    User_DAO user_dao;
    List<User_DTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        sg_tenDN = findViewById(R.id.sg_tenDN);
        sg_tenND = findViewById(R.id.sg_tenND);
        sg_email = findViewById(R.id.sg_email);
        sg_phone = findViewById(R.id.sg_phone);
        sg_year = findViewById(R.id.sg_year);
        sg_Mk = findViewById(R.id.sg_Mk);
        sg_Mk1 = findViewById(R.id.sg_Mk1);
        sg_Singup = findViewById(R.id.sg_Singup);
        sg_back = findViewById(R.id.sg_back);

        sg_Singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingupUser();
            }
        });
        sg_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    public void SingupUser(){
        String tenDN = sg_tenDN.getText().toString();
        String tenND = sg_tenND.getText().toString();
        String email = sg_email.getText().toString();
        String phone = sg_phone.getText().toString();
        String year = sg_year.getText().toString();
        String mk = sg_Mk.getText().toString();
        String mk1 = sg_Mk1.getText().toString();
String logregex="^[a-zA-Z0-9_]{3,15}$";
String passregex="^(?=.*[a-zA-Z0-9]).{6,}$";
        String yearregex="\\d{4}";
        String emailregex="^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$";
        String phoneregex="0\\d{9}";
        String regex=".*\\s";
        String numregex=".*\\d.*";
        if(tenDN.isEmpty() || tenND.isEmpty() || email.isEmpty() || phone.isEmpty() || year.isEmpty() || mk.isEmpty() || mk1.isEmpty()){
            Toast.makeText(this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else if (tenND.matches(regex) || tenND.startsWith(" ") || tenDN.matches(regex) || tenDN.startsWith(" ") || email.matches(regex) || email.startsWith(" ") || phone.matches(regex) || phone.startsWith(" ") || year.matches(regex) || year.startsWith(" ") || mk.matches(regex) || mk.startsWith(" ") || mk1.matches(regex) || mk1.startsWith(" ")) {
            Toast.makeText(this, "Không được để khoảng trắng", Toast.LENGTH_SHORT).show();
        } else if (tenND.matches(numregex)) {
            Toast.makeText(this, "Tên người dùng Không được chứa số", Toast.LENGTH_SHORT).show();
        } else if (!phone.matches(phoneregex)) {
            Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
        } else if (!year.matches(yearregex)) {
            Toast.makeText(this, "Năm sinh không hợp lệ", Toast.LENGTH_SHORT).show();
        } else if (!email.matches(emailregex)) {
            Toast.makeText(this, "email không hợp lệ", Toast.LENGTH_SHORT).show();
        } else if (!tenDN.matches(logregex)) {
            Toast.makeText(this, "Tên đăng nhập không hợp lệ", Toast.LENGTH_SHORT).show();
        } else if (!mk.matches(passregex) || !mk1.matches(passregex)) {
            Toast.makeText(this, "Mật khẩu phải ít nhất 6 kí tự số hoặc chữ", Toast.LENGTH_SHORT).show();
        } else if (!mk.matches(mk1)) {
            Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
        } else {


                if (mk.equals(mk1)) {
                    user_dao = new User_DAO(this);
                    User_DTO userDto = new User_DTO();
                    userDto.setMaND(tenDN);
                    userDto.setHoTen(tenND);
                    userDto.setEmail(email);
                    userDto.setMatKhau(mk);
                    userDto.setSDT(phone);
                    userDto.setNamSinh(year);
                    long kq = user_dao.Insert_User(userDto);
                    if (kq > 0) {
                        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SingUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Đăng ký ko thành công", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }



        }
    }
}