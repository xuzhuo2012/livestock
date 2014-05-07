package edu.hbut.livestock.frag;

import java.sql.Date;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.DisinfectRecord;
import edu.hbut.livestock.entity.DisinfectRecordId;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.MyListView;
import edu.hbut.livestock.util.MyListView.OnRefreshListener;
import edu.hbut.livestock.util.Tips;

/**
 * @author Hang
 */
public class DisinfectRecordFragment extends EntityListViewFragment<DisinfectRecord, DisinfectRecordId> {
	MyListView listView;
        private AsyncCallback<List<DisinfectRecord>> action = new ListCallback();

        /**
         * @author Hang
         */
        private final class ListCallback implements AsyncCallback<List<DisinfectRecord>> {
                @Override
                public void call(List<DisinfectRecord> result) {
                        if (result == null) {
                                showText(Tips.NETWORK_WRONG);
                                return;
                        }
                        if (result.size() == 0) {
                                showText(Tips.NO_DATA);
                                return;
                        }
                        setEntities(result);
                        notifyDataSetChanged();
                        showText(Tips.UPDATE_SUCCESS);
                }
        }

        @SuppressWarnings("unchecked")
        private RemoteProcedureCall<DisinfectRecord, DisinfectRecordId> call = (RemoteProcedureCall<DisinfectRecord, DisinfectRecordId>) ObjectConfigedFactory.getRemoteProcedureCallFactory().getBean(DisinfectRecord.class);

        /**
         * ���ѡ��
         */
        private Spinner yearSelect;

        public DisinfectRecordFragment() {
                super.setCall(call);
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = buildView(inflater, container);

                buildRefreshButtonEvent(view);

                buildPageButtonEvent(view);

                buildYearSelectEvent(view);

                return view;
        }

//        /**
//         * ��ѯ��ť�¼��������
//         *
//         * @param view
//         */
//        private void buildQueryButtonEvent(View view) {
//                Button query = (Button) view.findViewById(R.id.query_disinfect_button);
//                query.setOnClickListener(new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View view) {
//
//                        }
//                });
//        }

        /**
         * ���ѡ��
         *
         * @param view
         */
        private void buildYearSelectEvent(View view) {
                yearSelect = (Spinner) view.findViewById(R.id.disinfect_year_select);
                super.buildYearSelectSpinner(yearSelect);
        }

        /**
         * ��ҳ�¼��������
         *
         * @param view
         */
        private void buildPageButtonEvent(View view) {
                Button next = (Button) view.findViewById(R.id.next_disinfect_button);
                next.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                nextPage(getCurrentSelectedDate(yearSelect), action);
                        }
                });

                Button pre = (Button) view.findViewById(R.id.pre_disinfect_button);
                pre.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                prePage(getCurrentSelectedDate(yearSelect), action);
                        }
                });
        }


        /**
         * ���°�ť�¼��������
         *
         * @param view
         */
        private void buildRefreshButtonEvent(View view) {
                Button refresh = (Button) view.findViewById(R.id.disinfect_refresh_button);
                refresh.setOnClickListener(new RefreshOnClickListener());
        }


        /**
         * ������
         * @param inflater
         * @param container
         * @return
         */
        private View buildView(LayoutInflater inflater, ViewGroup container) {
                View view = inflater.inflate(R.layout.disinfect_manager_fragment_view, container, false);
                if (this.getAdapter() != null) {
                        listView = (MyListView) view.findViewById(R.id.disinfectrecord_listview);
                        if (listView != null) {
                                listView.setAdapter(this.getAdapter());
                                listView.setonRefreshListener(new OnRefreshListener() {

                        			@Override
                        			public void onRefresh() {
                        				new AsyncTask<Void, Void, Void>() {

                        					// ������UI�߳��У��ڵ���doInBackground()֮ǰִ��
                        					protected void onPostExecute(Void result) {
                        						getAdapter().notifyDataSetChanged();
                        						initData();
                        						listView.onRefreshComplete();
                        					}

                        					// ��̨���еķ������������з�UI�̣߳�����ִ�к�ʱ�ķ���
                        					protected Void doInBackground(Void... params) {
                        						try {
                        							Thread.sleep(1000);
                        						} catch (Exception e) {
                        							e.printStackTrace();
                        						}
                        						return null;
                        					}

                        				}.execute();

                        			}
                        		});
                        }
                }
                return view;
        }

        @Override
        public void initData() {
                Date date = new Date(System.currentTimeMillis());
                list(RemoteProcedureCall.DEFAULT_PAGE_START, RemoteProcedureCall.DEFAULT_PAGE_SIZE, date, action);
        }

        @Override
        public void listData(int start, int count) {
                list(start, count, new Date(System.currentTimeMillis()), action);
        }

}
