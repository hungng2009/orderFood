package android.project.order.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import android.project.order.DAO.HoaDon_DAO;
import android.project.order.DTO.HoaDon_DTO;
import android.project.order.R;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    List<HoaDon_DTO> list;
    Context context;
    String thanhtoan="";

    public HoaDonAdapter(List<HoaDon_DTO> list, Context context) {
        this.list = list;
        this.context = context;
    }
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");

    @NonNull
    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.ViewHolder holder, int position) {
holder.txt_madon.setText(list.get(position).getMahoadon()+"");
holder.txt_email.setText(list.get(position).getEmail());
holder.txt_hoten.setText(list.get(position).getHoten());
holder.txt_sdt.setText(list.get(position).getSDT());
holder.txt_diachi.setText(list.get(position).getDiachinhan());
holder.txt_thucdon.setText(list.get(position).getThucdon());
holder.txt_ngaydat.setText(list.get(position).getNgaydathang());
holder.txt_tongtien.setText(decimalFormat.format(list.get(position).getTongtien())+" VND");
holder.txt_thanhtoan.setText(list.get(position).getThanhtoan());
holder.txt_trangthai.setText(list.get(position).getTrangthai());
if (list.get(position).getTrangthai().equals("Đã mang món ăn lên")){
    holder.ttdoan.setVisibility(View.GONE);
}
if(list.get(position).getThanhtoan().equals("Đã thanh toán bằng tiền mặt")){
    holder.tt.setVisibility(View.GONE);
} else if (list.get(position).getThanhtoan().equals("Đã thanh toán bằng chuyển khoản")) {
    holder.tt.setVisibility(View.GONE);
}
//        holder.cb_xacnhan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if (isChecked) {
//                   holder.cb_xacnhan.setVisibility(View.GONE);
//                } else {
//                    holder.cb_xacnhan.setVisibility(View.VISIBLE);
//
//                }
//            }
//        });

        holder.ttdoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getTrangthai().equals(" Món ăn đã chế biến xong")){
                    HoaDon_DTO hoaDonDto = list.get(position);
                    HoaDon_DAO hoaDonDao = new HoaDon_DAO(context);
                    hoaDonDto.setTrangthai("Đã mang món ăn lên");
                    int kq = hoaDonDao.UpdateHD(hoaDonDto);
                    if(kq>0){
                        Toast.makeText(context, "Cập nhập trạng thái thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(hoaDonDao.getAll());
                        notifyDataSetChanged();

                    }else {
                        Toast.makeText(context, "Cập nhập trạng thái thất bại", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(context, "Món ăn chưa chế biến xong", Toast.LENGTH_SHORT).show();

                }
            }
        });
        holder.tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDon_DTO hoaDonDto = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View h = inflater.inflate(R.layout.layout_update_kieuthanhtoan,null);
                builder.setView(h);
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                RadioGroup rdg_thanhtoan= h.findViewById(R.id.rdg_kieuthanhtoan1);
                Button btn_suatt = h.findViewById(R.id.btn_suatt);
                Button btn_huytt = h.findViewById(R.id.btn_huysuatt);
                rdg_thanhtoan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (i==R.id.rdb_tienmat){
                            thanhtoan="Đã thanh toán bằng tiền mặt";

                        }else {
                            thanhtoan="Đã thanh toán bằng chuyển khoản";
                        }
                    }
                });
                btn_suatt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HoaDon_DAO hoaDon_dao = new HoaDon_DAO(context);
                        if(thanhtoan.equals("")){
                            Toast.makeText(context, "Chưa chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                        }else {
                            hoaDonDto.setThanhtoan(thanhtoan);
                            int kq = hoaDon_dao.UpdateHD(hoaDonDto);
                            if(kq>0){
                                Toast.makeText(context, "Cập nhập trạng thái thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(hoaDon_dao.getAll());
                                notifyDataSetChanged();

                                dialog.dismiss();
                            }else {
                                Toast.makeText(context, "Cập nhập trạng thái thất bại", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        }
                    }
                });
                btn_huytt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Hủy thanh toán", Toast.LENGTH_SHORT).show();
                        dialog.show();
                    }
                });
dialog.show();
            }

        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_madon,txt_email,txt_hoten,txt_sdt,txt_diachi,txt_thucdon,txt_ngaydat,txt_tongtien,txt_thanhtoan,txt_trangthai;
        CheckBox cb_xacnhan;
        Button tt,ttdoan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_madon=itemView.findViewById(R.id.txt_madon);
            txt_email=itemView.findViewById(R.id.txt_email);
            txt_hoten=itemView.findViewById(R.id.txt_hoten);
            txt_sdt=itemView.findViewById(R.id.txt_SDT);
            txt_diachi=itemView.findViewById(R.id.txt_diachi);
            txt_tongtien=itemView.findViewById(R.id.txt_tongtien);
            txt_thanhtoan=itemView.findViewById(R.id.txt_thanhtoan);
            txt_thucdon=itemView.findViewById(R.id.txt_thucdon);
            txt_ngaydat=itemView.findViewById(R.id.txt_ngaydat);
            txt_trangthai=itemView.findViewById(R.id.txt_trangthai);

            tt = itemView.findViewById(R.id.tt);
            ttdoan = itemView.findViewById(R.id.ttdoan);
//            cb_xacnhan=itemView.findViewById(R.id.cb_xacnhan);
        }
    }
    public void Thanhtoan(HoaDon_DTO hoaDonDto){



    }
}
