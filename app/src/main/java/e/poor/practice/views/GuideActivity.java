package e.poor.practice.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import e.poor.practice.R;

public class GuideActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.btn_skip)
    Button mBtnSkip;
    @BindView(R.id.layoutDots)
    LinearLayout mDotsLayout;
    TextView[] mDots;
    private int[] mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        mLayout = new int[]{
                R.layout.fragment_guide_01,
                R.layout.fragment_guide_02,
                R.layout.fragment_guide_03
        };
        addBottomDots(0);

        mSectionsPagerAdapter = new SectionsPagerAdapter();
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                addBottomDots(i);
                if (i == (mLayout.length - 1)) {
                    mBtnSkip.setText("OK");
                    mBtnNext.setVisibility(View.GONE);
                } else {
                    mBtnSkip.setText("SKIP");
                    mBtnNext.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addBottomDots(int currentPage) {
        mDots = new TextView[mLayout.length];
        mDotsLayout.removeAllViews();
        for (int i = 0; i < mLayout.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[currentPage].setTextColor(getResources().getColor(R.color.white_text));
        }
    }

    @OnClick(R.id.btn_next)
    public void nextPage() {
        int current = mViewPager.getCurrentItem();
        if (current < mLayout.length) {
            mViewPager.setCurrentItem(current + 1);
        } else {
            lauchHome();
        }
    }

    @OnClick(R.id.btn_skip)
    public void lauchHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    public class SectionsPagerAdapter extends PagerAdapter {
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(mLayout[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mLayout.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view  = (View) object;
            container.removeView(view);
        }
    }
}
