package android.project.order.Fragment_User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.project.order.DAO.User_DAO;
import android.project.order.LoginActivity;
import android.project.order.R;

public class Fragment_DoiMatKhau extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_doimatkhau,container,false);
        EditText edtpass=view.findViewById(R.id.edpassword);
        EditText edtnewpass=view.findViewById(R.id.ednewpassword);
        EditText edtrepass=view.findViewById(R.id.edrepassword);
        Button btnsave=view.findViewById(R.id.btnluu);
        Button btnhuy=view.findViewById(R.id.btnhuy);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpass=edtpass.getText().toString();
                String newpass=edtnewpass.getText().toString();
                String repass=edtrepass.getText().toString();

                if (newpass.equals(repass)){
                    SharedPreferences sharedPreferences=getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                    String MaND=sharedPreferences.getString("MaND","");
                    User_DAO user_dao=new User_DAO(getContext());
                    boolean check=user_dao.updatepass(MaND,oldpass,newpass);
                    if (check){
                        Toast.makeText(getContext(), "Update pass thành công", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "Update pass fail", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "pass ko trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtpass.setText("");
                edtrepass.setText("");
                edtnewpass.setText("");
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
