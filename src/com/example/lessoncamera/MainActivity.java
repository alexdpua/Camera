package com.example.lessoncamera;

import java.io.IOException;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

public class MainActivity extends Activity implements Callback,
		SurfaceHolder.Callback2, PreviewCallback {

	private Camera mCamera;
	private SurfaceView mPreview;
	private SurfaceHolder surfaceHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		mPreview = (SurfaceView) findViewById(R.id.SurfaceView);
		surfaceHolder = mPreview.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceHolder = mPreview.getHolder();

	}

	public void onBtnClick(View v) {
		Camera.Parameters param = mCamera.getParameters();
		
	}

	@Override
	protected void onPause() {
		mCamera.release();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		mCamera = Camera.open();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			mCamera.setPreviewDisplay(holder);
			mCamera.setPreviewCallback(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Size previewSize = mCamera.getParameters().getPreviewSize();
		float aspect = (float) previewSize.width / previewSize.height;
		int previewSurfaceWidth = mPreview.getWidth();
		int previewSurfaceHeight = mPreview.getHeight();
		LayoutParams lp = mPreview.getLayoutParams();
		if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {

			mCamera.setDisplayOrientation(90);
			lp.height = previewSurfaceHeight;
			lp.width = (int) (previewSurfaceHeight / aspect);
			;
		} else {

			mCamera.setDisplayOrientation(0);
			lp.width = previewSurfaceWidth;
			lp.height = (int) (previewSurfaceWidth / aspect);
		}

		mPreview.setLayoutParams(lp);
		mCamera.startPreview();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceRedrawNeeded(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPreviewFrame(byte[] arg0, Camera arg1) {
		// TODO Auto-generated method stub
		
	}

}
