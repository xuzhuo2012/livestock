package edu.hbut.livestock;

import java.sql.*;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import edu.hbut.livestock.entity.DeathProcess;
import edu.hbut.livestock.entity.DisinfectRecord;
import edu.hbut.livestock.entity.DisinfectRecordId;
import edu.hbut.livestock.entity.Feed;
import edu.hbut.livestock.entity.FeedingRecord;
//import edu.hbut.livestock.entity.FeedingRecordId;
import edu.hbut.livestock.entity.House;
//import edu.hbut.livestock.entity.HouseId;
import edu.hbut.livestock.entity.Livestock;
import edu.hbut.livestock.entity.ProducingRecord;
import edu.hbut.livestock.entity.ProducingRecordId;
import edu.hbut.livestock.entity.QuarantineApply;
import edu.hbut.livestock.entity.QuarantineApplyId;
import edu.hbut.livestock.frag.DeathProcessFragment;
import edu.hbut.livestock.frag.DefaultFragmentSwitchCallbackImpl;
import edu.hbut.livestock.frag.DisinfectRecordFragment;
import edu.hbut.livestock.frag.EntityListViewFragment;
//import edu.hbut.livestock.frag.FeedRecordFragment;
//import edu.hbut.livestock.frag.FeedingRecordFragment;
import edu.hbut.livestock.frag.FragmentSwitchCallback;
//import edu.hbut.livestock.frag.HouseFragment;
import edu.hbut.livestock.frag.LivestockFragment;
import edu.hbut.livestock.frag.ProducingRecordFragment;
import edu.hbut.livestock.frag.QuarantineApplyFragment;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.EntityViewAdapter;
import edu.hbut.livestock.util.ModuleId;

/**
 * 主模块界面
 * 
 * @author Hang
 * 
 */
public class WorkActivity extends Activity {

	private String[] modules = { ModuleId.FEED_MANAGE.getModuleName(),
			ModuleId.HOUSE_MANAGE.getModuleName(),
			ModuleId.MEDICAL_MANAGE.getModuleName(),
			};
	
	/*
	 * 可能显示的各个Fragment
	 */
//	private EntityListViewFragment<Feed, Timestamp> feedFragment;
//	
//	private EntityListViewFragment<FeedingRecord, FeedingRecordId> feedingEntityFragment;
//	
//	private EntityListViewFragment<House, HouseId> houseInfoFragment;
	
	private EntityListViewFragment<DeathProcess, Date> deathProcessFragment;
	
	private EntityListViewFragment<ProducingRecord, ProducingRecordId> producingRecordFragment;
	
	private EntityListViewFragment<Livestock, String> livestockFragment;
	
	private EntityListViewFragment<QuarantineApply, QuarantineApplyId> quarantineApplyFragment;
	
	private EntityListViewFragment<DisinfectRecord, DisinfectRecordId> disinfectRecordFragment;
	
//	private EntityViewAdapter<Feed> feedAdapter;
//	
//	private EntityViewAdapter<FeedingRecord> feedingAdapter;
//	
//	private EntityViewAdapter<House> houseAdapter;
	
	private EntityViewAdapter<DeathProcess> deathProcessAdapter;
	
	private EntityViewAdapter<ProducingRecord> producingRecordAdapter;
	
	private EntityViewAdapter<Livestock> livestockAdapter;
	
	private EntityViewAdapter<QuarantineApply> quarantineApplyAdapter;
	
	private EntityViewAdapter<DisinfectRecord> disinfectAdapter;
	
	/**
	 * 当前Activity的内容视图
	 */
	private ViewGroup root;
	
	/*
	 * 可能的Fragment切换回调接口
	 */
//	private FragmentSwitchCallback feedModuleFragmentSwitchCallback;
	
	private FragmentSwitchCallback houseModuleFragmentSwitchCallback;
	
	private FragmentSwitchCallback medicalModuleFragmentSwitchCallback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		root = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_work, null);
		setContentView(root);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.setDisplayShowTitleEnabled(false);
	    actionBar.setDisplayShowCustomEnabled(false);
	    actionBar.setDisplayShowHomeEnabled(false);
	    
//		initFeedView(actionBar);
		
		initHouseModuleView(actionBar);
		
		initMedicalModuleView(actionBar);

	}

	@SuppressWarnings("unchecked")
	private void initMedicalModuleView(ActionBar actionBar) {
		quarantineApplyFragment = new QuarantineApplyFragment();
		quarantineApplyAdapter = (EntityViewAdapter<QuarantineApply>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(QuarantineApply.class);
		quarantineApplyAdapter.setContext(this);
		quarantineApplyFragment.setAdapter(quarantineApplyAdapter);
		ActionBar.TabListener tabListener = new TabSelectListener(quarantineApplyFragment);
		actionBar.addTab(actionBar.newTab().setText(modules[2]).setTabListener(tabListener));
		
		disinfectRecordFragment = new DisinfectRecordFragment();
		disinfectAdapter = (EntityViewAdapter<DisinfectRecord>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(DisinfectRecord.class);
		disinfectAdapter.setContext(this);
		disinfectRecordFragment.setAdapter(disinfectAdapter);
		
		medicalModuleFragmentSwitchCallback = new FragmentSwitchCallback() {
			
			@Override
			public void execute(ModuleId newFunction, ModuleId oldFunction, Fragment currentFragment) {
				Fragment fragment = null;
				if (oldFunction.equals(ModuleId.QUARANTINE_APPLY)) {
					fragment = disinfectRecordFragment;
				} else {
					fragment = quarantineApplyFragment;
				}
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.remove(currentFragment);
				ft.replace(R.id.details, fragment);
				ft.commit();
			}
		};
		quarantineApplyFragment.setFragmentSwitchCallback(medicalModuleFragmentSwitchCallback);
		disinfectRecordFragment.setFragmentSwitchCallback(medicalModuleFragmentSwitchCallback);
	}

	@SuppressWarnings("unchecked")
	private void initHouseModuleView(ActionBar actionBar) {
//		houseInfoFragment = new HouseFragment();
//		houseAdapter = (EntityViewAdapter<House>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(House.class);
//		houseAdapter.setContext(this);
//		houseInfoFragment.setAdapter(houseAdapter);
//		ActionBar.TabListener tabListener = new TabSelectListener(houseInfoFragment);
//		actionBar.addTab(actionBar.newTab().setText(modules[1]).setTabListener(tabListener));
		
		
		deathProcessFragment = new DeathProcessFragment();
		deathProcessAdapter = (EntityViewAdapter<DeathProcess>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(DeathProcess.class);
		deathProcessAdapter.setContext(this);
		deathProcessFragment.setAdapter(deathProcessAdapter);
		ActionBar.TabListener tabListener = new TabSelectListener(deathProcessFragment);
		actionBar.addTab(actionBar.newTab().setText(modules[1]).setTabListener(tabListener));
		
		producingRecordFragment = new ProducingRecordFragment();
		producingRecordAdapter = (EntityViewAdapter<ProducingRecord>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(ProducingRecord.class);
		producingRecordAdapter.setContext(this);
		producingRecordFragment.setAdapter(producingRecordAdapter);
		
		livestockFragment = new LivestockFragment();
		livestockAdapter = (EntityViewAdapter<Livestock>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(Livestock.class);
		livestockAdapter.setContext(this);
		livestockFragment.setAdapter(livestockAdapter);
		
		houseModuleFragmentSwitchCallback = new DefaultFragmentSwitchCallbackImpl() {
			
			@Override
			protected void switchFrag(ModuleId newFunction, Fragment currentFragment) {
				EntityListViewFragment<?, ?> fragment = null;
//				ModuleId.HOUSE_INFO, ModuleId.DETH_PROCESSING, ModuleId.PRODUCING_RECORD, ModuleId.LIVESTOCK
				switch (newFunction) {
//				case HOUSE_INFO:
//					fragment = houseInfoFragment;
//					break;
				case DEATH_PROCESSING:
					fragment = deathProcessFragment;
					break;
				case PRODUCING_RECORD:
					fragment = producingRecordFragment;
					break;
				case LIVESTOCK:
					fragment = livestockFragment;
					break;
				default:
					return;
				}
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.remove(currentFragment);
				ft.replace(R.id.details, fragment);
				ft.commit();
			}
		};
//		houseInfoFragment.setFragmentSwitchCallback(houseModuleFragmentSwitchCallback);
		deathProcessFragment.setFragmentSwitchCallback(houseModuleFragmentSwitchCallback);
		livestockFragment.setFragmentSwitchCallback(houseModuleFragmentSwitchCallback);
		producingRecordFragment.setFragmentSwitchCallback(houseModuleFragmentSwitchCallback);
	}

//	@SuppressWarnings("unchecked")
//	private void initFeedView(ActionBar actionBar) {
//		final TabSelectListener tabListener = new TabSelectListener(feedFragment = new FeedRecordFragment());
//		feedAdapter = (EntityViewAdapter<Feed>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(Feed.class);
//		feedAdapter.setContext(this);
//		feedFragment.setAdapter(feedAdapter);
//		actionBar.addTab(actionBar.newTab().setText(modules[0]).setTabListener(tabListener));
//		feedModuleFragmentSwitchCallback = new DefaultFragmentSwitchCallbackImpl() {
//				
//			@Override
//			protected void switchFrag(ModuleId newFunction, android.app.Fragment currentFragment) {
//				FragmentTransaction ft = getFragmentManager().beginTransaction();
//				ft.remove(currentFragment);
//				if (newFunction.equals(ModuleId.FEEDING)) {
//					ft.replace(R.id.details, feedingEntityFragment);
//				} else {
//					ft.replace(R.id.details, feedFragment);
//				}
//				ft.commit();
//			}
//		};
//		feedFragment.setFragmentSwitchCallback(feedModuleFragmentSwitchCallback);
//		
//		feedingEntityFragment = new FeedingRecordFragment();
//		feedingEntityFragment.setFragmentSwitchCallback(feedModuleFragmentSwitchCallback);
//		feedingAdapter = (EntityViewAdapter<FeedingRecord>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(FeedingRecord.class);
//		feedingAdapter.setContext(this);
//		feedingEntityFragment.setAdapter(feedingAdapter);
//		
//	}

	/**
	 * 选项卡选择事件监听器
	 * 
	 * @author Hang
	 *
	 */
	public class TabSelectListener implements ActionBar.TabListener {
		
		private Fragment fragment;
		
		public TabSelectListener() {
		}

		public TabSelectListener(Fragment fragment) {
			super();
			this.fragment = fragment;
		}


		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (fragment != null) {
				ft.replace(R.id.details, fragment, null);
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (fragment != null) {
				ft.remove(fragment);
			}
		}

		public Fragment getFragment() {
			return fragment;
		}

		public void setFragment(Fragment fragment) {
			this.fragment = fragment;
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.v("mainactivity", "" + resultCode);
	}

}
