package android.project.order.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import android.project.order.DTO.LoaiDoAn_DTO;
import android.project.order.DbHelper.MyDbHelper;

public class LoaiDoAn_DAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase db ;
    public LoaiDoAn_DAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long Insert_LoaiDoAn(LoaiDoAn_DTO doAn_dto){
        ContentValues values = new ContentValues();
        values.put("tenloai",doAn_dto.getTenloai());

        return db.insert("dt_loai",null,values);
    }
    public int Update_LoaiDoAn(LoaiDoAn_DTO doAn_dto){
        ContentValues values = new ContentValues();
        values.put("tenloai",doAn_dto.getTenloai());
        String[] dk = new String[]{String.valueOf(doAn_dto.getMaloai())};

        return db.update("dt_loai",values,"maloai=?",dk);

    }
    public int Delete_LoaiDoAn(LoaiDoAn_DTO doAn_dto){
        String[] dk = new String[]{String.valueOf(doAn_dto.getMaloai())};

        return db.delete("dt_loai","maloai=?",dk);

    }

    public List<LoaiDoAn_DTO> getData(String sql , String...selectionArgs){
        List<LoaiDoAn_DTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        if(c!=null && c.getCount()>0){
            c.moveToFirst();
            do {
                LoaiDoAn_DTO loaiDoAnDto = new LoaiDoAn_DTO();
                loaiDoAnDto.setMaloai(c.getInt(0));
                loaiDoAnDto.setTenloai(c.getString(1));

                list.add(loaiDoAnDto);
            }while (c.moveToNext());
        }
        return list;
    }

    public List<LoaiDoAn_DTO > getAll(){
        String sql ="SELECT * FROM dt_loai";
        return getData(sql);
    }
    public LoaiDoAn_DTO getID(String id){
        String sql = "SELECT * FROM dt_loai WHERE MaND=?";
        List<LoaiDoAn_DTO> list = getData(sql,id);

        return list.get(0);
    }


}
