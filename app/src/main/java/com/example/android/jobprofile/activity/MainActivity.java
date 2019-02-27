package com.example.android.jobprofile.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.jobprofile.R;
import com.example.android.jobprofile.model.Designation;
import com.example.android.jobprofile.model.Employee;
import com.example.android.jobprofile.model.HighestQualification;
import com.example.android.jobprofile.model.Skill;
import com.example.android.jobprofile.rest.ApiClient;
import com.example.android.jobprofile.rest.ApiInterface;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjdiYmZlNTI4YjI5OWVhOGZjNTg4OWM3N2M5NWZjMDliOGVmNmI0NGNiN2QyMTg4NzIxMWIxYjFjMTRjOTdhYjg4NGViNjQ2MTlhZWRjY2RiIn0.eyJhdWQiOiIzIiwianRpIjoiN2JiZmU1MjhiMjk5ZWE4ZmM1ODg5Yzc3Yzk1ZmMwOWI4ZWY2YjQ0Y2I3ZDIxODg3MjExYjFiMWMxNGM5N2FiODg0ZWI2NDYxOWFlZGNjZGIiLCJpYXQiOjE1NTEwMTEwMDcsIm5iZiI6MTU1MTAxMTAwNywiZXhwIjoxNTgyNTQ3MDA3LCJzdWIiOiIxOTMiLCJzY29wZXMiOltdfQ.enC6mmwbQPNgociOwaJbuWeGIL0v5t_54_cFyq-4UF-GkL6qiPHN0iTzwoVn3dT8SYO3zQoHF9ZiDNZt1HsdMowPp0qDS48OYC1yOk4jjMJpm6bG3a14dBT5C_lbutJxR1Hy64KZSM0AZDBQVI6wSBVsUX9PB5NO96bkaOfoojIFln5hJoszTe38ipurz0aFKfV-EN1lRT4WH603q7vAuepPWDy9-XrTEh13MrYBte6ioP0Jfspeoe35Kfv-0S0965tBTCzOzSHjEnPDAP8f3pjgqcrVk_RnEzibcI50A-DiZxjtuRJhzS8_KFfSoBiicbBaW-aTsU0VX2RM2ZLZ4sfgCsW_hm_8DLIfZBDySjS29uwjiRL1Z8gXYgr4fKw9xqGAKvTPcAqeDpDxgyeaS5tJI0R-x_PLl40dIWZPomymtF75QEOv_OwgLLvuFdY7Hu7lfe8q3RKYcr5g4dt4HYlJq1h4F1UyRowAcZXLTk5lCHzv1-KzKbeZ5ZfpkxzyIOQsP5xVDZDo0RMQ2aC7Jt000k8jkpH5DtkAR40G_mh7ffcNgpIQAFYIxXC7dAn6FVPiIv1o-xweRQa1P1j9AffpEABo6eYrrGFIihok5qOkLm4dSwUAnxB-Kfa0doz-7JJmzDTRH6zCsyvYl2oysHuXTesn1rieFFDThDErq3Q";

    TextView tvName, tvDesignation, tvLocation, tvQualificationValue, tvWorkExperienceValue, tvExpectedCtcValue;

    TextView tvCurrentlyWorkingValue, tvDesignationValue, tvTabFieldValue;

    ImageView ivProfilePic, ivMapIcon;

    Button btnConfirm, btnEditProfile;

    Employee employee = new Employee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking Views with their Id's
        findViewsById();

        //ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //Call<Employee> call = apiService.getEmployeeDetails(74, authorization);

        //Call<Employee> call = apiService.getEmployeeDetails(74);

        //Api Call using Retrofit
        Call<Employee> call = ApiClient
                .getInstance()
                .getApi()
                .getEmployeeDetails(74);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                employee = response.body();

                Log.d(TAG, "employee designation: "+ employee.getDesignation());

                Log.d(TAG, "onResponse: " + response.toString());


                /*String empName = response.body().getName();
                Log.d(TAG, " Employee Name: " + empName);
                List<Skill> skills = response.body().getSkills();
                Log.d(TAG, "Skills are: " + skills);

                employee.setName(response.body().getName());
                employee.setDesignation(response.body().getDesignation());
                employee.setImageUrl(response.body().getImageUrl());
                employee.setExpectedCtc(response.body().getExpectedCtc());
                employee.setExperience(response.body().getExperience());
                employee.setLocation(response.body().getLocation());
                employee.setHighestQualification(response.body().getHighestQualification());
                employee.setExperience(response.body().getExperience());
                employee.setExpectedCtc(response.body().getExpectedCtc());
                employee.setSkills(response.body().getSkills());
                employee.setWorkFunctions(response.body().getWorkFunctions());
                employee.setIndustries(response.body().getIndustries());
                employee.setLatitude(response.body().getLatitude());
                employee.setLongitude(response.body().getLongitude());

                Log.d(TAG, "Employee Name: " + employee.getName());*/

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });

        //SetValues is a method to set values on a different Views.
        setValues();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    public void setValues() {

        //Hardcoded values
        employee.setName("Nitin Kumar");

        Designation designation = new Designation();
        designation.setName("Fresher");
        employee.setDesignation(designation);

        employee.setImageUrl("https://storage.googleapis.com/icif-cs/hunt/user/image20190116063258.jpeg");
        employee.setLocation("Malad West, Mumbai");

        HighestQualification highestQualification = new HighestQualification();
        highestQualification.setName("B.E. / B.Tech");
        employee.setHighestQualification(highestQualification);

        employee.setExperience("3 Years");
        employee.setExpectedCtc("7 - 12 LPA");

        Skill skill = new Skill();
        List<Skill> skills = new ArrayList<>();
        skill.setName("Android SDK");
        skills.add(skill);

        employee.setSkills(skills);



        //Setting hardcoded values to Views

        tvName.setText(employee.getName());
        tvDesignation.setText(employee.getDesignation().getName());
        tvLocation.setText(employee.getLocation());
        tvQualificationValue.setText(employee.getHighestQualification().getName());
        tvWorkExperienceValue.setText(employee.getExperience());
        tvExpectedCtcValue.setText(employee.getExpectedCtc());
        tvDesignationValue.setText(employee.getDesignation().getName());
        tvCurrentlyWorkingValue.setText("BinaryVeda Solutions Pvt. Ltd.");

        //Use of Glide to fetch an image from a URL and set it into specific placeholder
        Glide.with(this)
                .load(employee.getImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfilePic);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You confirmed it.", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Want to edit the profile?", Toast.LENGTH_SHORT).show();
            }
        });

        ivMapIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 19.182755, 72.84016);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }

    public void findViewsById() {
        tvName = findViewById(R.id.tvName);
        tvDesignation = findViewById(R.id.tvDesignation);
        tvLocation = findViewById(R.id.tvLocation);
        tvQualificationValue = findViewById(R.id.tvQualificationValue);
        tvWorkExperienceValue = findViewById(R.id.tvWorkExperienceValue);
        tvExpectedCtcValue = findViewById(R.id.tvExpectedCtcValue);
        tvCurrentlyWorkingValue = findViewById(R.id.tvCurrentlyWorkingValue);
        tvTabFieldValue = findViewById(R.id.tvTabFieldValue);
        tvDesignationValue = findViewById(R.id.tvDesignationValue);
        ivProfilePic = findViewById(R.id.ivProfilePic);
        ivMapIcon = findViewById(R.id.ivMapIcon);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnEditProfile = findViewById(R.id.btnEditProfile);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_maps:
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 19.182755, 72.84016);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                this.startActivity(intent);
                return true;

            case R.id.action_chat:
                Toast.makeText(this, "You clicked on the chat icon", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

}
