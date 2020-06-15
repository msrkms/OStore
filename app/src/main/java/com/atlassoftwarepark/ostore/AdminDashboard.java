package com.atlassoftwarepark.ostore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.atlassoftwarepark.ostore.Adepter.CustomProfileSpinnerItem;
import com.atlassoftwarepark.ostore.Adepter.NotificationDropDownAdepter;
import com.atlassoftwarepark.ostore.Adepter.NotificationSpinnerItem;
import com.atlassoftwarepark.ostore.Adepter.ProfileDropDownAdepter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayoutDashboard;
    private NavigationView navigationViewDashboad;
    private Toolbar toolbarDashboard;
    Spinner customSpinnerProfile,customSpinnerNotification;
    ArrayList<CustomProfileSpinnerItem> customListprofile;
    ArrayList<NotificationSpinnerItem> customListNotification;
    private int previousposition=-1;
    ProfileDropDownAdepter adapterprofile;
    NotificationDropDownAdepter adepterNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        drawerLayoutDashboard=findViewById(R.id.dashboad_drawerLayout);
        navigationViewDashboad=findViewById(R.id.nav_view);
        toolbarDashboard = (Toolbar) findViewById(R.id.toolbar);
        customSpinnerProfile =findViewById(R.id.spinnerProfile);
        customSpinnerNotification=findViewById(R.id.spinnerNotification);
        setSupportActionBar(toolbarDashboard);

        ActionBarDrawerToggle actionBarDrawerToggleDashboard = new ActionBarDrawerToggle(this,drawerLayoutDashboard,toolbarDashboard,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayoutDashboard.addDrawerListener(actionBarDrawerToggleDashboard);
        actionBarDrawerToggleDashboard.syncState();
        navigationViewDashboad.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer,new DashboadMainFragment())
                    .commit();
            navigationViewDashboad.setCheckedItem(R.id.nav_dashboard);
        }

        customListprofile = getCustomListProfile();
        adapterprofile = new ProfileDropDownAdepter(this, customListprofile);
        if (customSpinnerProfile != null) {
            customSpinnerProfile.setAdapter(adapterprofile);
            customSpinnerProfile.setOnItemSelectedListener(this);
        }

        customListNotification=getCustomListNotification();
        adepterNotification =new NotificationDropDownAdepter(this,customListNotification);
        if(customSpinnerNotification!=null){
            customSpinnerNotification.setAdapter(adepterNotification);

        }
        customSpinnerNotification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position==0){

                }else{
                    NotificationSpinnerItem item = (NotificationSpinnerItem) adapterView.getSelectedItem();
                    Toast.makeText(AdminDashboard.this,item.getSpinnerMessage(),Toast.LENGTH_SHORT).show();
                    customSpinnerNotification.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    /*@SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.xml.adminappbar, menu);
        return true;
    }*/

    private ArrayList<CustomProfileSpinnerItem> getCustomListProfile() {
        customListprofile = new ArrayList<>();

        customListprofile.add(new CustomProfileSpinnerItem("Hello Sarah Smith ", R.drawable.profile));
        customListprofile.add(new CustomProfileSpinnerItem("Profile", R.drawable.monitor));
        customListprofile.add(new CustomProfileSpinnerItem("Activities", R.drawable.shopping_cart));
        customListprofile.add(new CustomProfileSpinnerItem("Settings", R.drawable.pocket));
        customListprofile.add(new CustomProfileSpinnerItem("Logout", R.drawable.monitor));

        return customListprofile;
    }

    private ArrayList<NotificationSpinnerItem> getCustomListNotification() {
        customListNotification = new ArrayList<>();

        customListNotification.add(new NotificationSpinnerItem("Notification","",R.drawable.notification));
        customListNotification.add(new NotificationSpinnerItem("Jhon Deo ","Please Check your mail",R.drawable.user_2));
        customListNotification.add(new NotificationSpinnerItem("Sarah Smith ","Request for leave application",R.drawable.profile));
        customListNotification.add(new NotificationSpinnerItem("Jacob Rayan ","Your Payment invoice is generated",R.drawable.user_4));


        return customListNotification;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


        if(position==0){

        }else{
            CustomProfileSpinnerItem item = (CustomProfileSpinnerItem) adapterView.getSelectedItem();
            Toast.makeText(this, item.getSpinnerItemName(), Toast.LENGTH_SHORT).show();
            customSpinnerProfile.setSelection(0);
        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer,new DashboadMainFragment())
                        .commit();
                break;
            case R.id.nav_sellreport:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new SellReportFragment()).commit();
                break;
        }
        drawerLayoutDashboard.closeDrawer(GravityCompat.START);
        return true;
    }
}