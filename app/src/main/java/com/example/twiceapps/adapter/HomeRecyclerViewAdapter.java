package com.example.twiceapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twiceapps.R;
import com.example.twiceapps.home_anxiety;
import com.example.twiceapps.home_depression;
import com.example.twiceapps.home_stress;
import com.example.twiceapps.homerecycler;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>{

    public List<homerecycler> homerecyclerList;
    private Context context;

    public HomeRecyclerViewAdapter(Context context, List<homerecycler> homerecyclerList) {
        this.context = context;
        this.homerecyclerList = homerecyclerList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View home_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row,null);

        HomeViewHolder homeVH = new HomeViewHolder(home_row);

        return homeVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

        holder.tvHomeName.setText(homerecyclerList.get(position).getName());
        holder.imgViewHomeImage.setImageResource(homerecyclerList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return homerecyclerList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvHomeName;
        public ImageView imgViewHomeImage;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHomeName = itemView.findViewById(R.id.tv_home_name);
            imgViewHomeImage = itemView.findViewById(R.id.img_home);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            //Toast.makeText(view.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();

            if(getLayoutPosition()==0){

                Intent intent = new Intent(view.getContext(), home_stress.class);
                view.getContext().startActivity(intent);

            }else if(getLayoutPosition()==1){
                Intent intent = new Intent(view.getContext(), home_anxiety.class);
                view.getContext().startActivity(intent);

            }else if(getLayoutPosition()==2){
                Intent intent = new Intent(view.getContext(), home_depression.class);
                view.getContext().startActivity(intent);

            }

        }
    }

}
