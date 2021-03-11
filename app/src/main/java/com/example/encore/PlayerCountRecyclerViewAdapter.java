//package com.example.encore;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class PlayerCountRecyclerViewAdapter extends RecyclerView.Adapter<PlayerCountRecyclerViewAdapter.ViewHolder> {
//    private List<String> data;
//    private LayoutInflater pcinflater;
//
//    public PlayerCountRecyclerViewAdapter(Context context, List<String> data){
//        this.pcinflater = LayoutInflater.from(context);
//        this.data = data;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        View view = pcinflater.inflate(R.layout.player_count_layout, parent, false);
//        return new RecyclerView.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position){
//        String playerNumber = data.get(position);
//        holder.playerTextView.setText(playerNumber);
//    }
//
//    @Override
//    public int getItemCount(){
//        return data.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView myTextView;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            myTextView = itemView.findViewById(R.id.textViewPlayerCount);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
//    }
//
//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
//}
