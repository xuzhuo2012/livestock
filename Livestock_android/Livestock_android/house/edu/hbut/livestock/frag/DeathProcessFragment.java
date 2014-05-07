package edu.hbut.livestock.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import edu.hbut.livestock.AddDeathProcessActivity;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.DeathProcess;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.ModuleId;
import edu.hbut.livestock.util.Tips;

import java.sql.Date;
import java.util.List;

/**
 * @author Hang
 */
public class DeathProcessFragment extends EntityListViewFragment<DeathProcess, Date> {

    private AsyncCallback<List<DeathProcess>> action = new ListCallback();

    /**
     * 查询结果处理
     *
     * @author Hang
     */
    private final class ListCallback implements AsyncCallback<List<DeathProcess>> {
        @Override
        public void call(List<DeathProcess> result) {
            if (result == null) {
                showText(Tips.NETWORK_WRONG);
                return;
            }
            if (result.size() == 0) {
                showText("已经没有数据了");
                return;
            }
            setEntities(result);
            notifyDataSetChanged();
            showText(Tips.UPDATE_SUCCESS);
        }
    }

    @SuppressWarnings("unchecked")
    private RemoteProcedureCall<DeathProcess, Date> call = (RemoteProcedureCall<DeathProcess, Date>) ObjectConfigedFactory.getRemoteProcedureCallFactory().getBean(DeathProcess.class);

    private Spinner yearSelect;
    public DeathProcessFragment() {
        super.setCall(call);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = buildView(inflater, container);

        buildModuleSelectSpinner(view);

        buildAddButtonEvent(view);

        buildRefreshButtonEvent(view);

        buildDeleteButtonEvent(view);

        buildPabeButtonEvent(view);

        buildYearSelectSpinnerEvent(view);

        return view;
    }

    /**
     * 年份选择事件处理程序
     *
     * @param view
     */
    private void buildYearSelectSpinnerEvent(View view) {
        yearSelect = (Spinner) view.findViewById(R.id.death_process_year_select);
        super.buildYearSelectSpinner(yearSelect);
    }

    /**
     * 分页按钮事件处理程序
     *
     * @param view
     */
    private void buildPabeButtonEvent(View view) {
        Button next = (Button) view.findViewById(R.id.next_death_process_button);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nextPage(getCurrentSelectedDate(yearSelect), action);
            }
        });

        Button pre = (Button) view.findViewById(R.id.pre_death_process_button);
        pre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                prePage(getCurrentSelectedDate(yearSelect), action);
            }
        });
    }

    /**
     * 删除按钮事件处理
     *
     * @param view
     */
    private void buildDeleteButtonEvent(View view) {
        Button delete = (Button) view.findViewById(R.id.delete_death_process_button);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<DeathProcess> records = getAdapter().getSelectedItems();
                for (DeathProcess deathprocess : records) {
                    delete(deathprocess.getProcessDate(), new Date(System.currentTimeMillis()), new AsyncCallback<String>() {

                        @Override
                        public void call(String result) {
                            showText(result);
                        }
                    });
                }
                getAdapter().getEntities().removeAll(records);
                records.clear();
                notifyDataSetChanged();
            }
        });
    }

    /**
     * 更新按钮事件处理程序
     *
     * @param view
     */
    private void buildRefreshButtonEvent(View view) {
        Button refresh = (Button) view.findViewById(R.id.death_process_refresh_button);
        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    /**
     * 添加按钮事件处理程序
     *
     * @param view
     */
    private void buildAddButtonEvent(View view) {
        Button add = (Button) view.findViewById(R.id.add_death_process_button);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getAdapter().getContext(), AddDeathProcessActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 功能模块切换
     *
     * @param view
     */
    private void buildModuleSelectSpinner(View view) {
        final Spinner spinner = (Spinner) view.findViewById(R.id.death_process_submodule_select);
        spinner.setAdapter(new BaseAdapter() {

            private ModuleId[] ids = {
//                    ModuleId.HOUSE_INFO, 
                    ModuleId.DEATH_PROCESSING, ModuleId.PRODUCING_RECORD, ModuleId.LIVESTOCK
            };

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                TextView textView;
                if (view == null) {
                    textView = new TextView(getAdapter().getContext());
                } else {
                    textView = (TextView) view;
                }
                textView.setText(ids[position].getModuleName());
                return textView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return ids[position].getModuleName();
            }

            @Override
            public int getCount() {
                return ids.length;
            }
        });
        //######################################修改了1——>0
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int id, long position) {
                if (spinner.getSelectedItem().equals(ModuleId.DEATH_PROCESSING.getModuleName())) {
                    return;
                }
                if (getFragmentSwitchCallback() != null) {
                    getFragmentSwitchCallback().execute(ModuleId.fromName(spinner.getSelectedItem().toString()), ModuleId.DEATH_PROCESSING, DeathProcessFragment.this);
                    //#####################################修改1——>0
                    spinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private View buildView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.death_process_fragment_view, container, false);
        if (this.getAdapter() != null) {
            ListView listView = (ListView) view.findViewById(R.id.death_process_listview);
            if (listView != null) {
                listView.setAdapter(this.getAdapter());
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

    @Override
    public void initData(Date headerData) {
        list(RemoteProcedureCall.DEFAULT_PAGE_START, RemoteProcedureCall.DEFAULT_PAGE_SIZE, headerData, action);
    }
}
