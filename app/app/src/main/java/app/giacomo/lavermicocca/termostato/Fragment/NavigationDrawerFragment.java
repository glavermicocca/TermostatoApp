package app.giacomo.lavermicocca.termostato.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.giacomo.lavermicocca.termostato.ActivityDashboard;
import app.giacomo.lavermicocca.termostato.Adapter.MenuAdapter;
import app.giacomo.lavermicocca.termostato.Bean.Information;
import app.giacomo.lavermicocca.termostato.R;
import app.giacomo.lavermicocca.termostato.Utils.RecyclerItemClickListener;
import app.giacomo.lavermicocca.termostato.Utils.TextViewFont;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String PREF_FILE_NAME="testpref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";
    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private MenuAdapter adapter;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private boolean isDrawerOpened=false;
    //private ActivityDashboard activity;
    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    public static List<Information> getData(){
        //load only static data inside a drawer
        List<Information> data=new ArrayList<>();
        int[] icons={R.string.icon_clock,
                     R.string.icon_calendar,
                     R.string.icon_dashboard,
                     R.string.icon_text};
        String[] titles={"Sensors Clock","Schedule","Dashboard","Display text"};
        for(int i=0;i<titles.length && i<icons.length;i++)
        {
            Information current=new Information();
            current.iconId=icons[i];
            current.title=titles[i];
            data.add(current);
        }
        return data;
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer= Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView= (RecyclerView) layout.findViewById(R.id.drawerList);
        final List<Information> data = getData();
        adapter=new MenuAdapter(getActivity(),data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        TextViewFont tvProfilo = (TextViewFont) layout.findViewById(R.id.profilo);
        tvProfilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextViewFont tvPassword = (TextViewFont) layout.findViewById(R.id.password);
        tvPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextViewFont tvEsci = (TextViewFont) layout.findViewById(R.id.esci);
        tvEsci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        mDrawerLayout.closeDrawers();
                        ActivityDashboard activityDashboard = (ActivityDashboard) getActivity();
                        switch (position)
                        {
                            case 0:
                                activityDashboard.goToSensorsClock();
                                break;
                            case 1:
                                activityDashboard.goToCalendar();
                                break;
                            case 2:
                                activityDashboard.goToDashboard();
                                break;
                            case 3:
                                activityDashboard.goToDisplayText();
                                break;
                        }
                    }}
                ));

        return layout;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView=getActivity().findViewById(fragmentId);
        //this.activity = activity.getApplicationContext();
        mDrawerLayout=drawerLayout;
        mDrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset<0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
//        if(!mUserLearnedDrawer&& !mFromSavedInstanceState){
//            mDrawerLayout.openDrawer(containerView);
//        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        //mDrawerLayout.closeDrawers();
    }
}
