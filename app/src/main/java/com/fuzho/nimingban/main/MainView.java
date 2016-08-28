package com.fuzho.nimingban.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.fuzho.nimingban.BasePresenter;
import com.fuzho.nimingban.MVPBaseActivity;
import com.fuzho.nimingban.R;
import com.fuzho.nimingban.pojo.Article;

import java.util.ArrayList;

/**
 * Created by fuzhongqing on 16/8/27.
 * 主界面
 */
public class MainView extends MVPBaseActivity implements IMainView,NavigationView.OnNavigationItemSelectedListener {

    Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView mNavigationView;
    RecyclerView mRecyclerView;

    Adapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        ((IMainPresenter)mPresenter).getArticles();
    }

    void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(MainView.this, mDrawerLayout,mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.main_list);
        mAdapter = new Adapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected BasePresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showProcessBar(boolean b) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showLoadingMore(boolean b) {

    }

    @Override
    public void setData(ArrayList<Article> s) {
        if (mAdapter != null) {
            mAdapter.setArticles(s);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}