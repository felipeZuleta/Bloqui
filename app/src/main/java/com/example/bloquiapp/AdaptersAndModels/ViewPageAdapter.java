package com.example.bloquiapp.AdaptersAndModels;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bloquiapp.R;

import java.util.ArrayList;

public class ViewPageAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<ActionsModel> modelArrayList;

    public ViewPageAdapter(Context context, ArrayList<ActionsModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_toggle_action,container,false);

        ImageView toggleActionIcon = view.findViewById(R.id.toggle_action_icon);
        TextView titleAction = view.findViewById(R.id.toggle_action_title);
        CardView cardView = view.findViewById(R.id.toggle_action_cV);

        ActionsModel model = modelArrayList.get(position);
        String title = model.getTitle();
        int icon = model.getIcon();

        toggleActionIcon.setImageResource(icon);
        titleAction.setText(title);
        cardView.setCardBackgroundColor(view.getResources().getColor(R.color.dirtWhite));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
