package app.giacomo.lavermicocca.termostato;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shamanland.fonticon.FontIconTypefaceHolder;
import com.shamanland.fonticon.FontIconView;

/**
 * Created by Giacomo on 10/01/2015.
 */
public class BaseActionBarActivity extends AppCompatActivity {

    // Log tag
    private final String TAG = this.getClass().getSimpleName();

    public enum ToolbarType { EMPTY, FINISH, HAMBURGER; }

    private Toolbar toolbar;
    private TextView textViewTitle;
    private TextView textViewBreadcrumb;
    private TextView textViewTesseraId;
    private FontIconView fontIconView;

    private int titleResource = R.string.title_not_set;     //default
    private int breadcrumbResource = R.string.BREADCRUMBS_NON_SETTATA;
    private Integer iconBreadcrumb = null;

    private int subTitleResource = R.string.title_empty;    //default
    private int layout = R.layout.activity_main;            //default
    private String tessaraId = "";
    private ToolbarType toolbarType = ToolbarType.EMPTY;    //default

    private int activitiesLivel = 0;

    public BaseActionBarActivity(int layout, int titleResource, Integer iconBreadcrumb, int breadcrumbResource, String tessaraId, ToolbarType toolbarType)
    {
        this.layout = layout;
        this.titleResource = titleResource;
        this.toolbarType = toolbarType;
        this.breadcrumbResource = breadcrumbResource;
        this.tessaraId = tessaraId;
        this.iconBreadcrumb = iconBreadcrumb;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontIconTypefaceHolder.init(getAssets(), "icons/icons_set1.ttf");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(this.layout);

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.app_bar);
        this.toolbar = (Toolbar) relativeLayout.findViewById(R.id.toolbar);

        this.textViewTitle = (TextView) relativeLayout.findViewById(R.id.textViewTitle);
        this.textViewTitle.setText(this.titleResource);

        this.textViewBreadcrumb = (TextView) relativeLayout.findViewById(R.id.textViewBreadcrumb);
        this.textViewBreadcrumb.setText(this.breadcrumbResource);

        if(this.iconBreadcrumb != null) {
            this.fontIconView = (FontIconView) relativeLayout.findViewById(R.id.iconBreadcrumb);
            this.fontIconView.setText(this.iconBreadcrumb);
        }

        this.textViewTesseraId = (TextView) relativeLayout.findViewById(R.id.textViewTesseraId);
        this.textViewTesseraId.setText(tessaraId);

        //this.toolbar.setTitle(this.titleResource);
        //this.toolbar.setSubtitle(this.subTitleResource);

        //Activity nuda (MainActivity)
        if(this.toolbarType == ToolbarType.EMPTY) {
            //getSupportActionBar().setDisplayShowHomeEnabled(false);
        }//if EMPTY

        //Activity semplice freccia indietro per chiuderla
        if(this.toolbarType == ToolbarType.FINISH) {
            this.toolbar.setNavigationIcon(R.drawable.left_arrow);
            this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }//if FINISH

        //Activity con menu hamburger
        if(this.toolbarType == ToolbarType.HAMBURGER) {
            //
        }//if HAMBURGER

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                //raccoglie il livello dal navigazione dei fragment
                activitiesLivel = getSupportFragmentManager().getBackStackEntryCount();
            }
        });

    }//onCreate

    /**
     * Migorato uscita applicazione -> solo se mi trovo al livello 0
     */
    @Override
    public void onBackPressed() {
        if(activitiesLivel==0) {
            new AlertDialog.Builder(this)
                    .setMessage("Vuoi uscire dall'applicazione?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        else
        {
            //toglie dalla storia (undo)
            getSupportFragmentManager().popBackStack();
        }
    }


    //GETTER SETTER
    public Toolbar getToolbar() {

        return this.toolbar;
    }

    public void setTitleResource(int titleResource) {
        //this.toolbar.setTitle(titleResource);
        this.textViewTitle.setText(titleResource);
    }

    public void setTitleResource(String title) {
        //this.toolbar.setTitle(titleResource);
        this.textViewTitle.setText(title);
    }

    public void setSubTitleResource(int subTitleResource) {
        this.toolbar.setSubtitle(subTitleResource);
    }

    public void setBreadcrumb(int breadcrumbResource) {
        this.textViewBreadcrumb.setText(breadcrumbResource);
    }

    public void setBreadcrumb(String breadcrumb) {
        this.textViewBreadcrumb.setText(breadcrumb);
    }

    public void setIconBreadcrumb(int iconBreadcrumb)
    {
        this.fontIconView.setText(iconBreadcrumb);
    }

    public void setTesseraId(CharSequence tessaraId) {
        this.textViewTesseraId.setText(tessaraId);
    }
}
