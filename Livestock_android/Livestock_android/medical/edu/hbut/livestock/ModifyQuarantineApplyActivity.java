package edu.hbut.livestock;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import edu.hbut.livestock.entity.QuarantineApply;
import edu.hbut.livestock.entity.QuarantineApplyId;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author Hang
 *
 */
public class ModifyQuarantineApplyActivity extends ToastActivity {
	private QuarantineApply quarantine;
	
	private static final String[] result={"不合格","合格"}; 
	private static String res = result[0];
	
	@SuppressWarnings("unchecked")
	private RemoteProcedureCall<QuarantineApply, QuarantineApplyId> call = (RemoteProcedureCall<QuarantineApply, QuarantineApplyId>) ObjectConfigedFactory.getRemoteProcedureCallFactory().getBean(QuarantineApply.class);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_quarantine_apply);
		Intent intent = getIntent(); 
		Bundle bundle = intent.getExtras();
		quarantine = (QuarantineApply)bundle.get("quarantine");
		
		
		EditText countEditText = (EditText) findViewById(R.id.add_quarantine_apply_count);
        EditText applyDateTest = (EditText) findViewById(R.id.add_quarantine_apply_date);
        EditText signTest = (EditText) findViewById(R.id.add_quarantine_sign);
        EditText operatorTest = (EditText) findViewById(R.id.add_quarantine_operator);
        EditText userTest = (EditText) findViewById(R.id.add_quarantine_user);
        Spinner spinnerTest = (Spinner) findViewById(R.id.add_quarantine_result);
        Button modify = (Button) findViewById(R.id.modify_quarantine_apply_button);
        
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        applyDateTest.setText(fmt.format(quarantine.getId().getApplyDate()));
        applyDateTest.setEnabled(false);

        countEditText.setText(quarantine.getCount()+"");
        countEditText.setEnabled(false);
        
        signTest.setText(quarantine.getFlag());
        signTest.setEnabled(false);
        
        operatorTest.setText(quarantine.getOperator());
        operatorTest.setEnabled(false);
        
        userTest.setText(quarantine.getId().getUserid());
        userTest.setEnabled(false);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,result);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTest.setAdapter(adapter);  
        
        spinnerTest.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinnerTest.setVisibility(View.VISIBLE);  
        
        modify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				call.update(quarantine, new AsyncCallback<String>() {

					@Override
					public void call(String result) {
						showLongToast(result);
						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
							@Override
							public void run() {
								ModifyQuarantineApplyActivity.this.finish();
							}
						}, 2000);
					}
				});
			}
		});
	}
	
	class SpinnerSelectedListener implements OnItemSelectedListener{  
		  
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
            res = result[arg2];
            quarantine.setResult(res);
        }  
  
        public void onNothingSelected(AdapterView<?> arg0) {  
        }  
    }  
}
