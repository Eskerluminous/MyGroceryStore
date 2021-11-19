package com.example.mygrocerystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mygrocerystore.R;
import com.example.mygrocerystore.adapter.NavCategoryAdapter;
import com.example.mygrocerystore.adapter.NavCategoryDetailedAdapter;
import com.example.mygrocerystore.models.HomeCategory;
import com.example.mygrocerystore.models.NavCategoryDetailedModel;
import com.example.mygrocerystore.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List< NavCategoryDetailedModel > list;
    NavCategoryDetailedAdapter adapter;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);
        db=FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");

        recyclerView=findViewById(R.id.nav_cat_det_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        adapter=new NavCategoryDetailedAdapter(this,list);
        recyclerView.setAdapter(adapter);


        if (type != null && type.equalsIgnoreCase("drink")) {

            db.collection("NavCategoryDetailed").whereEqualTo("type", "drink").get().addOnCompleteListener(new OnCompleteListener< QuerySnapshot >() {
                @Override
                public void onComplete(@NonNull @NotNull Task< QuerySnapshot > task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                       NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(viewAllModel);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("breakfast")) {

            db.collection("NavCategoryDetailed").whereEqualTo("type", "breakfast").get().addOnCompleteListener(new OnCompleteListener< QuerySnapshot >() {
                @Override
                public void onComplete(@NonNull @NotNull Task< QuerySnapshot > task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(viewAllModel);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }

    }
}