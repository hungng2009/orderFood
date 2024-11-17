package android.project.order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.project.order.ChiTietSPActivity;
import android.project.order.DTO.DoAn_DTO;
import android.project.order.R;

public class User_DanhSachSP_Adapter extends RecyclerView.Adapter<User_DanhSachSP_Adapter.ViewHolder> {
    ArrayList<DoAn_DTO> list;
    Context context;



    public User_DanhSachSP_Adapter(ArrayList<DoAn_DTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_sp,parent,false);
        return new ViewHolder(view);
    }
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


// Định dạng số tiền
        Picasso.get().load(list.get(position).getAnh()).into(holder.img_anh);
holder.txt_ten.setText("Tên món: "+list.get(position).getTendoan());
holder.txt_gia.setText("Giá: "+ decimalFormat.format(list.get(position).getGiadoan())+" VND");
holder.txt_loai.setText("Loại đồ ăn : "+list.get(position).getTenloai());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i= new Intent(context, ChiTietSPActivity.class);
        i.putExtra("anh",list.get(position).getAnh());
        i.putExtra("tenmon",list.get(position).getTendoan());
        i.putExtra("giadoan",list.get(position).getGiadoan());
        i.putExtra("thongtin",list.get(position).getThongtin());
        context.startActivity(i);
    }
});

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
TextView txt_ten,txt_gia,txt_loai;
ImageView img_anh;
Button btn_datmon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             txt_ten=itemView.findViewById(R.id.txt_ten);
             txt_gia=itemView.findViewById(R.id.txt_gia);
             img_anh=itemView.findViewById(R.id.img_sp);
            txt_loai = itemView.findViewById(R.id.txt_loai);
//             btn_datmon=itemView.findViewById(R.id.btn_datmon);
        }
    }

}
