package com.example.android.jobprofile.activity;

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

import com.example.android.jobprofile.R;
import com.example.android.jobprofile.model.Employee;
import com.example.android.jobprofile.model.Skill;
import com.example.android.jobprofile.rest.ApiClient;
import com.example.android.jobprofile.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjdiYmZlNTI4YjI5OWVhOGZjNTg4OWM3N2M5NWZjMDliOGVmNmI0NGNiN2QyMTg4NzIxMWIxYjFjMTRjOTdhYjg4NGViNjQ2MTlhZWRjY2RiIn0.eyJhdWQiOiIzIiwianRpIjoiN2JiZmU1MjhiMjk5ZWE4ZmM1ODg5Yzc3Yzk1ZmMwOWI4ZWY2YjQ0Y2I3ZDIxODg3MjExYjFiMWMxNGM5N2FiODg0ZWI2NDYxOWFlZGNjZGIiLCJpYXQiOjE1NTEwMTEwMDcsIm5iZiI6MTU1MTAxMTAwNywiZXhwIjoxNTgyNTQ3MDA3LCJzdWIiOiIxOTMiLCJzY29wZXMiOltdfQ.enC6mmwbQPNgociOwaJbuWeGIL0v5t_54_cFyq-4UF-GkL6qiPHN0iTzwoVn3dT8SYO3zQoHF9ZiDNZt1HsdMowPp0qDS48OYC1yOk4jjMJpm6bG3a14dBT5C_lbutJxR1Hy64KZSM0AZDBQVI6wSBVsUX9PB5NO96bkaOfoojIFln5hJoszTe38ipurz0aFKfV-EN1lRT4WH603q7vAuepPWDy9-XrTEh13MrYBte6ioP0Jfspeoe35Kfv-0S0965tBTCzOzSHjEnPDAP8f3pjgqcrVk_RnEzibcI50A-DiZxjtuRJhzS8_KFfSoBiicbBaW-aTsU0VX2RM2ZLZ4sfgCsW_hm_8DLIfZBDySjS29uwjiRL1Z8gXYgr4fKw9xqGAKvTPcAqeDpDxgyeaS5tJI0R-x_PLl40dIWZPomymtF75QEOv_OwgLLvuFdY7Hu7lfe8q3RKYcr5g4dt4HYlJq1h4F1UyRowAcZXLTk5lCHzv1-KzKbeZ5ZfpkxzyIOQsP5xVDZDo0RMQ2aC7Jt000k8jkpH5DtkAR40G_mh7ffcNgpIQAFYIxXC7dAn6FVPiIv1o-xweRQa1P1j9AffpEABo6eYrrGFIihok5qOkLm4dSwUAnxB-Kfa0doz-7JJmzDTRH6zCsyvYl2oysHuXTesn1rieFFDThDErq3Q";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //Call<Employee> call = apiService.getEmployeeDetails(74, authorization);

        Call<Employee> call = apiService.getEmployeeDetails(74);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                String empName = response.body().getName();
                Log.d(TAG, " Employee Name: " + empName);
                List<Skill> skills = response.body().getSkills();
                Log.d(TAG, "Skills are: " + skills);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_maps) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
