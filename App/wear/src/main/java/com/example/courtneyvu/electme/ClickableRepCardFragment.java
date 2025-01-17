package com.example.courtneyvu.electme;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by CourtneyVu on 3/1/16.
 */
public class ClickableRepCardFragment extends CardFragment {

    private View fragmentView;

    public static CardFragment create(CharSequence title, CharSequence text, int iconRes) {
        return CardFragment.create(title, text, iconRes);
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentView = (ViewGroup) inflater.inflate(R.layout.rep_fragment, null);
        final Bundle savedInfo = getArguments();

        ImageView img = (ImageView) fragmentView.findViewById(R.id.rep_photo);
        img.setImageResource(savedInfo.getInt("photo"));

        TextView name = (TextView) fragmentView.findViewById(R.id.rep_name);
        name.setText(savedInfo.getString("name"));

        fragmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                TextView name = (TextView) fragmentView.findViewById(R.id.rep_name);
                String text = name.getText().toString();
                String bioguide = savedInfo.getString("bioguide");
                String term_ends = savedInfo.getString("term_ends");
                Log.d("test", "sees click");

                Intent sendIntent = new Intent(getActivity().getBaseContext(), WatchToPhoneService.class);
                sendIntent.putExtra("NAME", text);
                sendIntent.putExtra("BIOGUIDE", bioguide);
                sendIntent.putExtra("TERM_ENDS", term_ends);
                Log.d("test", "starts service");
                getActivity().startService(sendIntent);
            }
        });
        return fragmentView;

    }

}
