package com.example.tipster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.beans.IndexedPropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private List<View> allEds;
    private int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button addButton=(Button) findViewById(R.id.buttonOk);

        allEds=new ArrayList<View>();
final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linear);



    addButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)

        {
        counter++;
        final View view =getLayoutInflater().inflate(R.layout.custom_edittext_layout,null);
            TextView textView=(TextView) view.findViewById(R.id.txtTipAmount3);
        EditText text=(EditText) view.findViewById(R.id.editTextEnterYourName);
        text.setText("Text Enter Your Name"+counter);
        text.setTextSize(16);
            EditText text1=(EditText) view.findViewById(R.id.ediCost3);
            text1.setText("Cost");
            text1.setTextSize(16);
            allEds.add(view);
        linearLayout.addView(view);

            Button deleteField =(Button) findViewById(R.id.button2);
            deleteField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        ((LinearLayout)view.getParent()).removeView(view);
                        allEds.remove(view);
                    }catch (IndexOutOfBoundsException ex){
                        ex.printStackTrace();
                    }
                }
            });

    }

});



}
}