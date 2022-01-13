package com.example.twiceapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twiceapps.Guide;
import com.example.twiceapps.R;
import com.example.twiceapps.guide_anxiety;
import com.example.twiceapps.guide_depression;
import com.example.twiceapps.guide_stress;
import com.example.twiceapps.home_anxiety;
import com.example.twiceapps.home_depression;
import com.example.twiceapps.home_stress;

import java.util.List;

public class GuideRecyclerViewAdapter extends RecyclerView.Adapter<GuideRecyclerViewAdapter.GuideViewHolder>{

    public List<Guide> guideList;
    private Context context;

    public GuideRecyclerViewAdapter(Context context, List<Guide> guideList) {
        this.context = context;
        this.guideList = guideList;
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View guide_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_row,null);

        GuideViewHolder guideVH = new GuideViewHolder(guide_row);
        return guideVH;
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder holder, int position) {

        holder.tvGuideName.setText(guideList.get(position).getName());
        holder.imgViewGuideImage.setImageResource(guideList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return guideList.size();
    }

    public class GuideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvGuideName;
        public ImageView imgViewGuideImage;

        public GuideViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGuideName = itemView.findViewById(R.id.tv_guide_name);
            imgViewGuideImage = itemView.findViewById(R.id.img_guide);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if(getLayoutPosition()==0){

                Intent intent = new Intent(view.getContext(), guide_stress.class);
                view.getContext().startActivity(intent);

            }else if(getLayoutPosition()==1){
                Intent intent = new Intent(view.getContext(), guide_anxiety.class);
                view.getContext().startActivity(intent);

            }else if(getLayoutPosition()==2){
                Intent intent = new Intent(view.getContext(), guide_depression.class);
                view.getContext().startActivity(intent);

            }

        }
    }
}
