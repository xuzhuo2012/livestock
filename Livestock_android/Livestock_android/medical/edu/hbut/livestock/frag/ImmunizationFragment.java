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
import edu.hbut.livestock.entity.Immunization;
import edu.hbut.livestock.entity.ImmunizationId;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.MyListView;
import edu.hbut.livestock.util.MyListView.OnRefreshListener;
import edu.hbut.livestock.util.Tips;

/**
 * @author Hang
 */
public class ImmunizationFragment extends EntityListViewFragment<Immunization, ImmunizationId> {
	MyListView listView;
        private AsyncCallback<List<Immunization>> action = new ListCallback();

        /**
         * @author Hang
         */
        private final class ListCallback implements AsyncCallback<List<Immunization>> {
                @Override
                public void call(List<Immunization> result) {
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
        private RemoteProcedureCall<Immunization, ImmunizationId> call = (RemoteProcedureCall<Immunization, ImmunizationId>) ObjectConfigedFactory.getRemoteProcedureCallFactory().getBean(Immunization.class);

        /**
         * 年份选择
         */
        private Spinner yearSelect;

        public ImmunizationFragment() {
                super.setCall(call);
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = buildView(inflater, container);

                buildRefreshButtonEvent(view);

                buildPageButtonEvent(view);

                buildYearSelectEvent(view);

                return view;
        }

        /**
         * 年份选择
         *
         * @param view
         */
        private void buildYearSelectEvent(View view) {
                yearSelect = (Spinner) view.findViewById(R.id.immunization_year_select);
                super.buildYearSelectSpinner(yearSelect);
        }

        /**
         * 分页事件处理程序
         *
         * @param view
         */
        private void buildPageButtonEvent(View view) {
                Button next = (Button) view.findViewById(R.id.next_immunization_button);
                next.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                nextPage(getCurrentSelectedDate(yearSelect), action);
                        }
                });

                Button pre = (Button) view.findViewById(R.id.pre_immunization_button);
                pre.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                prePage(getCurrentSelectedDate(yearSelect), action);
                        }
                });
        }

        /**
         * 更新按钮事件处理程序
         *
         * @param view
         */
        private void buildRefreshButtonEvent(View view) {
                Button refresh = (Button) view.findViewById(R.id.immunization_refresh_button);
                refresh.setOnClickListener(new RefreshOnClickListener());
        }

        /**
         * 主界面
         * @param inflater
         * @param container
         * @return
         */
        private View buildView(LayoutInflater inflater, ViewGroup container) {
                View view = inflater.inflate(R.layout.immunization_manager_fragment_view, container, false);
                if (this.getAdapter() != null) {
                		listView = (MyListView) view.findViewById(R.id.immunization_listview);
                        if (listView != null) {
                                listView.setAdapter(this.getAdapter());
                                listView.setonRefreshListener(new OnRefreshListener() {

                        			@Override
                        			public void onRefresh() {
                        				new AsyncTask<Void, Void, Void>() {

                        					// 运行在UI线程中，在调用doInBackground()之前执行
                        					protected void onPostExecute(Void result) {
                        						getAdapter().notifyDataSetChanged();
                        						initData();
                        						listView.onRefreshComplete();
                        					}

                        					// 后台运行的方法，可以运行非UI线程，可以执行耗时的方法
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
