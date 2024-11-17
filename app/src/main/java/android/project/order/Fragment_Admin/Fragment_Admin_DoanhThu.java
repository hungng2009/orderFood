package android.project.order.Fragment_Admin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.Calendar;

import android.project.order.DAO.ThongKeTop_DAO;
import android.project.order.R;

public class Fragment_Admin_DoanhThu extends Fragment {
    Button btn_doangthu;
    AppCompatButton btn_dttungay,btn_dtdenngay;
    EditText dt_tungay,dt_denngay;
    TextView dt_tien;

    int mDay,mMonth,mYear;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###.###");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_doanhthu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_doangthu=view.findViewById(R.id.btn_doanhthu);
        btn_dttungay = view.findViewById(R.id.btn_dttungay);
        btn_dtdenngay = view.findViewById(R.id.btn_dtdenngay);
        dt_tungay = view.findViewById(R.id.dt_tungay);
        dt_denngay = view.findViewById(R.id.dt_denngay);
        dt_tien = view.findViewById(R.id.dt_tien);
        btn_dttungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dt_tungay.setText(""+dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },mYear,mMonth,mDay);
                d.show();
            }
        });
        btn_dtdenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dt_denngay.setText(""+dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },mYear,mMonth,mDay);
                d.show();

            }
        });
        btn_doangthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeTop_DAO thongKe_dao = new ThongKeTop_DAO(getContext());
                int doanhthu = thongKe_dao.getDoanhThu(dt_tungay.getText().toString(),dt_denngay.getText().toString());
                dt_tien.setText(""+decimalFormat.format(doanhthu)+" VND");


            }
        });
    }
}
