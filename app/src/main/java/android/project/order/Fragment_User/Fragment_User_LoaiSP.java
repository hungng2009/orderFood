package android.project.order.Fragment_User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.project.order.Adapter.User_LoaiSanPham_Adapter;
import android.project.order.DAO.LoaiDoAn_DAO;
import android.project.order.DTO.LoaiDoAn_DTO;
import android.project.order.R;

public class Fragment_User_LoaiSP extends Fragment {
    RecyclerView rc_loaisp;
    LoaiDoAn_DAO loaiDoAn_dao;
    List<LoaiDoAn_DTO> list;
    User_LoaiSanPham_Adapter user_loaiSanPham_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_loai_sp,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_loaisp = view.findViewById(R.id.rc_loaidoanuser);


        loaiDoAn_dao = new LoaiDoAn_DAO(getContext());
        list = loaiDoAn_dao.getAll();
        user_loaiSanPham_adapter = new User_LoaiSanPham_Adapter(list,getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        rc_loaisp.setLayoutManager(linearLayoutManager);
        rc_loaisp.setAdapter(user_loaiSanPham_adapter);
    }
}
