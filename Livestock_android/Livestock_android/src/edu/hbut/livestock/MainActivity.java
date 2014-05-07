package edu.hbut.livestock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SimpleAdapter;

import com.slidingmenu.lib.SlidingMenu;

import edu.hbut.livestock.entity.Antiepidemic;
import edu.hbut.livestock.entity.AntiepidemicId;
import edu.hbut.livestock.entity.DiagnosisRecord;
import edu.hbut.livestock.entity.DiagnosisRecordId;
import edu.hbut.livestock.entity.DisinfectRecord;
import edu.hbut.livestock.entity.DisinfectRecordId;
import edu.hbut.livestock.entity.Immunization;
import edu.hbut.livestock.entity.ImmunizationId;
import edu.hbut.livestock.entity.MedicineRecord;
import edu.hbut.livestock.entity.QuarantineApply;
import edu.hbut.livestock.entity.QuarantineApplyId;
import edu.hbut.livestock.frag.AntiepidemicFragment;
import edu.hbut.livestock.frag.DiagnosisRecordFragment;
import edu.hbut.livestock.frag.DisinfectRecordFragment;
import edu.hbut.livestock.frag.EntityListViewFragment;
import edu.hbut.livestock.frag.ImmunizationFragment;
import edu.hbut.livestock.frag.MedicineRecordFragment;
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
public class MainActivity extends Activity {
	private EntityViewAdapter<DisinfectRecord> disinfectAdapter;
	private EntityListViewFragment<DisinfectRecord, DisinfectRecordId> disinfectRecordFragment;
	
	private EntityViewAdapter<MedicineRecord> medicineAdapter;
	private EntityListViewFragment<MedicineRecord, Date> medicineRecordFragment;
	
	private EntityViewAdapter<Immunization> immunizationAdapter;
	private EntityListViewFragment<Immunization, ImmunizationId> immunizationFragment;
	
	private EntityViewAdapter<DiagnosisRecord> diagnosisAdapter;
	private EntityListViewFragment<DiagnosisRecord, DiagnosisRecordId> diagnosisFragment;
	
	private EntityViewAdapter<Antiepidemic> antiepidemicAdapter;
	private EntityListViewFragment<Antiepidemic, AntiepidemicId> antiepidemicFragment;
	
	private EntityViewAdapter<QuarantineApply> quarantineAdapter;
	private EntityListViewFragment<QuarantineApply, QuarantineApplyId> quarantineFragment;
	
	/** 当前Activity的内容视图 */
	private ViewGroup root;
	private ListView listView;
	private String[] menu = {ModuleId.MEDICINAL_RECORD.getModuleName(),ModuleId.DISINFECT_RECORD.getModuleName(),
			ModuleId.IMMUNIZATION.getModuleName(),ModuleId.DIAGNOSIS_RECORD.getModuleName(),ModuleId.MONITOR_RECORD.getModuleName(),
			ModuleId.QUARANTINE_PROGRESS.getModuleName()};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		root = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_work, null);
		setContentView(root);
		ViewGroup leftview = (ViewGroup) getLayoutInflater().inflate(R.layout.left_menu, null);
		listView = new ListView(leftview.getContext());
		List<Map<String,String>> items = new ArrayList<Map<String,String>>();
		for (int i = 0; i < menu.length; i++) {
			Map<String,String> item = new HashMap<String, String>();
			item.put("menu", menu[i]);
			items.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(leftview.getContext(), items, R.layout.left_menu, new String[]{"menu"}, new int[]{R.id.menuItem});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println("------------------------->" + arg2);
				switch (arg2) {
				case 0:
					initMedicineRecordView();
					break;
				case 1:
					initDisinfectRecordView();
					break;
				case 2:
					initImmuzationView();
					break;
				case 3:
					initDiagnosisView();
					break;
				case 4:
					initAntiepidemicView();
					break;
				default:
					initQuarantineView();
					break;
				}
				System.out.println(disinfectAdapter);
			}
			
		});
		leftview.addView(listView);
		
		
		final SlidingMenu menu = new SlidingMenu(this);
        Button button = new Button(this,null,android.R.attr.buttonBarButtonStyle);
        button.setText("菜单");
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 menu.showMenu();
			}
		});
        
        Button button2 = new Button(this,null,android.R.attr.buttonBarButtonStyle);
        button2.setText("用户信息");
        button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 menu.showSecondaryMenu();
			}
		});
        RelativeLayout view = (RelativeLayout)findViewById(R.id.headmenu);
        LayoutParams llp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        llp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        view.addView(button,llp);
        LayoutParams rlp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        view.addView(button2,rlp);
        
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setBehindWidth(200);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        
        menu.setMenu(leftview);
        
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setSecondaryMenu(R.layout.right_userinfo);
		menu.setSecondaryShadowDrawable(R.drawable.shadow);
		menu.setShadowDrawable(R.drawable.shadow);
	}

	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.v("mainactivity", "" + resultCode);
	}
	
	@SuppressWarnings("unchecked")
	private void initDisinfectRecordView() {
		disinfectRecordFragment = new DisinfectRecordFragment();
		disinfectAdapter = (EntityViewAdapter<DisinfectRecord>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(DisinfectRecord.class);
		disinfectAdapter.setContext(this);
		disinfectRecordFragment.setAdapter(disinfectAdapter);
		disinfectRecordFragment.initData();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.details, disinfectRecordFragment, null);
		ft.commit();
	}
	
	@SuppressWarnings("unchecked")
	private void initMedicineRecordView() {
		medicineRecordFragment = new MedicineRecordFragment();
		medicineAdapter = (EntityViewAdapter<MedicineRecord>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(MedicineRecord.class);
		medicineAdapter.setContext(this);
		medicineRecordFragment.setAdapter(medicineAdapter);
		medicineRecordFragment.initData();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.details, medicineRecordFragment, null);
		ft.commit();
	}
	
	@SuppressWarnings("unchecked")
	private void initImmuzationView() {
		immunizationFragment = new ImmunizationFragment();
		immunizationAdapter = (EntityViewAdapter<Immunization>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(Immunization.class);
		immunizationAdapter.setContext(this);
		immunizationFragment.setAdapter(immunizationAdapter);
		immunizationFragment.initData();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.details, immunizationFragment, null);
		ft.commit();
	}
	
	@SuppressWarnings("unchecked")
	private void initDiagnosisView() {
		diagnosisFragment = new DiagnosisRecordFragment();
		diagnosisAdapter = (EntityViewAdapter<DiagnosisRecord>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(DiagnosisRecord.class);
		diagnosisAdapter.setContext(this);
		diagnosisFragment.setAdapter(diagnosisAdapter);
		diagnosisFragment.initData();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.details, diagnosisFragment, null);
		ft.commit();
	}
	
	@SuppressWarnings("unchecked")
	private void initAntiepidemicView() {
		antiepidemicFragment = new AntiepidemicFragment();
		antiepidemicAdapter = (EntityViewAdapter<Antiepidemic>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(Antiepidemic.class);
		antiepidemicAdapter.setContext(this);
		antiepidemicFragment.setAdapter(antiepidemicAdapter);
		antiepidemicFragment.initData();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.details, antiepidemicFragment, null);
		ft.commit();
	}
	
	@SuppressWarnings("unchecked")
	private void initQuarantineView() {
		quarantineFragment = new QuarantineApplyFragment();
		quarantineAdapter = (EntityViewAdapter<QuarantineApply>) ObjectConfigedFactory.getVeiwAdapterFactory().getBean(QuarantineApply.class);
		quarantineAdapter.setContext(this);
		quarantineFragment.setAdapter(quarantineAdapter);
		quarantineFragment.initData();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.details, quarantineFragment, null);
		ft.commit();
	}
}
