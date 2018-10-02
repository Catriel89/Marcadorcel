package com.marcadordefutbol.catrisoft.marcadorcel;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;

import java.lang.reflect.Type;
import java.util.*;
import java.text.*;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.widget.AdapterView;
import android.view.View;

public class MainActivity extends AppCompatActivity{
    private Timer _timer = new Timer();

    private double num = 0;
    private boolean eq1_eq2 = false;
    private boolean chk_res_eq1 = false;
    private boolean chk_res_eq2 = false;
    private double seg = 0;
    private double min = 0;
    private boolean started = false;

    private ArrayList<String> list_jug_eq1 = new ArrayList<>();
    private ArrayList<String> list_jug_eq2 = new ArrayList<>();
    private ArrayList<String> list_eventos = new ArrayList<>();

    private LinearLayout linear1;
    private LinearLayout linear5;
    private LinearLayout linear6;
    private LinearLayout linear7;
    private LinearLayout linear8;
    private ListView lst_eq1;
    private ListView lst_eq2;
    private ListView lst_eventos;
    private TextView res_eq1;
    private TextView res_eq2;
    private TextView txt_time;
    private Button but_res_eq1;
    private Button but_res_eq2;

    private Intent cargapart = new Intent();
    private TimerTask timer;
    private Calendar c1 = new Calendar() {
        @Override
        protected void computeTime() {

        }

        @Override
        protected void computeFields() {

        }

        @Override
        public void add(int field, int amount) {

        }

        @Override
        public void roll(int field, boolean up) {

        }

        @Override
        public int getMinimum(int field) {
            return 0;
        }

        @Override
        public int getMaximum(int field) {
            return 0;
        }

        @Override
        public int getGreatestMinimum(int field) {
            return 0;
        }

        @Override
        public int getLeastMaximum(int field) {
            return 0;
        }
    };
    private Calendar c2 = Calendar.getInstance();
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.main);
        initialize();
        initializeLogic();
    }

    private void initialize(){
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear5 = (LinearLayout) findViewById(R.id.linear5);
        linear6 = (LinearLayout) findViewById(R.id.linear6);
        linear7 = (LinearLayout) findViewById(R.id.linear7);
        linear8 = (LinearLayout) findViewById(R.id.linear8);
        lst_eq1 = (ListView) findViewById(R.id.lst_eq1);
        lst_eq2 = (ListView) findViewById(R.id.lst_eq2);
        lst_eventos = (ListView) findViewById(R.id.lst_eventos);
        res_eq1 = (TextView) findViewById(R.id.res_eq1);
        res_eq2 = (TextView) findViewById(R.id.res_eq2);
        txt_time = (TextView) findViewById(R.id.txt_time);
        but_res_eq1 = (Button) findViewById(R.id.but_res_eq1);
        but_res_eq2 = (Button) findViewById(R.id.but_res_eq2);

        txt_time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (started){
                    started = false;
                    timer.cancel();
                }
                else{
                    started = true;
                    c1 = Calendar.getInstance();
                    timer = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    c2 = Calendar.getInstance();
                                    _formattime((long)(c2.getTimeInMillis() - c1.getTimeInMillis()));
                                }
                            });
                        }
                    };
                    _timer.scheduleAtFixedRate(timer,(int)(0),(int)(1000));
                }
            }
        });

        but_res_eq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                num = Double.parseDouble(res_eq1.getText().toString());
                if (num > 0) {
                    num--;
                    res_eq1.setText(String.valueOf((long)(num)));
                    list_eventos.remove((int)(list_eventos.size() - 1));
                    lst_eventos.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list_eventos));
                }
            }
        });

        but_res_eq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                num = Double.parseDouble(res_eq2.getText().toString());
                if (num > 0) {
                    num--;
                    res_eq2.setText(String.valueOf((long)(num)));
                    list_eventos.remove((int)(list_eventos.size() - 1));
                    lst_eventos.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list_eventos));
                }
            }
        });

        lst_eq1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4){
                final int _position = _param3;
                num = Double.parseDouble(res_eq1.getText().toString());
                num++;
                res_eq1.setText(String.valueOf((long)(num)));
                list_eventos.add(list_jug_eq1.get((int)(_position)).concat(" metio gol"));
                lst_eventos.setAdapter(new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1, list_eventos));
            }
        });

        lst_eq2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4){
                final int _position = _param3;
                num = Double.parseDouble(res_eq2.getText().toString());
                num++;
                res_eq2.setText(String.valueOf((long)(num)));
                list_eventos.add(list_jug_eq2.get((int)(_position)).concat(" metio gol"));
                lst_eventos.setAdapter(new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_2, list_eventos));
            }
        });
    }
    private void initializeLogic(){
        list_jug_eq1.add("Catri");
        list_jug_eq1.add("Bruce");
        list_jug_eq1.add("Willy");
        list_jug_eq1.add("Pom");
        list_jug_eq1.add("Ale");
        list_jug_eq2.add("Alee");
        list_jug_eq2.add("Gabi");
        list_jug_eq2.add("Rodri");
        list_jug_eq2.add("Rho");
        list_jug_eq2.add("Edu");
        lst_eq1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list_jug_eq1));
        lst_eq2.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list_jug_eq2));
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data){
        super.onActivityResult(_requestCode,_resultCode, _data);

        switch (_requestCode){
            default;
            break;
        }
    }

    private void _formattime(final double _time){
        seg = (_time / 1000) % 60;
        min = (_time / 60000) % 60;
        txt_time.setText(String.valueOf((long)(min)).concat(":").concat(String.valueOf((long)(seg))));
    }

    @Deprecated
    public void showMessage(String _s){
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public int getLocationY(View _v){
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location
    }

    @Deprecated
    public int getRandom(int _min, int _max){
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list){
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _ildx = 0; _ildx < _arr.size(); _ildx++){
            if (_arr.valueAt(_ildx))
                _result.add((double)_arr.keyAt(_ildx));
        }
        return _result;
    }

    @Deprecated
    public float getDip(int _input){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels(){
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels(){
        return getResources().getDisplayMetrics().heightPixels;
    }

}