package com.example.hw8_tasklist;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyTaskAdapter extends RecyclerView.Adapter {

    public interface myTaskAdapterListener {
        public void go(int position);
    }
    myTaskAdapterListener myColorAdapterListener;

    public MyTaskAdapter(myTaskAdapterListener myColorAdapterListener) {
        this.myColorAdapterListener = myColorAdapterListener;
    }
    public class MyColorViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewDesc;
        TextView textViewPriority;
        public MyColorViewHolder(@NonNull View itemView){
            super(itemView);
            textViewPriority = itemView.findViewById(R.id.textViewBlue);
            textViewDesc = itemView.findViewById(R.id.textViewGreen);
            textViewName = itemView.findViewById(R.id.textViewRed);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    myColorAdapterListener.go(position);
                }
            });
        }
        public void updateTextViews(myTask myTask){
            textViewName.setText(String.valueOf(myTask.name));
            textViewDesc.setText(String.valueOf(myTask.desc));
            textViewPriority.setText(String.valueOf(myTask.priorty));

            if (String.valueOf(textViewPriority.getText()).equals("1")){
                Log.v("priority: ", String.valueOf(textViewPriority.getText()));
                textViewName.setBackgroundColor(RED);
                textViewDesc.setBackgroundColor(RED);
                textViewPriority.setBackgroundColor(RED);
            }else if(String.valueOf(textViewPriority.getText()).equals("2")){
                textViewName.setBackgroundColor(Color.rgb(255, 165, 0));
                textViewDesc.setBackgroundColor(Color.rgb(255, 165, 0));
                textViewPriority.setBackgroundColor(Color.rgb(255, 165, 0));
            }else if(String.valueOf(textViewPriority.getText()).equals("3")){
                textViewName.setBackgroundColor(YELLOW);
                textViewDesc.setBackgroundColor(YELLOW);
                textViewPriority.setBackgroundColor(YELLOW);
            }else if(String.valueOf(textViewPriority.getText()).equals("4")){
                textViewName.setBackgroundColor(GREEN);
                textViewDesc.setBackgroundColor(GREEN);
                textViewPriority.setBackgroundColor(GREEN);
            } else {
                textViewName.setBackgroundColor(BLUE);
                textViewDesc.setBackgroundColor(BLUE);
                textViewPriority.setBackgroundColor(BLUE);

            }
        }
    }
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_task_layout,parent, false);
        return new MyColorViewHolder(view);
    }
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        MyColorViewHolder myColorViewHolder = (MyColorViewHolder) holder;
        myTask myColor = Task.myTasks.get(position);
        myColorViewHolder.updateTextViews(myColor);
    }
    public int getItemCount(){
        return Task.myTasks.size();
    }
}
