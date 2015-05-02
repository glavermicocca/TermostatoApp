package app.giacomo.lavermicocca.termostato.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.Calendar;

import app.giacomo.lavermicocca.termostato.ActivityDashboard;
import app.giacomo.lavermicocca.termostato.Business.DashboardBusiness;
import app.giacomo.lavermicocca.termostato.R;

public class Dashboard extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Report.
     */
    // TODO: Rename and change types and number of parameters
    public static Dashboard newInstance(String param1, String param2) {
        Dashboard fragment = new Dashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Dashboard() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        setupView(view);

        return view;
    }//onCreateView

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
            dashboardBusiness = new DashboardBusiness(activity);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    //------------ VIEW INITS -----------------

    Context mContext;
    DashboardBusiness dashboardBusiness;

    private void setupView(View view)
    {
        ((ActivityDashboard)getActivity()).setTitleResource(R.string.Sensors_Clock);
        this.mContext = getActivity().getApplicationContext();

        CheckBox checkBoxHeatingSystem = (CheckBox) view.findViewById(R.id.check_box_heating_system_on_off);
        checkBoxHeatingSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardBusiness.setSwitchOnOff();
            }
        });

        final CheckBox checkBoxSeasonSummer = (CheckBox) view.findViewById(R.id.check_box_season_summer);
        final CheckBox checkBoxSeasonWinter = (CheckBox) view.findViewById(R.id.check_box_season_winter);

        checkBoxSeasonSummer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSeasonWinter.setChecked(false);
                dashboardBusiness.setSeason("Summer");
            }
        });

        checkBoxSeasonWinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSeasonSummer.setChecked(false);
                dashboardBusiness.setSeason("Winter");
            }
        });

        final CheckBox checkBoxDisplayOnOff = (CheckBox) view.findViewById(R.id.check_box_display_on_off);
        final CheckBox checkBoxTempUmid = (CheckBox) view.findViewById(R.id.check_box_display_temperature_humidity);
        final CheckBox checkBoxTempCustomText = (CheckBox) view.findViewById(R.id.check_box_display_custom_text);
        checkBoxDisplayOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardBusiness.setDisplayLight("alwaysOn=true");
                checkBoxTempUmid.setChecked(false);
                checkBoxTempCustomText.setChecked(false);
            }
        });
        checkBoxTempUmid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardBusiness.setDisplayLight("tempUmid=true");
                checkBoxTempCustomText.setChecked(false);
                checkBoxDisplayOnOff.setChecked(false);
            }
        });
        checkBoxTempCustomText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardBusiness.setDisplayLight("customText=true");
                checkBoxTempUmid.setChecked(false);
                checkBoxDisplayOnOff.setChecked(false);
            }
        });

        final Button buttonSetDate = (Button) view.findViewById(R.id.btn_set_current_date);
        buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                dashboardBusiness.setSystemDate(cal.getTime());
            }
        });
    }
}
