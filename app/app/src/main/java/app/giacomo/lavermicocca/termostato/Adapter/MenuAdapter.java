package app.giacomo.lavermicocca.termostato.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import app.giacomo.lavermicocca.termostato.Bean.Information;
import app.giacomo.lavermicocca.termostato.R;

/**
 * Created by Windows on 22-12-2014.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    List<Information> data = Collections.emptyList();
    private LayoutInflater inflater;
    private int color;
    private int colorZebra;

    public MenuAdapter(Context context, List<Information> data) {
        inflater = LayoutInflater.from(context);

        this.color = context.getResources().getColor(R.color.blue_light);
        this.colorZebra = context.getResources().getColor(R.color.blue_dark);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);

        MyViewHolder holder;
        view.setBackgroundColor(colorZebra);
        holder = new MyViewHolder(view);
        return holder;

//        switch (viewType) {
//            case 0:
//                view.setBackgroundColor(color);
//                holder = new MyViewHolder(view);
//                return holder;
//            case 2:
//                view.setBackgroundColor(colorZebra);
//                holder = new MyViewHolder(view);
//                return holder;
//        }
//        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        //holder.icon.setImageResource(current.iconId);
        holder.icon.setText(current.iconId);
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2 * 2;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        //ImageView icon;
        com.shamanland.fonticon.FontIconView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            //icon= (ImageView) itemView.findViewById(R.id.listIcon);
            icon = (com.shamanland.fonticon.FontIconView) itemView.findViewById(R.id.listIcon);
        }
    }
}
