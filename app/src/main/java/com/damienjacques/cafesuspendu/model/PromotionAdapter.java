package com.damienjacques.cafesuspendu.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.damienjacques.cafesuspendu.R;

import java.util.ArrayList;
import java.util.List;

public class PromotionAdapter extends ArrayAdapter
{
    public PromotionAdapter(Context context, ArrayList<PromotionLine> promo)
    {
        super(context,0,promo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        PromotionLine promoLine = (PromotionLine) getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.promotionline,parent,false);
        }

        TextView nameCoffee = (TextView) convertView.findViewById(R.id.CafeNamePromo);
        TextView descriptionPromo = (TextView) convertView.findViewById(R.id.textPromo);

        ProgressBar progressBarPromo = (ProgressBar) convertView.findViewById(R.id.progressBarPromo);
        nameCoffee.setText(promoLine.getCafeName());
        descriptionPromo.setText(promoLine.getDescriptionPromo());
        progressBarPromo.setProgress(promoLine.getProgressStatus());
        return convertView;
    }
}
