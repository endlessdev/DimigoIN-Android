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
import butterknife.Bind;
import com.bumptech.glide.Glide;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import us.narin.dimigoin.R;
import us.narin.dimigoin.fragments.CommunityNavFragment;
import us.narin.dimigoin.fragments.InfoNavFragment;
import us.narin.dimigoin.fragments.MainFragment;
import us.narin.dimigoin.fragments.SubmitNavFragment;
import us.narin.dimigoin.model.Login;
import us.narin.dimigoin.util.Session;
import us.narin.dimigoin.util.TransactionFrag;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public TabLayout mTabLayout;
    public Toolbar toolbar;

    @Bind(R.id.nav_user_name)
    TextView navUserName;

    @Bind(R.id.nav_user_data)
    TextView navUserData;
    @Bind(R.id.nav_user_img)
    ImageView navUserImg;
    @Bind(R.id.nav_bg)
    ImageView navBackground;

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
        Toast.makeText(getApplicationContext(), String.format("%d학년 %d반 %d번 재학생", login.getData().getGrade(), login.getData().getStdClass(), login.getData().getNumber()), Toast.LENGTH_LONG).show();



        View rootView = navigationView.getHeaderView(0);
        navUserName = (TextView)rootView.findViewById(R.id.nav_user_name);
        navUserData = (TextView)rootView.findViewById(R.id.nav_user_data);
        navUserImg = (ImageView)rootView.findViewById(R.id.nav_user_img);
        navBackground = (ImageView)rootView.findViewById(R.id.nav_bg);

        Glide.with(this).load(R.drawable.nav_bg).centerCrop().fitCenter().into(navBackground);
        Glide.with(this).load(R.drawable.profile).bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(navUserImg);

        navUserName.setText(String.format("%s(%s)", login.getData().getName(), Session.getAccountId(getApplicationContext())));
        navUserData.setText(String.format("%d학년 %d반 %d번 재학생", login.getData().getGrade(), login.getData().getStdClass(), login.getData().getNumber()));

        setFragment(TransactionFrag.HOME);

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
                setFragment(TransactionFrag.HOME);
                break;
            case R.id.nav_info:
                setFragment(TransactionFrag.INFO);
                break;
            case R.id.nav_comunity:
                setFragment(TransactionFrag.COMMUNITY);
                break;
            case R.id.nav_submit:
                setFragment(TransactionFrag.SUBMIT);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(TransactionFrag flag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (flag) {
            case HOME:
                MainFragment mainFragment = new MainFragment();
                fragmentTransaction.replace(R.id.content_fragment, mainFragment);
                Log.d("MainActivity","setFragment(HOME)");
                break;
            case INFO:
                InfoNavFragment infoNavFragment = new InfoNavFragment(fragmentManager);
                fragmentTransaction.replace(R.id.content_fragment, infoNavFragment);
                Log.d("MainActivity","setFragment(INFO)");
                break;
            case COMMUNITY:
                CommunityNavFragment communityNavFragment = new CommunityNavFragment(fragmentManager);
                fragmentTransaction.replace(R.id.content_fragment, communityNavFragment);
                Log.d("MainActivity","setFragment(COMMUNITY)");
                break;
            case SUBMIT:
                SubmitNavFragment submitNavFragment = new SubmitNavFragment(fragmentManager);
                fragmentTransaction.replace(R.id.content_fragment, submitNavFragment);
                Log.d("MainActivity","setFragment(SUBMIT)");
                break;
        }
        fragmentTransaction.commit();
    }

}
