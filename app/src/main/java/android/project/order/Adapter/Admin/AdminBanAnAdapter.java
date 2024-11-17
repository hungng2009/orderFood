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

import android.project.order.DAO.BanAn_DAO;
import android.project.order.DTO.BanAn_DTO;
import android.project.order.R;

public class AdminBanAnAdapter extends RecyclerView.Adapter<AdminBanAnAdapter.ViewHolder> {

    Context context;
    List<BanAn_DTO> list;
    BanAn_DAO banAnDao;

    public AdminBanAnAdapter(Context context, List<BanAn_DTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rc_banan,parent,false);
        AdminBanAnAdapter.ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BanAn_DTO banAnDto = list.get(position);
        holder.ban_ma.setText("Mã bàn : "+banAnDto.getMaBan());
        holder.ban_ten.setText("Tên bàn : "+banAnDto.getTenban());
        holder.ban_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update_SP_PHU(banAnDto);

            }
        });
        holder.ban_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete_SP_PHU(banAnDto);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView ban_ma,ban_ten;
        ImageView ban_delete,ban_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ban_ma = itemView.findViewById(R.id.ban_ma);
            ban_ten = itemView.findViewById(R.id.ban_ten);
            ban_delete = itemView.findViewById(R.id.ban_delete);
            ban_edit = itemView.findViewById(R.id.ban_edit);
        }
    }
    public  void Delete_SP_PHU( BanAn_DTO banAnDto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa hay không?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                banAnDao= new BanAn_DAO(context);

                int kq = banAnDao.Delete_BanAN(banAnDto);
                if(kq>0){
                    Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(banAnDao.getAll());
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

    public  void Update_SP_PHU(BanAn_DTO banAnDto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_update_banan,null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        TextInputEditText ed_suaban = v.findViewById(R.id.ed_suaban);
        Button btn_suaban = v.findViewById(R.id.btn_suaban);
        Button btn_huysuaban = v.findViewById(R.id.btn_huysuaban);

        ed_suaban.setText(banAnDto.getTenban());

        btn_suaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenban = ed_suaban.getText().toString();
                banAnDao = new BanAn_DAO(context);
                banAnDto.setTenban(tenban);
                String regex=".*\\s";
                String numregex=".*\\d.*";
                if(tenban.isEmpty()){
                    Toast.makeText(context, "Vui lòng nhập tên bàn mới", Toast.LENGTH_SHORT).show();
                } else if (tenban.matches(regex) || tenban.startsWith(" ")) {
                    Toast.makeText(context, "Không được để khoảng trắng", Toast.LENGTH_SHORT).show();
                } else {
                    int kq = banAnDao.Update_BanAN(banAnDto);
                    if(kq>0){
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(banAnDao.getAll());
                        notifyDataSetChanged();
                        ed_suaban.setText("");
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        btn_huysuaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hủy update", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
