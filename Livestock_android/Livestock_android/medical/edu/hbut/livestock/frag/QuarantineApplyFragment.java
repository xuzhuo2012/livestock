package edu.hbut.livestock.frag;

import java.sql.Date;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import edu.hbut.livestock.ModifyQuarantineApplyActivity;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.QuarantineApply;
import edu.hbut.livestock.entity.QuarantineApplyId;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.MyListView;
import edu.hbut.livestock.util.MyListView.OnRefreshListener;
import edu.hbut.livestock.util.Tips;

/**
 * @author Hang
 */
public class QuarantineApplyFragment extends EntityListViewFragment<QuarantineApply, QuarantineApplyId> {
		MyListView listView;
        private AsyncCallback<List<QuarantineApply>> action = new ListCallback();

        /**
         * 查询结果处理
         *
         * @author Hang
         */
        private final class ListCallback implements AsyncCallback<List<QuarantineApply>> {
                @Override
                public void call(List<QuarantineApply> result) {
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
        private RemoteProcedureCall<QuarantineApply, QuarantineApplyId> call = (RemoteProcedureCall<QuarantineApply, QuarantineApplyId>) ObjectConfigedFactory.getRemoteProcedureCallFactory().getBean(QuarantineApply.class);

        /**
         * 年份选择器
         */
        private Spinner yearSelect;

        public QuarantineApplyFragment() {
                super.setCall(call);
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = buildView(inflater, container);

                buildModuleSelectSpinner(view);

                buildAddButtonEvent(view);

                buildRefreshButtonEvent(view);

                buildDeleteButtonEvent(view);

                buildPageButtonEvent(view);

                buildYearSelectEvent(view);

//                buildQueryButtonEvent(view);

                return view;
        }
//
//        /**
//         * 查询按钮事件处理
//         *
//         * @param view
//         */
//        private void buildQueryButtonEvent(View view) {
//                Button query = (Button) view.findViewById(R.id.query_quarantine_apply_button);
//                query.setOnClickListener(new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View view) {
//
//                        }
//                });
//        }

        /**
         * 年份选择器
         *
         * @param view
         */
        private void buildYearSelectEvent(View view) {
                yearSelect = (Spinner) view.findViewById(R.id.quarantine_apply_year_select);
                super.buildYearSelectSpinner(yearSelect);
        }

        /**
         * 分页按钮事件处理程序
         *
         * @param view
         */
        private void buildPageButtonEvent(View view) {
                Button next = (Button) view.findViewById(R.id.next_quarantine_apply_button);
                next.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                nextPage(getCurrentSelectedDate(yearSelect), action);
                        }
                });

                Button pre = (Button) view.findViewById(R.id.pre_quarantine_apply_button);
                pre.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                prePage(getCurrentSelectedDate(yearSelect), action);
                        }
                });
        }

        /**
         * 删除按钮事件处理程序
         *
         * @param view
         */
        private void buildDeleteButtonEvent(View view) {
                Button delete = (Button) view.findViewById(R.id.delete_quarantine_applyrecord_button);
                delete.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                                if (getAdapter() == null) {
                                        return;
                                }
                                List<QuarantineApply> records = getAdapter().getSelectedItems();
                                for (final QuarantineApply apply : records) {
                                        delete(apply.getId(), apply.getHeaderDate(), new AsyncCallback<String>() {

                                                @Override
                                                public void call(String result) {
                                                        showText(result);
                                                        if (Tips.DELETE_SUCCESS.equals(result)) {
                                                                getAdapter().getEntities().remove(apply);
                                                                notifyDataSetChanged();
                                                        }
                                                }
                                        });
                                }
                                records.clear();
                        }
                });
        }

        /**
         * 更新按钮事件处理
         *
         * @param view
         */
        private void buildRefreshButtonEvent(View view) {
                Button refresh = (Button) view.findViewById(R.id.quarantine_apply_refresh_button);
                refresh.setOnClickListener(new RefreshOnClickListener());
        }

        /**
         * 添加按钮事件处理程序
         *
         * @param view
         */
        private void buildAddButtonEvent(View view) {
                Button add = (Button) view.findViewById(R.id.add_quarantine_applyrecord_button);
                add.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                        	List<QuarantineApply> list = getAdapter().getSelectedItems();
                        	QuarantineApply app = null;
                        	if (list != null && list.size()>0) {
                        		app = list.get(0);
							}
                        	Bundle bundle = new Bundle();
                        	bundle.putSerializable("quarantine", app);
                            Intent intent = new Intent(getAdapter().getContext(), ModifyQuarantineApplyActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                });
        }

        /**
         * 子功能模块切换
         *
         * @param view
         */
        private void buildModuleSelectSpinner(View view) {
//                final Spinner spinner = (Spinner) view.findViewById(R.id.quarantine_apply_manager_submodule_select);
//                spinner.setAdapter(new SubModuleBaseAdapter());
//                spinner.setOnItemSelectedListener(new SubModuleItemSelectedListener(spinner));
        }

        private View buildView(LayoutInflater inflater, ViewGroup container) {
                View view = inflater.inflate(R.layout.quarantine_apply_manager_fragment_view, container, false);
                if (this.getAdapter() != null) {
                		listView = (MyListView) view.findViewById(R.id.quarantine_applyrecord_listview);
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
