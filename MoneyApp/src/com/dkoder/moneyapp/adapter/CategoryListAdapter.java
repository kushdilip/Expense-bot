package com.dkoder.moneyapp.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.dkoder.moneyapp.activity.CategoriesFragment;
import com.dkoder.moneyapp.model.Category;

public class CategoryListAdapter extends ArrayAdapter<Category> {
	Activity context;
	private ArrayList<Category> allCategoryItemsArray;
	private LayoutInflater inflater;
	
	public CategoryListAdapter(Activity context, CategoriesFragment categoriesFragment,
			List<Category> categories) {
		super(context, android.R.layout.simple_list_item_1, categories);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.allCategoryItemsArray = new ArrayList<Category>();
		allCategoryItemsArray.addAll(categories);
		inflater = context.getLayoutInflater(); 	
	}
	
	
}
