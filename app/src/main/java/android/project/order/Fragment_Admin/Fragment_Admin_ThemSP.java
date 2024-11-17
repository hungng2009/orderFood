package android.project.order.Fragment_Admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.project.order.Adapter.Admin.Admin_ThemSp_Adapter;
import android.project.order.DAO.DoAn_DAO;
import android.project.order.DAO.LoaiDoAn_DAO;
import android.project.order.DTO.DoAn_DTO;
import android.project.order.DTO.LoaiDoAn_DTO;
import android.project.order.R;

public class Fragment_Admin_ThemSP extends Fragment {
    RecyclerView rc_themsp;
    FloatingActionButton tsp_float_add;
    List<DoAn_DTO> list;
    DoAn_DAO doAn_dao;
    Admin_ThemSp_Adapter admin_themSp_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_themsp,container,false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_themsp =view.findViewById(R.id.rc_themsp);
        tsp_float_add = view.findViewById(R.id.tsp_float_add);
        doAn_dao = new DoAn_DAO(getContext());
        list = doAn_dao.getAll();
        admin_themSp_adapter = new Admin_ThemSp_Adapter(getContext(),list,getdsloai(),doAn_dao);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        rc_themsp.setLayoutManager(linearLayoutManager);
        rc_themsp.setAdapter(admin_themSp_adapter);

        tsp_float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ADD();
            }
        });

    }
    public void Dialog_ADD() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_themdoan, null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        TextInputEditText ed_linkanh=v.findViewById(R.id.ed_linkanh);
        TextInputEditText ed_tendoan=v.findViewById(R.id.ed_tendoan);
        TextInputEditText ed_giadoan=v.findViewById(R.id.ed_giadoan);
        TextInputEditText ed_mota=v.findViewById(R.id.ed_mota);
        Spinner spnLoai=v.findViewById(R.id.spnloaidoan);

        SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),getdsloai(), android.R.layout.simple_list_item_1,new String[]{"tenloai"},new int[]{android.R.id.text1});
        spnLoai.setAdapter(simpleAdapter);

        Button btn_themdoan=v.findViewById(R.id.btn_themdoan);
        Button btn_huythemdoan=v.findViewById(R.id.btn_huythemdoan);

        btn_themdoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkanh = ed_linkanh.getText().toString();
                String tendoan = ed_tendoan.getText().toString();
                String modoan = ed_mota.getText().toString();
                String regex=".*\\s";
                String numregex=".*\\d.*";
                if (linkanh.isEmpty() || tendoan.isEmpty() || modoan.isEmpty()){
                    Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (linkanh.matches(regex) || tendoan.matches(regex) || modoan.matches(regex) || linkanh.startsWith(" ") || tendoan.startsWith(" ") || modoan.startsWith(" ")) {
                    Toast.makeText(getContext(), "Không được nhập khoảng trắng", Toast.LENGTH_SHORT).show();
                } else if (tendoan.matches(numregex)) {
                    Toast.makeText(getContext(), "Tên đồ ăn không được chứa số", Toast.LENGTH_SHORT).show();
                } else{
try {


    int giadoan = Integer.parseInt(ed_giadoan.getText().toString());

    HashMap<String, Object> hs = (HashMap<String, Object>) spnLoai.getSelectedItem();
    int maloai = (int) hs.get("maloai");
    doAn_dao = new DoAn_DAO(getContext());

    DoAn_DTO doAn_dto = new DoAn_DTO();
    doAn_dto.setTendoan(tendoan);
    doAn_dto.setMaloai(maloai);

    doAn_dto.setGiadoan(giadoan);
    doAn_dto.setAnh(linkanh);
    doAn_dto.setThongtin(modoan);

    if (doAn_dao.insertDoAn(doAn_dto) > 0) {
        Toast.makeText(getContext(), "thêm thành công", Toast.LENGTH_SHORT).show();
        list.clear();
        list.addAll(doAn_dao.getAll());
        admin_themSp_adapter.notifyDataSetChanged();
        dialog.dismiss();

    } else {
        Toast.makeText(getContext(), "thêm thất bại", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

}catch (Exception e){
    Toast.makeText(getContext(), "Giá phải là số và không được bỏ trống", Toast.LENGTH_SHORT).show();
}
                }
            }
        });
        btn_huythemdoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "huy them", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        dialog.show();
    }
    private List<HashMap<String, Object>> getdsloai(){
        LoaiDoAn_DAO loaiDoAn_dao=new LoaiDoAn_DAO(getContext());
        List<LoaiDoAn_DTO> list=loaiDoAn_dao.getAll();
        List<HashMap<String, Object>> listHM=new ArrayList<>();
        for (LoaiDoAn_DTO loai:list){
            HashMap<String,Object> hs=new HashMap<>();
            hs.put("maloai",loai.getMaloai());
            hs.put("tenloai",loai.getTenloai());
            listHM.add(hs);
        }
        return listHM;
    }

}
