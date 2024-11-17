package android.project.order.Fragment_User;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.project.order.Adapter.User_DanhSachSP_Adapter;
import android.project.order.DAO.DoAn_DAO;
import android.project.order.DTO.DoAn_DTO;
import android.project.order.R;

public class Fragment_User_DanhSachSP extends Fragment {
    EditText editTextTK;

    private ImageView headerImageView;
    private User_DanhSachSP_Adapter adapter;
    ArrayList<DoAn_DTO> list;
    ArrayList<DoAn_DTO> list1;
    private RecyclerView listViewDanhSach;

    private int[] imageResources = {R.drawable.comrang, R.drawable.pho, R.drawable.buncha,R.drawable.phoxao};
    private int currentImageIndex = 0;

    private Handler handler = new Handler();
    private Runnable imageSwitcher = new Runnable() {
        @Override
        public void run() {
            currentImageIndex = (currentImageIndex + 1) % imageResources.length;
            headerImageView.setImageResource(imageResources[currentImageIndex]);

            handler.postDelayed(this, 3000);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_danhsach_sp, container, false);
        headerImageView = rootView.findViewById(R.id.headerImageView);
        listViewDanhSach = rootView.findViewById(R.id.listView_ds);




        DoAn_DAO doAnDAO;
        doAnDAO = new DoAn_DAO(getContext());
         list= doAnDAO.getAll();
         list1=doAnDAO.getAll();
        adapter = new User_DanhSachSP_Adapter(list,requireContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false);
        listViewDanhSach.setLayoutManager(linearLayoutManager);
        listViewDanhSach.setAdapter(adapter);




        handler.post(imageSwitcher);
        editTextTK = rootView.findViewById(R.id.editTextTK);
        editTextTK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                list.clear();
                for (DoAn_DTO dt :list1){
                    if(dt.getTendoan().contains(s.toString())){
                        list.add(dt);
                    }else if(dt.getTenloai().contains(s.toString())){
                        list.add(dt);
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return rootView;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }

    @Override
    public void onDestroyView() {
        handler.removeCallbacks(imageSwitcher);
        super.onDestroyView();
    }


}
