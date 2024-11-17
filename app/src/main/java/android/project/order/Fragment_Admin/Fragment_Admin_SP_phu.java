package android.project.order.Fragment_Admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import android.project.order.Adapter.Admin.Admin_SP_phu_Adapter;
import android.project.order.DAO.DoAnPhu_DAO;
import android.project.order.DTO.DoAnPhu_DTO;
import android.project.order.R;

public class Fragment_Admin_SP_phu extends Fragment {

    RecyclerView rc_doanphu;
    FloatingActionButton dap_float_add;
    List<DoAnPhu_DTO> list;
    DoAnPhu_DAO doAnPhu_dao;
    Admin_SP_phu_Adapter admin_sp_phu_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_sp_phu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc_doanphu = view.findViewById(R.id.rc_doanphu);
        dap_float_add = view.findViewById(R.id.dap_float_add);

        doAnPhu_dao = new DoAnPhu_DAO(getContext());
        list= doAnPhu_dao.getAll();
        admin_sp_phu_adapter = new Admin_SP_phu_Adapter(getContext(),list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        rc_doanphu.setLayoutManager(linearLayoutManager);
        rc_doanphu.setAdapter(admin_sp_phu_adapter);
        dap_float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ADD();

            }
        });
    }

    public void Dialog_ADD() {
        AlertDialog.Builder  builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_themsp_phu,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        TextInputEditText ed_themspphu = v.findViewById(R.id.ed_themspphu);
        Button btn_addspphu = v.findViewById(R.id.btn_themspphu);
        Button btn_huyaddspphu = v.findViewById(R.id.btn_huythemspphu);

        btn_addspphu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenspphu = ed_themspphu.getText().toString();
                String regex=".*\\s";
                String numregex=".*\\d.*";
                if(tenspphu.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập tên sản phẩm phụ mới", Toast.LENGTH_SHORT).show();
                } else if (tenspphu.matches(regex) || tenspphu.startsWith(" ")) {
                    Toast.makeText(getContext(), "Không được để khoảng trắng", Toast.LENGTH_SHORT).show();
                } else if (tenspphu.matches(numregex)) {
                    Toast.makeText(getContext(), "Không được chứa số", Toast.LENGTH_SHORT).show();
                }else {

                    DoAnPhu_DTO doAnPhu_dto = new DoAnPhu_DTO();
                    doAnPhu_dto.setTenDoAnPhu(tenspphu);
                    long kq = doAnPhu_dao.Insert_DoAnPhu(doAnPhu_dto);
                    if (kq > 0) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(doAnPhu_dao.getAll());
                        admin_sp_phu_adapter.notifyDataSetChanged();
                        ed_themspphu.setText("");
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "ko Thêm dc", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        btn_huyaddspphu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), " Hủy Thêm", Toast.LENGTH_SHORT).show();
dialog.dismiss();
            }
        });
        dialog.show();

    }
}
