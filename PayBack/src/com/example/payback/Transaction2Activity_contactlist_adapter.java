package com.example.payback;
/*
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Transaction2Activity_contactlist_adapter extends ArrayAdapter<Friend> {

  private final List<Friend> list;
  private final Activity context;

  public Transaction2Activity_contactlist_adapter(Activity context, List<Friend> list) {
    super(context, R.layout.activity_transaction2_contactlist, list);
    this.context = context;
    this.list = list;
  }

  static class ViewHolder {
    protected TextView text;
    protected CheckBox checkbox;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
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
      viewHolder.checkbox.setTag(list.get(position));
    } else {
      view = convertView;
      ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
    }
    ViewHolder holder = (ViewHolder) view.getTag();
    holder.text.setText(list.get(position).getfName());
    holder.checkbox.setChecked(list.get(position).isSelected());
    return view;
  }
} 
*/
