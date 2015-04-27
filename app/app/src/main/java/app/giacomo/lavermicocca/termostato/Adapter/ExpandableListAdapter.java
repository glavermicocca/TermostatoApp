package app.giacomo.lavermicocca.termostato.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import app.giacomo.lavermicocca.termostato.Bean.ScheduleBean;
import app.giacomo.lavermicocca.termostato.Bean.ScheduleItemBean;
import app.giacomo.lavermicocca.termostato.R;

/**
 * Created by Giacomo on 27/04/2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<String>> _listDataChild;
        private ScheduleBean scheduleBean;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData, ScheduleBean scheduleBean) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
            this.scheduleBean = scheduleBean;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        private List<ScheduleItemBean> getScheduleItemBean(int groupPosition)
        {
            List<ScheduleItemBean> scheduleItemBean = null;
            switch (groupPosition)
            {
                case 0:
                    return (List<ScheduleItemBean>) this.scheduleBean.getMonday();
                case 1:
                    return (List<ScheduleItemBean>) this.scheduleBean.getTuesday();
                case 2:
                    return (List<ScheduleItemBean>) this.scheduleBean.getWednesday();
                case 3:
                    return (List<ScheduleItemBean>) this.scheduleBean.getThursday();
                case 4:
                    return (List<ScheduleItemBean>) this.scheduleBean.getFriday();
                case 5:
                    return (List<ScheduleItemBean>) this.scheduleBean.getSaturday();
                case 6:
                    return (List<ScheduleItemBean>) this.scheduleBean.getSunday();
            }
            return scheduleItemBean;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = (String) getChild(groupPosition, childPosition);
            final List<ScheduleItemBean> scheduleItemBean = getScheduleItemBean(groupPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item, null);
            }

            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
            txtListChild.setText(childText);

            TableLayout tableLayout = (TableLayout) convertView.findViewById(R.id.table_layout_schedule);

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
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group, null);
            }

            TextView lblListHeader = (TextView) convertView.findViewById(R.id.title_group);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

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