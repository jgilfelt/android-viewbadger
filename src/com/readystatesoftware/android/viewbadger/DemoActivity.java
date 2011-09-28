package com.readystatesoftware.android.viewbadger;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DemoActivity extends Activity {
    
	ImageView icon;
	TextView text;
	Button button;
	
	BadgeView badge;
	BadgeView badge2;
	BadgeView badge3;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        icon = (ImageView) findViewById(R.id.imageView1);
        text = (TextView) findViewById(R.id.textView1);
        button = (Button) findViewById(R.id.apply);
        
        badge = new BadgeView(this, icon);
        badge.setText("1");
    	badge.setBadgeMargin(10);
    	
    	badge2 = new BadgeView(this, text);
    	badge2.setTextColor(Color.BLUE);
    	badge2.setBadgeBackground(Color.YELLOW);
    	badge2.setText("New!");
    	badge2.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
    	badge2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(DemoActivity.this, "clicked badge", Toast.LENGTH_SHORT).show();
			}
		});
    	
    	badge3 = new BadgeView(this, button);
    	badge3.setText("99");
    	badge3.setTextColor(Color.DKGRAY);
    	badge3.setTextSize(10);
    	badge3.setBackgroundColor(Color.WHITE);
    	badge3.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
    }
    
    public void onApplyClick(View v) {
    	
    	badge3.toggle();
    	badge2.toggle(true);
    	
    	
    	
    	TranslateAnimation anim = new TranslateAnimation(-100, 0, 0, 0);
        anim.setInterpolator(new BounceInterpolator());
        anim.setDuration(1000);
    	
    	badge.toggle(anim, null);
    }
    
}