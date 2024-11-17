package android.project.order.Adapter;

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

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import android.project.order.DAO.GioHangDAo;
import android.project.order.DTO.GioHangDTO;
import android.project.order.R;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.ViewHolder> {
    Context context;
    List<GioHangDTO> list;
    GioHangDAo gioHangDAo;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
        // Thêm các sự kiện khác nếu cần
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // Trong onBindViewHolder


    public GiohangAdapter(Context context, List<GioHangDTO> list) {
        this.context = context;
        this.list = list;
    }
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rc_giohangsp,parent,false);
        GiohangAdapter.ViewHolder viewHolder = new GiohangAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHangDTO gioHangDTO = list.get(position);
        Picasso.get().load(gioHangDTO.getAnhsp()).into(holder.gh_anh);
        holder.gh_tensp.setText(gioHangDTO.getTensp()+" và "+gioHangDTO.getTendoanphu());
        holder.gh_gia.setText(decimalFormat.format(gioHangDTO.getGiasp())+"VND");
        holder.gh_soluongdoan.setText(""+gioHangDTO.getSoluongsp());
//        holder.gh_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        holder.gh_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DeleteGH(gioHangDTO);
////                list.remove(position);
//
//            }
//        });
        holder.gh_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onDeleteClick(position);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView gh_tensp,gh_gia,gh_soluongdoan;
        ImageView gh_anh,gh_delete,gh_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gh_anh = itemView.findViewById(R.id.gh_anh);
            gh_tensp = itemView.findViewById(R.id.gh_tensp);
            gh_gia = itemView.findViewById(R.id.gh_gia);
            gh_soluongdoan = itemView.findViewById(R.id.gh_soluongdoan);
            gh_delete = itemView.findViewById(R.id.gh_delete);
//            gh_edit = itemView.findViewById(R.id.gh_edit);

        }
    }
    public void  DeleteGH(GioHangDTO gioHangDTO){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa hay không?");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gioHangDAo = new GioHangDAo(context);

                int kq = gioHangDAo.DeleteGH(gioHangDTO);
                if(kq>0){
                    Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(gioHangDAo.getAll());
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
