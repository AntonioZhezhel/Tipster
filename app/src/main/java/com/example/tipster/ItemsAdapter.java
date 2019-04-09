package com.example.tipster;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends ListAdapter<Item,ItemsAdapter.ViewHolder> {

    private List<Item> mItems=new ArrayList<>();

    public static final DiffUtil.ItemCallback<Item>DIFF_CALLBACK=
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame( Item oldItem, Item newItem) {
                    return oldItem.getId()==newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Item oldItem, Item newItem) {
                    return(oldItem.getName()==newItem.getName()&&oldItem.getCost()==newItem.getCost());
                }
            };

    public ItemsAdapter(){super(DIFF_CALLBACK);}
    public void addMorreItems(List<Item> newItems){
        int insertionPosition=mItems.size();
        mItems.addAll(newItems);
        notifyItemRangeInserted(insertionPosition,newItems.size());
        submitList(mItems);

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View itemView=inflater.inflate(R.layout.custom_edittext_layout,parent,false) ;
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;

    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,int position){
        viewHolder.editTextName.setText(mItems.get(position).getName());
        viewHolder.editTextCost3.setText(mItems.get(position).getCost());

        Item item=getItem(position);
        EditText editText=viewHolder.editTextName;
        editText.setText(item.getName());
        EditText editText1=viewHolder.editTextCost3;
        editText1.setText(item.getCost());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public EditText editTextName;
        public EditText editTextCost3;
        public TextView txtTipAmount3;
        public Button buttonDelete;
        public  Button buttonOK;
        public ViewHolder(View itemView){
            super(itemView);
            editTextCost3=(EditText)itemView.findViewById(R.id.ediCost3);
            editTextName=(EditText)itemView.findViewById(R.id.editTextEnterYourName);
            txtTipAmount3=(TextView)itemView.findViewById(R.id.txtTipAmount3);
            buttonDelete=(Button)itemView.findViewById(R.id.buttonDelete);
            buttonOK=(Button)itemView.findViewById(R.id.buttonOk);

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    try {
                        mItems.remove(position);
                        notifyItemRemoved(position);
                    }catch (ArrayIndexOutOfBoundsException e){e.printStackTrace();}
                }
            });
            editTextName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mItems.get(getAdapterPosition()).setmName(editTextName.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            editTextCost3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mItems.get(getAdapterPosition()).setmCost(editTextCost3.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }
}
