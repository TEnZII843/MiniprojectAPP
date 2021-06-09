package com.example.sqlitecrud;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {
    public String id;
    Context context;
    dbmanager db;
    ArrayList<model> dataholder;

    public myadapter(Context context, ArrayList<model> dataholder) {
        this.context = context;
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, final int position) {

        /*Log.d("yes",dataholder.get(position).getTaskname());*/
        holder.taskname.setText(dataholder.get(position).getTaskname());
        holder.timeRequired.setText(dataholder.get(position).getTimeRequired());
        holder.venue.setText(dataholder.get(position).getVenue());
        holder.date.setText(dataholder.get(position).getDate());
        holder.time.setText(dataholder.get(position).getTime());
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = dataholder.get(position).getId();
                db = new dbmanager(context);
                int deletedRows = db.deleteData(id);
                Log.d("yes",String.valueOf(deletedRows));
                if (deletedRows != 0) {
                    Toast.makeText(context, "entry deleted", Toast.LENGTH_SHORT).show();
                    Log.d("yes","if");
                } else{
                    Toast.makeText(context, "entry not deleted", Toast.LENGTH_SHORT).show();
                    Log.d("yes","else");
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView taskname, timeRequired, venue, date, time;
        ImageView Delete,update;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            Delete = itemView.findViewById(R.id.Delete);
            taskname = (TextView) itemView.findViewById(R.id.TaskName);
            timeRequired = (TextView) itemView.findViewById(R.id.TimeRequired);
            venue = (TextView) itemView.findViewById(R.id.Venue);
            date = (TextView) itemView.findViewById(R.id.Date);
            time = (TextView) itemView.findViewById(R.id.Time);
        }
    }

}
