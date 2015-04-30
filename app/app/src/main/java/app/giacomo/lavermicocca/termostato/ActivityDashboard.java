package app.giacomo.lavermicocca.termostato;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;

import app.giacomo.lavermicocca.termostato.Bean.ScheduleItemBean;
import app.giacomo.lavermicocca.termostato.Fragment.CustomPickerSchedule;
import app.giacomo.lavermicocca.termostato.Fragment.Schedule;
import app.giacomo.lavermicocca.termostato.Fragment.Dashboard;
import app.giacomo.lavermicocca.termostato.Fragment.DisplayText;
import app.giacomo.lavermicocca.termostato.Fragment.NavigationDrawerFragment;
import app.giacomo.lavermicocca.termostato.Fragment.SensorsClock;

public class ActivityDashboard extends BaseActionBarActivity implements
                                                              SensorsClock.OnFragmentInteractionListener,
                                                              Schedule.OnFragmentInteractionListener,
                                                              Dashboard.OnFragmentInteractionListener,
                                                              DisplayText.OnFragmentInteractionListener,
        CustomPickerSchedule.OnFragmentCustomPickerInteractionListener {

    public FragmentManager fm;

    public ActivityDashboard() {
        super(R.layout.activity_dashboard, R.string.title_not_set, null, R.string.title_empty, "", ToolbarType.HAMBURGER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fm = getSupportFragmentManager();

        final NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)fm.findFragmentById(R.id.fragment_navigation_drawer);
        final DrawerLayout mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, mDrawerLayout, this.getToolbar());


    }//onCreate

    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View v = super.onCreateView(name, context, attrs);

        return v;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // vado alla dashboard (homepage)
        goToSensorsClock();
    }//onResume

    /**
     * cancella la storia (questo nel caso in cui ho scansionato un nuovo NFC)
     *
     * @param fm
     */
    private void clearHistoryFragments(FragmentManager fm) {
        if (fm != null) {
            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
        }
    }

    /**
     * SENSORS CLOCK
     */
    public void goToSensorsClock() {
        if (fm != null) {
            clearHistoryFragments(fm);

            // Perform the FragmentTransaction to load in the list tab content.
            // Using FragmentTransaction#replace will destroy any Fragments
            // currently inside R.id.fragment_content and add the new Fragment
            // in its place.

            FragmentTransaction ft = fm.beginTransaction();
            Fragment fr = SensorsClock.newInstance("", "");
            ft.addToBackStack("goToSensorsClock");
            ft.replace(R.id.relLayout, fr);
            ft.commit();
        }
    }//goToSensorsClock

    /**
     * CALENDAR
     */
    public void goToCalendar() {
        if (fm != null) {
            clearHistoryFragments(fm);

            // Perform the FragmentTransaction to load in the list tab content.
            // Using FragmentTransaction#replace will destroy any Fragments
            // currently inside R.id.fragment_content and add the new Fragment
            // in its place.

            FragmentTransaction ft = fm.beginTransaction();
            Fragment fr = Schedule.newInstance("", "");
            ft.addToBackStack("goToCalendar");
            ft.replace(R.id.relLayout, fr);
            ft.commit();
        }
    }//goToCalendar

    /**
     * DASHBOARD
     */
    public void goToDashboard() {
        if (fm != null) {
            clearHistoryFragments(fm);

            // Perform the FragmentTransaction to load in the list tab content.
            // Using FragmentTransaction#replace will destroy any Fragments
            // currently inside R.id.fragment_content and add the new Fragment
            // in its place.

            FragmentTransaction ft = fm.beginTransaction();
            Fragment fr = Dashboard.newInstance("", "");
            ft.addToBackStack("goToDashboard");
            ft.replace(R.id.relLayout, fr);
            ft.commit();
        }
    }//goToDashboard

    /**
     * DISPLAY TEXT
     */
    public void goToDisplayText() {
        if (fm != null) {
            clearHistoryFragments(fm);

            // Perform the FragmentTransaction to load in the list tab content.
            // Using FragmentTransaction#replace will destroy any Fragments
            // currently inside R.id.fragment_content and add the new Fragment
            // in its place.

            FragmentTransaction ft = fm.beginTransaction();
            Fragment fr = DisplayText.newInstance("", "");
            ft.addToBackStack("goToDisplayText");
            ft.replace(R.id.relLayout, fr);
            ft.commit();
        }
    }//goToDisplayText

    //-------------------------- CALLBACK FROM FRAGMENTS -------------------------------------------

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onClickGoToCard(View view)
    {
        ActivityDashboard.this.goToSensorsClock();
    }

    @Override
    public void onFragmentCustomPickerInteraction(String customPickerName, ScheduleItemBean scheduleItemBean) {

    }

    //---------------- SETUP VIEW -----------------------
}
