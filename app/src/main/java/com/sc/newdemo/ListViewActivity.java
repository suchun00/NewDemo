package com.sc.newdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by suchun on 2017/8/29.
 */
public class ListViewActivity extends AppCompatActivity {
    @BindView(R.id.listYlfn)
    ListView listYlfn;
    @BindView(R.id.btnPre)
    Button btnPre;
    @BindView(R.id.btnNext)
    Button btnNext;
    List<Map<String, String>> listItem = new ArrayList<Map<String, String>>();
    // 用于显示每列5个Item项。
    int VIEW_COUNT = 3;
    // 用于显示页号的索引
    int index = 0;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity);
        ButterKnife.bind(this);
        getList();
        myAdapter=new MyAdapter(this);
        listYlfn.setAdapter(myAdapter);
    }

    @OnClick({R.id.btnPre, R.id.btnNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPre:
                preView();
                break;
            case R.id.btnNext:
                nextView();
                break;
        }
    }

    private void nextView() {
        index++;

        // 刷新ListView里面的数值。
        myAdapter.notifyDataSetChanged();

        // 检查Button是否可用。
        checkButton();
    }

    private void checkButton() {
        // 索引值小于等于0，表示不能向前翻页了，以经到了第一页了。
        // 将向前翻页的按钮设为不可用。
        if (index <= 0) {
            btnPre.setEnabled(false);
        }else{
            btnPre.setEnabled(true);
        }
        // 值的长度减去前几页的长度，剩下的就是这一页的长度，如果这一页的长度比View_Count小，表示这是最后的一页了，后面在没有了。
        // 将向后翻页的按钮设为不可用。
        if (listItem.size() - index * VIEW_COUNT <= VIEW_COUNT) {
            btnNext.setEnabled(false);
        }
        // 否则将2个按钮都设为可用的。
        else {
            btnNext.setEnabled(true);
        }
    }

    private void preView() {
        index--;

        // 刷新ListView里面的数值。
        myAdapter.notifyDataSetChanged();

        // 检查Button是否可用。
        checkButton();
    }

    public List<Map<String, String>> getList(){
       for(int i=0; i<50; i++){
           Map<String, String> map = new HashMap<>();
           map.put("ylfn_did", "hi"+i+"");
           /*map.put("ylfn_name", "susan"+i+"");
           map.put("gmsfz", "34011"+i+"");*/
           listItem.add(map);
       }
        return listItem;
    }
    public class MyAdapter extends BaseAdapter{
        Activity activity;
        public MyAdapter(Activity a){
            activity = a;
        }
        @Override
        public int getCount() {
            // ori表示到目前为止的前几页的总共的个数。
            int ori = VIEW_COUNT * index;
            // 值的总个数-前几页的个数就是这一页要显示的个数，如果比默认的值小，说明这是最后一页，只需显示这么多就可以了
            if (listItem.size() - ori < VIEW_COUNT) {
                return listItem.size() - ori;
            }
            // 如果比默认的值还要大，说明一页显示不完，还要用换一页显示，这一页用默认的值显示满就可以了。
            else {
                return VIEW_COUNT;
            }
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            /*convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.ylfn,null);
            TextView ylfn_did_view = (TextView)convertView.findViewById(R.id.ylfn_did);
            TextView ylfn_name_view = (TextView)convertView.findViewById(R.id.ylfn_name);
            TextView ylfn_gmsfz_view = (TextView)convertView.findViewById(R.id.gmsfz);
            ylfn_did_view.setText(listItem.get(position + index * VIEW_COUNT).get("ylfn_did"));
            ylfn_name_view.setText(listItem.get(position + index * VIEW_COUNT).get("ylfn_name"));
            ylfn_gmsfz_view.setText(listItem.get(position + index * VIEW_COUNT).get("gmsfz"));*/
            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.account_activity,null);
            TextView name = (TextView) convertView.findViewById(R.id.text);
            name.setText(listItem.get(position + index * VIEW_COUNT).get("ylfn_did"));
            return convertView;
        }
    }
}
