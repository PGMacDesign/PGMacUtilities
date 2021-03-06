package com.pgmacdesign.pgmactips.customui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pgmacdesign.pgmactips.R;
import com.pgmacdesign.pgmactips.adaptersandlisteners.CustomClickCallbackLink;
import com.pgmacdesign.pgmactips.adaptersandlisteners.CustomLongClickCallbackLink;
import com.pgmacdesign.pgmactips.adaptersandlisteners.MultipurposeChoiceAdapter;
import com.pgmacdesign.pgmactips.adaptersandlisteners.OnTaskCompleteListener;
import com.pgmacdesign.pgmactips.misc.CustomAnnotationsBase;
import com.pgmacdesign.pgmactips.misc.PGMacTipsConstants;
import com.pgmacdesign.pgmactips.utilities.HashMapUtilities;
import com.pgmacdesign.pgmactips.utilities.MiscUtilities;
import com.pgmacdesign.pgmactips.utilities.StringUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Multi-purpose choice dialog to allow for modular usage
 * Created by Patrick-SSD2 on 11/4/2017.
 */
@CustomAnnotationsBase.RequiresDependency(requiresDependency = CustomAnnotationsBase.Dependencies.AndroidSupport_Design)
public class MultipurposeChoiceDialog extends AlertDialog implements
		TextWatcher, View.OnClickListener, CustomClickCallbackLink, CustomLongClickCallbackLink {
	
	private Context context;
	
	private boolean dataHasBeenSet, timerBeingRan;
	
	private Timer timer;
	private String title;
	private String value = "";
	private String[] values;
	private String[] selectedValues;
	private TextView et;
	
	//Data values (converted)
	private List<MultipurposeChoiceAdapter.MultipurposeChoiceObject> dataValues;
	
	//Adapters
	private MultipurposeChoiceAdapter adapter;
	
	//private EditText dialog_filter;
	private View separator2, separator;
	private TextView multipurpose_choice_dialog_top_tv;
	private RecyclerView multipurpose_choice_dialog_recyclerview;
	private LinearLayout multipurpose_choice_dialog_bottom_layout;
	private RelativeLayout multipurpose_choice_dialog_top_layout;
	private Button multipurpose_choice_dialog_cancel_button, multipurpose_choice_dialog_confirm_button;
	
	private MultipurposeChoiceAdapter.MultipurposeChoiceType type;
	private OnTaskCompleteListener listener;
	
	private Map<String, Integer> userSelectedItems;
	
	private int selectedIconImageResource, unselectedIconImageResource;

	@CustomAnnotationsBase.RequiresDependency(requiresDependency = CustomAnnotationsBase.Dependencies.AndroidSupport_Design)
	public MultipurposeChoiceDialog(@NonNull Context context,
	                                @Nullable String title,
	                                @NonNull String[] values,
	                                @NonNull OnTaskCompleteListener listener,
	                                @NonNull MultipurposeChoiceAdapter.MultipurposeChoiceType type,
	                                @Nullable Integer selectedIconImageResource,
	                                @Nullable Integer unselectedIconImageResource) {
		super(context);
		this.title = title;
		this.values = values;
		this.listener = listener;
		this.type = type;
		this.selectedIconImageResource = (selectedIconImageResource == null)
				? 0 : selectedIconImageResource;
		this.unselectedIconImageResource = (unselectedIconImageResource == null)
				? 0 : unselectedIconImageResource;
	}

	@CustomAnnotationsBase.RequiresDependency(requiresDependency = CustomAnnotationsBase.Dependencies.AndroidSupport_Design)
	public MultipurposeChoiceDialog(@NonNull Context context,
	                                @Nullable String title,
	                                @NonNull List<String> values,
	                                @NonNull OnTaskCompleteListener listener,
	                                @NonNull MultipurposeChoiceAdapter.MultipurposeChoiceType type,
	                                @Nullable Integer selectedIconImageResource,
	                                @Nullable Integer unselectedIconImageResource) {
		super(context);
		this.title = title;
		this.values = values.toArray(new String[values.size()]);
		this.listener = listener;
		this.type = type;
		this.selectedIconImageResource = (selectedIconImageResource == null)
				? 0 : selectedIconImageResource;
		this.unselectedIconImageResource = (unselectedIconImageResource == null)
				? 0 : unselectedIconImageResource;
	}

	@CustomAnnotationsBase.RequiresDependency(requiresDependency = CustomAnnotationsBase.Dependencies.AndroidSupport_Design)
	public MultipurposeChoiceDialog(@NonNull Context context,
	                                @Nullable String title,
	                                @NonNull String[] values,
	                                @NonNull String[] selectedValues,
	                                @NonNull OnTaskCompleteListener listener,
	                                @NonNull MultipurposeChoiceAdapter.MultipurposeChoiceType type,
	                                @Nullable Integer selectedIconImageResource,
	                                @Nullable Integer unselectedIconImageResource) {
		super(context);
		this.title = title;
		this.values = values;
		this.selectedValues = selectedValues;
		this.listener = listener;
		this.type = type;
		this.selectedIconImageResource = (selectedIconImageResource == null)
				? 0 : selectedIconImageResource;
		this.unselectedIconImageResource = (unselectedIconImageResource == null)
				? 0 : unselectedIconImageResource;
	}

	@CustomAnnotationsBase.RequiresDependency(requiresDependency = CustomAnnotationsBase.Dependencies.AndroidSupport_Design)
	public MultipurposeChoiceDialog(@NonNull Context context,
	                                @Nullable String title,
	                                @NonNull List<String> values,
	                                @Nullable List<String> selectedValues,
	                                @NonNull OnTaskCompleteListener listener,
	                                @NonNull MultipurposeChoiceAdapter.MultipurposeChoiceType type,
	                                @Nullable Integer selectedIconImageResource,
	                                @Nullable Integer unselectedIconImageResource) {
		super(context);
		this.title = title;
		this.selectedValues = selectedValues.toArray(new String[selectedValues.size()]);
		this.values = values.toArray(new String[values.size()]);
		this.listener = listener;
		this.type = type;
		this.selectedIconImageResource = (selectedIconImageResource == null)
				? 0 : selectedIconImageResource;
		this.unselectedIconImageResource = (unselectedIconImageResource == null)
				? 0 : unselectedIconImageResource;
	}

	public void setRecyclerviewScrollbarVisible(boolean showScrollbar){
		if(multipurpose_choice_dialog_recyclerview != null){
			if(!showScrollbar) {
				if(Build.VERSION.SDK_INT >= 16) {
					multipurpose_choice_dialog_recyclerview.setScrollBarSize(0);
				}
			}
		}
	}

	public void setRecyclerviewScrollbarFade(boolean shouldFade){
		if(multipurpose_choice_dialog_recyclerview != null){
			multipurpose_choice_dialog_recyclerview.setVerticalFadingEdgeEnabled(shouldFade);
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multipurpose_choice_dialog_fragment);
		initVariables();
		
		multipurpose_choice_dialog_top_tv = (TextView) this.findViewById(
				R.id.multipurpose_choice_dialog_top_tv);
		multipurpose_choice_dialog_recyclerview = (RecyclerView) this.findViewById(
				R.id.multipurpose_choice_dialog_recyclerview);
		multipurpose_choice_dialog_bottom_layout = (LinearLayout) this.findViewById(
				R.id.multipurpose_choice_dialog_bottom_layout);
		multipurpose_choice_dialog_top_layout = (RelativeLayout) this.findViewById(
				R.id.multipurpose_choice_dialog_top_layout);
		multipurpose_choice_dialog_cancel_button = (Button) this.findViewById(
				R.id.multipurpose_choice_dialog_cancel_button);
		multipurpose_choice_dialog_confirm_button = (Button) this.findViewById(
				R.id.multipurpose_choice_dialog_confirm_button);
		separator2 = (View) this.findViewById(R.id.separator2);
		separator = (View) this.findViewById(R.id.separator);
		
		multipurpose_choice_dialog_cancel_button.setTransformationMethod(null);
		multipurpose_choice_dialog_confirm_button.setTransformationMethod(null);
		multipurpose_choice_dialog_cancel_button.setTag("multipurpose_choice_dialog_cancel_button");
		multipurpose_choice_dialog_confirm_button.setTag("multipurpose_choice_dialog_confirm_button");
		multipurpose_choice_dialog_cancel_button.setOnClickListener(this);
		multipurpose_choice_dialog_confirm_button.setOnClickListener(this);
		
		//set ui fields
		if(type == MultipurposeChoiceAdapter.MultipurposeChoiceType.SINGLE_SELECT){
			//multipurpose_choice_dialog_top_tv.setText(R.string.select_one_option);
			multipurpose_choice_dialog_top_tv.setText(""); // TODO: 2017-10-18
			multipurpose_choice_dialog_top_tv.setVisibility(View.GONE);
		} else if (type == MultipurposeChoiceAdapter.MultipurposeChoiceType.MULTI_SELECT){
			//multipurpose_choice_dialog_top_tv.setText(R.string.select_your_options);
			multipurpose_choice_dialog_top_tv.setText(""); // TODO: 2017-10-18
			multipurpose_choice_dialog_top_tv.setVisibility(View.GONE);
		}
		
		separator.setVisibility(View.GONE);
		separator2.setVisibility(View.GONE);
		
		//Recyclerview
		this.multipurpose_choice_dialog_recyclerview.setLayoutManager(
				new LinearLayoutManager(this.context));
		if(MiscUtilities.isArrayNullOrEmpty(this.selectedValues)){
			this.dataValues = MultipurposeChoiceAdapter.buildSimpleObjectList(
					Arrays.asList(this.values));
		} else {
			this.dataValues = MultipurposeChoiceAdapter.buildSimpleObjectList(
					Arrays.asList(this.values), Arrays.asList(this.selectedValues));
		}
		
		if(type == MultipurposeChoiceAdapter.MultipurposeChoiceType.SINGLE_SELECT){
			this.adapter = new MultipurposeChoiceAdapter(context,
					MultipurposeChoiceAdapter.MultipurposeChoiceType.SINGLE_SELECT,
					this, this, selectedIconImageResource, unselectedIconImageResource);
			this.adapter.setListObjects(this.dataValues);
		} else if (type == MultipurposeChoiceAdapter.MultipurposeChoiceType.MULTI_SELECT){
			this.adapter = new MultipurposeChoiceAdapter(context,
					MultipurposeChoiceAdapter.MultipurposeChoiceType.MULTI_SELECT,
					this, this, selectedIconImageResource, unselectedIconImageResource);
			this.adapter.setListObjects(this.dataValues);
		}
		multipurpose_choice_dialog_recyclerview.setAdapter(adapter);
		
		//Bottom Layout
		if(type == MultipurposeChoiceAdapter.MultipurposeChoiceType.SINGLE_SELECT){
			multipurpose_choice_dialog_bottom_layout.setVisibility(View.GONE);
		} else if(type == MultipurposeChoiceAdapter.MultipurposeChoiceType.MULTI_SELECT){
			multipurpose_choice_dialog_bottom_layout.setVisibility(View.VISIBLE);
		}
	}
	
	private void initVariables(){
		this.dataHasBeenSet = false;
		this.timerBeingRan = false;
		if(this.context == null) {
			this.context = getContext();
		}
		if(StringUtilities.isNullOrEmpty(title)) {
			this.title = "";
		}
		if(MiscUtilities.isArrayNullOrEmpty(values)){
			values = new String[]{};
		}
		if(this.type == null) {
			this.type = MultipurposeChoiceAdapter.MultipurposeChoiceType.SINGLE_SELECT;
		}
		if(this.listener == null) {
			this.listener = null;
		}
		this.userSelectedItems = new HashMap<>();
	}
	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {}
	@Override
	public void afterTextChanged(Editable s) {
		ArrayList<String> list = new ArrayList<String>();
		for (String value : values) {
			if (value.toLowerCase().contains(s.toString().toLowerCase())) {
				list.add(value);
			}
		}
		if (list.size() == 0) {
			//dialog_filter.setText(value);
			//dialog_filter.setSelection(dialog_filter.getText().length());
		} else if (s.toString().length() > value.length()) {
			value = s.toString();
			
		} else {
			value = s.toString();
			
		}
	}
	
	
	@Override
	public void show() {
		try {
			this.getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
					android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} catch (Exception e){
			e.printStackTrace();
		}
		super.show();
		
	}
	
	@Override
	public void onClick(View v) {
		String tag = null;
		try {
			tag = (String) v.getTag();
		} catch (Exception e){
			return;
		}
		if(!StringUtilities.isNullOrEmpty(tag)) {
			if (tag.equals("multipurpose_choice_dialog_cancel_button")) {
				this.dismiss();
				return;
			}
			if (tag.equals("multipurpose_choice_dialog_confirm_button")) {
				this.dismiss();
				LinkedHashMap toReturn = HashMapUtilities.sortMapValues(userSelectedItems);
				listener.onTaskComplete(toReturn, PGMacTipsConstants.TAG_MAP_STRING_INTEGER);
				return;
			}
		}
	}
	
	/**
	 * Click Listener
	 * @param object Object
	 * @param customTag Object(s) being sent back. if you want, you can use if(obj instanceof XX)
	 *                  and use that as a separator.
	 * @param positionIfAvailable int position. This will be sent back as not null when there is
	 *                            a reason to include it. That reason may be that the item selected
	 *                            was chosen from a listview/ Recyclerview, in which case the
	 *                            position can be helpful. If this is not set, it will send back
	 */
	@Override
	public void itemClicked(Object object, Integer customTag, Integer positionIfAvailable) {
		
		if(timerBeingRan){
			return;
		}
		setupTimer();
		if(customTag != null){
			if(customTag == PGMacTipsConstants.TAG_MULTIPURPOSE_CHOICE_CLICK_ADAPTER){
				MultipurposeChoiceAdapter.MultipurposeChoiceObject obj =
						(MultipurposeChoiceAdapter.MultipurposeChoiceObject) object;
				if(this.dataValues == null){
					this.dataValues = new ArrayList<>();
				}
				if(positionIfAvailable == null){
					positionIfAvailable = 0;
				}
				// TODO: 2017-06-16 decide on using whether to use pos instead for better performance
				String description = obj.getDescription();
				if(this.type == MultipurposeChoiceAdapter.MultipurposeChoiceType.SINGLE_SELECT){
					if(obj.isSelected()){
						//Do nothing, already selected
					} else {
						if(this.listener != null){
							this.listener.onTaskComplete(description, PGMacTipsConstants.TAG_STRING);
							this.dismiss();
						}
					}
				} else if(this.type == MultipurposeChoiceAdapter.MultipurposeChoiceType.MULTI_SELECT){
					if(obj.isSelected()){
						//Unselect and update list
						try {
							MultipurposeChoiceAdapter.MultipurposeChoiceObject o =
									this.dataValues.get(positionIfAvailable);
							o.setSelected(false);
							adapter.updateOneObject(positionIfAvailable, o);
							userSelectedItems.remove(description);
						} catch (Exception e){
							e.printStackTrace();
						}
					} else {
						//Select and update list
						try {
							MultipurposeChoiceAdapter.MultipurposeChoiceObject o =
									this.dataValues.get(positionIfAvailable);
							o.setSelected(true);
							adapter.updateOneObject(positionIfAvailable, o);
							userSelectedItems.put(description, positionIfAvailable);
						} catch (Exception e){
							e.printStackTrace();
						}
					}
				}
				
				
			}
		}
	}
	
	/**
	 * Long click listener
	 * @param object Object(s) being sent back. if you want, you can use if(obj instanceof XX)
	 *                  and use that as a separator.
	 * @param customTag The integer custom tag. Use this for sending back specific tags that you
	 *                  want to reference and differentiate between.
	 * @param positionIfAvailable int position. This will be sent back as not null when there is
	 *                            a reason to include it. That reason may be that the item selected
	 *                            was chosen from a listview/ Recyclerview, in which case the
	 *                            position can be helpful. If this is not set, it will send back
	 */
	@Override
	public void itemLongClicked(Object object, Integer customTag, Integer positionIfAvailable) {
		//L.m("item long clicked -- POS ==" + positionIfAvailable);
	}
	
	private void setupTimer(){
		if(timer == null){
			timer = new Timer();
		}
		timer.cancel();
		timer = new Timer();
		timerBeingRan = true;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//This is in place to prevent the system from double clicking by mistake
				timerBeingRan = false;
			}
		}, 250);
	}
}