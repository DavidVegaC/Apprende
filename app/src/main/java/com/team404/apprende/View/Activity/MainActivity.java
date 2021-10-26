package com.team404.apprende.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.team404.apprende.View.Fragment.MainFragment;
import com.team404.apprende.R;
import com.team404.apprende.Util.CustomAnimation;
import com.team404.apprende.Util.NavigationFragment;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    private void initComponent(){
        mainFragment = new MainFragment();
        goToMainFragment();
    }

    public void goToMainFragment(){
        NavigationFragment.addFragment(null, mainFragment, "mainFragment", this,
                R.id.main_activity_content, false, CustomAnimation.LEFT_RIGHT);
    }


}