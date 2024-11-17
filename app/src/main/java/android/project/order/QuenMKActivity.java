package android.project.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import android.project.order.DAO.User_DAO;

public class QuenMKActivity extends AppCompatActivity {
    TextInputEditText qmk_mk,qmk_mk1;
    Button qmk_ht,qmk_huy;
    User_DAO user_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mk);
        qmk_mk=findViewById(R.id.qmk_mk);
        qmk_mk1 = findViewById(R.id.qmk_mk1);
        qmk_ht=findViewById(R.id.qmk_ht);
        qmk_huy = findViewById(R.id.qmk_huy);

        Bundle bundle = getIntent().getExtras();


        qmk_ht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = bundle.getString("email");
                String newPass = qmk_mk.getText().toString();
                String cfPass = qmk_mk1.getText().toString();
                user_dao = new User_DAO(QuenMKActivity.this);
                if(newPass != null && newPass.equals(cfPass)){
                    int kq = user_dao.Update_DangNhap(email,newPass);

                    if(kq>0){
                        Toast.makeText(QuenMKActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QuenMKActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(QuenMKActivity.this, "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        qmk_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuenMKActivity.this, "Hủy đổi mật khẩu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuenMKActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}