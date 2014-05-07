package edu.hbut.livestock.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import edu.hbut.livestock.AddLivestockActivity;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.Livestock;
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
public class LivestockFragment extends EntityListViewFragment<Livestock, String> {

    private AsyncCallback<List<Livestock>> action = new ListCallback();

    /**
     * 查询结果处理
     *
     * @author Hang
     */
    private final class ListCallback implements AsyncCallback<List<Livestock>> {
        @Override
        public void call(List<Livestock> result) {
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
    private RemoteProcedureCall<Livestock, String> call = (RemoteProcedureCall<Livestock, String>) ObjectConfigedFactory
            .getRemoteProcedureCallFactory().getBean(Livestock.class);

    /**
     * 年份选择器
     */
    private Spinner yearSelect;

    public LivestockFragment() {
        super.setCall(call);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = buildView(inflater, container);

        buildModuleSelectSpinner(view);

        buildAddButtonEvent(view);

        buildRefreshButtonEvent(view);

        buildDeleteButtonEvent(view);

        buildPageButtonEvent(view);

        buildYearSelect(view);

        return view;
    }

    /**
     * 记录的时间年份选择器
     *
     * @param view
     */
    private void buildYearSelect(View view) {
        yearSelect = (Spinner) view.findViewById(R.id.livestock_select);
        super.buildYearSelectSpinner(yearSelect);
    }

    /**
     * 分页按钮事件处理
     *
     * @param view
     */
    private void buildPageButtonEvent(View view) {
        Button next = (Button) view.findViewById(R.id.next_livestock_button);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nextPage(new Date(System.currentTimeMillis()), action);
            }
        });

        Button pre = (Button) view.findViewById(R.id.pre_livestock_button);
        pre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                prePage(new Date(System.currentTimeMillis()), action);
            }
        });
    }

    /**
     * 删除按钮事件处理
     *
     * @param view
     */
    private void buildDeleteButtonEvent(View view) {
        Button delete = (Button) view
                .findViewById(R.id.delete_livestock_button);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<Livestock> records = getAdapter().getSelectedItems();
                for (Livestock livestock : records) {
                    delete(livestock.getLivestockid(),
                            new Date(System.currentTimeMillis()),
                            new AsyncCallback<String>() {

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
     * 更新按钮事件处理
     *
     * @param view
     */
    private void buildRefreshButtonEvent(View view) {
        Button refresh = (Button) view.findViewById(R.id.livestock_refresh_button);
        refresh.setOnClickListener(new RefreshOnClickListener(yearSelect));
    }

    /**
     * 添加按钮事件处理程序
     *
     * @param view
     */
    private void buildAddButtonEvent(View view) {
        Button add = (Button) view.findViewById(R.id.add_livestock_button);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getAdapter().getContext(),
                        AddLivestockActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 功能选择器事件处理
     *
     * @param view
     */
    private void buildModuleSelectSpinner(View view) {
        final Spinner spinner = (Spinner) view
                .findViewById(R.id._submodule_select);
        spinner.setAdapter(new BaseAdapter() {

            private ModuleId[] ids = {
//            		ModuleId.HOUSE_INFO,
                    ModuleId.DEATH_PROCESSING, ModuleId.PRODUCING_RECORD,
                    ModuleId.LIVESTOCK};

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
        //##################################修改3——>2
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int id, long position) {
                if (spinner.getSelectedItem().equals(
                        ModuleId.LIVESTOCK.getModuleName())) {
                    return;
                }
                if (getFragmentSwitchCallback() != null) {
                    getFragmentSwitchCallback().execute(
                            ModuleId.fromName(spinner.getSelectedItem()
                                    .toString()), ModuleId.LIVESTOCK,
                            LivestockFragment.this);
                  //##################################修改3——>2
                    spinner.setSelection(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private View buildView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.livestock_fragment_view,
                container, false);
        if (this.getAdapter() != null) {
            ListView listView = (ListView) view
                    .findViewById(R.id.livestock_listview);
            if (listView != null) {
                listView.setAdapter(this.getAdapter());
            }
        }
        return view;
    }

    @Override
    public void initData() {
        Date date = new Date(System.currentTimeMillis());
        list(RemoteProcedureCall.DEFAULT_PAGE_START,
                RemoteProcedureCall.DEFAULT_PAGE_SIZE, date, action);
    }

    @Override
    public void listData(int start, int count) {
        list(start, count, new Date(System.currentTimeMillis()), action);
    }


}
