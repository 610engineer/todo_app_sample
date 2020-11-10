package com.jour1.todo_app_sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    ArrayList myDataset;
    Context context;

    //constructor
    public MyAdapter(Context context, ArrayList mDataSet){
        this.context = context;
        this.myDataset = mDataSet;
    }

    @Override
    //inflate xml file and set object to ViewHolder instance
    public MyViewHolder onCreateViewHolder(ViewGroup parent , int ViewTypre){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_text_view, parent,false);
        return new MyViewHolder(view);
    }

    //provide a reference to the views for each data item
    public class MyViewHolder extends RecyclerView.ViewHolder{
        //string for each data items
        TextView todo_txt;
        public MyViewHolder(@NonNull View v){
            super(v);
            todo_txt = v.findViewById(R.id.textView);
        }
    }


    ///get one data and set onCreateViewHolder
    @Override
    public void onBindViewHolder(MyViewHolder holder , int position){
        holder.todo_txt.setText(String.valueOf(myDataset.get(position)));
    }

    //return the size of dataset
    @Override
    public int getItemCount(){
        return myDataset.size();
    }
}
