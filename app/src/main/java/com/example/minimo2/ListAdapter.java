package com.example.minimo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.minimo2.responses.Badge;

import java.util.List;

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

        private List<Badge> BadgeList;
        private Context context;

        public ListAdapter( Context context){
            this.context = context;
        }

        public List<Badge> getBadge() {
            return BadgeList;
        }

        public void setBadgeList(List<Badge> BadgeList) {
            this.BadgeList = BadgeList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.insignia_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.Badgename.setText(BadgeList.get(position).getName());
            Glide.with(this.context).load(BadgeList.get(position).getAvatar_url()).into(holder.Badgeimage);
        }

        @Override
        public int getItemCount() {
            return BadgeList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView Badgeimage;
            TextView Badgename;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                Badgeimage = (ImageView)itemView.findViewById(R.id.insigniaimagen);
                Badgename = (TextView)itemView.findViewById(R.id.textView5);

            }
        }
    }
}
