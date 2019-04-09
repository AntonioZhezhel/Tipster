package com.example.tipster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.beans.IndexedPropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        setupViews();
}

void setupViews(){
    RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rvItems);
    adapter=new ItemsAdapter();
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    adapter.addMorreItems(Item.createItemsList(2));

    Button addMoreButton=(Button)findViewById(R.id.buttonOk);
    addMoreButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            adapter.addMorreItems(Item.createItemsList(2));
        }
    });


}
}