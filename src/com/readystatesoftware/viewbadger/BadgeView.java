package com.readystatesoftware.viewbadger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
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
	private static final int DEFAULT_POSITION = POSITION_TOP_RIGHT;
	private static final int DEFAULT_BADGE_COLOR = Color.RED;
	private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
	
	private static Animation fadeIn;
	private static Animation fadeOut;
	
	private Context context;
	private View target;
	
	private int badgePosition;
	private int badgeMargin;
	private int badgeColor;
	
	private boolean isShown;
	
	private ShapeDrawable badgeBg;
	
	public BadgeView(Context context) {
		this(context, null, android.R.attr.textViewStyle);
	}
	
	public BadgeView(Context context, AttributeSet attrs) {
		 this(context, attrs, android.R.attr.textViewStyle);
	}
	
	public BadgeView(Context context, View target) {
		 this(context, null, android.R.attr.textViewStyle, target);
	}
	
	public BadgeView(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs, defStyle, null);
	}
	
	public BadgeView(Context context, AttributeSet attrs, int defStyle, View target) {
		super(context, attrs, defStyle);
		init(context, target);
	}

	private void init(Context context, View target) {
		
		this.context = context;
		this.target = target;
		
		// apply defaults
		badgePosition = DEFAULT_POSITION;
		badgeMargin = dipToPixels(DEFAULT_MARGIN_DIP);
		badgeColor = DEFAULT_BADGE_COLOR;
		
		setTypeface(Typeface.DEFAULT_BOLD);
		int paddingPixels = dipToPixels(DEFAULT_LR_PADDING_DIP);
		setPadding(paddingPixels, 0, paddingPixels, 0);
		setTextColor(DEFAULT_TEXT_COLOR);
		
		fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(200);

		fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		//fadeOut.setStartOffset(1000);
		fadeOut.setDuration(200);
		
		isShown = false;
		
		applyTo(this.target);
		
	}

	private void applyTo(View target) {
		
		LayoutParams lp = target.getLayoutParams();
		
		ViewParent parent = target.getParent();
		ViewGroup group = (ViewGroup) parent; // TODO verify!
		int index = group.indexOfChild(target);
		
		FrameLayout container = new FrameLayout(context);
		
		group.removeView(target);
		group.addView(container, index, lp);
		
		container.addView(target);
		//if (getBackground() == null) {
		//	setBackgroundDrawable(getDefaultBackground());
		//}
		this.setVisibility(View.GONE);
		container.addView(this);
		//applyLayoutParams();
		
		group.invalidate();
		
	}
	
	public void show() {
		show(false, null);
	}
	
	public void show(boolean animate) {
		show(animate, fadeIn);
	}
	
	public void show(Animation anim) {
		show(true, anim);
	}
	
	public void hide() {
		hide(false, null);
	}
	
	public void hide(boolean animate) {
		hide(animate, fadeOut);
	}
	
	public void hide(Animation anim) {
		hide(true, anim);
	}
	
	public void toggle() {
		toggle(false, null, null);
	}
	
	public void toggle(boolean animate) {
		toggle(animate, fadeIn, fadeOut);
	}
	
	public void toggle(Animation animIn, Animation animOut) {
		toggle(true, animIn, animOut);
	}
	
	private void show(boolean animate, Animation anim) {
		if (getBackground() == null) {
			if (badgeBg == null) {
				badgeBg = getDefaultBackground();
			}
			setBackgroundDrawable(badgeBg);
		}
		applyLayoutParams();
		
		if (animate) {
			this.startAnimation(anim);
		}
		this.setVisibility(View.VISIBLE);
		isShown = true;
	}
	
	private void hide(boolean animate, Animation anim) {
		this.setVisibility(View.GONE);
		if (animate) {
			this.startAnimation(anim);
		}
		isShown = false;
	}
	
	private void toggle(boolean animate, Animation animIn, Animation animOut) {
		if (isShown) {
			hide(animate && (animOut != null), animOut);	
		} else {
			show(animate && (animIn != null), animIn);
		}
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

	public View getTarget() {
		return target;
	}

	public boolean isShown() {
		return isShown;
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

	public void setBadgeBackground(int badgeColor) {
		this.badgeColor = badgeColor;
		badgeBg = getDefaultBackground();
	}


}
