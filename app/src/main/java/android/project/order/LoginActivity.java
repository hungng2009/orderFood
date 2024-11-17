package android.project.order;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import android.project.order.DAO.User_DAO;

public class LoginActivity extends AppCompatActivity {
    TextView lg_qmk;
    Button lg_login,lg_singup;
    TextInputEditText lg_name,lg_pass;
    CheckBox lg_check;
    User_DAO user_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lg_name = findViewById(R.id.lg_name);
        lg_pass = findViewById(R.id.lg_pass);
        lg_check = findViewById(R.id.lg_check);
        lg_login = findViewById(R.id.lg_login);
        lg_singup = findViewById(R.id.lg_singup);
        lg_qmk = findViewById(R.id.lg_qmk);

        user_dao = new User_DAO(this);
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        lg_name.setText(sharedPreferences.getString("USERNAME",""));
        lg_pass.setText(sharedPreferences.getString("PASSWORD",""));
        lg_check.setChecked(sharedPreferences.getBoolean("REMEMBER",false));

        lg_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();

            }
        });
        lg_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SingUpActivity.class);
                startActivity(intent);
            }
        });
        lg_qmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, KiemTraMKActivity.class);
                startActivity(intent);
            }
        });

    }
    public void checkLogin(){
        String user = lg_name.getText().toString();
        String pass = lg_pass.getText().toString();
        if(user.isEmpty()||pass.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
        }else {
            if (user_dao.checkLogin(user,pass)){//sua cho này
                Toast.makeText(this, "Login Thành công", Toast.LENGTH_SHORT).show();
                Remember(user,pass,lg_check.isChecked());
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(LoginActivity.this, "Username hoặc password ko đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public  void Remember(String u , String  p ,boolean status){
        // lưu sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!status){
            //xóa tình trạng lưu trước đó
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }

        //lưu lại toàn bộ
        editor.commit();
    }
}