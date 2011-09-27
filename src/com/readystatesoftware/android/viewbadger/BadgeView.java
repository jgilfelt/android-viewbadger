package com.readystatesoftware.android.viewbadger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TextView;

public class BadgeView extends TextView {

	public static final int POSITION_TOP_LEFT = 1;
	public static final int POSITION_TOP_RIGHT = 2;
	public static final int POSITION_BOTTOM_LEFT = 3;
	public static final int POSITION_BOTTOM_RIGHT = 4;
	
	private static final int DEFAULT_MARGIN_DIP = 5;
	private static final int DEFAULT_LR_PADDING_DIP = 5;
	private static final int DEFAULT_CORNER_RADIUS_DIP = 8;
	private static final int DEFAULT_POSITION = POSITION_BOTTOM_RIGHT;
	private static final int DEFAULT_BADGE_COLOR = Color.RED;
	private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
	
	private Context context;
	private int badgePosition;
	private int badgeMargin;
	private int badgeColor;
	
	public BadgeView(Context context) {
		super(context);
		
		this.context = context;
		
		// apply defaults
		badgePosition = DEFAULT_POSITION;
		badgeMargin = dipToPixels(DEFAULT_MARGIN_DIP);
		badgeColor = DEFAULT_BADGE_COLOR;
		
		setTypeface(Typeface.DEFAULT_BOLD);
		int paddingPixels = dipToPixels(DEFAULT_LR_PADDING_DIP);
		setPadding(paddingPixels, 0, paddingPixels, 0);
		setTextColor(DEFAULT_TEXT_COLOR);
		
	}
	
	public void applyTo(View target) {
		
		LayoutParams lp = target.getLayoutParams();
		
		ViewParent parent = target.getParent();
		ViewGroup group = (ViewGroup) parent; // TODO verify!
		int index = group.indexOfChild(target);
		
		FrameLayout container = new FrameLayout(context);
		
		group.removeView(target);
		group.addView(container, index, lp);
		
		container.addView(target);
		if (getBackground() == null) {
			setBackgroundDrawable(getDefaultBackground());
		}
		container.addView(this);
		applyLayoutParams();
		
		group.invalidate();
		
		
	}
	
	private ShapeDrawable getDefaultBackground() {
		
		int r = dipToPixels(DEFAULT_CORNER_RADIUS_DIP);
		float[] outerR = new float[] {r, r, r, r, r, r, r, r};
        //RectF   inset = new RectF(6, 6, 6, 6);
        //float[] innerR = new float[] { 12, 12, 0, 0, 12, 12, 0, 0 };
        
        RoundRectShape rr = new RoundRectShape(outerR, null, null);
		ShapeDrawable drawable = new ShapeDrawable(rr);
		drawable.getPaint().setColor(badgeColor);
		
		return drawable;
		
	}
	
	private void applyLayoutParams() {
		
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		switch (badgePosition) {
		case POSITION_TOP_LEFT:
			lp.gravity = Gravity.LEFT | Gravity.TOP;
			lp.setMargins(badgeMargin, badgeMargin, 0, 0);
			break;
		case POSITION_TOP_RIGHT:
			lp.gravity = Gravity.RIGHT | Gravity.TOP;
			lp.setMargins(0, badgeMargin, badgeMargin, 0);
			break;
		case POSITION_BOTTOM_LEFT:
			lp.gravity = Gravity.LEFT | Gravity.BOTTOM;
			lp.setMargins(badgeMargin, 0, 0, badgeMargin);
			break;
		case POSITION_BOTTOM_RIGHT:
			lp.gravity = Gravity.RIGHT | Gravity.BOTTOM;
			lp.setMargins(0, 0, badgeMargin, badgeMargin);
			break;
		default:
			break;
		}
		
		setLayoutParams(lp);
		
	}

	private int dipToPixels(int dip) {
		Resources r = getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
		return (int) px;
	}

	public int getBadgePosition() {
		return badgePosition;
	}

	public void setBadgePosition(int layoutPosition) {
		this.badgePosition = layoutPosition;
	}

	public int getBadgeMargin() {
		return badgeMargin;
	}

	public void setBadgeMargin(int badgeMargin) {
		this.badgeMargin = badgeMargin;
	}
	
	public int getBadgeColor() {
		return badgeColor;
	}

	public void setBadgeColor(int badgeColor) {
		this.badgeColor = badgeColor;
	}


}
