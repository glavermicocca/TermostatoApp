package app.giacomo.lavermicocca.termostato.Fragment;

/**
 * Created by Giacomo on 09/01/2015.
 */

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shamanland.fonticon.FontIconView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.giacomo.lavermicocca.termostato.Bean.ScheduleItemBean;
import app.giacomo.lavermicocca.termostato.R;

public class CustomPickerSchedule extends DialogFragment
{
    EditText editText;
    TextView textViewWindowTitle;
    public static String VIEW_TARGET = "view_target";
    ScheduleItemBean customBeanList;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    private FragmentActivity myContext;

    private static final String TITLE_WINDOW_TEXT = "titleWindowText";

    private String titleWindowText;

    private String customPickerName;

    private OnFragmentCustomPickerInteractionListener mListener;

    /**
     *
     * @param titleWindowText
     * @return
     */
    // TODO: Rename and change types and number of parameters
    public static CustomPickerSchedule newInstance(String titleWindowText) {
        CustomPickerSchedule fragment = new CustomPickerSchedule();
        Bundle args = new Bundle();
        args.putString(TITLE_WINDOW_TEXT, titleWindowText);
        fragment.setArguments(args);
        return fragment;
    }

    public CustomPickerSchedule() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titleWindowText = getArguments().getString(TITLE_WINDOW_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.custom_picker_schedule, container, false);

        setupView(view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentCustomPickerInteraction("",null);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        this.myContext=(FragmentActivity) activity;
        super.onAttach(activity);
        try {
            mListener = (OnFragmentCustomPickerInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentCustomPickerInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentCustomPickerInteraction(String customPickerName, ScheduleItemBean scheduleItemBean);
    }

    private void setupView(View view)
    {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        textViewWindowTitle = (TextView) view.findViewById(R.id.title_window);
        textViewWindowTitle.setText(titleWindowText);

        com.shamanland.fonticon.FontIconView timeStartUp = (FontIconView) view.findViewById(R.id.button_start_time_up);
        timeStartUp.setOnTouchListener(touchListenerUp);

        com.shamanland.fonticon.FontIconView timeStartDown = (FontIconView) view.findViewById(R.id.button_start_time_down);
        timeStartDown.setOnTouchListener(touchListenerDown);

        com.shamanland.fonticon.FontIconView timeStartUpHours = (FontIconView) view.findViewById(R.id.button_start_time_hours_up);
        timeStartUpHours.setOnTouchListener(touchListenerUpHours);

        com.shamanland.fonticon.FontIconView timeStartDownHours = (FontIconView) view.findViewById(R.id.button_start_time_hours_down);
        timeStartDownHours.setOnTouchListener(touchListenerDownHours);


        com.shamanland.fonticon.FontIconView timeEndUp = (FontIconView) view.findViewById(R.id.button_end_time_up);
        timeEndUp.setOnTouchListener(touchListenerUp);

        com.shamanland.fonticon.FontIconView timeEndDown = (FontIconView) view.findViewById(R.id.button_end_time_down);
        timeEndDown.setOnTouchListener(touchListenerDown);

        com.shamanland.fonticon.FontIconView timeEndUpHours = (FontIconView) view.findViewById(R.id.button_end_time_hours_up);
        timeEndUpHours.setOnTouchListener(touchListenerUpHours);

        com.shamanland.fonticon.FontIconView timeEndDownHours = (FontIconView) view.findViewById(R.id.button_end_time_hours_down);
        timeEndDownHours.setOnTouchListener(touchListenerDownHours);


        com.shamanland.fonticon.FontIconView temperatureUp = (FontIconView) view.findViewById(R.id.button_temperature_up);
        temperatureUp.setOnTouchListener(touchListenerTemperatureUp);
        com.shamanland.fonticon.FontIconView temperatureDown = (FontIconView) view.findViewById(R.id.button_temperature_down);
        temperatureDown.setOnTouchListener(touchListenerTemperatureDown);

    }//setupView

    private View.OnTouchListener touchListenerTemperatureUp = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RelativeLayout p = (RelativeLayout) v.getParent();
            TextView tv = (TextView) p.findViewWithTag(VIEW_TARGET);
            int currentTemperature = Integer.parseInt(""+tv.getText()) + 1;
            tv.setText("" + currentTemperature);
            return false;
        }
    };

    private View.OnTouchListener touchListenerTemperatureDown = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RelativeLayout p = (RelativeLayout) v.getParent();
            TextView tv = (TextView) p.findViewWithTag(VIEW_TARGET);
            int currentTemperature = Integer.parseInt(""+tv.getText()) - 1;
            tv.setText("" + currentTemperature);
            return false;
        }
    };

    private View.OnTouchListener touchListenerUp = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RelativeLayout p = (RelativeLayout) v.getParent();
            TextView tv = (TextView) p.findViewWithTag(VIEW_TARGET);

            tv.setText(setTimeUp("" + tv.getText(), (long)60000));
            return false;
        }
    };

    private View.OnTouchListener touchListenerDown = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RelativeLayout p = (RelativeLayout) v.getParent();
            TextView tv = (TextView) p.findViewWithTag(VIEW_TARGET);

            tv.setText(setTimeDown("" + tv.getText(), (long)60000));
            return false;
        }
    };

    private View.OnTouchListener touchListenerUpHours = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RelativeLayout p = (RelativeLayout) v.getParent();
            TextView tv = (TextView) p.findViewWithTag(VIEW_TARGET);

            tv.setText(setTimeUp("" + tv.getText(), (long)60000*60));
            return false;
        }
    };

    private View.OnTouchListener touchListenerDownHours = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RelativeLayout p = (RelativeLayout) v.getParent();
            TextView tv = (TextView) p.findViewWithTag(VIEW_TARGET);

            tv.setText(setTimeDown("" + tv.getText(), (long)60000*60));
            return false;
        }
    };

    private String setTimeUp(String currentDate, Long span)
    {
        try {
            Date date = new SimpleDateFormat("HH:mm").parse(currentDate);
            Long millis = date.getTime();
            date.setTime(millis + span);
            return new SimpleDateFormat("HH:mm").format(date);
        } catch (ParseException e) {
            Log.e("", e.getLocalizedMessage());
        }
        return "00:00";
    }//setTimeUp

    private String setTimeDown(String currentDate, Long span)
    {
        try {
            Date date = new SimpleDateFormat("HH:mm").parse(currentDate);
            Long millis = date.getTime();
            date.setTime(millis - span);
            return new SimpleDateFormat("HH:mm").format(date);
        } catch (ParseException e) {
            Log.e("", e.getLocalizedMessage());
        }
        return "00:00";
    }//setTimeUp
}