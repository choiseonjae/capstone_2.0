package com.example.capstonee.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.capstonee.R;
import com.example.capstonee.SelectedRoleAlbum;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class HotPlaceAdapter extends RecyclerView.Adapter<HotPlaceAdapter.ItemViewHolder> {

    // adapter 에 들어갈 list 입니다.
    private ArrayList<Map<String, String>> list = new ArrayList<>();
    private Context context;
    private View view;


    public HotPlaceAdapter(Context context) {
        this.context = context;
    }

    // LayoutInflater 를 이용하여 전 단계에서 만들었던 item.xml 을 inflate 시킵니다. return 인자는 ViewHolder 입니다.
    @NonNull
    @Override
    public HotPlaceAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_area_item, parent, false);
        return new HotPlaceAdapter.ItemViewHolder(view);
    }

    // view 와 item 을 연결
    @Override
    public void onBindViewHolder(@NonNull HotPlaceAdapter.ItemViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    // RecyclerView 의 총 개수
    @Override
    public int getItemCount() {

        return list.size();
    }

    // 외부에서 item 을 추가시킬 함수
    public void addItem(Map map) {
        list.add(map);
    }


    // ViewHolder
    // 하나의 View 객체 를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView title_textView;
        private ImageView image_imageView;
        private LinearLayout linearLayout;
        private Map<String, String> map;

        ItemViewHolder(View view) {
            super(view);

            title_textView = view.findViewById(R.id.popular_text_view);
            image_imageView = view.findViewById(R.id.popular_image_view);
            linearLayout = view.findViewById(R.id.popular_linearlayout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder ad = new AlertDialog.Builder(v.getContext());
                    ad.setTitle("주소");
                    if (map.get("address").equals(""))
                        ad.setMessage("죄송합니다. 주소를 가져오지 못했습니다.");
                    else
                        ad.setMessage(map.get("address"));

                    ad.show();
                }
            });

        }

        void onBind(Map<String, String> map) {
            this.map = map;
            title_textView.setText(map.get("title"));
            Log.e("-> ", map.get("image").replace("http://", "https://"));
            Picasso.with(context).load(map.get("image").replace("http://", "https://")).fit().into(image_imageView);


        }

    }
}



