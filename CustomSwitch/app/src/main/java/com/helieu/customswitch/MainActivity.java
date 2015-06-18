package com.helieu.customswitch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.helieu.customswitch.widget.Switch;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch customSwitch = (Switch) findViewById(R.id.customSwitch);
        customSwitch.setSwitchToggleListener(new Switch.SwitchToggleListener() {
            @Override
            public void onSwitchToggle(Switch.SwitchToggleState switchToggleState) {
                Toast.makeText(MainActivity.this, String.format("Switch state %s", switchToggleState.name()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
