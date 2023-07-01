package com.ampesoftware.secretoffitness;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MainFavItemsList extends Activity {
	ListView lstfav;
	dbhelper dbh=new dbhelper(this);
	public static int[] recortnumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try{
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_fav_items_list);
		
		lstfav=(ListView)findViewById(R.id.favlist);

		switch(MainApplicationMenu.whichpage)
		{
			case 1:
				reffavdietfood();
				break;
			case 2:
				reffavdietplan();
				break;
			case 3:
				reffavbeinshape();
				break;
			case 4:
				reffavsportadvice();
				break;
			case 5:
				reffavsnack();
				break;
		}
		
		lstfav.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
							Intent inte =new Intent(MainFavItemsList.this,MainView.class);
							inte.putExtra("Pos", recortnumber[arg2]);
							inte.putExtra("Activityname", "Favorite");
							startActivity(inte);
							finish();
			}
		});
		}catch (Exception e) {
			onBackPressed();
		}
		


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_fav_items_list, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
        case R.id.action_listfavback:
        	onBackPressed();
            break;
          default:
          break;
        }

    	return super.onOptionsItemSelected(item);
    }
	public void reffavdietfood(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
        dbh.openDataBase();
		int i=dbh.countfavdietfood();
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.faveddietfood(2,j).toString());
			listfav.add(dbh.faveddietfood(6,j).toString());
			listinx.add(dbh.faveddietfood(0,j).toString());
			recortnumber[j]=Integer.parseInt(dbh.faveddietfood(0,j).toString());
    	}
		dbh.close();
		lstfav.setAdapter(adapter);
    }

public void reffavdietplan(){
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> listfav = new ArrayList<String>();
	ArrayList<String> listinx = new ArrayList<String>();
    MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
    dbh.openDataBase();
	int i=dbh.countfavdietplan();
	recortnumber=new int[i];
	for(int j=0;j<i;j++){
		list.add(dbh.faveddietplan(1,j).toString());
		listfav.add(dbh.faveddietplan(3,j).toString());
		listinx.add(dbh.faveddietplan(0,j).toString());
		recortnumber[j]=Integer.parseInt(dbh.faveddietplan(0,j).toString());
	}
	dbh.close();
	lstfav.setAdapter(adapter);
}

public void reffavbeinshape(){
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> listfav = new ArrayList<String>();
	ArrayList<String> listinx = new ArrayList<String>();
    MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
    dbh.openDataBase();
	int i=dbh.countfavbeinshape();
	recortnumber=new int[i];
	for(int j=0;j<i;j++){
		list.add(dbh.favedbeinshape(1,j).toString());
		listfav.add(dbh.favedbeinshape(3,j).toString());
		listinx.add(dbh.favedbeinshape(0,j).toString());
		recortnumber[j]=Integer.parseInt(dbh.favedbeinshape(0,j).toString());
	}
	dbh.close();
	lstfav.setAdapter(adapter);
}

public void reffavsnack(){
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> listfav = new ArrayList<String>();
	ArrayList<String> listinx = new ArrayList<String>();
    MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
    dbh.openDataBase();
	int i=dbh.countfavsnack();
	recortnumber=new int[i];
	for(int j=0;j<i;j++){
		list.add(dbh.favedsnack(1,j).toString());
		listfav.add(dbh.favedsnack(3,j).toString());
		listinx.add(dbh.favedsnack(0,j).toString());
		recortnumber[j]=Integer.parseInt(dbh.favedsnack(0,j).toString());
	}
	dbh.close();
	lstfav.setAdapter(adapter);
}
public void reffavsportadvice(){
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> listfav = new ArrayList<String>();
	ArrayList<String> listinx = new ArrayList<String>();
    MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
    dbh.openDataBase();
	int i=dbh.countfavsportadvice();
	recortnumber=new int[i];
	for(int j=0;j<i;j++){
		list.add(dbh.favedsportadvice(1,j).toString());
		listfav.add(dbh.favedsportadvice(3,j).toString());
		listinx.add(dbh.favedsportadvice(0,j).toString());
		recortnumber[j]=Integer.parseInt(dbh.favedsportadvice(0,j).toString());
	}
	dbh.close();
	lstfav.setAdapter(adapter);
}
@Override
public void onBackPressed() {
	Intent intf =new Intent(MainFavItemsList.this,MainItemsList.class);
	startActivity(intf);
	finish();
}


}
