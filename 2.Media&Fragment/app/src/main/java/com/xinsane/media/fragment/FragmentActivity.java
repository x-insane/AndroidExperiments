package com.xinsane.media.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.xinsane.media.R;
import com.xinsane.media.fragment.account.AccountFragment;
import com.xinsane.media.fragment.challenge.ChallengeFragment;
import com.xinsane.media.fragment.game.GameListFragment;
import com.xinsane.util.LogUtil;

public class FragmentActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private AccountFragment accountFragment = new AccountFragment();
    private GameListFragment gameListFragment = new GameListFragment();
    private ChallengeFragment challengeFragment = new ChallengeFragment();

    public BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        navigation = findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                viewPager.setCurrentItem(item.getOrder());
                return true;
            }
        });
        viewPager = findViewById(R.id.main_content);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                navigation.getMenu().getItem(position).setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                LogUtil.d(String.valueOf(getItemId(position)));
                switch ((int) getItemId(position)) {
                    case 0:
                        return gameListFragment;
                    case 1:
                        return challengeFragment;
                    case 2:
                        return accountFragment;
                }
                return null;
            }
            @Override
            public int getCount() {
                return 3;
            }
        });
    }

}
