package com.sc.newdemo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sc.entity.NetAddress;
import com.sc.greendao.dao.NetAddressDao;
import com.sc.utils.App;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.net)
    Button net;
    @BindView(R.id.tv)
    TextView tv;
    private NetAddressDao netAddressDao;
    @BindView(R.id.button1)
    Button button1;
    String netName = "NingGuo";
    String ipAddress;
    private Calendar c;

    @OnClick(R.id.button1)
    public void getDate(View view) {
        c = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                button1.setText("" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.net)
    public void updateNet(View view){
        netAddressDao = App.getInstance().getSession().getNetAddressDao();
        //insert();
        update();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void update() {
        ipAddress = et.getText().toString().trim();
        NetAddress netAddress = netAddressDao.load((long) 1);
        if (netAddress != null) {
            netAddress.setIpAddress(ipAddress);
            netAddressDao.update(netAddress);
            Toast.makeText(getApplicationContext(), "网址更改成功", Toast.LENGTH_SHORT);
            et.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "网址更改失败", Toast.LENGTH_SHORT);
        }
    }

    /**
     * 数据库添加信息
     */
    private void insert() {
        ipAddress = et.getText().toString().trim();
        NetAddress netAddress = new NetAddress(null, netName, ipAddress);
        netAddressDao.insert(netAddress);
    }


}
