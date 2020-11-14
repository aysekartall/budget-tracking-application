package com.example.butcetakipuygulamasi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context icerikContext;
    List<Icerikler> veri;

    public CustomAdapter(Context icerikContext, List<Icerikler> veri) {
        this.icerikContext = icerikContext;
        this.veri = veri;
    }

    @Override
    public int getCount() {
        return veri.size();
    }

    @Override
    public Object getItem(int position) {
        return veri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vw = View.inflate(icerikContext,R.layout.customlist_icerik,null);

        TextView tv_baslik=(TextView)vw.findViewById(R.id.tv_icerikbaslik);
        tv_baslik.setText(veri.get(position).getIcerikIsim());
        ImageView img_resim=(ImageView)vw.findViewById(R.id.img_iceriklogo);
        img_resim.setImageResource(veri.get(position).getIcerikImg());

        return vw;
    }
}