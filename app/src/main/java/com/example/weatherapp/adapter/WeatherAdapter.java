package com.example.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.City;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {


    private ArrayList<City> mList = new ArrayList<>();
    private Context context;
    private IClickListener iClickListener;

    public interface IClickListener {
        void onItemCliclk(int position);
    }


    public WeatherAdapter(Context c) {
        context = c;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvcity)
        TextView tvcity;
        @BindView(R.id.tvweather)
        TextView tvweather;
        @BindView(R.id.tvcoord)
        TextView tvcoord;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            iClickListener.onItemCliclk(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        // return view holder for your normal list item
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        City city = mList.get(position);
        holder.tvcity.setText(city.getName() + " , " + city.getApIsys().getCountry());
        holder.tvweather.setText(city.getWeather().get(0).getDescription());
        holder.tvcoord.setText("[ " + city.getCoord().getLat() + " , " + city.getCoord().getLon() + " ]");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<City> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
        notifyDataSetChanged();
    }

    public City getItem(int position) {
        return mList.get(position);
    }

    public void setItemListener(IClickListener itemListener) {
        iClickListener = itemListener;
    }


}
