package viewbadger.demo;

import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;
import com.readystatesoftware.viewbadger.R;

public class DemoActivity extends TabActivity {
    
	Button btnPosition;
	Button btnColour;
	Button btnAnim1;
	Button btnAnim2;
	Button btnCustom;
	Button btnClick;
	Button btnTab;
	
	BadgeView badge1;
	BadgeView badge2;
	BadgeView badge3;
	BadgeView badge4;
	BadgeView badge5;
	BadgeView badge6;
	BadgeView badge7;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("demos")
                .setIndicator("Badge Demos")
                .setContent(R.id.tab1));

        tabHost.addTab(tabHost.newTabSpec("tests")
                .setIndicator("Layout Tests")
                .setContent(R.id.tab2));
       
        // *** default badge ***
        
		View target = findViewById(R.id.default_target);
		BadgeView badge = new BadgeView(this, target);
		badge.setText("1");
		badge.show();
        
        // *** set position ***
		
        btnPosition = (Button) findViewById(R.id.position_target);
        badge1 = new BadgeView(this, btnPosition);
        badge1.setText("12");
        badge1.setBadgePosition(BadgeView.POSITION_BOTTOM_LEFT);
        btnPosition.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge1.toggle();
			}
		});
        
        // *** badge/text colour ***
        
    	btnColour = (Button) findViewById(R.id.colour_target);
    	badge2 = new BadgeView(this, btnColour);
    	badge2.setText("New!");
    	badge2.setTextColor(Color.BLUE);
    	badge2.setBadgeBackground(Color.YELLOW);
    	btnColour.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge2.toggle();
			}
		});
    	
    	// *** default animation ***
    	
    	btnAnim1 = (Button) findViewById(R.id.anim1_target);
        badge3 = new BadgeView(this, btnAnim1);
        badge3.setText("84");
        btnAnim1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge3.toggle(true);
			}
		});
    	
        // *** custom animation ***
        
    	btnAnim2 = (Button) findViewById(R.id.anim2_target);
        badge4 = new BadgeView(this, btnAnim2);
    	badge4.setText("123");
    	badge4.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
    	badge4.setBadgeMargin(15);
    	badge4.setBadgeBackground(Color.parseColor("#A4C639"));
    	btnAnim2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TranslateAnimation anim = new TranslateAnimation(-100, 0, 0, 0);
		        anim.setInterpolator(new BounceInterpolator());
		        anim.setDuration(1000);
		    	badge4.toggle(anim, null);
			}
		});
        
    	// *** custom background ***
    	
        btnCustom = (Button) findViewById(R.id.custom_target);
        badge5 = new BadgeView(this, btnCustom);
        badge5.setText("8");
        badge5.setBackgroundResource(R.drawable.badge_ifaux);
    	badge5.setBadgeMargin(10);
    	badge5.setTextSize(12);
    	btnCustom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge5.toggle();
			}
		});
    	
    	// *** clickable badge ***
    	
    	btnClick = (Button) findViewById(R.id.click_target);
    	badge6 = new BadgeView(this, btnClick);
    	badge6.setText("click me");
    	badge6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(DemoActivity.this, "clicked badge", Toast.LENGTH_SHORT).show();
			}
		});
    	btnClick.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge6.toggle();
			}
		});
    	
    	// *** tab ***
		// TODO needs work 
    	
        btnTab = (Button) findViewById(R.id.tab_btn);
        TabWidget tabs = (TabWidget) findViewById(android.R.id.tabs);
        ViewGroup tab = (ViewGroup) tabs.getChildTabViewAt(1);
        View v = tab.getChildAt(0); 
        badge7 = new BadgeView(this, v);
        badge7.setText("5");
    	badge7.setBadgeMargin(0);
        btnTab.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				badge7.toggle();
			}
		});
    	
    }

	@Override
	protected void onResume() {
		super.onResume();
		
		BadgeView badge;
		View target;
		
		// *** test linear layout container ***
		
		target = findViewById(R.id.linear_target);
		badge = new BadgeView(this, target);
		badge.setText("OK");
		badge.show();
		
		// *** test relative layout container ***
		
		target = findViewById(R.id.relative_target);
		badge = new BadgeView(this, target);
		badge.setText("OK");
		badge.show();
		
		// *** test frame layout container ***
		
		target = findViewById(R.id.frame_target);
		badge = new BadgeView(this, target);
		badge.setText("OK");
		badge.show();
		
		// *** test table layout container ***
		
		target = findViewById(R.id.table_target);
		badge = new BadgeView(this, target);
		badge.setText("OK");
		badge.show();
		
		
	}
    
    
    
}