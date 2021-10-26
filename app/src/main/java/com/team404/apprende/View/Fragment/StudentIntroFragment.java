package com.team404.apprende.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.team404.apprende.R;
import com.team404.apprende.Util.CustomAnimation;
import com.team404.apprende.Util.NavigationFragment;
import com.team404.apprende.View.Activity.LoginActivity;
import com.team404.apprende.View.Activity.SignUpActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentIntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentIntroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewGroup rootView;

    private ImageView btnBackMain;
    private ConstraintLayout constraintLayoutSI, constraintLayoutNO;

    public StudentIntroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentIntroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentIntroFragment newInstance(String param1, String param2) {
        StudentIntroFragment fragment = new StudentIntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_student_intro, container, false);
        initComponent();
        return rootView;
    }

    private void initComponent(){

        btnBackMain = rootView.findViewById(R.id.btnBackMain);
        constraintLayoutSI = rootView.findViewById(R.id.constraintLayoutSI);
        constraintLayoutNO = rootView.findViewById(R.id.constraintLayoutNO);

        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationFragment.addFragment(null, new MainFragment(), "mainFragment", getActivity(),
                        R.id.main_activity_content, false, CustomAnimation.IN_OUT);
            }
        });

        constraintLayoutSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent=new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        constraintLayoutNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }




}