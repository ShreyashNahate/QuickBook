package com.example.quickbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyViewHolder2> {

    private Context context;
    private List<DataClass2> dataList;
    String imageUrl = "";
    public MyAdapter2(Context context, List<DataClass2> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item2, parent, false);
        return new MyViewHolder2(view);
    }


    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {


        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, zoom.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                context.startActivity(intent);
            }
        });
        imageUrl = dataList.get(holder.getAdapterPosition()).getDataImage();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

//    public void searchDataList(ArrayList<DataClass2> searchList){
//        dataList = searchList;
//        notifyDataSetChanged();
//    }
}

class MyViewHolder2 extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recTitle ;
    CardView recCard;
    ImageButton imageButton;

    public MyViewHolder2(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recTitle = itemView.findViewById(R.id.recTitle);
        imageButton = itemView.findViewById(R.id.deleteimage);
    }
}