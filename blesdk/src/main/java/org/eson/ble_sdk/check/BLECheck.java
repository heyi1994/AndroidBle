package org.eson.ble_sdk.check;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import org.eson.ble_sdk.BLESdk;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @作者 xiaoyunfei
 * @日期: 2017/2/22
 * @说明： 蓝牙检测工具
 */

public class BLECheck {


	private BluetoothManager bluetoothManager;

	private BLECheckListener bleCheckListener;

	private BluetoothAdapter bluetoothAdapter;

	private BLECheck() {
	}

	private static BLECheck bleCheck;

	public static void init() {
		if (bleCheck == null) {
			bleCheck = new BLECheck();
		}
	}

	public static BLECheck get() {
		init();
		return bleCheck;
	}


	/**
	 * 检测蓝牙状态
	 *
	 * @param context
	 * @param checkListener
	 */
	public void checkBleState(Context context, BLECheckListener checkListener) {


		//检测是否有蓝牙的权限
		if (!checkBlePermission(context)) {
			checkListener.noBluetoothPermission();
			return;
		}

		//是否支持蓝牙设备
		if (!supportBle(context)) {
			checkListener.notSupportBle();
			return;
		}

		//蓝牙是否打开
		if (!bleEnable()) {
			checkListener.bleClosing();
			return;
		}

		//蓝牙状态可用
		checkListener.bleStateOK();
	}

	/**
	 * 蓝牙是否打开可用状态
	 *
	 * @return
	 */
	private boolean bleEnable() {

		bluetoothAdapter = BLESdk.get().getBluetoothAdapter();
		if (bluetoothAdapter == null) {
			return false;
		}

		return bluetoothAdapter.isEnabled();
	}

	/**
	 * 是否支持蓝牙设备
	 *
	 * @param context
	 *
	 * @return
	 */
	private boolean supportBle(Context context) {
		return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
	}

	/**
	 * 是否具有访问蓝牙的权限蓝牙
	 *
	 * @param context
	 *
	 * @return
	 */
	public boolean checkBlePermission(Context context) {
		if (Build.VERSION.SDK_INT < 23) {
			return true;
		}

		String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION};
		boolean hasPermissions = EasyPermissions.hasPermissions(context, perms);
		return hasPermissions;
	}

	/**
	 * 申请蓝牙权限
	 *
	 * @param activity
	 * @param rationale
	 * @param requestCode
	 */
	public void requestBlePermission(Activity activity, String rationale, int requestCode) {
		EasyPermissions.requestPermissions(activity, rationale, requestCode,
				new String[]{Manifest.permission.ACCESS_COARSE_LOCATION});
	}

	/**
	 * 打开蓝牙
	 */
	public void openBle() {
		if (bluetoothAdapter == null) {
			return;
		}
		bluetoothAdapter.enable();
	}

	/**
	 * 打开蓝牙
	 *
	 * @param activity
	 * @param requestCode
	 */
	public void openBle(Activity activity, int requestCode) {
		Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		activity.startActivityForResult(intent, requestCode);
	}
}
