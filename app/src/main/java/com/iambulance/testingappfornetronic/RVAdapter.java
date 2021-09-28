package com.iambulance.testingappfornetronic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVHolder> {

    private List<MyModel> data;
    private Context context;

    public RVAdapter(List<MyModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new RVHolder(inflater.inflate(R.layout.item_card, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVHolder holder, int position) {
        holder.tv_name.setText(data.get(position).getName());
        Picasso.get()
                .load(data.get(position).getImage())
                .into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class RVHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_name;
        public RVHolder(@NonNull View view) {
            super(view);
            iv = view.findViewById(R.id.iv_user_picture);
            tv_name = view.findViewById(R.id.tv_name);
        }
    }
}
