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

import android.project.order.Adapter.Admin.Admin_QL_LoaiSP_Adapter;
import android.project.order.DAO.LoaiDoAn_DAO;
import android.project.order.DTO.LoaiDoAn_DTO;
import android.project.order.R;

public class Fragment_Admin_QL_LoaiSP extends Fragment {
    RecyclerView rc_loaidoan;
    FloatingActionButton lda_float_add;
    List<LoaiDoAn_DTO > list;
    LoaiDoAn_DAO loaiDoAn_dao;
    Admin_QL_LoaiSP_Adapter admin_ql_loaiSP_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_ql_loai_sp,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_loaidoan = view.findViewById(R.id.rc_loaidoan);
        lda_float_add = view.findViewById(R.id.lda_float_add);

        loaiDoAn_dao = new LoaiDoAn_DAO(getContext());
        list = loaiDoAn_dao.getAll();
        admin_ql_loaiSP_adapter = new Admin_QL_LoaiSP_Adapter(getContext(),list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        rc_loaidoan.setLayoutManager(linearLayoutManager);
        rc_loaidoan.setAdapter(admin_ql_loaiSP_adapter);

        lda_float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ADD();
            }
        });
    }
    public void Dialog_ADD() {
        AlertDialog.Builder  builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_themloaidoan,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        TextInputEditText ed_themloaidoan = v.findViewById(R.id.ed_themloaidoan);
        Button btn_themloaidoan = v.findViewById(R.id.btn_themloaidoan);
        Button btn_huythemloaidoan = v.findViewById(R.id.btn_huythemloaidoan);

        btn_themloaidoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = ed_themloaidoan.getText().toString();
                String regex=".*\\s";
                String numregex=".*\\d.*";
                if(tenloai.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập tên loại đồ ăn mới", Toast.LENGTH_SHORT).show();
                } else if (tenloai.matches(regex) || tenloai.startsWith(" ")) {
                    Toast.makeText(getContext(), "Không được để khoảng trắng", Toast.LENGTH_SHORT).show();
                } else if (tenloai.matches(numregex)) {
                    Toast.makeText(getContext(), "Không được chứa số", Toast.LENGTH_SHORT).show();
                }else{
                    LoaiDoAn_DTO loaiDoAn_dto = new LoaiDoAn_DTO();
                    loaiDoAn_dto.setTenloai(tenloai);
                    long kq = loaiDoAn_dao.Insert_LoaiDoAn(loaiDoAn_dto);
                    if (kq > 0) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(loaiDoAn_dao.getAll());
                        admin_ql_loaiSP_adapter.notifyDataSetChanged();
                        ed_themloaidoan.setText("");
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "ko Thêm dc", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

            }
        });
        btn_huythemloaidoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), " Hủy Thêm", Toast.LENGTH_SHORT).show();
dialog.dismiss();
            }
        });
        dialog.show();

    }
}
