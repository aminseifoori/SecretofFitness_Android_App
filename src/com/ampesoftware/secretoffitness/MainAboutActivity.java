package com.ampesoftware.secretoffitness;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MainAboutActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_about);
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_about, menu);
		return true;
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
         case R.id.action_backabout:
        	  finish();
        	 break;
          default:
          break;
        }
    	return super.onOptionsItemSelected(item);
    }
	
	public void openlink() {
		String url = "http://www.ampesoftware.com";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url)); 
		startActivity(i); 
		
	}
	
    @Override
    protected void onDestroy() {
    super.onDestroy();

    unbindDrawables(findViewById(R.id.RootView));
    System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
        view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
            unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
        ((ViewGroup) view).removeAllViews();
        }
    }

}
