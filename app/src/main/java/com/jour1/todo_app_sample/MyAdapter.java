package com.jour1.todo_app_sample;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList myDataset;
    Context context;
    DataBaseHelper helper;
    SQLiteDatabase db ;

    //constructor
    public MyAdapter(Context context, ArrayList mDataSet){
        this.context = context;
        this.myDataset = mDataSet;
    }

    @Override
    //inflate xml file and set object to ViewHolder instance
    public MyViewHolder onCreateViewHolder(ViewGroup parent , int ViewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_text_view, parent,false);
        final MyViewHolder holder = new MyViewHolder(view);
        final String[] array = (String[]) myDataset.toArray(new String[0]);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                final int position = holder.getAdapterPosition();
                final String[] items = {"Edit","Delete"};
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Select")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch(i){
                                    case 0 :
                                        break;

                                    case 1 :
                                         // helper.rowDelete(position);
                                          break;
                                }
                            }
                        })
                        .show();


                return true;
            }
        });
        return holder;
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

    /*void dbDelete(){
        db.execSQL("Delete FROM todosample");
        notifyDataSetChanged();
    }*/
}
