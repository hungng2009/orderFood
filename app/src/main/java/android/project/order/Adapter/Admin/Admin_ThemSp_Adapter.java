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
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import android.project.order.DAO.DoAn_DAO;
import android.project.order.DTO.DoAn_DTO;
import android.project.order.R;

public class Admin_ThemSp_Adapter extends RecyclerView.Adapter<Admin_ThemSp_Adapter.ViewHolder> {

    Context context;
    List<DoAn_DTO> list;
    DoAn_DAO doAn_dao;
    private List<HashMap<String,Object>> listHM;

    public Admin_ThemSp_Adapter(Context context, List<DoAn_DTO> list,List<HashMap<String,Object>> listHM,DoAn_DAO doAn_dao) {
        this.context = context;
        this.list = list;
this.listHM=listHM;
this.doAn_dao=doAn_dao;
    }
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rc_themsp,parent,false);
        Admin_ThemSp_Adapter.ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DoAn_DTO doAn_dto = list.get(position);
        Picasso.get().load(doAn_dto.getAnh()).into(holder.tsp_anh);
        holder.tsp_tensp.setText(doAn_dto.getTendoan());
        holder.tsp_gia.setText("Giá:"+decimalFormat.format(doAn_dto.getGiadoan())+"VND");
        holder.tsp_loaidoan.setText("Loại : "+ doAn_dto.getTenloai());
        holder.tsp_mota.setText(doAn_dto.getThongtin());
        holder.tsp_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDOAN(doAn_dto);

            }
        });
        holder.tsp_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDOAN(doAn_dto);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tsp_tensp,tsp_gia,tsp_mota,tsp_loaidoan;
        ImageView tsp_delete,tsp_edit,tsp_anh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tsp_tensp = itemView.findViewById(R.id.tsp_tensp);
            tsp_gia = itemView.findViewById(R.id.tsp_gia);
            tsp_mota = itemView.findViewById(R.id.tsp_mota);
            tsp_delete = itemView.findViewById(R.id.tsp_delete);
            tsp_edit = itemView.findViewById(R.id.tsp_edit);
            tsp_loaidoan=itemView.findViewById(R.id.tsp_loaidoan);
            tsp_anh = itemView.findViewById(R.id.tsp_anh);
        }
    }

    public void DeleteDOAN(DoAn_DTO doAn_dto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa hay không?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doAn_dao = new DoAn_DAO(context);

                int kq = doAn_dao.Delete_DoAn(doAn_dto);
                if(kq>0){
                    Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(doAn_dao.getAll());
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

    public void UpdateDOAN(DoAn_DTO doAn_dto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_updatedoan,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        TextInputEditText ed_linkanh=v.findViewById(R.id.ed_sualinkanh);
        TextInputEditText ed_tendoan=v.findViewById(R.id.ed_suatendoan);
        TextInputEditText ed_giadoan=v.findViewById(R.id.ed_suagiadoan);
        TextInputEditText ed_mota=v.findViewById(R.id.ed_suamota);


        Button btn_themdoan=v.findViewById(R.id.btn_suadoan);
        Button btn_huythemdoan=v.findViewById(R.id.btn_huysuadoan);

        ed_linkanh.setText(doAn_dto.getAnh());
        ed_tendoan.setText(doAn_dto.getTendoan()+"");
        ed_giadoan.setText(""+doAn_dto.getGiadoan());
        ed_mota.setText(doAn_dto.getThongtin());

        btn_themdoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anh = ed_linkanh.getText().toString();
                String ten = ed_tendoan.getText().toString();
                String mota = ed_mota.getText().toString();
                String regex=".*\\s";
                String numregex=".*\\d.*";
                if (anh.isEmpty() || ten.isEmpty() || mota.isEmpty()) {
                    Toast.makeText(context, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (anh.matches(regex) || ten.matches(regex) || mota.matches(regex) || anh.startsWith(" ") || ten.startsWith(" ") || mota.startsWith(" ")) {
                    Toast.makeText(context, "Không được nhập khoảng trắng", Toast.LENGTH_SHORT).show();
                } else if (ten.matches(numregex)) {
                    Toast.makeText(context, "Tên đồ ăn không được chứa số", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int gia = Integer.parseInt(ed_giadoan.getText().toString());
                        doAn_dao = new DoAn_DAO(context);

                        doAn_dto.setAnh(anh);
                        doAn_dto.setTendoan(ten);
                        doAn_dto.setGiadoan(gia);
                        doAn_dto.setThongtin(mota);
                        int kq = doAn_dao.Update_DoAn(doAn_dto);
                        if (kq > 0) {
                            Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(doAn_dao.getAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "ko thanh con", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }catch (Exception e){
                        Toast.makeText(context, "Giá phải là số và không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_huythemdoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "huy them", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();


    }


}
