package edu.hbut.livestock;

import edu.hbut.livestock.entity.Feed;
import edu.hbut.livestock.http.AsyncCallback;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.*;

/**
 * 添加饲料变动记录界面
 * 
 * @author Hang
 * 
 */
public class AddFeedActivity extends FeedOperateActivity {

	@Override
	protected void initWidget() {
		/*
		 * 初始化控件
		 */
		changeDateEditText = (EditText) findViewById(R.id.add_feed_changeDate);
                changeDateEditText.setText(format.format(new Date()));
		feedNameEditText = (EditText) findViewById(R.id.add_feed_name);
		factoryEditText = (EditText) findViewById(R.id.add_feed_factory);
		batchNumberEditText = (EditText) findViewById(R.id.add_feed_batchNumber);
		changeTypeEditText = (EditText) findViewById(R.id.add_feed_changeType);
		changeCountEditText = (EditText) findViewById(R.id.add_feed_changeCount);
		remarkEditText = (EditText) findViewById(R.id.add_feed_remark);

		submitButton = (Button) findViewById(R.id.add_feed_submit);
		cancelButton = (Button) findViewById(R.id.add_feed_cancel_button);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_add_feed;
	}

	@Override
	protected void request(Feed feed) {
		call.add(feed, new AsyncCallback<String>() {

			@Override
			public void call(String t) {
				AddFeedActivity.this.finish();
				Toast.makeText(AddFeedActivity.this, t, Toast.LENGTH_LONG).show();
			}
		});
	}

}
