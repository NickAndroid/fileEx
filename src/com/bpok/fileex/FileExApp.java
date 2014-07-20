package com.bpok.fileex;

import java.util.ArrayList;
import java.util.List;

import android.howard.exp.adapter.NaviAdapter;
import android.howard.exp.bean.RootItemBean;
import android.howard.exp.fragment.MenuDrawer;
import android.howard.exp.fragment.RootPage;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FileExApp extends ActionBarActivity implements
		ActionBar.OnNavigationListener ,
		MenuDrawer.NavigationDrawerCallbacks{

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	private MenuDrawer mNavigationDrawerFragment;
	private DrawerLayout mDrawerLayout = null;

	private Fragment currentPageFragment, mShowPageFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// init drawer
		mNavigationDrawerFragment = (MenuDrawer) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		// init drawerLayout
		mDrawerLayout = mNavigationDrawerFragment.mDrawerLayout;

		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// actionBar.setLogo(null);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		// actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.white));
		// set up the adapter
		List<RootItemBean> menuList = new ArrayList<RootItemBean>();
		RootItemBean item_1 = new RootItemBean();
		item_1.setTitleString("全部");
		RootItemBean item_2 = new RootItemBean();
		item_2.setTitleString("图片");
		RootItemBean item_3 = new RootItemBean();
		item_3.setTitleString("音乐");
		RootItemBean item_4 = new RootItemBean();
		item_4.setTitleString("视频");
		RootItemBean item_5 = new RootItemBean();
		item_5.setTitleString("文档");
		menuList.add(item_1);
		menuList.add(item_2);
		menuList.add(item_3);
		menuList.add(item_4);
		menuList.add(item_5);
		actionBar.setListNavigationCallbacks(new NaviAdapter(menuList, this),
				this);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getSupportActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getSupportActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		switch (position) {
		case 0:
			currentPageFragment = new RootPage();
			Toast.makeText(this, "0", Toast.LENGTH_LONG).show();
			break;
		case 1:
			Toast.makeText(this, "1", Toast.LENGTH_LONG).show();
			break;
		case 2:
			Toast.makeText(this, "2", Toast.LENGTH_LONG).show();
			break;
		case 3:
			Toast.makeText(this, "3", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, currentPageFragment).commit();
		return true;
	}

	/**
	 * @param fragment
	 */
	public void showShow(Fragment fragment) {
		getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(R.anim.view_push_down_in,
						R.anim.view_push_down_out)
				.replace(R.id.show_frame, fragment).commit();

		mShowPageFragment = fragment;
	}

	/**
	 * remove page
	 */
	public void RemoveShowPage() {
		Fragment fragment = null;
		if (mShowPageFragment != null) {
			fragment = mShowPageFragment;
			getSupportFragmentManager()
					.beginTransaction()
					.setCustomAnimations(R.anim.view_push_down_out,
							R.anim.view_push_down_out).remove(fragment)
					.commit();
		}
		mShowPageFragment = null;
	}

	@Override
	public void onBackPressed() {
		if (mShowPageFragment != null) {
			RemoveShowPage();
			return;
		} else if (currentPageFragment instanceof RootPage) {
			((RootPage) currentPageFragment).handleBackPressed();
		} else
			super.onBackPressed();
	}

	public void quit() {
		finish();
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// TODO Auto-generated method stub
		
	}

}
