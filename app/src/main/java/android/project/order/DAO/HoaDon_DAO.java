package android.project.order.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import android.project.order.DTO.HoaDon_DTO;
import android.project.order.DbHelper.MyDbHelper;

public class HoaDon_DAO {
    MyDbHelper myDbHelper ;
    static SQLiteDatabase db;

    public HoaDon_DAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public long InsertHD(HoaDon_DTO hoaDonDto){
        ContentValues values = new ContentValues();
        values.put("Email",hoaDonDto.getEmail());
        values.put("hoten",hoaDonDto.getHoten());
        values.put("SDT",hoaDonDto.getSDT());
        values.put("diachinhan",hoaDonDto.getDiachinhan());
        values.put("thucdon",hoaDonDto.getThucdon());
        values.put("ngaydathang",hoaDonDto.getNgaydathang());
        values.put("tongtien",hoaDonDto.getTongtien());
        values.put("thanhtoan",hoaDonDto.getThanhtoan());
        values.put("trangthai",hoaDonDto.getTrangthai());

        return db.insert("dt_hoadon",null,values);
    }
    public int UpdateHD(HoaDon_DTO hoaDonDto){
        ContentValues values = new ContentValues();
        values.put("Email",hoaDonDto.getEmail());
        values.put("hoten",hoaDonDto.getHoten());
        values.put("SDT",hoaDonDto.getSDT());
        values.put("diachinhan",hoaDonDto.getDiachinhan());
        values.put("thucdon",hoaDonDto.getThucdon());
        values.put("ngaydathang",hoaDonDto.getNgaydathang());
        values.put("tongtien",hoaDonDto.getTongtien());
        values.put("thanhtoan",hoaDonDto.getThanhtoan());
        values.put("trangthai",hoaDonDto.getTrangthai());

        String[] dk = new String[]{String.valueOf(hoaDonDto.getMahoadon())};
        return db.update("dt_hoadon",values,"mahoadon=?",dk);
    }
    public List<HoaDon_DTO> getAll(){
        String sql = "SELECT * FROM dt_hoadon";
        return getData(sql);
    }
    public List<HoaDon_DTO> getAllByEmail(String email){
        List<HoaDon_DTO> list= new ArrayList<>();
        String sql = "SELECT * FROM dt_hoadon WHERE Email=?";

        Cursor c=db.rawQuery(sql, new String[]{email});
        if (c!=null && c.getCount()>0){
            c.moveToFirst();
            do {
                int madon=c.getInt(0);
                String Email=c.getString(1);
                String hoten=c.getString(2);
                String sdt=c.getString(3);
                String diachi=c.getString(4);
                String thucdon =c.getString(5);
                String ngaydat=c.getString(6);
                int tongtien=c.getInt(7);
                String thanhtoan=c.getString(8);
                String trangthai=c.getString(9);

                HoaDon_DTO hoaDon_dto=new HoaDon_DTO();

                hoaDon_dto.setMahoadon(madon);
                hoaDon_dto.setEmail(Email);
                hoaDon_dto.setHoten(hoten);
                hoaDon_dto.setSDT(sdt);
                hoaDon_dto.setDiachinhan(diachi);
                hoaDon_dto.setThucdon(thucdon);
                hoaDon_dto.setNgaydathang(ngaydat);
                hoaDon_dto.setTongtien(tongtien);
                hoaDon_dto.setThanhtoan(thanhtoan);
                hoaDon_dto.setTrangthai(trangthai);
                list.add(hoaDon_dto);


            }while (c.moveToNext());
        }
        return list;
    }
    public HoaDon_DTO getID(String id){
        String sql = "SELECT * FROM dt_hoadon WHERE Email=?";
        List <HoaDon_DTO> list = getData(sql,id);
        return list.get(0);
    }

    public List<HoaDon_DTO> getData(String sql, String... selectionArgs){
        List<HoaDon_DTO> list= new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        if (c!=null && c.getCount()>0){
            c.moveToFirst();
            do {
                int madon=c.getInt(0);
                String Email=c.getString(1);
                String hoten=c.getString(2);
                String sdt=c.getString(3);
                String diachi=c.getString(4);
                String thucdon =c.getString(5);
                String ngaydat=c.getString(6);
                int tongtien=c.getInt(7);
                String thanhtoan=c.getString(8);
                String trangthai=c.getString(9);

                HoaDon_DTO hoaDon_dto=new HoaDon_DTO();

                hoaDon_dto.setMahoadon(madon);
                hoaDon_dto.setEmail(Email);
                hoaDon_dto.setHoten(hoten);
                hoaDon_dto.setSDT(sdt);
                hoaDon_dto.setDiachinhan(diachi);
                hoaDon_dto.setThucdon(thucdon);
                hoaDon_dto.setNgaydathang(ngaydat);
                hoaDon_dto.setTongtien(tongtien);
                hoaDon_dto.setThanhtoan(thanhtoan);
                hoaDon_dto.setTrangthai(trangthai);
                list.add(hoaDon_dto);


            }while (c.moveToNext());
        }
        return list;
    }


}
