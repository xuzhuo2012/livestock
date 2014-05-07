package edu.hbut.livestock.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import edu.hbut.livestock.AddHouseActivity;
import edu.hbut.livestock.R;
import edu.hbut.livestock.entity.House;
import edu.hbut.livestock.entity.HouseId;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
import edu.hbut.livestock.util.ModuleId;
import edu.hbut.livestock.util.Tips;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * @author Hang
 */
public class HouseFragment extends EntityListViewFragment<House, HouseId> {

    private AsyncCallback<List<House>> action = new ListCallback();

    /**
     * 查询结果处理
     *
     * @author Hang
     */
    private final class ListCallback implements AsyncCallback<List<House>> {
        @Override
        public void call(List<House> result) {
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
    private RemoteProcedureCall<House, HouseId> call = (RemoteProcedureCall<House, HouseId>) ObjectConfigedFactory.getRemoteProcedureCallFactory().getBean(House.class);

    public HouseFragment() {
        super.setCall(call);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = buildView(inflater, container);

        buildModuleSelectSpinner(view);

        buildAddButtonEvent(view);

        buildRefreshButtonEvent(view);

        buildDeleteButtonEvent(view);

        buildPageButtonEvent(view);

        return view;
    }

    /**
     * 添加按钮事件处理程序
     *
     * @param view
     */
    private void buildAddButtonEvent(View view) {
        Button add = (Button) view.findViewById(R.id.add_houseinfo_button);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getAdapter().getContext(), AddHouseActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 更新按钮事件处理程序
     *
     * @param view
     */
    private void buildRefreshButtonEvent(View view) {
        Button refresh = (Button) view.findViewById(R.id.houseinfo_refresh_button);
        refresh.setOnClickListener(new RefreshOnClickListener(null));
    }

    /**
     * 删除按钮事件处理程序
     *
     * @param view
     */
    private void buildDeleteButtonEvent(View view) {
        Button delete = (Button) view.findViewById(R.id.delete_houseinfo_button);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<House> records = getAdapter().getSelectedItems();
                for (House house : records) {
                    delete(house.getId(), null, new AsyncCallback<String>() {

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
     * 分页按钮事件处理程序
     *
     * @param view
     */
    private void buildPageButtonEvent(View view) {
        Button next = (Button) view.findViewById(R.id.next_houseinfo_button);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nextPage(new Date(System.currentTimeMillis()), action);
            }
        });

        Button pre = (Button) view.findViewById(R.id.pre_houseinfo_button);
        pre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                prePage(new Date(System.currentTimeMillis()), action);
            }
        });
    }

    /**
     * 功能模块选择器事件处理程序
     *
     * @param view
     */
    private void buildModuleSelectSpinner(View view) {
        final Spinner spinner = (Spinner) view.findViewById(R.id.houseinfo_manager_submodule_select);
        spinner.setAdapter(new BaseAdapter() {

            private ModuleId[] ids = { 
//            		ModuleId.HOUSE_INFO, 
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
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int id, long position) {
                if (spinner.getSelectedItem().equals(ModuleId.HOUSE_INFO.getModuleName())) {
                    return;
                }
                if (getFragmentSwitchCallback() != null) {
                    getFragmentSwitchCallback().execute(ModuleId.fromName(spinner.getSelectedItem().toString()), ModuleId.HOUSE_INFO, HouseFragment.this);
                    spinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * 主功能界面资源文件设置
     *
     * @param inflater
     * @param container
     * @return
     */
    private View buildView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.houseinfo_manager_fragment_view, container, false);
        if (this.getAdapter() != null) {
            ListView listView = (ListView) view.findViewById(R.id.houseinfo_listview);
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

}
