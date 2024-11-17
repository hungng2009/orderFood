package android.project.order.Adapter.Admin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import android.project.order.DTO.HoaDon_DTO;
import android.project.order.R;

public class AdminHoaDonAdapter extends RecyclerView.Adapter<AdminHoaDonAdapter.ViewHolder>{
    Context context;
    List<HoaDon_DTO>  list;

    public AdminHoaDonAdapter(Context context, List<HoaDon_DTO> list) {
        this.context = context;
        this.list = list;
    }
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rc_admin_hoadon,parent,false);
        AdminHoaDonAdapter.ViewHolder viewHolder = new AdminHoaDonAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_madon.setText(list.get(position).getMahoadon()+"");
        holder.txt_email.setText(list.get(position).getEmail());
        holder.txt_hoten.setText(list.get(position).getHoten());
        holder.txt_sdt.setText(list.get(position).getSDT());
        holder.txt_diachi.setText(list.get(position).getDiachinhan());
        holder.txt_thucdon.setText(list.get(position).getThucdon());
        holder.txt_ngaydat.setText(list.get(position).getNgaydathang());
        holder.txt_tongtien.setText(decimalFormat.format(list.get(position).getTongtien())+"VND");
        holder.txt_thanhtoan.setText(list.get(position).getThanhtoan());
        holder.txt_trangthai2.setText(list.get(position).getTrangthai());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_madon,txt_email,txt_hoten,txt_sdt,txt_diachi,txt_thucdon,txt_ngaydat,txt_tongtien,txt_thanhtoan,txt_trangthai2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_madon=itemView.findViewById(R.id.txt_madon2);
            txt_email=itemView.findViewById(R.id.txt_email2);
            txt_hoten=itemView.findViewById(R.id.txt_hoten2);
            txt_sdt=itemView.findViewById(R.id.txt_SDT2);
            txt_diachi=itemView.findViewById(R.id.txt_diachi2);
            txt_tongtien=itemView.findViewById(R.id.txt_tongtien2);
            txt_thanhtoan=itemView.findViewById(R.id.txt_thanhtoan2);
            txt_thucdon=itemView.findViewById(R.id.txt_thucdon2);
            txt_ngaydat=itemView.findViewById(R.id.txt_ngaydat2);
            txt_trangthai2 = itemView.findViewById(R.id.txt_trangthai2);
        }
    }
}
