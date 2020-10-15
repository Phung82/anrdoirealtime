package com.sb.splashactivity.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sb.splashactivity.LoadCar;
import com.sb.splashactivity.R;

import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public FirebaseFirestore db;
    private EditText txt_cCarName;
    private EditText txt_cBrand;
    private EditText txt_cModel;
    private EditText txt_cVINNum;
    private Button btn_addCar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        txt_cCarName = root.findViewById(R.id.txt_carCarName);
        txt_cBrand = root.findViewById(R.id.txt_carCarBrand);
        txt_cModel= root.findViewById(R.id.txt_carCarModel);
        txt_cVINNum= root.findViewById(R.id.txt_carNINNum);
        btn_addCar= root.findViewById(R.id.btn_addCar);


        db = FirebaseFirestore.getInstance();
        btn_addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input data
                String cCarName = txt_cCarName.getText().toString().trim();
                String cBrand = txt_cBrand.getText().toString().trim();
                String cModel = txt_cModel.getText().toString().trim();
                String cVINNum = txt_cVINNum.getText().toString().trim();

                loadCar(cCarName,cBrand,cModel,cVINNum);
            }
        });

        return root;
    }

    private void loadCar(String cCarName, String cBrand, String cModel, String cVINNum) {
        LoadCar loadCar = new LoadCar(cCarName,cBrand,cModel,cVINNum);
        DocumentReference messageRef = db
                .collection("USERMAIN").document("USER_1");
        Map<String, Object> docData = new HashMap<>();
        docData.put("HONDA", loadCar);

        messageRef.collection("CAR").document("Car_1")
                .set(docData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}