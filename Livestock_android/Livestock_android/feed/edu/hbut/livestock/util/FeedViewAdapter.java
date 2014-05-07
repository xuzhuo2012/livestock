package edu.hbut.livestock.util;

import java.text.SimpleDateFormat;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.Feed;
import edu.hbut.livestock.util.TableEntityViewAdapter;

/**
 * ������ʾ���ϱ䶯�����в�ѯ���ļ�¼
 * 
 * @author Hang
 * 
 */
public class FeedViewAdapter extends TableEntityViewAdapter<Feed> {
	
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public FeedViewAdapter() {
		super();
	}

	public FeedViewAdapter(List<Feed> entities, Context context) {
		super(entities, context);
	}

	@Override
	protected void setViewContent(final Feed feed, View view) {
		/*
		 * ���ø�TextViewҪ��ʾ��ֵ
		 */
		setTextOnView(R.id.feed_batchNumber_show, feed.getBatchNumber(), view);
		setTextOnView(R.id.feed_changeCount_show, Integer.toString(feed.getChangeCount()), view);
		setTextOnView(R.id.feed_changeDate_show, format.format(feed.getChangeDate()), view);
		setTextOnView(R.id.feed_changeType_show, feed.getChangeType(), view);
		setTextOnView(R.id.feed_factory_show, feed.getFactory(), view);
		setTextOnView(R.id.feed_feedName_show, feed.getFeedName(), view);
		setTextOnView(R.id.feed_remark_show, feed.getRemark(), view);
		
		CheckBox box = (CheckBox) view.findViewById(R.id.feed_item_select);
		box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					//����ǰ���������ѡ���б���
					select(feed);
				} else {
					//����ǰ�����Ƴ���ѡ���б�
					deselect(feed);
				}
			}
		});
	}

	@Override
	protected int getEntityViewLayoutResourceId() {
		return R.layout.feed_view;
	}

}
