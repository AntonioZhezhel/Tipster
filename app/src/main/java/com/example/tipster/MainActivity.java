package com.example.tipster;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.chip.ChipGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    final static int DEFAULT_NUM_PEOPLE=3;

    final static NumberFormat formatter=NumberFormat.getCurrencyInstance();

private EditText txtAmount;
private EditText txtPeople;
private EditText txtTipeOther;
private RadioGroup radioGroupTips;
private Button Calculate;
private Button Reset;

private TextView txtTipAmount;
private TextView txtTotalToPay;
private TextView txtTipPerPerson;
private int radioCheckedId=-1;
//private NumberPicker mLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.content_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtAmount.requestFocus();

        txtPeople = (EditText) findViewById(R.id.txtPeopel);
        txtPeople.setText(Integer.toString(DEFAULT_NUM_PEOPLE));

        txtTipeOther = (EditText) findViewById(R.id.txtTipeOther);


        radioGroupTips = (RadioGroup) findViewById(R.id.radioGroupTips);

        Calculate = (Button) findViewById(R.id.Calculate);
        Calculate.setEnabled(false);

        Reset = (Button) findViewById(R.id.Reset);

        txtTipAmount = (TextView) findViewById(R.id.txtTipAmount);
        txtTotalToPay = (TextView) findViewById(R.id.txtTotalToPay);
        txtTipPerPerson = (TextView) findViewById(R.id.txtTipPerPerson);
        txtTipeOther.setEnabled(false);




        radioGroupTips.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioTen || checkedId == R.id.radioFifteen || checkedId == R.id.radioTwenty) {
                    txtTipeOther.setEnabled(false);

                    Calculate.setEnabled(txtAmount.getText().length() > 0 && txtPeople.getText().length() > 0);
                }
                if (checkedId == R.id.radioOther) {
                    txtTipeOther.setEnabled(true);
                    txtTipeOther.requestFocus();

                    Calculate.setEnabled(txtAmount.getText().length() > 0
                            && txtPeople.getText().length() > 0
                            && txtTipeOther.getText().length() > 0);

                }
                radioCheckedId = checkedId;
            }


        });

        txtAmount.setOnKeyListener(mKeyListener);
        txtPeople.setOnKeyListener(mKeyListener);
        txtTipeOther.setOnKeyListener(mKeyListener);

        Calculate.setOnClickListener(mClickListener);
        Reset.setOnClickListener(mClickListener);

      //  mLogic = new NumberPicker(txtPeople, 1, Integer.MAX_VALUE);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



        View.OnKeyListener mKeyListener=new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (v.getId()) {
                    case R.id.txtAmount:
                    case R.id.txtPeopel:
                        Calculate.setEnabled(txtAmount.getText().length() > 0 && txtPeople.getText().length() > 0);
                        break;
                    case R.id.txtTipeOther:
                        Calculate.setEnabled(txtAmount.getText().length() > 0
                                && txtPeople.getText().length() > 0
                                && txtTipeOther.getText().length() > 0);
                        break;
                }
                return false;
            }
            };

        View.OnClickListener mClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.Calculate) {
                    calculate();

                    }else{
                        reset();
                    }
                }
            };


    private void reset(){
        txtTipAmount.setText("");
        txtTotalToPay.setText("");
        txtTipPerPerson.setText("");
        txtAmount.setText("");
        txtPeople.setText(Integer.toString(DEFAULT_NUM_PEOPLE ));
        txtTipeOther.setText("");
        radioGroupTips.clearCheck();
        radioGroupTips.check(R.id.radioTen);
        txtAmount.requestFocus();

    }

   /* public void decrement(View v){
        mLogic.decrement();
    }
    public void increment(View v) {
        mLogic.increment();
    }*/

    private void calculate() {
        Double billAmount = Double.parseDouble(
                txtAmount.getText().toString());
        Double totalPeople = Double.parseDouble(
                txtPeople.getText().toString());
        Double percentage = null;
        boolean isError = false;
        if (billAmount < 1.0) {
            showErrorAlert("Enter a valid Total Amount.",
                    txtAmount.getId());
            isError = true;
        }
        if(totalPeople < 1.0){
            showErrorAlert("Enter a valid value for No.of People.",
                    txtPeople.getId());
            isError=true;
        }
        if (radioCheckedId== -1){
            radioCheckedId = radioGroupTips.getCheckedRadioButtonId();
        }
        if(radioCheckedId==R.id.radioTen){
            percentage=10.00;
        }else if (radioCheckedId==R.id.radioFifteen){
            percentage=15.00;
        }else if (radioCheckedId==R.id.radioTwenty){
            percentage=20.00;
        }else if (radioCheckedId==R.id.radioOther){
            percentage=Double.parseDouble(
                    txtTipeOther.getText().toString());
            if(percentage <1.0){
                showErrorAlert("Enter a valid Tip percentage",
                        txtTipeOther.getId());
                isError=true;
            }
        }
        if (!isError){
            double tipAmount = ((billAmount * percentage) / 100);
            double totalToPay = billAmount + tipAmount;
            double perPersonPays = totalToPay / totalPeople;

            txtTipAmount.setText(formatter.format(tipAmount));
            txtTotalToPay.setText(formatter.format(totalToPay));
            txtTipPerPerson.setText(formatter.format(perPersonPays));
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
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}