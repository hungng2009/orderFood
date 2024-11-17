package android.project.order.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import android.project.order.DbHelper.MyDbHelper;

public class ThongKeTop_DAO {

    SQLiteDatabase db;
    MyDbHelper myDbHelper;

    public ThongKeTop_DAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }



    public  int getDoanhThu(String tuNgay , String denNgay){
        String sqlDoanhThu = "SELECT SUM(tongtien) FROM dt_hoadon where ngaydathang BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay,denNgay});

        while (c.moveToNext()){
            try {
                list.add(c.getInt(0));

            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
