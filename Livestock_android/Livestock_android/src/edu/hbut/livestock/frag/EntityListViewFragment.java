package edu.hbut.livestock.frag;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import edu.hbut.livestock.entity.QuarantineApply;
import edu.hbut.livestock.http.AsyncCallback;
import edu.hbut.livestock.http.RemoteProcedureCall;
import edu.hbut.livestock.http.RequestException;
import edu.hbut.livestock.util.EntityViewAdapter;
import edu.hbut.livestock.util.StaticDatas;
import edu.hbut.livestock.util.Tips;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @param <T>
 * @author Hang
 */
public class EntityListViewFragment<T, ID extends Serializable> extends Fragment {

    /**
     * ʵ����ʾ������
     */
    private EntityViewAdapter<T> adapter;

    /**
     * Զ�̵���ģ��
     */
    private RemoteProcedureCall<T, ID> call;

    private FragmentSwitchCallback fragmentSwitchCallback;

    private Context context;

    /**
     * ��¼��ǰFragment�Ƿ��ǳ�����ʾ
     */
    private boolean state = true;

    public EntityListViewFragment() {
        super();
    }

    public EntityViewAdapter<T> getAdapter() {
        return adapter;
    }

    public void setAdapter(EntityViewAdapter<T> adapter) {
        this.adapter = adapter;
    }

    /**
     * ��ȡ��ʼ������
     */
    public void initData() {
        //����ҳ��ѯ�е�ǰҳ������Ϊ1
        synchronized (call) {
            call.resetCurrentPage();
        }
    }

    /**
     * ���ݱ�ͷ��ȡ��ʼ����
     *
     * @param headerData ��ͷ�е�ʱ����Ϣ
     */
    public void initData(Date headerData) {
        //����ҳ��ѯ�е�ǰҳ������Ϊ1
        synchronized (call) {
            call.resetCurrentPage();
        }
    }

    public void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public List<T> getEntities() {
        return adapter.getEntities();
    }

    public void setEntities(List<T> entities) {
        adapter.setEntities(entities);
    }

    public RemoteProcedureCall<T, ID> getCall() {
        return call;
    }

    public void setCall(RemoteProcedureCall<T, ID> call) {
        this.call = call;
    }

    public void add(T t, AsyncCallback<String> action) throws RequestException {
        if (call != null) {
            call.add(t, action);
        }
    }

    public void delete(ID id, Date headerDate, AsyncCallback<String> action)
            throws RequestException {
        if (call != null) {
            call.delete(id, headerDate, action);
        }
    }

    public void findById(ID id, Date headerDate, AsyncCallback<T> action)
            throws RequestException {
        if (call != null) {
            call.findById(id, headerDate, action);
        }
    }

    public void list(int start, int count, Date headerDate,
                     AsyncCallback<List<T>> action) throws RequestException {
        if (state) {
            state = false;
        }
        if (call != null) {
            call.list(start, count, headerDate, action);
        }
    }

    public void update(T t, AsyncCallback<String> action)
            throws RequestException {
        call.update(t, action);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void listData(int start, int count) {
    }

    public void nextPage(Date headerdate, AsyncCallback<List<T>> action) {
        call.nextPage(headerdate, action);
    }

    public void prePage(Date headerdate, AsyncCallback<List<T>> action) {
        call.prePage(headerdate, action);
    }

    public FragmentSwitchCallback getFragmentSwitchCallback() {
        return fragmentSwitchCallback;
    }

    public void setFragmentSwitchCallback(
            FragmentSwitchCallback fragmentSwitchCallback) {
        this.fragmentSwitchCallback = fragmentSwitchCallback;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * ��ʾ��ʾ��Ϣ
     *
     * @param text
     */
    public void showText(String text) {
        Toast.makeText(adapter.getContext(), text, Toast.LENGTH_LONG).show();
    }

    /**
     * �������ѡ�����е����ݣ�������¼��������
     *
     * @param yearSelect
     */
    public void buildYearSelectSpinner(final Spinner yearSelect) {
        final List<Integer> list = new ArrayList<Integer>();
        Calendar calendar = Calendar.getInstance();
        for (int i = StaticDatas.START_YEAR; i <= calendar.get(Calendar.YEAR); i++) {
            list.add(i);
        }
        yearSelect.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView;
                if (convertView == null) {
                    textView = new TextView(getAdapter().getContext());
                } else {
                    textView = (TextView) convertView;
                }
                textView.setText(Integer.toString(list.get(position)));
                return textView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        yearSelect.setSelection(list.size() - 1);
        yearSelect
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    Calendar calendar = Calendar.getInstance();

                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        calendar.set(Calendar.YEAR,
                                (Integer) yearSelect.getSelectedItem());
                        initData(new Date(calendar.getTimeInMillis()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }

    /**
     * ��ǰʱ��ѡ����ѡ���ʱ��
     *
     * @param yearSelect ���ѡ����
     * @return
     */
    protected Date getCurrentSelectedDate(Spinner yearSelect) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,
                (Integer) yearSelect.getSelectedItem());
        int year = (Integer) yearSelect.getSelectedItem();
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * �����¼�������
     */
    protected class RefreshOnClickListener implements View.OnClickListener {

        private Spinner yearSelect;

        protected RefreshOnClickListener() {
        }

        protected RefreshOnClickListener(Spinner yearSelect) {
            this.yearSelect = yearSelect;
        }

        private Calendar calendar = Calendar.getInstance();

        @Override
        public void onClick(View v) {
            if (yearSelect != null) {
                int year = (Integer) yearSelect.getSelectedItem();
                calendar.set(Calendar.YEAR, year);
                initData(new Date(calendar.getTimeInMillis()));
            } else {
                initData();
            }
        }
    }
}