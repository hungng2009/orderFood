package android.project.order.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import android.project.order.DTO.User_DTO;
import android.project.order.DbHelper.MyDbHelper;

public class User_DAO {
    SharedPreferences sharedPreferences;
    MyDbHelper myDbHelper ;
    SQLiteDatabase db;
    public User_DAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
        sharedPreferences= context.getSharedPreferences("THONGTIN",Context.MODE_PRIVATE);
    }

    public long Insert_User(User_DTO userDto){
        ContentValues values = new ContentValues();
        values.put("MaND",userDto.getMaND());
        values.put("HoTen",userDto.getHoTen());
        values.put("MatKhau",userDto.getMatKhau());
        values.put("Email",userDto.getEmail());
        values.put("NamSinh",userDto.getNamSinh());
        values.put("SDT",userDto.getSDT());
values.put("LoaiTaiKhoan","user");
        return db.insert("dt_nguoidung",null,values);

    }
    public int Update_User(User_DTO userDto){
        ContentValues values = new ContentValues();
        values.put("MaND",userDto.getMaND());
        values.put("HoTen",userDto.getHoTen());
        values.put("MatKhau",userDto.getMatKhau());
        values.put("Email",userDto.getEmail());
        values.put("NamSinh",userDto.getNamSinh());
        values.put("SDT",userDto.getSDT());
        String[] dk = new String[]{userDto.getMaND()};

        return db.update("dt_nguoidung",values,"MaND=?",dk);

    }

    public int Delete_User(User_DTO userDto){
        String[] dk = new String[]{userDto.getMaND()};

        return db.delete("dt_nguoidung","MaND=?",dk);

    }

    public List<User_DTO> getData(String sql, String...selectionArgs){
        List<User_DTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();

            do {
                User_DTO userDto = new User_DTO();
                userDto.setMaTV(c.getInt(0));
                userDto.setMaND(c.getString(1));
                userDto.setHoTen(c.getString(2));
                userDto.setMatKhau(c.getString(3));
                userDto.setEmail(c.getString(4));
                userDto.setNamSinh(c.getString(5));
                userDto.setSDT(c.getString(6));
                userDto.setTypeAcc(c.getString(7));
                saveLoggedInUser(userDto);

                list.add(userDto);
            } while (c.moveToNext());


        }

        return list;
    }

    public List<User_DTO> getAll(){
        String sql = "SELECT * FROM dt_nguoidung";
        return getData(sql);
    }


    public User_DTO getID(String id){
        String sql = "SELECT * FROM dt_nguoidung WHERE MaND=?";
        List<User_DTO> list = getData(sql,id);

        return list.get(0);
    }

    public boolean checkLogin(String user , String pass){
        Cursor c = db.rawQuery("SELECT * FROM dt_nguoidung WHERE MaND=? AND MatKhau=?",new String[]{user,pass});
        if(c.getCount()>0){
            c.moveToFirst();
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("MaND", c.getString(1));
            editor.commit();
            return true;
        }else {
            return false;
        }
    }

    public int Update_DangNhap(String Email, String pass){
        ContentValues values = new ContentValues();
        values.put("MatKhau",pass);
        String[] dk ={Email};

        return db.update("dt_nguoidung",values,"Email=?",dk);
    }
    public Boolean getAccountEmail(String Email){
        String[] columns = {"Email"};
        String selection = "Email=?";
        String[] selectionArgs = {Email};

        Cursor cursor = db.query("dt_nguoidung", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();

        return count > 0;
    }
    public boolean updatepass(String username,String oldpass,String newpass){

        Cursor cursor=db.rawQuery("SELECT * FROM dt_nguoidung WHERE MaND=? AND MatKhau=?",new String[]{username,oldpass});
        if (cursor.getCount()>0){
            ContentValues contentValues=new ContentValues();
            contentValues.put("matkhau",newpass);
            long check=db.update("dt_nguoidung",contentValues,"MaND=?",new String[]{username});
            if(check==-1){
                return false;}else{
                return true;}
        }
        return false;
    }
    public void saveLoggedInUser(User_DTO userDto) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("MaTV", userDto.getMaTV());
        editor.putString("MaND", userDto.getMaND());
        editor.putString("HoTen", userDto.getHoTen());
        editor.putString("MatKhau", userDto.getMatKhau());
        editor.putString("Email", userDto.getEmail());
        editor.putString("NamSinh", userDto.getNamSinh());
        editor.putString("SDT", userDto.getSDT());
        editor.putString("TypeAcc", userDto.getTypeAcc());
        editor.apply();
    }
    public boolean isLoggedIn() {
        return sharedPreferences.contains("MaTV");
    }
    public User_DTO getCurrentLoggedInUser() {
        if (isLoggedIn()) {
            String maTV= String.valueOf(sharedPreferences.getInt("MaTv",0));
            String maND = sharedPreferences.getString("MaND", "");
            String hoTen = sharedPreferences.getString("HoTen", "");
            String matKhau = sharedPreferences.getString("MatKhau", "");
            String email = sharedPreferences.getString("Email", "");
            String namSinh = sharedPreferences.getString("NamSinh", "");
            String sdt = sharedPreferences.getString("SDT", "");
            String typeAcc = sharedPreferences.getString("TypeAcc", "");

            return new User_DTO(maTV,maND, hoTen, matKhau, email, namSinh, sdt, typeAcc);
        } else {
            return null;
        }
    }





}
