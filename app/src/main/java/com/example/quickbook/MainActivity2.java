package com.example.quickbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String x = bundle.getString("Title");
            textView.setText(x);

            Glide.with(this).load(bundle.getString("Image")).into(imageView);
        }
            FloatingActionButton fab,fab2;
            ValueEventListener eventListener;
            RecyclerView recyclerView;
            List<DataClass2> dataList;
            MyAdapter2 adapter;
            SearchView searchView;

                recyclerView = findViewById(R.id.recyclerView);
                fab = findViewById(R.id.fab);
//
                GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 1);
                recyclerView.setLayoutManager(gridLayoutManager);

                AlertDialog.Builder builder = new AlertDialog.Builder( this);
                builder.setCancelable(false);
                builder.setView(R.layout.progress_layout);
                AlertDialog dialog = builder.create();
                dialog.show();

                dataList = new ArrayList<>();

                adapter = new MyAdapter2( this, dataList);
                recyclerView.setAdapter(adapter);
                Intent intent = getIntent();
                String x = intent.getStringExtra("Title");
                Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference(x);
                dialog.show();
                eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dataList.clear();
                        for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                            DataClass2 dataClass = itemSnapshot.getValue(DataClass2.class);


                            dataList.add(dataClass);
                        }
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        dialog.dismiss();
                    }
                });

//                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                    @Override
//                    public boolean onQueryTextSubmit(String query) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onQueryTextChange(String newText) {
//                        return false;
//                    }
//                });

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), UploadActivity2.class);
                        intent.putExtra("caption",bundle.getString("Title"));
                        startActivity(intent);
                    }
                });

//                fab2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
////                        intent.putExtra("caption",bundle.getString("Title"));
////                        startActivity(intent);
//                    }
//                });

//            }
//            public void searchList(String text){
//                ArrayList<DataClass> searchList = new ArrayList<>();
//                for (DataClass dataClass: dataList){
//                    if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase())){
//                        searchList.add(dataClass);
//                    }
//                }
//                adapter.searchDataList(searchList);
            }

    }
