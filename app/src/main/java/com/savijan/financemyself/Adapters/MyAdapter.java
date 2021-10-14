package com.savijan.financemyself.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.savijan.financemyself.Database.DatabaseManager;
import com.savijan.financemyself.Database.UserItemDB;
import com.savijan.financemyself.ListActivity;
import com.savijan.financemyself.R;

import org.androidannotations.annotations.ViewById;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {

    private List<UserItemDB> userItemDBList;
    private Context mContext;

    public MyAdapter(List<UserItemDB> userItemDBList, Context context) {
        this.userItemDBList = userItemDBList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_row, parent, false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final UserItemDB user = userItemDBList.get(position);

        holder.name.setText(user.getName());
        holder.age.setText(user.getAge());
        holder.index.setText(user.getIndex());

        holder.editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mContext instanceof ListActivity){
                    ((ListActivity)mContext).presentAlert(user.getName(), user.getAge(), user.getIndex());
                    notifyDataSetChanged();
                }
            }
        });

        holder.deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentAlertDelete(holder.getAbsoluteAdapterPosition(), user.getIndex());
            }
        });

    }

    public void updateUserList(List<UserItemDB> newList){
        userItemDBList.clear();
        userItemDBList.addAll(newList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userItemDBList.size();
    }

    private void presentAlertDelete(int absoluteAdapterPosition, String index) {

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("Delete User")
                .setMessage("Are you sure to delete this user?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseManager.getInstance(mContext).deleteUser(index);
                        deleteUser
                    }
                })

    }

    public class viewHolder extends RecyclerView.ViewHolder{

        public TextView name, age, index;
        public LinearLayout editLayout, deleteLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
            index = (TextView) itemView.findViewById(R.id.index);
            editLayout = (LinearLayout) itemView.findViewById(R.id.edit_layout);
            deleteLayout = (LinearLayout) itemView.findViewById(R.id.delete_layout);
        }
    }



}
