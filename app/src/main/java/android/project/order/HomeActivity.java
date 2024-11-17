package android.project.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import android.project.order.DAO.User_DAO;
import android.project.order.DTO.User_DTO;
import android.project.order.Fragment_Admin.FragmentDonHangDauBep;
import android.project.order.Fragment_Admin.Fragment_Admin_BanAn;
import android.project.order.Fragment_Admin.Fragment_Admin_DoanhThu;
import android.project.order.Fragment_Admin.Fragment_Admin_DonHang;
import android.project.order.Fragment_Admin.Fragment_Admin_QL_LoaiSP;
import android.project.order.Fragment_Admin.Fragment_Admin_QL_ND;
import android.project.order.Fragment_Admin.Fragment_Admin_SP_phu;
import android.project.order.Fragment_Admin.Fragment_Admin_ThemSP;
import android.project.order.Fragment_User.Fragment_DoiMatKhau;
import android.project.order.Fragment_User.Fragment_User_DanhSachSP;
import android.project.order.Fragment_User.Fragment_User_HoaDon;
import android.project.order.Fragment_User.Fragment_User_LoaiSP;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout home_all;
    NavigationView main_nav_view;
    Toolbar home_toolbar;
    FragmentContainerView home_fragment;
    FragmentManager fm;
    View mHeaderview;
    User_DAO user_dao;
    TextView Nameuser;

    Fragment_Admin_DoanhThu fragment_admin_doanhThu;
    Fragment_Admin_DonHang fragment_admin_donHang;
    Fragment_Admin_QL_LoaiSP fragment_admin_ql_loaiSP;
    Fragment_Admin_QL_ND fragment_admin_ql_nd;
    Fragment_Admin_SP_phu fragment_admin_sp_phu;
    Fragment_Admin_ThemSP fragment_admin_themSP;
    Fragment_User_DanhSachSP fragment_user_danhSachSP;
    Fragment_User_HoaDon fragment_user_hoaDon;
    Fragment_User_LoaiSP fragment_user_loaiSP;
    Fragment_DoiMatKhau fragment_doiMatKhau;
    Fragment_Admin_BanAn fragment_admin_banAn;
    FragmentDonHangDauBep fragmentDonHangDauBep;

    String maND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        home_all = findViewById(R.id.home_all);
        home_toolbar = findViewById(R.id.home_toobal);
        home_fragment = findViewById(R.id.home_fragment);

        setSupportActionBar(home_toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,home_all,home_toolbar,R.string.open,R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        home_all.addDrawerListener(drawerToggle);

        fragment_admin_doanhThu = new Fragment_Admin_DoanhThu();
        fragment_admin_donHang = new Fragment_Admin_DonHang();
        fragment_admin_ql_loaiSP = new Fragment_Admin_QL_LoaiSP();
        fragment_admin_ql_nd = new Fragment_Admin_QL_ND();
        fragment_admin_sp_phu = new Fragment_Admin_SP_phu();
        fragment_admin_themSP = new Fragment_Admin_ThemSP();
        fragment_admin_banAn = new Fragment_Admin_BanAn();

        fragment_user_hoaDon = new Fragment_User_HoaDon();
        fragment_user_loaiSP = new Fragment_User_LoaiSP();
        fragment_user_danhSachSP = new Fragment_User_DanhSachSP();
        fragment_doiMatKhau = new Fragment_DoiMatKhau();
        fragmentDonHangDauBep = new FragmentDonHangDauBep();


        fm = getSupportFragmentManager();
//        fm.beginTransaction().add(R.id.home_fragment,fragment_admin_themSP).commit();

        main_nav_view = findViewById(R.id.main_nav_view);
        mHeaderview = main_nav_view.getHeaderView(0);
        Nameuser = mHeaderview.findViewById(R.id.tv_header);
        Bundle bundle = getIntent().getExtras();
         maND = bundle.getString("user");
        user_dao = new User_DAO(this);
        User_DTO user_dto = user_dao.getID(maND);
        String username = user_dto.getHoTen();
        Nameuser.setText("Welcom " +username+ "!" );

        if(maND.equalsIgnoreCase("admin")){
            main_nav_view.getMenu().findItem(R.id.admin).setVisible(true);
            main_nav_view.getMenu().findItem(R.id.admin1).setVisible(true);
            fm.beginTransaction().add(R.id.home_fragment,fragment_admin_themSP).commit();


        }else if(maND.equalsIgnoreCase("daubep")) {
            main_nav_view.getMenu().findItem(R.id.daubep).setVisible(true);
            fm.beginTransaction().add(R.id.home_fragment,fragmentDonHangDauBep).commit();

        }else {
            main_nav_view.getMenu().findItem(R.id.user).setVisible(true);
            fm.beginTransaction().add(R.id.home_fragment,fragment_user_danhSachSP).commit();
        }
        main_nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home_admin_doanhthu){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_admin_doanhThu).commit();
                }else if(item.getItemId() == R.id.home_admin_donhang){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_admin_donHang).commit();
                }else if(item.getItemId() == R.id.home_admin_ND){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_admin_ql_nd).commit();
                }else if(item.getItemId() == R.id.home_admin_loaiSP){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_admin_ql_loaiSP).commit();
                }else if(item.getItemId() == R.id.home_admin_SP_phu){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_admin_sp_phu).commit();
                }else if(item.getItemId() == R.id.home_admin_themSP){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_admin_themSP).commit();
                }else if(item.getItemId() == R.id.home_user_danhsachSP){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_user_danhSachSP).commit();
                }else if(item.getItemId() == R.id.home_user_hoadon){
//                    fm.beginTransaction().replace(R.id.home_fragment,fragment_user_hoaDon).commit();
                    Intent intent = new Intent(HomeActivity.this,HoaDonUserActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ma",maND);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(item.getItemId() == R.id.home_user_loaiSP){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_user_loaiSP).commit();
                } else if(item.getItemId() == R.id.home_taikhoan){
                    home_all.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(HomeActivity.this, TaiKhoanNDActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ma",maND);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(item.getItemId() == R.id.home_doimatkhau){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_doiMatKhau).commit();
                }else if(item.getItemId() == R.id.home_admin_Banan){
                    fm.beginTransaction().replace(R.id.home_fragment,fragment_admin_banAn).commit();
                }else if(item.getItemId() == R.id.home_Dangxuat){
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.home_daubep_hoadon) {
                    fm.beginTransaction().replace(R.id.home_fragment,fragmentDonHangDauBep).commit();

                }

                getSupportActionBar().setTitle(item.getTitle());
                home_all.close();
                return true;
            }
        });

    }
}