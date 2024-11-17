package android.project.order.Adapter.Admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import android.project.order.DAO.LoaiDoAn_DAO;
import android.project.order.DTO.LoaiDoAn_DTO;
import android.project.order.R;

public class Admin_QL_LoaiSP_Adapter extends RecyclerView.Adapter<Admin_QL_LoaiSP_Adapter.ViewHolder>{

    Context context;
    List<LoaiDoAn_DTO> list;
    LoaiDoAn_DAO loaiDoAn_dao;

    public Admin_QL_LoaiSP_Adapter(Context context, List<LoaiDoAn_DTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rc_loaidoan,parent,false);
        Admin_QL_LoaiSP_Adapter.ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiDoAn_DTO loaiDoAnDto = list.get(position);
        holder.lda_maloai.setText("Mã Loại : "+loaiDoAnDto.getMaloai());
        holder.lda_ten.setText("Tên loại : "+loaiDoAnDto.getTenloai());
        holder.lda_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete_Loai_DOAN(loaiDoAnDto);

            }
        });
        holder.lda_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update_Loai_DOAN(loaiDoAnDto);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView lda_maloai,lda_ten;
        ImageView lda_delete,lda_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lda_maloai = itemView.findViewById(R.id.lda_maloai);
            lda_ten = itemView.findViewById(R.id.lda_ten);
            lda_delete = itemView.findViewById(R.id.lda_delete);
            lda_edit = itemView.findViewById(R.id.lda_edit);
        }
    }
    public  void Delete_Loai_DOAN(LoaiDoAn_DTO loaiDoAn_dto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa hay không?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loaiDoAn_dao = new LoaiDoAn_DAO(context);

                int kq = loaiDoAn_dao.Delete_LoaiDoAn(loaiDoAn_dto);
                if(kq>0){
                    Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(loaiDoAn_dao.getAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "ko xóa được", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();

            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "hủy xóa", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }
    public  void Update_Loai_DOAN(LoaiDoAn_DTO loaiDoAn_dto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_update_loai_doan,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        TextInputEditText ed_sualoaidoan = v.findViewById(R.id.ed_sualoaidoan);
        Button btn_sualoaidoan = v.findViewById(R.id.btn_sualoaidoan);
        Button btn_huysualoaidoan = v.findViewById(R.id.btn_huysualoaidoan);

        ed_sualoaidoan.setText(loaiDoAn_dto.getTenloai());

        btn_sualoaidoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = ed_sualoaidoan.getText().toString();
                String regex=".*\\s";
                String numregex=".*\\d.*";
                loaiDoAn_dao = new LoaiDoAn_DAO(context);
                loaiDoAn_dto.setTenloai(tenloai);
                if(tenloai.equals("")){
                    Toast.makeText(context, "Vui lòng nhập tên loại đồ ăn mới", Toast.LENGTH_SHORT).show();
                } else if (tenloai.matches(regex) || tenloai.startsWith(" ")) {
                    Toast.makeText(context, "Không được để khoảng trắng", Toast.LENGTH_SHORT).show();
                } else if (tenloai.matches(numregex)) {
                    Toast.makeText(context, "Không được chứa số", Toast.LENGTH_SHORT).show();
                } else {
                    int kq = loaiDoAn_dao.Update_LoaiDoAn(loaiDoAn_dto);
                    if(kq>0){
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(loaiDoAn_dao.getAll());
                        notifyDataSetChanged();
                        ed_sualoaidoan.setText("");
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        btn_huysualoaidoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hủy update", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
