package android.project.order.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import android.project.order.DTO.BanAn_DTO;
import android.project.order.DbHelper.MyDbHelper;

public class BanAn_DAO {

    MyDbHelper myDbHelper;
    SQLiteDatabase db;
    public BanAn_DAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long Insert_BanAN(BanAn_DTO banAnDto){
        ContentValues values = new ContentValues();
        values.put("tenban",banAnDto.getTenban());

        return db.insert("dt_banan",null,values);
    }
    public int Update_BanAN(BanAn_DTO banAnDto){
        ContentValues values = new ContentValues();
        values.put("tenban",banAnDto.getTenban());
        String[] dk = new String[]{String.valueOf(banAnDto.getMaBan())};

        return db.update("dt_banan",values,"maban=?",dk);

    }
    public int Delete_BanAN(BanAn_DTO banAnDto){

        String[] dk = new String[]{String.valueOf(banAnDto.getMaBan())};

        return db.delete("dt_banan","maban=?",dk);

    }

    public List<BanAn_DTO> getData(String sql , String...selectionArgs){
        List<BanAn_DTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        if(c!=null && c.getCount()>0){
            c.moveToFirst();
            do {
                BanAn_DTO banAnDto = new BanAn_DTO();
                banAnDto.setMaBan(c.getInt(0));
                banAnDto.setTenban(c.getString(1));

                list.add(banAnDto);
            }while (c.moveToNext());
        }
        return list;
    }

    public List<BanAn_DTO > getAll(){
        String sql = "SELECT * FROM dt_banan";
        return getData(sql);
    }
    public BanAn_DTO getID(String id){
        String sql = "SELECT * FROM dt_banan WHERE maban=?";
        List<BanAn_DTO> list = getData(sql,id);

        return list.get(0);
    }
}
