package com.coolstar.children.wearlistening;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyActivity extends Activity implements WearableListView.ClickListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        WearableListView listView = (WearableListView) findViewById(R.id.list);
        listView.setAdapter(new Adapter(this));
        listView.setClickListener(this);

    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {

        Integer iv = (Integer) viewHolder.itemView.getTag();
        Intent intent = new Intent(MyActivity.this,CardPagerActivity.class);
        intent.putExtra("grade",iv);
        startActivity(intent);
    }

    @Override
    public void onTopEmptyRegionClick() {

    }

    private static final class Adapter extends WearableListView.Adapter {
        private final Context mContext;
        private final LayoutInflater mInflater;

        private Adapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new WearableListView.ViewHolder(
                    mInflater.inflate(R.layout.notif_preset_list_item, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            TextView view = (TextView) holder.itemView.findViewById(R.id.name);
            view.setText(GradePresets.PRESETS[position]);
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return GradePresets.PRESETS.length;
        }
    }

}
