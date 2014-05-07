package edu.hbut.livestock.frag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import edu.hbut.livestock.*;
import edu.hbut.livestock.entity.ProducingRecord;
import edu.hbut.livestock.entity.ProducingRecordId;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.ProducingRecordRemoteProcedureCall;
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
public class ProducingRecordFragment extends
        EntityListViewFragment<ProducingRecord, ProducingRecordId> {

    private AsyncCallback<List<ProducingRecord>> action = new ListCallback();

    /**
     * ��ѯ�������
     *
     * @author Hang
     */
    private final class ListCallback implements
            AsyncCallback<List<ProducingRecord>> {
        @Override
        public void call(List<ProducingRecord> result) {
            if (result == null) {
                showText(Tips.NETWORK_WRONG);
                return;
            }
            if (result.size() == 0) {
                showText("�Ѿ�û��������");
                return;
            }
            setEntities(result);
            notifyDataSetChanged();
            showText(Tips.UPDATE_SUCCESS);
        }
    }

    @SuppressWarnings("unchecked")
    private RemoteProcedureCall<ProducingRecord, ProducingRecordId> call = (RemoteProcedureCall<ProducingRecord, ProducingRecordId>) ObjectConfigedFactory
            .getRemoteProcedureCallFactory().getBean(ProducingRecord.class);
    private Spinner yearSpinner;

    public ProducingRecordFragment() {
        super.setCall(call);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = buildView(inflater, container);

        buildModuleSelectSpinner(view);

        buildAddButtonEvent(view);

        buildYearSelect(view);

        buildDeleteButtonEvent(view);

        buildPageButtonEvent(view);

        buildQueryEvent(view);

        buildRefreshButtonEvent(view);

        return view;
    }

    /**
     * ���°�ť�¼�����
     *
     * @param view
     */
    private void buildRefreshButtonEvent(View view) {
        Button refresh = (Button) view.findViewById(R.id.refresh_producing_record_button);
        refresh.setOnClickListener(new RefreshOnClickListener(yearSpinner));
    }

    /**
     * ����Ȧ��Ų�ѯ
     *
     * @param view
     */
    private void buildQueryEvent(View view) {
        Button query = (Button) view
                .findViewById(R.id.query_producing_record_button);
        query.setOnClickListener(new View.OnClickListener() {

            private Dialog queryDialog;

            /**
             * ����Ȧ���ѯ��Ȧ��������
             */
            private EditText houseEditText;

            @Override
            public void onClick(View v) {
                if (queryDialog == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            getAdapter().getContext());
                    builder.setTitle(R.string.query_condition);
                    builder.setPositiveButton(R.string.ok,
                            new OnClickListener() {

                                private Calendar calendar = Calendar
                                        .getInstance();

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    /*
                                     * ��ȡȦ��Ų�����Ȧ��Ž��в�ѯ
                                     */
                                    String houseid = houseEditText.getText()
                                            .toString();
                                    if (houseid == null
                                            || houseid.trim().equals("")) {
                                        showText("������Ȧ���");
                                        return;
                                    }
                                    ProducingRecordRemoteProcedureCall pc = (ProducingRecordRemoteProcedureCall) call;
                                    calendar.set(Calendar.YEAR,
                                            (Integer) yearSpinner
                                                    .getSelectedItem());
                                    pc.queryByHouse(
                                            Integer.parseInt(houseid),
                                            ProducingRecordRemoteProcedureCall.DEFAULT_PAGE_START,
                                            ProducingRecordRemoteProcedureCall.DEFAULT_PAGE_SIZE,
                                            new Date(calendar.getTimeInMillis()),
                                            action);
                                }
                            });
                    builder.setNegativeButton(R.string.cancel, null);
                    /*
                        ���öԻ�������ݣ����ز�����Դ
                     */
                    LayoutInflater inflater = (LayoutInflater) getAdapter()
                            .getContext().getSystemService(
                                    Context.LAYOUT_INFLATER_SERVICE);
                    View view = inflater.inflate(
                            R.layout.producing_record_query, null);
                    builder.setView(view);
                    houseEditText = (EditText) view
                            .findViewById(R.id.producing_record_query);
                    queryDialog = builder.create();
                }
                queryDialog.show();
            }
        });
    }

    /**
     * ��Ӽ�¼��ʱ��ѡ���¼�
     *
     * @param view
     */
    private void buildYearSelect(View view) {
        yearSpinner = (Spinner) view
                .findViewById(R.id.producing_record_year_select);
        super.buildYearSelectSpinner(yearSpinner);

    }

    /**
     * ��ӷ�ҳ��ť�¼��������
     *
     * @param view
     */
    private void buildPageButtonEvent(View view) {
        Button next = (Button) view
                .findViewById(R.id.next_producing_record_button);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nextPage(new Date(System.currentTimeMillis()), action);
            }
        });

        Button pre = (Button) view
                .findViewById(R.id.pre_producing_record_button);
        pre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                prePage(new Date(System.currentTimeMillis()), action);
            }
        });
    }

    /**
     * ���ɾ����ť�¼��������
     *
     * @param view
     */
    private void buildDeleteButtonEvent(View view) {
        Button delete = (Button) view
                .findViewById(R.id.delete_producing_record_button);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<ProducingRecord> records = getAdapter().getSelectedItems();
                for (ProducingRecord record : records) {
                    delete(record.getId(), null, new AsyncCallback<String>() {

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
     * ��Ӱ�ť�¼��������
     *
     * @param view
     */
    private void buildAddButtonEvent(View view) {
        Button add = (Button) view
                .findViewById(R.id.add_producing_record_button);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getAdapter().getContext(),
                        AddProducingRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * ����ѡ�����¼��������
     *
     * @param view
     */
    private void buildModuleSelectSpinner(View view) {
        final Spinner spinner = (Spinner) view
                .findViewById(R.id.producing_record_manager_submodule_select);
        /*
        ����ѡ�����е�����
         */
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
      //##################################�޸�2---->1
        spinner.setSelection(1);
        /*
        ���ѡ���¼������л�����
         */
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int id, long position) {
                if (spinner.getSelectedItem().equals(
                        ModuleId.PRODUCING_RECORD.getModuleName())) {
                    return;
                }
                if (getFragmentSwitchCallback() != null) {
                    getFragmentSwitchCallback().execute(
                            ModuleId.fromName(spinner.getSelectedItem()
                                    .toString()), ModuleId.PRODUCING_RECORD,
                            ProducingRecordFragment.this);
                  //##################################�޸�2------->1
                    spinner.setSelection(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private View buildView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(
                R.layout.producing_record_manager_fragment_view, container,
                false);
        if (this.getAdapter() != null) {
            ListView listView = (ListView) view
                    .findViewById(R.id.producing_record_listview);
            if (listView != null) {
                listView.setAdapter(this.getAdapter());
            }
        }
        return view;
    }

    public void initData() {
        listData(RemoteProcedureCall.DEFAULT_PAGE_START,
                RemoteProcedureCall.DEFAULT_PAGE_SIZE);
        super.initData();
    }

    @Override
    public void initData(Date headerData) {
        list(RemoteProcedureCall.DEFAULT_PAGE_START,
                RemoteProcedureCall.DEFAULT_PAGE_SIZE, headerData, action);
        super.initData(headerData);
    }

}
