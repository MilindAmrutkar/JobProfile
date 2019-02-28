package com.example.android.jobprofile.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.jobprofile.R;
import com.example.android.jobprofile.model.Designation;
import com.example.android.jobprofile.model.Employee;
import com.example.android.jobprofile.model.HighestQualification;
import com.example.android.jobprofile.model.Industry;
import com.example.android.jobprofile.model.Skill;
import com.example.android.jobprofile.model.WorkFunction;
import com.example.android.jobprofile.rest.ApiClient;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView tvName, tvDesignation, tvLocation, tvQualificationValue, tvWorkExperienceValue, tvExpectedCtcValue;

    TextView tvCurrentlyWorkingValue, tvDesignationValue, tvTabFieldValue;

    ImageView ivProfilePic, ivMapIcon;

    Button btnConfirm, btnEditProfile;

    final Employee employee = new Employee();

    TabLayout tabLayout;

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

                /*employee = response.body();

                Log.d(TAG, "onResponse: " + response.toString());*/

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
        skill = new Skill();
        skill.setName("Android Studio");
        skills.add(skill);
        skill = new Skill();
        skill.setName("SDLC");
        skills.add(skill);

        employee.setSkills(skills);

        WorkFunction workFunction = new WorkFunction();
        List<WorkFunction> workFunctions = new ArrayList<>();
        workFunction.setName("Android Development");
        workFunctions.add(workFunction);
        employee.setWorkFunctions(workFunctions);

        Industry industry = new Industry();
        List<Industry> industries = new ArrayList<>();
        industry.setName("Information Technology");
        industries.add(industry);
        employee.setIndustries(industries);

        employee.setLongitude(72.84016);
        employee.setLatitude(19.182755);

        //Setting hardcoded values to Views

        tvName.setText(employee.getName());
        tvDesignation.setText(employee.getDesignation().getName());
        tvLocation.setText(employee.getLocation());
        tvQualificationValue.setText(employee.getHighestQualification().getName());
        tvWorkExperienceValue.setText(employee.getExperience());
        tvExpectedCtcValue.setText(employee.getExpectedCtc());
        tvDesignationValue.setText(employee.getDesignation().getName());
        tvCurrentlyWorkingValue.setText("BinaryVeda Solutions Pvt. Ltd.");
        //Setting default tab value for employee skill set
        String empSkill = "";
        for (Skill eSkill : employee.getSkills()) {
            empSkill += eSkill.getName() + "  ";
        }
        tvTabFieldValue.setText(empSkill);

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

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();

                switch (tabPosition) {
                    case 0:
                        String skillSet = "";
                        for (Skill skill2 : employee.getSkills()) {
                            skillSet += skill2.getName() + "  ";
                        }
                        tvTabFieldValue.setText(skillSet);
                        break;

                    case 1:
                        String workFunc = "";
                        for (WorkFunction workFunction2 : employee.getWorkFunctions()) {
                            workFunc += workFunction2.getName() + " ";
                        }
                        tvTabFieldValue.setText(workFunc);
                        break;

                    case 2:
                        String industryValue = "";
                        for (Industry industry2 : employee.getIndustries()) {
                            industryValue += industry2.getName() + " ";
                        }
                        tvTabFieldValue.setText(industryValue);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
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
        tabLayout = findViewById(R.id.tabLayout);
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
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", employee.getLatitude(), employee.getLongitude());
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
