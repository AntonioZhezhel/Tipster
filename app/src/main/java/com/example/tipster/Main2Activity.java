package com.example.tipster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements OnCostChangeListener {

    ItemsAdapter adapter;
    final static NumberFormat formatter = NumberFormat.getCurrencyInstance();
    //private OnCostChangeListener mListener;


    private EditText ediCost3;
    private TextView txtTotalPrice;
    private Button calculateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        // ediCost3=(EditText) findViewById(R.id.ediCost3);
        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);
        calculateBtn = (Button) findViewById(R.id.calculate2);
        //calculateBtn.setOnClickListener( mClickListener);

        setupViews();


    }

    void setupViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvItems);
        adapter = new ItemsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.addMorreItems(Item.createItemsList(2));

        Button addMoreButton = (Button) findViewById(R.id.buttonOk);
        addMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addMorreItems(Item.createItemsList(2));
            }
        });


    }
   /* OnCostChangeListener mClickListener=new OnCostChangeListener() {

        @Override
        public void onChange(List<Item> list) {

            String sum= String.valueOf(0);
            for (Item it : list){
                sum += (it.getCost());
            }
            final String finalSum = sum;

            txtTotalPrice.setText(formatter.format(finalSum));
        }
    };*/


    @Override
    public void onChange(List<Item> list) {
        int sum = 0;
        for (Item it : list) {
            sum += (it.getCost());
        }
        txtTotalPrice.setText(formatter.format(sum));
    }
/*
View.OnClickListener mClickListener=new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.calculateBtn) {
            calculate()
        }
    }
};

    private void calculate(){
    Double Cost3=Double.interface (
            ediCost3.getText().toString()
    );
    boolean isError = false;

        if (Cost3 < 1.0) {
            showErrorAlert("Enter a valid Total Amount.",
                    txtTotalPrice.getId());
            isError = true;}

        if(!isError){
            double tipCost3=(Cost3+Cost3);

        txtTotalPrice.setText(formatter.format(tipCost3));

    }

    }
    private void showErrorAlert(String errorMessage,final int fieldId){
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        findViewById(fieldId).requestFocus();
                    }
                }).show();
    }*/
}