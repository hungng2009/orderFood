package android.project.order.Fragment_Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import android.project.order.Adapter.Admin.Admin_QL_ND_Adapter;
import android.project.order.DAO.User_DAO;
import android.project.order.DTO.User_DTO;
import android.project.order.R;

public class Fragment_Admin_QL_ND extends Fragment {
    RecyclerView rc_ND;
    FloatingActionButton tnd_float_add;
    List<User_DTO> list;
    User_DAO user_dao;
    Admin_QL_ND_Adapter admin_ql_nd_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_ql_nd,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_ND =view.findViewById(R.id.rc_themnd);
        tnd_float_add = view.findViewById(R.id.tnd_float_add);

        user_dao = new User_DAO(getContext());
        list = user_dao.getAll();
        admin_ql_nd_adapter = new Admin_QL_ND_Adapter(getContext(),list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        rc_ND.setLayoutManager(linearLayoutManager);
        rc_ND.setAdapter(admin_ql_nd_adapter);
    }}
