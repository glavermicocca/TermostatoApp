package app.giacomo.lavermicocca.termostato.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import app.giacomo.lavermicocca.termostato.Bean.ScheduleItemBean;
import app.giacomo.lavermicocca.termostato.Business.ScheduleBusiness;
import app.giacomo.lavermicocca.termostato.Fragment.CustomPickerSchedule;
import app.giacomo.lavermicocca.termostato.R;

/**
 * Created by Giacomo on 27/04/2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<ScheduleItemBean>> _listDataChild;
        private ScheduleBusiness scheduleBusiness;
        private FragmentManager fm;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<ScheduleItemBean>> listChildData, ScheduleBusiness scheduleBusiness, FragmentManager fm) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
            this.scheduleBusiness = scheduleBusiness;
            this.fm = fm;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            final ScheduleItemBean scheduleItemBean = (ScheduleItemBean) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item, null);
            }

            TextView tvStartTime = (TextView) convertView.findViewById(R.id.start_time);
            tvStartTime.setText(scheduleItemBean.getStartTime());
            TextView tvEndTime = (TextView) convertView.findViewById(R.id.end_time);
            tvEndTime.setText(scheduleItemBean.getEndTIme());
            TextView tvTemperature = (TextView) convertView.findViewById(R.id.temperature);
            tvTemperature.setText(scheduleItemBean.getTemperature() + "°");

//            if(childPosition > 0)
//            {
//                LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.LinearLayout_header);
//                linearLayout.setVisibility(View.GONE);
//            }

            final com.shamanland.fonticon.FontIconView fontIconView = (com.shamanland.fonticon.FontIconView) convertView.findViewById(R.id.remove);
            fontIconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<ScheduleItemBean> sib = _listDataChild.get(_listDataHeader.get(groupPosition));
                    sib.remove(childPosition);
                    ExpandableListAdapter.this.notifyDataSetInvalidated();
                    //rimuovo dalla lista immediatamente
                    scheduleBusiness.removeSchedule(_listDataHeader.get(groupPosition).toLowerCase(),""+childPosition);
                }
            });

            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group, null);
            }

            TextView lblListHeader = (TextView) convertView.findViewById(R.id.title_group);
            lblListHeader.setText(headerTitle);

            com.shamanland.fonticon.FontIconView buttonIcon = (com.shamanland.fonticon.FontIconView) convertView.findViewById(R.id.button_add);
            buttonIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String day = _listDataHeader.get(groupPosition);
                    CustomPickerSchedule customNotifyReshop = CustomPickerSchedule.newInstance(day, groupPosition);
                    customNotifyReshop.show(fm, "day_picker");
                }
            });

            LinearLayout linearLayout = (LinearLayout)convertView.findViewById(R.id.linear_layout_header);
            if(isExpanded)
            {
                linearLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                linearLayout.setVisibility(View.GONE);
            }

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
}