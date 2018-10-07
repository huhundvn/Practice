package e.poor.practice.views;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.poor.practice.interfaces.IoItemClickListener;
import e.poor.practice.presenters.Presenter;
import e.poor.practice.R;
import e.poor.practice.adapters.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    CustomAdapter mCustomAdapter;
    IoItemClickListener mListener;

    Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mPresenter = new Presenter(this);

        mListener = new IoItemClickListener() {
            @Override
            public void onClickItem() {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        };
        mCustomAdapter = new CustomAdapter(mPresenter.getList(), mListener);
        mRvList.setLayoutManager(new GridLayoutManager(this, 2));
        mRvList.setAdapter(mCustomAdapter);
    }
}
