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

import android.project.order.DAO.DoAnPhu_DAO;
import android.project.order.DTO.DoAnPhu_DTO;
import android.project.order.R;

public class Admin_SP_phu_Adapter extends RecyclerView.Adapter<Admin_SP_phu_Adapter.ViewHolder> {

    Context context;
    List<DoAnPhu_DTO> list;
    DoAnPhu_DAO doAnPhu_dao;

    public Admin_SP_phu_Adapter(Context context, List<DoAnPhu_DTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rc_doanphu,parent,false);
        Admin_SP_phu_Adapter.ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DoAnPhu_DTO doAnPhuDto = list.get(position);
        holder.dap_ma.setText("Mã đồ ăn phụ : "+doAnPhuDto.getMaDoAnPhu());
        holder.dap_ten.setText("Tên đồ ăn phụ : "+doAnPhuDto.getTenDoAnPhu());
        holder.dap_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update_SP_PHU(doAnPhuDto);

            }
        });
        holder.dap_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete_SP_PHU(doAnPhuDto);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView dap_ma,dap_ten;
        ImageView dap_delete,dap_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dap_ma = itemView.findViewById(R.id.dap_ma);
            dap_ten = itemView.findViewById(R.id.dap_ten);
            dap_delete = itemView.findViewById(R.id.dap_delete);
            dap_edit = itemView.findViewById(R.id.dap_edit);
        }
    }
    public  void Delete_SP_PHU(DoAnPhu_DTO doAnPhu_dto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa hay không?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doAnPhu_dao = new DoAnPhu_DAO(context);

                int kq = doAnPhu_dao.Delete_DoAnPhu(doAnPhu_dto);
                if(kq>0){
                    Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(doAnPhu_dao.getAll());
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

    public  void Update_SP_PHU(DoAnPhu_DTO doAnPhu_dto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_update_sp_phu,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        TextInputEditText ed_suaspphu = v.findViewById(R.id.ed_suaspphu);
        Button btn_suaspphu = v.findViewById(R.id.btn_suaspphu);
        Button btn_huysuaspphu = v.findViewById(R.id.btn_huysuaspphu);

        ed_suaspphu.setText(doAnPhu_dto.getTenDoAnPhu());

        btn_suaspphu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenspphu = ed_suaspphu.getText().toString();
                doAnPhu_dao = new DoAnPhu_DAO(context);
                doAnPhu_dto.setTenDoAnPhu(tenspphu);
                String regex=".*\\s";
                String numregex=".*\\d.*";
                if(tenspphu.isEmpty()){
                    Toast.makeText(context, "Vui lòng nhập tên sản phẩm phụ mới", Toast.LENGTH_SHORT).show();
                } else if (tenspphu.matches(regex) || tenspphu.startsWith(" ")) {
                    Toast.makeText(context, "Không được để khoảng trắng", Toast.LENGTH_SHORT).show();
                } else if (tenspphu.matches(numregex)) {
                    Toast.makeText(context, "Không được chứa số", Toast.LENGTH_SHORT).show();
                }else {
                    int kq = doAnPhu_dao.Update_DoAnPhu(doAnPhu_dto);
                    if(kq>0){
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(doAnPhu_dao.getAll());
                        notifyDataSetChanged();
                        ed_suaspphu.setText("");
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        btn_huysuaspphu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hủy update", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
