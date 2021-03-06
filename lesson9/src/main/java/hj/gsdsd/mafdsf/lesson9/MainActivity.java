package hj.gsdsd.mafdsf.lesson9;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new WelcomeFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final Fragment fragment;
        switch (item.getItemId()) {
            case R.id.nav_comparision:
                fragment = new ComparisonFragment();
                toolbar.setTitle(R.string.menu_comparison);
                break;
            case R.id.nav_animator:
                fragment = new AnimatorFragment();
                toolbar.setTitle(R.string.menu_animator);
                break;
            case R.id.nav_set:
                fragment = new AnimatorSetFragment();
                toolbar.setTitle(R.string.menu_animator_set);
                break;
            case R.id.nav_interpolator:
                fragment = new InterpolatorFragment();
                toolbar.setTitle(R.string.menu_interpolator);
                break;
            default:
                throw new UnsupportedOperationException();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
