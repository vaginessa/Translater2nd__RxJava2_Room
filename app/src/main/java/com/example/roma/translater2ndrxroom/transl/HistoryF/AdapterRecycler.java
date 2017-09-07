package com.example.roma.translater2ndrxroom.transl.HistoryF;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.translater2ndrxroom.R;
import com.example.roma.translater2ndrxroom.data.TranslateItem;

import java.util.ArrayList;
import java.util.List;


public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.HistoryChildRecycler> {

    List<TranslateItem> listItems = new ArrayList<>();

    List<TranslateItem> listDelete = new ArrayList<>();

    Context context;

    HistoryAndFavoriteContract.TranslateItemListener listener;

    boolean delete = false;


    public AdapterRecycler(HistoryAndFavoriteContract.TranslateItemListener listener, List<TranslateItem> list, Context context) {
        listItems = list;
        this.listener = listener;
        this.context = context;
    }


    public class HistoryChildRecycler extends RecyclerView.ViewHolder {
        ConstraintLayout container;
        TextView wordIn;
        TextView wordOut;
        TextView lang;
        ImageView imageFavorite;

        public HistoryChildRecycler(View itemView) {
            super(itemView);
            container = (ConstraintLayout) itemView.findViewById(R.id.container_child_history);
            imageFavorite = (ImageView) itemView.findViewById(R.id.image_favorite);
            imageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTranslateFavoriteClick(listItems.get(getAdapterPosition()));
                }
            });
            wordIn = (TextView) itemView.findViewById(R.id.word_in);
            wordOut = (TextView) itemView.findViewById(R.id.word_out);
            lang = (TextView) itemView.findViewById(R.id.lang);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listDelete.contains(listItems.get(getAdapterPosition()))) {
                        listDelete.remove(listItems.get(getAdapterPosition()));
                        listItems.get(getAdapterPosition()).setDelete(0);
                    } else {
                        listDelete.add(listItems.get(getAdapterPosition()));
                        listItems.get(getAdapterPosition()).setDelete(1);
                    }
                    listener.sendListDelete(listDelete);
                    notifyItemChanged(getAdapterPosition());
                    return false;
                }
            });

        }

    }

    @Override
    public HistoryChildRecycler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.history_child_recycler, parent, false);
        return new HistoryChildRecycler(view);
    }

    @Override
    public void onBindViewHolder(HistoryChildRecycler holder, int position) {
        holder.wordIn.setText(listItems.get(position).getWordIn());
        holder.wordOut.setText(listItems.get(position).getWordOut());
        Log.v("sdlfskjdfg", listItems.get(position).isFavorite() + "");
        holder.lang.setText(listItems.get(position).getLangIn());
        if (listItems.get(position).isFavorite()) {
            holder.imageFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.imageFavorite.setImageResource(R.drawable.ic_inactive_favorite);
        }
        if (listItems.get(position).isDelete()) {
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLight));
        } else
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.cardview_light_background));


    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void updateAdapter() {
        notifyDataSetChanged();
    }

    public void updateAdapter(List<TranslateItem> list) {
        listItems = list;
        notifyDataSetChanged();
    }

    public void deleteFromList() {
        for (int i = 0; i < listItems.size(); i++) {
            if (listItems.get(i).isDelete()) {
                listItems.remove(i);
                i--;
            }
        }
        notifyDataSetChanged();
        Log.v("DFSDSDFSDFDF", " " + listItems.size());
    }


}
