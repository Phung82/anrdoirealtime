package com.sb.splashactivity;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_info extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FirebaseAnalytics mFirebaseAnalytics;
    private EditText txt_FullName;
    private EditText txt_PhoneNum;
    private EditText UserName;
    private EditText txt_JobName;
    private EditText txt_DOB;
    private EditText txt_Sev;
    public FirebaseFirestore db;
    private Button btn_save;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText txt_Birthday;
    public fragment_info() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_info.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_info newInstance(String param1, String param2) {
        fragment_info fragment = new fragment_info();
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
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        txt_FullName = view.findViewById(R.id.txt_FullName);
        txt_PhoneNum = view.findViewById(R.id.txt_PhoneNum);
        txt_JobName = view.findViewById(R.id.txt_JobName);
        txt_DOB = view.findViewById(R.id.txt_DoB);
        txt_Sev = view.findViewById(R.id.txt_Sex);
        btn_save = view.findViewById(R.id.btn_save);
        //FireStore instance
        db = FirebaseFirestore.getInstance();
        //dat ngay
        txt_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chonngay();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input data
                String fullName = txt_FullName.getText().toString().trim();
                String phoneNum = txt_PhoneNum.getText().toString().trim();
                String jobName = txt_JobName.getText().toString().trim();
                String DoB = txt_DOB.getText().toString();
                String sex = txt_Sev.getText().toString().trim();
                //func
                uploadUserData(fullName,phoneNum,jobName,DoB,sex);
            }
        });


        return  view;
    }

    private void Chonngay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog =new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //thoi gian hien tai
                //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YY");
                //txt_dateTime.setText(simpleDateFormat.format(calendar.getTime()));
                //i: nam i1:thang i2: ngay
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YY");
                txt_DOB.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    private void uploadUserData(String fullName, String phoneNum, String jobName, String doB, String sex) {
        UploadUser loadUser = new UploadUser(fullName,phoneNum,jobName,doB,sex);
        DocumentReference washingtonRef = db.collection("USERMAIN").document("USER_4");
        washingtonRef
                .update("USER_4", loadUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"Uploaded!",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error updating document", e);
                        Toast.makeText(getActivity(),"Error updating document: "+ e,Toast.LENGTH_SHORT).show();
                    }
                });
    }


}