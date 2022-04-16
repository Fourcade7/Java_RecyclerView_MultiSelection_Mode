package com.fourcade7.java_recycler_multiselection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context context;
    ArrayList<User> userArrayList;
    ArrayList<User> clearuserArrayList=new ArrayList<>();
    int a=0;
    MainActivity mainActivity;

    public UserAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
        mainActivity=(MainActivity) context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (userArrayList.get(position).isSelect()){
            holder.imageView2.setVisibility(View.VISIBLE);
        }else {
            holder.imageView2.setVisibility(View.INVISIBLE);
        }


        holder.textView.setText(userArrayList.get(position).getName());
        holder.imageView1.setImageResource(userArrayList.get(position).getImage());

        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                userArrayList.get(position).setSelect(!userArrayList.get(position).isSelect());
                mainActivity.relativeLayout.setVisibility(View.VISIBLE);
                if (userArrayList.get(position).isSelect()){
                    holder.imageView2.setVisibility(View.VISIBLE);
                    a++;
                    mainActivity.textView.setText(a+" item selected");
                    mainActivity.textView2.setText(a+"");
                    clearuserArrayList.add(userArrayList.get(position));
                }else {
                    holder.imageView2.setVisibility(View.INVISIBLE);
                    a--;
                    clearuserArrayList.remove(userArrayList.get(position));
                    mainActivity.textView.setText(a+" item selected");
                    mainActivity.textView2.setText(a+"");
                    if (a==0){
                        mainActivity.relativeLayout.setVisibility(View.INVISIBLE);
                        mainActivity.textView.setText("Multi Selection");
                    }
                }
                return true;
            }
        });

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a>0){
                    userArrayList.get(position).setSelect(!userArrayList.get(position).isSelect());
                    if (userArrayList.get(position).isSelect()){
                        holder.imageView2.setVisibility(View.VISIBLE);
                        a++;
                        clearuserArrayList.add(userArrayList.get(position));
                        mainActivity.textView.setText(a+" item selected");
                        mainActivity.textView2.setText(a+"");
                    }else {
                        holder.imageView2.setVisibility(View.INVISIBLE);
                        a--;
                        clearuserArrayList.remove(userArrayList.get(position));
                        mainActivity.textView.setText(a+" item selected");
                        mainActivity.textView2.setText(a+"");
                        if (a==0){
                            mainActivity.relativeLayout.setVisibility(View.INVISIBLE);
                            mainActivity.textView.setText("Multi Selection");
                        }
                    }
                }else {
                    mainActivity.relativeLayout.setVisibility(View.INVISIBLE);
                    mainActivity.textView.setText("Multi Selection");
                }
            }
        });

        mainActivity.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete");
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       userArrayList.removeAll(clearuserArrayList);
                       clearuserArrayList.clear();
                       notifyDataSetChanged();
                       mainActivity.relativeLayout.setVisibility(View.INVISIBLE);
                       mainActivity.textView.setText("Multi Selection");
                       a=0;
                   }
               });
               builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });

               builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });
               builder.create();
               builder.show();


            }
        });



    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }


    public  void filterlist(ArrayList<User> filterlist){
        userArrayList=filterlist;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1,imageView2;
        TextView textView;
        RelativeLayout relativeLayout;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textview1);
            imageView1=itemView.findViewById(R.id.imageview1);
            imageView2=itemView.findViewById(R.id.imageview2);
            relativeLayout=itemView.findViewById(R.id.relativelay1);
        }
    }
}
