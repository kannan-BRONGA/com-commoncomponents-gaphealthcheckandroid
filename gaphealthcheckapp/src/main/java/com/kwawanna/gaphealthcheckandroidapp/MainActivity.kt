package com.kwawanna.gaphealthcheckandroidapp

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.gap.healthcheck.Constants
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv = findViewById<TextView>(R.id.PStextView)


        //GetBatteryLevel
        tv.text = getBatteryLevelNatively().toString()

        //Access the Field GOOD from constants file
        tv.text = Constants.GOOD

        //Get Constants from Library
        tv.text = getLibraryConstant().toString()

    }

    //Get Constants from Library
    fun getLibraryConstant(): HashMap<String, String> {
        val pairs = HashMap<String, String>()
        val constants = Constants;
        val f: Array<Field> = constants.javaClass.getFields()
        for (i in f.indices) {
            pairs[f[i].getName().toLowerCase()] = f[i].get(constants).toString()
        }
        return pairs
    }

    //GetBatteryLevel
    fun getBatteryLevelNatively(): Float? {
        //get battery natively
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            this.registerReceiver(null, ifilter)
        }
        val batteryPct: Float? = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        }
        return batteryPct
    }



}