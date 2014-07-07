package com.coolstar.children.wearlistening;

 import android.app.Activity;
 import android.content.res.Resources;
 import android.os.Bundle;
 import android.speech.tts.TextToSpeech;
 import android.support.wearable.view.GridViewPager;
 import android.view.View;
 import android.view.WindowInsets;

 /**
 * Created by pengyixiong on 14-7-7.
 */
public class CardPagerActivity extends Activity{
    private int gradenum;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_pager);

        gradenum = getIntent().getIntExtra("grade",0);

        tts = new TextToSpeech(CardPagerActivity.this, new TextToSpeech.OnInitListener(){

            @Override
            public void onInit(int status) {

            }
        });

        final Resources res = getResources();
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                // Adjust page margins:
                //   A little extra horizontal spacing between pages looks a bit
                //   less crowded on a round display.
                final boolean round = insets.isRound();
                int rowMargin = res.getDimensionPixelOffset(R.dimen.page_row_margin);
                int colMargin = res.getDimensionPixelOffset(round ?
                        R.dimen.page_column_margin_round : R.dimen.page_column_margin);
                pager.setPageMargins(rowMargin, colMargin);
                return insets;
            }
        });
        pager.setAdapter(new SampleGridPagerAdapter(this, getFragmentManager(),gradenum,tts));
    }
}
