package us.narin.dimigoin.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import us.narin.dimigoin.R;
import us.narin.dimigoin.fragments.navigation.MainFragment;
import us.narin.dimigoin.fragments.navigation.NavCommunityFragment;
import us.narin.dimigoin.fragments.navigation.NavInfoFragment;
import us.narin.dimigoin.fragments.navigation.NavSubmitFragment;
import us.narin.dimigoin.model.pojo.Login;
import us.narin.dimigoin.util.Schema;
import us.narin.dimigoin.util.Session;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private static final String userDataFormat = "%d학년 %d반 %d번 재학생";

    public TabLayout mTabLayout;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabLayout = (TabLayout)findViewById(R.id.main_tab);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(null);
            getSupportActionBar().setLogo(R.mipmap.ic_logo_text);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Login login = (Login) getIntent().getSerializableExtra("stdModel");
        Toast.makeText(getApplicationContext(), String.format(userDataFormat, login.getData().getGrade(), login.getData().getStdClass(), login.getData().getNumber()), Toast.LENGTH_LONG).show();

        final View navRootView = navigationView.getHeaderView(0);

        final TextView navUserName = (TextView)navRootView.findViewById(R.id.nav_user_name);
        final TextView navUserData = (TextView)navRootView.findViewById(R.id.nav_user_data);
        final ImageView navUserImg = (ImageView)navRootView.findViewById(R.id.nav_user_img);
        final ImageView navBackground = (ImageView)navRootView.findViewById(R.id.nav_bg);

        Glide.with(this).load(R.drawable.nav_bg).centerCrop().fitCenter().into(navBackground);
        Glide.with(this).load(R.drawable.profile).bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(navUserImg);

        navUserName.setText(String.format("%s(%s)", login.getData().getName(), Session.getAccountId(getApplicationContext())));
        navUserData.setText(String.format(userDataFormat, login.getData().getGrade(), login.getData().getStdClass(), login.getData().getNumber()));

        setFragment(Schema.TransactionFrag.HOME);

        navigationView.getMenu().getItem(0).setChecked(true);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                setFragment(Schema.TransactionFrag.HOME);
                break;
            case R.id.nav_info:
                setFragment(Schema.TransactionFrag.INFO);
                break;
            case R.id.nav_comunity:
                setFragment(Schema.TransactionFrag.COMMUNITY);
                break;
            case R.id.nav_submit:
                setFragment(Schema.TransactionFrag.SUBMIT);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Schema.TransactionFrag flag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (flag) {
            case HOME:
                MainFragment mainFragment = new MainFragment();
                fragmentTransaction.replace(R.id.content_fragment, mainFragment);
                Log.d(TAG,"setFragment(HOME)");
                break;
            case INFO:
                NavInfoFragment infoNavFragment = new NavInfoFragment(fragmentManager);
                fragmentTransaction.replace(R.id.content_fragment, infoNavFragment);
                Log.d(TAG,"setFragment(INFO)");
                break;
            case COMMUNITY:
                NavCommunityFragment communityNavFragment = new NavCommunityFragment(fragmentManager);
                fragmentTransaction.replace(R.id.content_fragment, communityNavFragment);
                Log.d(TAG,"setFragment(COMMUNITY)");
                break;
            case SUBMIT:
                NavSubmitFragment submitNavFragment = new NavSubmitFragment(fragmentManager);
                fragmentTransaction.replace(R.id.content_fragment, submitNavFragment);
                Log.d(TAG,"setFragment(SUBMIT)");
                break;
        }
        fragmentTransaction.commit();
    }

}
