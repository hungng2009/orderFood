package android.project.order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import android.project.order.DTO.DoAn_DTO;
import android.project.order.R;

public class ListspofloaispAdapter extends RecyclerView.Adapter<ListspofloaispAdapter.ViewHolder>{
     List<DoAn_DTO> list;
    Context context;
    String tenloai;

    public ListspofloaispAdapter(List<DoAn_DTO> list, Context context) {
        this.list = list;
        this.context = context;
    }
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listspofloaisp,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getAnh()).into(holder.imgsp);
holder.txttenmonan.setText("Tên món ăn:"+list.get(position).getTendoan());
holder.txtgiamonan.setText("Giá món ăn:"+decimalFormat.format(list.get(position).getGiadoan())+"VND");
holder.txtthongtin.setText("Thông tin:"+list.get(position).getThongtin());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenmonan,txtgiamonan,txtthongtin;
ImageView imgsp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenmonan=itemView.findViewById(R.id.txttenmonan);
            txtgiamonan=itemView.findViewById(R.id.txtgiamonan);
            txtthongtin=itemView.findViewById(R.id.txtthongtin);
            imgsp=itemView.findViewById(R.id.imgsp);
        }
    }
}
