package com.studytrack.app.studytrack_v1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private enum Screen { UNIS, OLYMPS, COLLEDGES }

    HashMap<Screen, myFragment> frags;
    FragmentManager fragmentManager;

    private Context ctx = this;
    private Screen screen;
    private Toolbar toolbar;
    private Drawer drawer;
    private MenuItem filterMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initFragments();
        initDrawer();

        fragmentManager = getSupportFragmentManager();

        renderScreen(Screen.UNIS);
        screen = Screen.UNIS;
    }

    private void initDrawer()
    {
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
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
                        Log.d("myFragments", Integer.toString(position));
                        switch (position) {
                            case 1:
                                renderToolbar(Screen.UNIS);
                                renderScreen(Screen.UNIS);
                                screen = Screen.UNIS;
                                break;
                            case 2:
                                renderToolbar(Screen.COLLEDGES);
                                renderScreen(Screen.COLLEDGES);
                                screen = Screen.COLLEDGES;
                                break;
                            case 3:
                                renderToolbar(Screen.OLYMPS);
                                renderScreen(Screen.OLYMPS);
                                screen = Screen.OLYMPS;
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
    }

    private void initFragments()
    {
        Log.d("myFragments", "INIT FRAGMENTS");
        frags = new HashMap<Screen, myFragment>();
        frags.put(Screen.UNIS, new SearchFragment());
        frags.put(Screen.COLLEDGES, new StartFragment());
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
        //curFrag.Refresh();
    }

    private void initToolBar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
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
        filterMenuItem = menu.findItem(R.id.action_filter);
        //filterMenuItem.setIcon(R.mipmap.ic_filter);
        //filterMenuItem.setIcon(getResources().getDrawable(FontAwesome.Icon.faw_filter));

        renderToolbar(Screen.UNIS);
        return true;
    }

    private void renderToolbar(Screen screen_nxt)
    {
        switch (screen_nxt)
        {
            case UNIS:
                filterMenuItem.setVisible(true);
                toolbar.setTitle(R.string.screen_unis_title);
                break;

            case COLLEDGES:
                filterMenuItem.setVisible(false);
                toolbar.setTitle(R.string.screen_colledges_title);
                break;

            case OLYMPS:
                filterMenuItem.setVisible(true);
                toolbar.setTitle(R.string.screen_olymps_title);
        }

    }

    private void renderScreen(Screen screen_nxt)
    {
        if ((screen != null) && (screen == screen_nxt))
            return;

        Fragment nextFrag = frags.get(screen_nxt);

        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment, nextFrag)
                .commit() ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        Toast.makeText(ctx, "Coming soon", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_filter) {

            //return true;
        //}
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen()){
            drawer.closeDrawer();
        }
        else{
            super.onBackPressed();
        }
    }
}
