package com.ampesoftware.secretoffitness;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainApplicationMenu extends Activity {
	ImageButton btn1;
	ImageButton btn2;
	ImageButton btn3;
	ImageButton btn4;
	ImageButton btn5;
	TextView txtfs;

	public static int whichpage=0;
	public static float fontsize;
	public static float deffontsize;
	public static int fontrev;
	Intent in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_application_menu);
        
		txtfs=(TextView)findViewById(R.id.txttitle);
        fontsize=txtfs.getTextSize();
        deffontsize=txtfs.getTextSize();
        
	    dbhelper dbh=new dbhelper(this);
	    dbh.openDataBase();
	    fontrev=dbh.getfontrev();
	        if(fontrev==0)
	        {
	            dbh.setfontsize(fontsize);
	            
	        }else{
	        	fontsize=dbh.getfontsize();
	        }
		dbh.close();
        
        btn1=(ImageButton)findViewById(R.id.imagebutton1);
        btn2=(ImageButton)findViewById(R.id.btnfavlist);
        btn3=(ImageButton)findViewById(R.id.imageButton3);
        btn4=(ImageButton)findViewById(R.id.imageButton4);
        btn5=(ImageButton)findViewById(R.id.imageButton5);

        btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				whichpage=1;
				in=new Intent(MainApplicationMenu.this,MainItemsList.class);
				startActivity(in);
			}
		});
        
        btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				whichpage=2;
				in=new Intent(MainApplicationMenu.this,MainItemsList.class);
				startActivity(in);
			}
		});
        
        btn3.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				whichpage=3;
				in=new Intent(MainApplicationMenu.this,MainItemsList.class);
				startActivity(in);
			}
		});
        
        btn4.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				whichpage=4;
				in=new Intent(MainApplicationMenu.this,MainItemsList.class);
				startActivity(in);
			}
		});
        
        btn5.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				whichpage=5;
				in=new Intent(MainApplicationMenu.this,MainItemsList.class);
				startActivity(in);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_application_menu, menu);
		return true;
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
    	case R.id.action_share:
     		String shareBody = "http://cafebazaar.ir/app/?id=com.ampesoftware.secretoffitness";
 	        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
 	        sharingIntent.setType("text/plain");
 	        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "نرم افزار رازهای  تناسب اندام");
 	        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
 	        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.hello_world)));          break;
        // action with ID action_settings was selected
         case R.id.action_back:
        	 	onBackPressed();
            break;
         case R.id.action_about:
        	in=new Intent(MainApplicationMenu.this,MainAboutActivity.class);
			startActivity(in);
          default:
          break;
        }

    	return super.onOptionsItemSelected(item);
    }
	
    @Override
    public void onBackPressed() {
    	AlertDialog.Builder alt=new AlertDialog.Builder(this);
    	alt.setTitle("آیا مایل هستید از برنامه خارج شوید؟");
    	alt.setMessage("شما میتوانید با زدن کلید 5 ستاره جهت حمایت از ارائه محتوای فارسی  و حمایت از ما به این برنامه 5 ستاره بدیهد...با تشکر ");
    	alt.setCancelable(true);
    	alt.setPositiveButton("بلی", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				dbhelper dbh=new dbhelper(MainApplicationMenu.this);
				dbh.openDataBase();
				dbh.setfontsize(fontsize);
				dbh.close();
				finish();
			}
		});
    	alt.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
		});
    	alt.setNeutralButton("5 ستاره", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			
				   Intent goToMarket = new Intent(Intent.ACTION_EDIT,Uri.parse("market://details?id=com.ampesoftware.secretoffitness"));
				    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				    startActivity(goToMarket); 
			}
		});
    	AlertDialog sltd=alt.create();
    	sltd.show();
      
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
