package android.project.order.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import android.project.order.DTO.DoAn_DTO;
import android.project.order.DbHelper.MyDbHelper;

public class DoAn_DAO {
    MyDbHelper myDbHelper ;
    SQLiteDatabase db;

    public DoAn_DAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public long insertDoAn(DoAn_DTO doAn_dto){
        ContentValues values= new ContentValues();
        values.put("tendoan",doAn_dto.getTendoan());
        values.put("giadoan",doAn_dto.getGiadoan());
        values.put("maloai",doAn_dto.getMaloai());

        values.put("thongtin",doAn_dto.getThongtin());
        values.put("anh",doAn_dto.getAnh());
         return db.insert("dt_doan",null,values);
    }
    public int Update_DoAn(DoAn_DTO doAn_dto){
        ContentValues values= new ContentValues();
        values.put("tendoan",doAn_dto.getTendoan());
        values.put("giadoan",doAn_dto.getGiadoan());
        values.put("maloai",doAn_dto.getMaloai());
        values.put("thongtin",doAn_dto.getThongtin());
        values.put("anh",doAn_dto.getAnh());
        String[] dk = new String[]{String.valueOf(doAn_dto.getMadoan())};

        return db.update("dt_doan",values,"madoan=?",dk);
    }
    public int Delete_DoAn(DoAn_DTO doAn_dto){
        String[] dk = new String[]{String.valueOf(doAn_dto.getMadoan())};

        return db.delete("dt_doan","madoan=?",dk);

    }

    public ArrayList<DoAn_DTO> getAll(){
        String sql = "SELECT da.madoan ,da.tendoan,da.giadoan,da.maloai,la.tenloai,da.thongtin,da.anh " +
                "FROM dt_doan da INNER JOIN dt_loai la ON da.maloai = la.maloai";
        return getData(sql);
    }
    public DoAn_DTO getID(String id){
        String sql = "SELECT * FROM dt_doan WHERE madoan=?";
        List<DoAn_DTO> list = getData(sql,id);

        return list.get(0);
    }

    public ArrayList<DoAn_DTO> getData1(String loai){
        ArrayList<DoAn_DTO> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=myDbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT tendoan,giadoan,thongtin,anh FROM dt_doan JOIN dt_loai ON dt_doan.maloai=dt_loai.maloai WHERE dt_loai.tenloai=?",new String[]{loai});
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new DoAn_DTO(cursor.getString(0), cursor.getInt(1), cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<DoAn_DTO> getData(String sql , String...selectionArgs){
        ArrayList<DoAn_DTO> list= new ArrayList<>();

        Cursor c=db.rawQuery(sql,selectionArgs);
if (c!=null && c.getCount()>0){
c.moveToFirst();
do {
    int ma=c.getInt(0);
    String ten=c.getString(1);
    int gia=c.getInt(2);
    int maloai=c.getInt(3);
    String tenloai=c.getString(4);
    String thongtin=c.getString(5);
    String anh = c.getString(6);

DoAn_DTO doAn_dto= new DoAn_DTO();

doAn_dto.setMadoan(ma);
doAn_dto.setTendoan(ten);
doAn_dto.setGiadoan(gia);
doAn_dto.setMaloai(maloai);
doAn_dto.setTenloai(tenloai);
doAn_dto.setThongtin(thongtin);
doAn_dto.setAnh(anh);
list.add(doAn_dto);


}while (c.moveToNext());
}
return list;
    }


}
