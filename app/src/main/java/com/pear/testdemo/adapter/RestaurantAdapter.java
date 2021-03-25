package com.pear.testdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pear.testdemo.R;
import com.pear.testdemo.bean.Item;
import com.pear.testdemo.bean.RestBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    Context context;
    private ArrayList<RestBean> arrayList;
    private ArrayList<RestBean> getFilterData;

    public RestaurantAdapter(Context context, ArrayList<RestBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.getFilterData = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lyt_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RestBean restBean = arrayList.get(position);
        Log.d("davd","1 "+restBean.getResNm());
        holder.htNm.setText(restBean.getResNm());
        holder.htLoc.setText(restBean.getResLoc());
        holder.dt.setText(restBean.getTmStp());
        holder.amt.setText("₹ " + restBean.getResPrc());

        Picasso.get().
                load(restBean.getResImg())
                .fit()
                .centerInside()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.imgHot);

        final ArrayList<Item> items = restBean.getItems();
        String item = "";
        for(int i = 0; i < items.size(); i++){
            String detail = items.get(i).getQnty() + " x " + items.get(i).getName();
//            Log.d("hjhjh","1 "+detail);
            if(i == items.size() - 1){
                item = item + detail;
            }else{
                item = item + detail + ", ";
            }
        }
        holder.items.setText(item);
    }

    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence == null || charSequence.length() == 0) {
                    filterResults.count = getFilterData.size();
                    filterResults.values = getFilterData;
                } else {
                    Log.d("sSs","jh1:"+charSequence.toString());
                    ArrayList<RestBean> result = new ArrayList<>();
                    String searchStr = charSequence.toString().toLowerCase();
                    Log.d("sSs","jh2:"+getFilterData.size());
                    for (RestBean restBean : getFilterData) {
                        if (restBean.getResNm().toLowerCase().contains(searchStr)) {
                            result.add(restBean);
                        }else if(restBean.getResLoc().toLowerCase().contains(searchStr)){
                            result.add(restBean);
                        }
                        filterResults.count = result.size();
                        filterResults.values = result;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                arrayList = (ArrayList<RestBean>) results.values;
                Log.d("sSs","jh3: "+arrayList.size());
                notifyDataSetChanged();

            }
        };
        return filter;

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView htNm, htLoc, items, dt, amt;
        ImageView imgHot;

        public MyViewHolder(View view) {
            super(view);
            htNm = (TextView) view.findViewById(R.id.hotelNm);
            htLoc = (TextView) view.findViewById(R.id.hotelLoc);
            imgHot = (ImageView) view.findViewById(R.id.imgHotel);
            items = view.findViewById(R.id.txtItems);
            dt = view.findViewById(R.id.txtDt);
            amt = view.findViewById(R.id.totAmt);
        }
    }
}
