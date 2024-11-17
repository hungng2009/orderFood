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

import android.project.order.Adapter.Admin.AdminBanAnAdapter;
import android.project.order.DAO.BanAn_DAO;
import android.project.order.DTO.BanAn_DTO;
import android.project.order.R;

public class Fragment_Admin_BanAn extends Fragment {

    RecyclerView rc_banan;
    FloatingActionButton ban_float_add;
    List<BanAn_DTO> list;
    BanAn_DAO banAnDao;
    AdminBanAnAdapter adminBanAnAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_banan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc_banan = view.findViewById(R.id.rc_banan);
        ban_float_add = view.findViewById(R.id.ban_float_add);

        banAnDao = new BanAn_DAO(getContext());
        list= banAnDao.getAll();
        adminBanAnAdapter = new AdminBanAnAdapter(getContext(),list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
        rc_banan.setLayoutManager(linearLayoutManager);
        rc_banan.setAdapter(adminBanAnAdapter);
        ban_float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ADD();

            }
        });
    }

    public void Dialog_ADD() {
        AlertDialog.Builder  builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_themsp_banan,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        TextInputEditText ed_thembanan = v.findViewById(R.id.ed_thembanan);
        Button btn_thembanan = v.findViewById(R.id.btn_thembanan);
        Button btn_huythembanan = v.findViewById(R.id.btn_huythembanan);

        btn_thembanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenban = ed_thembanan.getText().toString();
                String regex=".*\\s";
                String numregex=".*\\d.*";
                if(tenban.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập tên bàn mới", Toast.LENGTH_SHORT).show();
                } else if (tenban.matches(regex) || tenban.startsWith(" ")) {
                    Toast.makeText(getContext(), "Không được để khoảng trắng", Toast.LENGTH_SHORT).show();
                } else {

                    BanAn_DTO banAnDto = new BanAn_DTO();
                    banAnDto.setTenban(tenban);
                    long kq = banAnDao.Insert_BanAN(banAnDto);
                    if (kq > 0) {
                        Toast.makeText(getContext(), "Thêm bàn thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(banAnDao.getAll());
                        adminBanAnAdapter.notifyDataSetChanged();
                        ed_thembanan.setText("");
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "ko Thêm dc", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        btn_huythembanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), " Hủy Thêm", Toast.LENGTH_SHORT).show();
dialog.dismiss();
            }
        });
        dialog.show();

    }
}
