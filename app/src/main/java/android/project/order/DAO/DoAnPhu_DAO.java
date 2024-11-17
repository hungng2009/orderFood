package android.project.order.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import android.project.order.DTO.DoAnPhu_DTO;
import android.project.order.DbHelper.MyDbHelper;

public class DoAnPhu_DAO {

    MyDbHelper myDbHelper;
    SQLiteDatabase db;
    public DoAnPhu_DAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long Insert_DoAnPhu(DoAnPhu_DTO doAnPhu_dto){
        ContentValues values = new ContentValues();
        values.put("TenDoAnPhu",doAnPhu_dto.getTenDoAnPhu());

        return db.insert("dt_doanphu",null,values);
    }
    public int Update_DoAnPhu(DoAnPhu_DTO doAnPhu_dto){
        ContentValues values = new ContentValues();
        values.put("TenDoAnPhu",doAnPhu_dto.getTenDoAnPhu());
        String[] dk = new String[]{String.valueOf(doAnPhu_dto.getMaDoAnPhu())};

        return db.update("dt_doanphu",values,"MaDoAnPhu=?",dk);

    }
    public int Delete_DoAnPhu(DoAnPhu_DTO doAnPhu_dto){

        String[] dk = new String[]{String.valueOf(doAnPhu_dto.getMaDoAnPhu())};

        return db.delete("dt_doanphu","MaDoAnPhu=?",dk);

    }

    public List<DoAnPhu_DTO> getData(String sql , String...selectionArgs){
        List<DoAnPhu_DTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        if(c!=null && c.getCount()>0){
            c.moveToFirst();
            do {
                DoAnPhu_DTO doAnPhuDto = new DoAnPhu_DTO();
                doAnPhuDto.setMaDoAnPhu(c.getInt(0));
                doAnPhuDto.setTenDoAnPhu(c.getString(1));

                list.add(doAnPhuDto);
            }while (c.moveToNext());
        }
        return list;
    }

    public List<DoAnPhu_DTO > getAll(){
        String sql = "SELECT * FROM dt_doanphu";
        return getData(sql);
    }
    public DoAnPhu_DTO getID(String id){
        String sql = "SELECT * FROM dt_doanphu WHERE MaDoAnPhu=?";
        List<DoAnPhu_DTO> list = getData(sql,id);

        return list.get(0);
    }
}
