package com.example.budna.smoney;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.budna.smoney.adapter.SlidingMenuAdapter;
import com.example.budna.smoney.fragment.Dela;
import com.example.budna.smoney.fragment.Dohodnina;
import com.example.budna.smoney.fragment.Domov;
import com.example.budna.smoney.fragment.IzbiraRegije;
import com.example.budna.smoney.fragment.Mnenje;
import com.example.budna.smoney.fragment.Nastavitve;
import com.example.budna.smoney.fragment.Popusti;
import com.example.budna.smoney.fragment.Ure;
import com.example.budna.smoney.fragment.VsaPodjetja;
import com.example.budna.smoney.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private RelativeLayout mainContent;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    public static MainActivity ma = new MainActivity();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mainContent = (RelativeLayout) findViewById(R.id.main_content);
        listSliding = new ArrayList<>();

        listSliding.add(new ItemSlideMenu(R.drawable.home, "Domov"));
        listSliding.add(new ItemSlideMenu(R.drawable.ura, "Dodajanje ur"));
        listSliding.add(new ItemSlideMenu(R.drawable.podjetje, "Podjetje"));
        listSliding.add(new ItemSlideMenu(R.drawable.dohodnina, "Dohodnina"));
        listSliding.add(new ItemSlideMenu(R.drawable.posta, "Mnenje"));
        listSliding.add(new ItemSlideMenu(R.drawable.prosta_dela, "Prosta dela"));
        listSliding.add(new ItemSlideMenu(R.drawable.popust1, "Popusti"));

        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(listSliding.get(0).getTitle());
        listViewSliding.setItemChecked(0, true);
        drawerLayout.closeDrawer(listViewSliding);

        replaceFragment(0);

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setTitle(listSliding.get(position).getTitle());

                listViewSliding.setItemChecked(position, true);

                replaceFragment(position);

                drawerLayout.closeDrawer(listViewSliding);

            }
        });


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }


        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()) {
            case R.id.menu_item_1:
                startNastavitve();
                setTitle("Nastavitve");
                listViewSliding.setItemChecked(0, false);
                listViewSliding.setItemChecked(1, false);
                listViewSliding.setItemChecked(2, false);
                listViewSliding.setItemChecked(3, false);
                listViewSliding.setItemChecked(4, false);
                listViewSliding.setItemChecked(5, false);
                listViewSliding.setItemChecked(6, false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void startNastavitve(){
        android.app.Fragment newFragment = new Nastavitve();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private void replaceFragment(int pos){
        Fragment fragment = null;
        switch(pos){
            case 0:
                fragment = new Domov();
                break;
            case 1:
                fragment = new Ure();
                break;
            case 2:
                fragment = new VsaPodjetja();
                break;
            case 3:
                fragment = new Dohodnina();
                break;
            case 4:
                fragment = new Mnenje();
                break;
            case 5:
                fragment = new Dela();
                break;
            case 6:
                fragment = new Popusti();
                break;
            default:
                fragment = new Domov();
                break;
        }

        if(null!=fragment){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }

    }

    public void OknoDodaj(Fragment fragment){

    }
}
