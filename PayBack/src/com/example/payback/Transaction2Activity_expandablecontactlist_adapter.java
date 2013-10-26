package com.example.payback;

import java.util.List;

import com.example.payback.Transaction2Activity_contactlist_adapter.ViewHolder;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class Transaction2Activity_expandablecontactlist_adapter extends BaseExpandableListAdapter {

  private final SparseArray<Transaction2Activity_expandablecontactlist_group> groups;
  private LayoutInflater inflater;
  private Activity activity;
//  private List<Friend> list;

  public Transaction2Activity_expandablecontactlist_adapter(Activity act, SparseArray<Transaction2Activity_expandablecontactlist_group> groups) {
    activity = act;
    this.groups = groups;
    inflater = act.getLayoutInflater();
  }
  
  static class ViewHolder {
	    protected TextView text;
	    protected CheckBox checkbox;
  }
  
  @Override
  public Friend getChild(int groupPosition, int childPosition) {
    return groups.get(groupPosition).children.get(childPosition);
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return 0;
  }

  @Override
  public View getChildView(int groupPosition, final int childPosition,
      boolean isLastChild, View convertView, ViewGroup parent) {
    final String children = getChild(groupPosition, childPosition).Friendtostring();
    TextView text = null;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.activity_transaction2_contactlist, null);
    }
    text = (TextView) convertView.findViewById(R.id.contactname);
    text.setText(children);
    convertView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(activity, children,
            Toast.LENGTH_SHORT).show();
      }
    });
    return convertView;
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return groups.get(groupPosition).children.size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return groups.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return groups.size();
  }

  @Override
  public void onGroupCollapsed(int groupPosition) {
    super.onGroupCollapsed(groupPosition);
  }

  @Override
  public void onGroupExpanded(int groupPosition) {
    super.onGroupExpanded(groupPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
    return 0;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	  
	  View view = null;
	    if (convertView == null) {
	      LayoutInflater inflator = context.getLayoutInflater();
	      view = inflator.inflate(R.layout.activity_transaction2_contactlist, null);
	      final ViewHolder viewHolder = new ViewHolder();
	      viewHolder.text = (TextView) view.findViewById(R.id.contactname);
	      viewHolder.checkbox = (CheckBox) view.findViewById(R.id.checkBox);
	      viewHolder.checkbox
	          .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

	            @Override
	            public void onCheckedChanged(CompoundButton buttonView,
	                boolean isChecked) {
	            	Friend element = (Friend) viewHolder.checkbox
	                  .getTag();
	              element.setSelected(buttonView.isChecked());

	            }
	          });
	      view.setTag(viewHolder);
	      viewHolder.checkbox.setTag(list.get(groupPosition));
	    } else {
	      view = convertView;
	      ((ViewHolder) view.getTag()).checkbox.setTag(list.get(groupPosition));
	    }
	    ViewHolder holder = (ViewHolder) view.getTag();
	    holder.text.setText(list.get(groupPosition).getfName());
	    holder.checkbox.setChecked(list.get(groupPosition).isSelected());

//    if (convertView == null) {
//      convertView = inflater.inflate(R.layout.activity_transaction2_expandablecontactlist_group, null);
//    }
    Transaction2Activity_expandablecontactlist_group group = (Transaction2Activity_expandablecontactlist_group) getGroup(groupPosition);
    ((CheckedTextView) convertView).setText(group.string);
    ((CheckedTextView) convertView).setChecked(isExpanded);
//    return convertView;
	    
	    return view;

  }
  


  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return false;
  }
} 