package com.studytrack.app.studytrack_v1;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.search.material.library.MaterialSearchView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private enum Screen {UNIS, OLYMPS, COLLEGES}

    HashMap<Screen, myFragment> frags;
    FragmentManager fragmentManager;

    private Context ctx = this;
    private Screen screen;
    private Toolbar toolbar;
    private Drawer drawer;
    private MenuItem filterMenuItem;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        initToolBar();
        initFragments();
        initDrawer();

        screen = Screen.UNIS;
        renderScreen();
    }

    private void initDrawer() {
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .withTranslucentStatusBar(false)
                .withHeader(R.layout.drawer_header)
                .withShowDrawerOnFirstLaunch(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_main_sec1_text).withIcon(FontAwesome.Icon.faw_graduation_cap),
                        new PrimaryDrawerItem().withName(R.string.drawer_main_sec2_text).withIcon(FontAwesome.Icon.faw_university),
                        new PrimaryDrawerItem().withName(R.string.drawer_main_sec3_text).withIcon(FontAwesome.Icon.faw_pencil),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.drawer_extra_sec1_text).withIcon(FontAwesome.Icon.faw_star).withSelectable(false)
                )
                .addStickyDrawerItems(
                        new SecondaryDrawerItem().withName(R.string.drawer_footer_sec1_text).withSelectable(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_footer_sec2_text).withSelectable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //if (drawerItem.isSelected())
                          //  return true;

                        switch (position) {
                            case 1:
                                screen = Screen.UNIS;
                                renderScreen();
                                break;
                            case 2:
                                screen = Screen.COLLEGES;
                                renderScreen();
                                break;
                            case 3:
                                screen = Screen.OLYMPS;
                                renderScreen();
                                break;
                            default:
                                Toast.makeText(ctx, "Coming soon", Toast.LENGTH_SHORT).show();

                                return true;
                        }
                        drawer.closeDrawer();
                        return true;
                    }
                })
                .build();

        ActionBarDrawerToggle toggle = drawer.getActionBarDrawerToggle();

        ImageView headerImg = (ImageView) drawer.getHeader().findViewById(R.id.drawer_header_img);
        Picasso.with(this).load(R.drawable.face).into(headerImg);
    }

    private void initFragments() {
        frags = new HashMap<>();
        frags.put(Screen.UNIS, new SearchFragment());
        frags.put(Screen.COLLEGES, new CollegesFragment());
        frags.put(Screen.OLYMPS, new OlympsFragment());

        myFragment curFrag = frags.get(Screen.UNIS);
        ArrayList<UniListItem> search_items = new ArrayList<>();
        for (int i = 0; i < 20; ++i)
            search_items.add(new UniListItem("Московский государственный университет имени М.В. Ломоносова",
                    R.mipmap.ic_launcher,
                    "Россия, Москва", 87.7f));

        Bundle bundle = new Bundle();
        bundle.putSerializable("uni_array", search_items);
        curFrag.putData(bundle);
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        MaterialSearchView searchView = (MaterialSearchView) findViewById(R.id.search_view);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        //MenuItem item = menu.findItem(R.id.action_search);

        //searchView.setMenuItem(item);
        filterMenuItem = menu.findItem(R.id.action_filter);

        //renderToolbar(Screen.UNIS);
        return true;
    }

    private void renderScreen() {

        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }

        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.replace(R.id.main_fragment, frags.get(screen));
        trans.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        }
        else if (!frags.get(screen).onBackPressed()) {
            if (fragmentManager.getBackStackEntryCount() == 0)
                drawer.openDrawer();
            else
                super.onBackPressed();
        }
    }
}
