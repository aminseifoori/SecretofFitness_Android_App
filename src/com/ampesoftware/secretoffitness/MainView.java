package com.ampesoftware.secretoffitness;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainView extends Activity {
	TextView txtname;
	TextView txtrecipe;
	CheckBox chk;
    ImageView imageview;
    ImageButton imgplus;
    ImageButton imgmines;
	dbhelper dbh=new dbhelper(this);
	String sharestr;
	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);
		
		txtname=(TextView)findViewById(R.id.txtname);
		txtrecipe=(TextView)findViewById(R.id.txtrecipe);
		txtname.setTextSize(MainApplicationMenu.fontsize);
		txtrecipe.setTextSize(MainApplicationMenu.fontsize);
		imgplus=(ImageButton)findViewById(R.id.btnplus);
		imgmines=(ImageButton)findViewById(R.id.btnmines);
		chk=(CheckBox)findViewById(R.id.chkfav);
		
		String imagename;

		txtrecipe.setMovementMethod(new ScrollingMovementMethod());
		txtrecipe.setTextSize(MainApplicationMenu.fontsize);
		txtname.setTextSize(MainApplicationMenu.fontsize);
		Bundle b=getIntent().getExtras();
		position=b.getInt("Pos");
		int favorite;
		String str;
		int res;
		imageview= (ImageView)findViewById(R.id.imgview);
		
		switch(MainApplicationMenu.whichpage)
		{
			case 1:
				dbh.openDataBase();
				txtname.setText(dbh.getdatadietfoodid(position,2));
				str=dbh.getdatadietfoodid(position,3)+"\n"+"\n"+dbh.getdatadietfoodid(position,4)+"\n"+"\n"+dbh.getdatadietfoodid(position,5);
				txtrecipe.setText(str);
				sharestr=dbh.getdatadietfoodid(position,2)+"\n"+str;
				favorite=Integer.parseInt(dbh.getdatadietfoodid(position,6));
				
		        imagename = "f"+position;
		        res = getResources().getIdentifier(imagename, "drawable", this.getPackageName());        
		        imageview.setImageResource(res);
				
				if(favorite==0){
					chk.setChecked(false);
				}else{
					chk.setChecked(true);
				}
				dbh.close();
				getActionBar().setTitle("غذاهای رژیمی");
				break;
			case 2:
				dbh.openDataBase();
				txtname.setText(dbh.getdatadietplanid(position,1));
				txtrecipe.setText(dbh.getdatadietplanid(position,2));
				sharestr=dbh.getdatadietplanid(position,1)+"\n"+dbh.getdatadietplanid(position,2);
				favorite=Integer.parseInt(dbh.getdatadietplanid(position,3));
				
		        imagename = "dietpic";
		        res = getResources().getIdentifier(imagename, "drawable", this.getPackageName());        
		        imageview.setImageResource(res);
		        
				if(favorite==0){
					chk.setChecked(false);
				}else{
					chk.setChecked(true);
				}
				dbh.close();
				getActionBar().setTitle("برنامه رژیم غذایی");
				
				break;
			case 3:
				dbh.openDataBase();
				txtname.setText(dbh.getdatabeinshapeid(position,1));
				txtrecipe.setText(dbh.getdatabeinshapeid(position,2));
				sharestr=dbh.getdatabeinshapeid(position,1)+"\n"+dbh.getdatabeinshapeid(position,2);
				favorite=Integer.parseInt(dbh.getdatabeinshapeid(position,3));
				
		        imagename = "b"+position;
		        res = getResources().getIdentifier(imagename, "drawable", this.getPackageName());        
		        imageview.setImageResource(res);
		        
				if(favorite==0){
					chk.setChecked(false);
				}else{
					chk.setChecked(true);
				}
				dbh.close();
				getActionBar().setTitle("توصیه های تناسب اندام");
				break;
			case 4:
				dbh.openDataBase();
				txtname.setText(dbh.getdatasportadviceid(position,1));
				txtrecipe.setText(dbh.getdatasportadviceid(position,2));
				sharestr=dbh.getdatasportadviceid(position,1)+"\n"+dbh.getdatasportadviceid(position,2);
				favorite=Integer.parseInt(dbh.getdatasportadviceid(position,3));
				
		        imagename = "dietpic";
		        res = getResources().getIdentifier(imagename, "drawable", this.getPackageName());        
		        imageview.setImageResource(res);
		        
				if(favorite==0){
					chk.setChecked(false);
				}else{
					chk.setChecked(true);
				}
				dbh.close();
				getActionBar().setTitle("توصیه های ورزشی");
				break;
			case 5:
				dbh.openDataBase();
				txtname.setText(dbh.getdatasnackid(position,1));
				txtrecipe.setText(dbh.getdatasnackid(position,2));
				sharestr=dbh.getdatasnackid(position,1)+"\n"+dbh.getdatasnackid(position,2);
				favorite=Integer.parseInt(dbh.getdatasnackid(position,3));
				
		        imagename = "s"+position;
		        res = getResources().getIdentifier(imagename, "drawable", this.getPackageName());        
		        imageview.setImageResource(res);
		        
				if(favorite==0){
					chk.setChecked(false);
				}else{
					chk.setChecked(true);
				}
				dbh.close();
				getActionBar().setTitle("میان وعده های کم کالری");
				break;
		}
		
		imgplus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(MainApplicationMenu.fontsize<40){
					MainApplicationMenu.fontsize+=1;
				txtrecipe.setTextSize(MainApplicationMenu.fontsize);
				txtname.setTextSize(MainApplicationMenu.fontsize);
				}
			}
		});
		
		imgmines.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(MainApplicationMenu.fontsize>8){
					MainApplicationMenu.fontsize-=1;
				txtrecipe.setTextSize(MainApplicationMenu.fontsize);
				txtname.setTextSize(MainApplicationMenu.fontsize);
				}
			}
		});
		
		chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				switch(MainApplicationMenu.whichpage)
				{
					case 1:
						if(chk.isChecked()){
							dbh.openDataBase();
							dbh.favdietfood(1,position);
							dbh.close();
						}if(!chk.isChecked()){
							dbh.openDataBase();
							dbh.favdietfood(0,position);
							dbh.close();
						}
																		
						break;
					case 2:
						if(chk.isChecked()){
							dbh.openDataBase();
							dbh.favdietplan(1,position);
							dbh.close();
						}if(!chk.isChecked()){
							dbh.openDataBase();
							dbh.favdietplan(0,position);
							dbh.close();
						}
						break;
					case 3:
						if(chk.isChecked()){
							dbh.openDataBase();
							dbh.favbeinshape(1,position);
							dbh.close();
						}if(!chk.isChecked()){
							dbh.openDataBase();
							dbh.favbeinshape(0,position);
							dbh.close();
						}
						break;
					case 4:
						if(chk.isChecked()){
							dbh.openDataBase();
							dbh.favsportadvice(1,position);
							dbh.close();
						}if(!chk.isChecked()){
							dbh.openDataBase();
							dbh.favsportadvice(0,position);
							dbh.close();
						}
						break;
					case 5:
						if(chk.isChecked()){
							dbh.openDataBase();
							dbh.favSnack(1,position);
							dbh.close();
						}if(!chk.isChecked()){
							dbh.openDataBase();
							dbh.favSnack(0,position);
							dbh.close();
						}
						break;
				}

				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
    	case R.id.action_sharerecepie:
 	        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
 	        sharingIntent.setType("text/plain");
 	        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "نرم افزار رازهای  تناسب اندام");
 	        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sharestr);
 	        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.hello_world)));
 	        break;
        case R.id.action_mainviewback:
    		onBackPressed();			
            break;
          default:
          break;
        }

    	return super.onOptionsItemSelected(item);
    }
	@Override
	public void onBackPressed() {
		Bundle b=getIntent().getExtras();
		String ac=b.getString("Activityname");
		if(ac.equals("listitem")){
			Intent intf =new Intent(MainView.this,MainItemsList.class);
			startActivity(intf);
			finish();
		}else{
			Intent intf =new Intent(MainView.this,MainFavItemsList.class);
			startActivity(intf);
			finish();
		}


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
