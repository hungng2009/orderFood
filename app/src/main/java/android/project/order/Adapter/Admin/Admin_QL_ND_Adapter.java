package android.project.order.Adapter.Admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.project.order.DAO.User_DAO;
import android.project.order.DTO.User_DTO;
import android.project.order.R;

public class Admin_QL_ND_Adapter extends RecyclerView.Adapter<Admin_QL_ND_Adapter.ViewHolder> {

    Context context;
    List<User_DTO> list;
    User_DAO user_dao;

    public Admin_QL_ND_Adapter(Context context, List<User_DTO> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rc_nd,parent,false);
        Admin_QL_ND_Adapter.ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User_DTO user_dto = list.get(position);
        holder.txtmatv.setText("Ma Thanh Vien:"+user_dto.getMaTV());
        holder.txttentv.setText("Ho Va Ten:"+user_dto.getHoTen());
        holder.txtnamsinh.setText("Năm sinh:"+user_dto.getNamSinh());
        holder.txtemail.setText("Email:"+user_dto.getEmail());
        holder.txtphone.setText("Phone:"+user_dto.getSDT());
        if (user_dto.getTypeAcc().equalsIgnoreCase("admin")){
            holder.imgdel.setVisibility(View.GONE);
        }

        holder.imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Delete_User(user_dto);
            }
        });
//        if (user_dto.getTypeAcc().equalsIgnoreCase("admin")){
//ViewGroup.LayoutParams params=holder.itemView.getLayoutParams();
//params.height=1;
//holder.itemView.setLayoutParams(params);
//            holder.itemView.setVisibility(View.INVISIBLE);
//        }else{
//            ViewGroup.LayoutParams params=holder.itemView.getLayoutParams();
//            holder.itemView.setLayoutParams(params);
//            params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
//            holder.itemView.setVisibility(View.VISIBLE);
//
//        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtmatv,txttentv,txtnamsinh,txtemail,txtphone;
        ImageView imgedit,imgdel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtmatv = itemView.findViewById(R.id.txtmatv);
            txttentv = itemView.findViewById(R.id.txttentv);
            txtnamsinh = itemView.findViewById(R.id.txtnamsinh);
            txtemail = itemView.findViewById(R.id.txtemail);
            txtphone = itemView.findViewById(R.id.txtphone);


            imgdel=itemView.findViewById(R.id.imgdel);
        }
    }
    public  void Delete_User(User_DTO user_dto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa hay không?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user_dao = new User_DAO(context);

                int kq = user_dao.Delete_User(user_dto);
                if(kq>0){
                    Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(user_dao.getAll());
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

}
