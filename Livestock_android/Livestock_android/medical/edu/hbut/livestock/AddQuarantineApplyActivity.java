package edu.hbut.livestock;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class AddQuarantineApplyActivity extends ToastActivity {
	
	@SuppressWarnings("unchecked")
	private RemoteProcedureCall<QuarantineApply, QuarantineApplyId> call = (RemoteProcedureCall<QuarantineApply, QuarantineApplyId>) ObjectConfigedFactory.getRemoteProcedureCallFactory().getBean(QuarantineApply.class);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_quarantine_apply);
		
		Button add = (Button) findViewById(R.id.add_quarantine_apply_button);
		
		Button cancel = (Button) findViewById(R.id.cancel_quarantine_apply_button);
		
		final EditText countEditText = (EditText) findViewById(R.id.add_quarantine_apply_count);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String count = countEditText.getText().toString();
				if (count == null || count.trim().equals("")) {
					showLongToast("«Î ‰»Î…Í±®ºÏ“ﬂ–Û«› ˝¡ø");
				}
				QuarantineApply apply = new QuarantineApply();
				apply.setCount(Integer.parseInt(count));
				apply.setHeaderDate(new Date(System.currentTimeMillis()));
				call.add(apply, new AsyncCallback<String>() {

					@Override
					public void call(String result) {
						showLongToast(result);
					}
				});
				finish();
			}
			
		});

        EditText applyDateTest = (EditText) findViewById(R.id.add_quarantine_apply_date);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        applyDateTest.setText(fmt.format(new Date(System.currentTimeMillis())));
        applyDateTest.setEnabled(false);

        EditText applyStateTest = (EditText) findViewById(R.id.add_quarantine_apply_state);
        applyStateTest.setText("¥˝…Û");
        applyStateTest.setEnabled(false);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
