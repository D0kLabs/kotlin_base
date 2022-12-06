package com.d0klabs.kotlinbase

import android.Manifest
import android.Manifest.permission.*
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.ACTION_FOUND
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler

import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

public class MainActivity : AppCompatActivity() {
	
	val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }
	
	val receiverBt = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context?, intent: Intent?) {
            Log.v("MainActivity", "BroadcastReceiver running...")
            val action: String? = intent?.action
            Log.v("MainActivity", "BroadcastReceiver: action is $action")
            when (action) {
                BluetoothDevice.ACTION_FOUND -> {
                    Log.v("MainActivity", "Bluetooth device found!")
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
					val deviceName = device?.name
                    val deviceHardwareAddress = device?.address // MAC Address
				
				}
            }
		}
	}

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
		  //check device support bluetooth or not
		 val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
         if (bluetoothManager == null) {
            Log.e("MainActivity", "bluetoothManager is null")
         }
		 val bluetoothAdapter = bluetoothManager.getAdapter()
		  if (bluetoothAdapter == null){
			  Toast.makeText(applicationContext, "No Bluetooth adapter found!",  Toast.LENGTH_LONG).show()
		 } else {
				  val btOn = Intent (BluetoothAdapter.ACTION_REQUEST_ENABLE)
				  //;startActivityForResult(btOn, 1)
				  Toast.makeText(applicationContext, "Bluetooth enable!", Toast.LENGTH_LONG).show()
				  }
    }
	
	override fun onDestroy() {
        super.onDestroy()
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val bluetoothAdapter = bluetoothManager.getAdapter()
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery()
        }
        unregisterReceiver(receiverBt)
    }

}