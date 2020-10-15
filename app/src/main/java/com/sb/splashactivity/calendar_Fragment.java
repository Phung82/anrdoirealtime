package com.sb.splashactivity;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link calendar_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calendar_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText txt_dateTime;
    public calendar_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment calendar_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static calendar_Fragment newInstance(String param1, String param2) {
        calendar_Fragment fragment = new calendar_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        txt_dateTime = view.findViewById(R.id.txt_bookdateTime);
        txt_dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chonngay();
            }
        });
        return view;
    }

    private void Chonngay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog =new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //thoi gian hien tai
                //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YY");
                //txt_dateTime.setText(simpleDateFormat.format(calendar.getTime()));
                //i: nam i1:thang i2: ngay
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YY");
                txt_dateTime.setText(simpleDateFormat.format(calendar.getTime()));


            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}