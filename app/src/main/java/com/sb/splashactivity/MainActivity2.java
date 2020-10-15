package com.sb.splashactivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.app.AlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sb.splashactivity.ui.home.HomeFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.database.DatabaseReference;
import java.util.HashMap;
import java.util.Map;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class MainActivity2 extends AppCompatActivity{
    private DatabaseReference mDatabase;
    private AppBarConfiguration mAppBarConfiguration;
    private FrameLayout frameLayout;
    Button btnAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //code them
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //khoa lai de chinh ben content_main.xml
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_info, R.id.nav_car,R.id.nav_calendar, R.id.nav_sign_out)
                .setDrawerLayout(drawer)
                .build();
        //facebook


        //khoa lai vi khoa fragment_home mac dinh
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //navigationView.setNavigationItemSelectedListener((OnNavigationItemSelectedListener) this);
        //navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        //navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = findViewById(R.id.main_frameLayout);

    }

    private void displayAlertDialog() {
        FirebaseDatabase mFirebaseInstance = getInstance();
        mDatabase = mFirebaseInstance.getReference("USERMAIN/USER_1");
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.activity_odometer, null);
        final EditText etdatetime = (EditText) alertLayout.findViewById(R.id.et_datetime);
        final EditText etodometer = (EditText) alertLayout.findViewById(R.id.et_odometer);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("UpDate Odometer");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                String user = etdatetime.getText().toString();
                String pass = etodometer.getText().toString();
                upLoadOdom(user,pass);

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private void upLoadOdom(final String user, final String pass) {
        final LoadOdom loadOdom = new LoadOdom(user,pass);
        //mFirebaseDatabase.child("USER_1/CAR:Car_1").push().setValue(loadCar);
        //mData.child("USERMAIN/USER_1/CAR/").push().setValue(loadCar);
        mDatabase.child("USERMAIN/USER_1/DOM").push().setValue(loadOdom);
        // Read from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Toast.makeText(getBaseContext(), "Date: " + user + " Odometer: " + pass, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.odometer_icon:
                displayAlertDialog();
                return true;
            case R.id.history_icon:
                Toast.makeText(MainActivity2.this, "Item History clicked!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Notifi_icon:
                Toast.makeText(MainActivity2.this, "Item Notifi clicked!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search_icon:
                Toast.makeText(MainActivity2.this, "Item Setting clicked!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}