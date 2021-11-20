package com.example.emelyanovpmppr27;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.emelyanovpmppr27.Fragments.ActionBarHistoryFragment;
import com.example.emelyanovpmppr27.Fragments.ActionBarHistoryFragmentOpenned;
import com.example.emelyanovpmppr27.Fragments.ActionBarMainFragment;
import com.example.emelyanovpmppr27.Fragments.ActionBarSettingsFragment;
import com.example.emelyanovpmppr27.Fragments.ActionBarSettingsFragmentOpenned;
import com.example.emelyanovpmppr27.Fragments.HistoryFragment;
import com.example.emelyanovpmppr27.Fragments.MainFragment;
import com.example.emelyanovpmppr27.Fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DrawerLayout drawer;
    FrameLayout actionBar;
    LinearLayout menuButton;
    NavigationView navigationView;

    int defaultButtonHeight;
    int defaultActionBarHeight;
    ViewTreeObserver ViewTreeObserver;

    int currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawerLayout);
        actionBar = findViewById(R.id.myActionBar);
        menuButton = findViewById(R.id.menuButton);
        navigationView = findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(this);

        ShowMainFragment();

        ViewTreeObserver = menuButton.getViewTreeObserver();
        ViewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                defaultButtonHeight = menuButton.getLayoutParams().height;
                menuButton.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        ViewTreeObserver = actionBar.getViewTreeObserver();
        ViewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                defaultActionBarHeight = actionBar.getLayoutParams().height;
                if(navigationView.getCheckedItem() == null)
                    HideActionBar();
                actionBar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        ActionBarDrawerToggle drawerToggle =
                new ActionBarDrawerToggle(this,drawer,R.string.draweOpen,R.string.drawerClose) {

            @Override
            public void onDrawerClosed(View v){
                if(currentFragment == 0) {
                    ShowMenuButton();
                    HideActionBar();
                }
                else if(currentFragment == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarHistoryFragment()).commit();
                }
                else if(currentFragment == 2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarSettingsFragment()).commit();
                }
            }

            public void onDrawerOpened(View v){
                if(currentFragment == 0) {
                    HideMenuButton();
                    ShowActionBar();
                }
                else if(currentFragment == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarHistoryFragmentOpenned()).commit();
                }
                else if(currentFragment == 2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarSettingsFragmentOpenned()).commit();
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

                    if (newState == DrawerLayout.STATE_DRAGGING) {
                        if (!drawer.isDrawerOpen(GravityCompat.START)) {
                            //opening
                            if(currentFragment == 0) {
                                HideMenuButton();
                                ShowActionBar();
                            }
                            else if(currentFragment == 1){
                                getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarHistoryFragmentOpenned()).commit();
                            }
                            else if(currentFragment == 2){
                                getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarSettingsFragmentOpenned()).commit();
                            }
                        } else {
                            if(currentFragment == 0) {
                                HideActionBar();
                            }
                            else if(currentFragment == 1){
                                getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarHistoryFragment()).commit();
                            }
                            else if(currentFragment == 2){
                                getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarSettingsFragment()).commit();
                            }
                        }
                    }
                    if (newState == DrawerLayout.STATE_IDLE) {
                        if (!drawer.isDrawerOpen(GravityCompat.START)) {
                            if(currentFragment == 0) {
                                ShowMenuButton();
                                HideActionBar();
                            }
                            else if(currentFragment == 1){
                                getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarHistoryFragment()).commit();
                            }
                            else if(currentFragment == 2){
                                getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarSettingsFragment()).commit();
                            }

                        }
                    }

            }
        };
        drawer.setDrawerListener(drawerToggle);
    }


    private void HideActionBar(){
        LinearLayout.LayoutParams actionBarParams = (LinearLayout.LayoutParams) actionBar.getLayoutParams();
        actionBarParams.height = 0;
        actionBar.setLayoutParams(actionBarParams);
    }

    private void ShowActionBar(){
        LinearLayout.LayoutParams actionBarParams = (LinearLayout.LayoutParams) actionBar.getLayoutParams();
        actionBarParams.height = defaultActionBarHeight;
        actionBar.setLayoutParams(actionBarParams);
    }

    private void HideMenuButton(){
        FrameLayout.LayoutParams newMenuButtonParams = (FrameLayout.LayoutParams) menuButton.getLayoutParams();
        newMenuButtonParams.height = 0;
        newMenuButtonParams.width = 0;
        menuButton.setLayoutParams(newMenuButtonParams);
    }

    private void ShowMenuButton(){
        FrameLayout.LayoutParams newMenuButtonParams = (FrameLayout.LayoutParams) menuButton.getLayoutParams();
        newMenuButtonParams.height = defaultButtonHeight;
        newMenuButtonParams.width = defaultButtonHeight;
        menuButton.setLayoutParams(newMenuButtonParams);
    }

    public void ShowMainFragment(){
        currentFragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarMainFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
    }

    public void ShowHistoryFragment(){
        currentFragment = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarHistoryFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
    }

    public void ShowSettingsFragment(){
        currentFragment = 2;
        getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarSettingsFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
    }

    public void menuBtnClick(View v){
        HideMenuButton();
        ShowActionBar();

        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_item_history:
                ShowHistoryFragment();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_item_settings:
               ShowSettingsFragment();
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            if(currentFragment == 0) {
                ShowMenuButton();
                HideActionBar();
            }
            else if(currentFragment == 1){
                getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarHistoryFragment()).commit();
            }
            else if(currentFragment == 2){
                getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarSettingsFragment()).commit();
            }
        }
        else
            super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.menuButtonBack:
                ShowMainFragment();
                drawer.closeDrawer(GravityCompat.START);
                navigationView.getCheckedItem().setChecked(false);
                break;

            case R.id.menuButtonOpen:
                drawer.openDrawer(GravityCompat.START);
                if(currentFragment == 0) {
                    HideMenuButton();
                    ShowActionBar();
                }
                else if(currentFragment == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarHistoryFragmentOpenned()).commit();
                }
                else if(currentFragment == 2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.ActionBarContainer, new ActionBarSettingsFragmentOpenned()).commit();
                }
                break;

            case R.id.lblExit:
                Intent loginActivity = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(loginActivity);
                finish();
                break;
        }
    }
}
