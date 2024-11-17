package android.project.order.Adapter.Admin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import android.project.order.DAO.HoaDon_DAO;
import android.project.order.DTO.HoaDon_DTO;
import android.project.order.R;

public class DauBepHoaDonAdapter extends RecyclerView.Adapter<DauBepHoaDonAdapter.ViewHolder>{
    Context context;
    List<HoaDon_DTO>  list;

    public DauBepHoaDonAdapter(Context context, List<HoaDon_DTO> list) {
        this.context = context;
        this.list = list;
    }
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rc_daubep_hoadon,parent,false);
        DauBepHoaDonAdapter.ViewHolder viewHolder = new DauBepHoaDonAdapter.ViewHolder(v);

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
        if (list.get(position).getTrangthai().equals(" Món ăn đã chế biến xong")){
            holder.ttdoan3.setVisibility(View.GONE);
        }
        if(list.get(position).getTrangthai().equals("Đã mang món ăn lên")){
            holder.ttdoan3.setVisibility(View.GONE);
        }

        holder.ttdoan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDon_DTO hoaDonDto = list.get(position);
                HoaDon_DAO hoaDonDao = new HoaDon_DAO(context);
                hoaDonDto.setTrangthai(" Món ăn đã chế biến xong");
                int kq = hoaDonDao.UpdateHD(hoaDonDto);
                if(kq>0){
                    Toast.makeText(context, "Cập nhập trạng thái thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(hoaDonDao.getAll());
                    notifyDataSetChanged();

                }else {
                    Toast.makeText(context, "Cập nhập trạng thái thất bại", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_madon,txt_email,txt_hoten,txt_sdt,txt_diachi,txt_thucdon,txt_ngaydat,txt_tongtien,txt_thanhtoan,txt_trangthai2;
        Button ttdoan3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_madon=itemView.findViewById(R.id.txt_madon3);
            txt_email=itemView.findViewById(R.id.txt_email3);
            txt_hoten=itemView.findViewById(R.id.txt_hoten3);
            txt_sdt=itemView.findViewById(R.id.txt_SDT3);
            txt_diachi=itemView.findViewById(R.id.txt_diachi3);
            txt_tongtien=itemView.findViewById(R.id.txt_tongtien3);
            txt_thanhtoan=itemView.findViewById(R.id.txt_thanhtoan3);
            txt_thucdon=itemView.findViewById(R.id.txt_thucdon3);
            txt_ngaydat=itemView.findViewById(R.id.txt_ngaydat3);
            txt_trangthai2 = itemView.findViewById(R.id.txt_trangthai3);
            ttdoan3 = itemView.findViewById(R.id.ttdoan3);
        }
    }
}
