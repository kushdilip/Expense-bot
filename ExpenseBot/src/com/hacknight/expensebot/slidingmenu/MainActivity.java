package com.hacknight.expensebot.slidingmenu;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ListView;

import com.hacknight.expensebot.R;

public class MainActivity extends Activity {
//	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
//	private ActionBarDrawerToggle mDrawerToggle;
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	
	
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_slide);
	        
	    
	 }
	
}
