package android.project.order.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "duan_datdoan";
    static final int DB_VERSION=20;

    public MyDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql_nguoidung = "CREATE TABLE dt_nguoidung (\n" +
                "    maTV INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    MaND    TEXT NOT NULL,\n" +
                "    HoTen   TEXT NOT NULL,\n" +
                "    MatKhau TEXT NOT NULL,\n" +
                "    Email   TEXT NOT NULL,\n" +
                "    NamSinh TEXT ,\n" +
                "    SDT     TEXT,\n" +
                "    LoaiTaiKhoan TEXT\n" +
                ");\n";

        sqLiteDatabase.execSQL(sql_nguoidung);
        String mauUSer ="INSERT INTO dt_nguoidung " +
                "VALUES (1,'admin','Quản lý','admin','helo@gmail.com','2004','012345678','admin')," +
                "(2,'nguoidung01','Người dùng','nguoidung01','nguoidung@gmail.com','2005','123456789','user')," +
                "(3,'daubep','Đầu bếp','daubep','daubep@gmail.com','2002','123456789','daubep')"
                ;
        sqLiteDatabase.execSQL(mauUSer);
        String sql_Loai = "CREATE TABLE dt_loai (maloai integer primary key not null, tenloai text not null)";
        sqLiteDatabase.execSQL(sql_Loai);
        String mauLoai = "INSERT INTO dt_loai values('1','cơm'),('2','bún'),('3','xôi'),('4','cháo')";
        sqLiteDatabase.execSQL(mauLoai);

        String sql_doan = "CREATE TABLE dt_doan(madoan integer primary key not null ,tendoan text not null,giadoan integer not null, maloai integer references dt_loai(maloai)  not null,thongtin text not null,anh text )";
        sqLiteDatabase.execSQL(sql_doan);
        String doan_1= "INSERT INTO dt_doan values('1','cơm rang',30000,1,'Cơm rất ngon','https://lh3.googleusercontent.com/CB-FFqhq6t5UbEnTKo0Rw6fX1gtO89k4ZPDZLHDNW09Gv9JH89xeaqohwsq6xzfuEHAooiFLhMbDgl_zkKrRP8fBLZk=w622')";
        sqLiteDatabase.execSQL(doan_1);
        String doan_2= "INSERT INTO dt_doan values('2','bún chả ',30000,2,'Bún rất ngon','https://bizweb.dktcdn.net/100/442/328/products/bun-cha-ha-noi.jpg?v=1644892472637')";
        sqLiteDatabase.execSQL(doan_2);
        String doan_3= "INSERT INTO dt_doan values('3','Xôi ngô',15000,3,'Xôi rất dẻo và thơm','https://i-giadinh.vnecdn.net/2022/02/25/Thanh-pham-1-6778-1645781140.jpg')";
        sqLiteDatabase.execSQL(doan_3);
        String doan_4= "INSERT INTO dt_doan values('4','Cháo thịt',10000,4,'Cháo rất giàu dinh dưỡng','https://cdn.tgdd.vn/Files/2021/08/19/1376391/cach-nau-chao-coi-so-diep-don-gian-dinh-duong-tot-cho-suc-khoe-ca-nha-202201191541013201.jpg')";
        sqLiteDatabase.execSQL(doan_4);
        String sql_doanphu="CREATE TABLE dt_doanphu (\n" +
                "    MaDoAnPhu  INTEGER PRIMARY KEY,\n" +
                "    TenDoAnPhu TEXT    NOT NULL\n" +
                ");\n";
        sqLiteDatabase.execSQL(sql_doanphu);
        String doanphu="INSERT INTO dt_doanphu(TenDoAnPhu) VALUES ('Giò'),('Chả'),('Nem'),('Trứng')";
        sqLiteDatabase.execSQL(doanphu);
String sql_hoadon="CREATE TABLE dt_hoadon(mahoadon integer primary key,Email text not null,hoten text,SDT text not null,diachinhan text not null, thucdon text ,ngaydathang text,tongtien integer ,thanhtoan text,trangthai text)";
sqLiteDatabase.execSQL(sql_hoadon);
//        String mauhoadon = "INSERT INTO dt_hoadon values('1','nguoidung@gmail.com','Người dùng','123456789','bàn số 1','cơm rang','22/2/2002',12000,'Đã thanh toán bằng tiền mặt','Đang chế biến món ăn')";
//        sqLiteDatabase.execSQL(mauhoadon);

        String giohang = "CREATE TABLE dt_giohang(masp integer primary key not null ,tensp text not null,tendoanphu text," +
                "giasp integer not null,soluong integer,anhsp text )";
        sqLiteDatabase.execSQL(giohang);
//        String maugh = "INSERT INTO dt_giohang(tensp,tendoanphu,giasp,soluong,anhsp) VALUES ('cơm rang','Giò',12000,2,'https://lh3.googleusercontent.com/CB-FFqhq6t5UbEnTKo0Rw6fX1gtO89k4ZPDZLHDNW09Gv9JH89xeaqohwsq6xzfuEHAooiFLhMbDgl_zkKrRP8fBLZk=w622')," +
//                "('bún chả','Chả',13000,3,'https://bizweb.dktcdn.net/100/442/328/products/bun-cha-ha-noi.jpg?v=1644892472637')";
//        sqLiteDatabase.execSQL(maugh);
        String sql_ban = "CREATE TABLE dt_banan(maban integer primary key not null ,tenban text not null)";
        sqLiteDatabase.execSQL(sql_ban);
        String ban="INSERT INTO dt_banan(tenban) VALUES ('Bàn số 1'),('Bàn số 2'),('Bàn số 3'),('Bàn số 4')";
        sqLiteDatabase.execSQL(ban);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists dt_nguoidung");
        sqLiteDatabase.execSQL("DROP TABLE if exists dt_doan");
        sqLiteDatabase.execSQL("DROP TABLE if exists dt_loai");
        sqLiteDatabase.execSQL("DROP TABLE if exists dt_doanphu");
        sqLiteDatabase.execSQL("DROP TABLE if exists dt_hoadon");
        sqLiteDatabase.execSQL("DROP TABLE if exists dt_giohang");
        sqLiteDatabase.execSQL("DROP TABLE if exists dt_banan");

        onCreate(sqLiteDatabase);

    }
}
