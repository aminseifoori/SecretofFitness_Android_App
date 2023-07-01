package com.ampesoftware.secretoffitness;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class MainItemsList extends Activity {
	dbhelper dbh;
	ListView lst;
	ImageButton btnsearch;
	EditText txtsearch;
	int searchflag;
	public static int[] recortnumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_items_list);
		dbh=new dbhelper(this);
		lst=(ListView)findViewById(R.id.list1);
		lst.setItemsCanFocus(false);
		txtsearch=(EditText)findViewById(R.id.txtsearch);
		btnsearch=(ImageButton)findViewById(R.id.btnsearch);
		
		txtsearch.setTextSize(MainApplicationMenu.deffontsize);		
		//txtsearch.clearFocus();
		//btnsearch.requestFocus();
		searchflag=0;
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		switch(MainApplicationMenu.whichpage)
		{
			case 1:
				getActionBar().setTitle("غذاهای رژیمی");
				refreshdietfood();
				break;
			case 2:
				getActionBar().setTitle("برنامه رژیم غذایی");
				refreshdietplan();
				break;
			case 3:
				getActionBar().setTitle("توصیه های تناسب اندام");
				refreshbeinshape();
				break;
			case 4:
				getActionBar().setTitle("توصیه های ورزشی");
				refreshsportadvice();
				break;
			case 5:
				getActionBar().setTitle("میان وعده های کم کالری");
				refreshSnack();
				break;
		}
		
		
		lst.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
							Intent inte =new Intent(MainItemsList.this,MainView.class);
							inte.putExtra("Pos", recortnumber[arg2]);
							inte.putExtra("Activityname", "listitem");
							finish();
							startActivity(inte);
							
			}
		});
		
		
		txtsearch.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
	
				String str=txtsearch.getText().toString();
				if(str.equals("")){
					if(searchflag==1){
						searchflag=0;
					switch(MainApplicationMenu.whichpage)
					{
						case 1:
							refreshdietfood();
							
							break;
						case 2:
							refreshdietplan();
							break;
						case 3:
							refreshbeinshape();
							break;
						case 4:
							refreshsportadvice();
							break;
						case 5:
							refreshSnack();
							break;
					}
					}
				}else{
					searchflag=1;
				switch(MainApplicationMenu.whichpage)
				{
					case 1:
							refreshdietfoodsearch();
						break;
					case 2:
							refreshdietplansearch();
						break;
					case 3:
						refreshbeinshapesearch();
						break;
					case 4:
						refreshsportadvicesearch();
						break;
					case 5:
						refreshSnacksearch();
						break;
				}}
				
				return false;
			}
		});
		
		btnsearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String str=txtsearch.getText().toString();
				if(str.equals("")){
					if(searchflag==1){
						searchflag=0;
					switch(MainApplicationMenu.whichpage)
					{
						case 1:
							refreshdietfood();
							break;
						case 2:
							refreshdietplan();
							break;
						case 3:
							refreshbeinshape();
							break;
						case 4:
							refreshsportadvice();
							break;
						case 5:
							refreshSnack();
							break;
					}
					}
					}else{
					searchflag=1;	
				switch(MainApplicationMenu.whichpage)
				{
					case 1:
							refreshdietfoodsearch();
						break;
					case 2:
							refreshdietplansearch();
						break;
					case 3:
						refreshbeinshapesearch();
						break;
					case 4:
						refreshsportadvicesearch();
						break;
					case 5:
						refreshSnacksearch();
						break;
				}}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_items_list, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        // action with ID action_refresh was selected
    	case R.id.action_favlist:
			Intent intf =new Intent(MainItemsList.this,MainFavItemsList.class);
			startActivity(intf);
    		finish();
        case R.id.action_listback:
        	 	finish();
            break;
          default:
          break;
        }

    	return super.onOptionsItemSelected(item);
    }
	

    public void refreshdietfood(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
        dbh.openDataBase();
		int i=dbh.countrecordsdietfood();
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.getdatadietfood(j, 2).toString());
			listfav.add(dbh.getdatadietfood(j, 6).toString());
			listinx.add(dbh.getdatadietfood(j, 0).toString());
			recortnumber[j]=Integer.parseInt(dbh.getdatadietfood(j, 0).toString());
    	}
		dbh.close();
		lst.setAdapter(adapter);
    }
 
    public void refreshbeinshape(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
		
        dbh.openDataBase();
		int i=dbh.countrecordsbeinshape();
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.getdatabeinshape(j, 1).toString());
			listfav.add(dbh.getdatabeinshape(j, 3).toString());
			listinx.add(dbh.getdatabeinshape(j, 0).toString());
			recortnumber[j]=Integer.parseInt(dbh.getdatabeinshape(j, 0).toString());
		}
		dbh.close();
		lst.setAdapter(adapter);
    }
    
    public void refreshdietplan(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
		
        dbh.openDataBase();
		int i=dbh.countrecordsdietplan();
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.getdatadietplam(j, 1).toString());
			listfav.add(dbh.getdatadietplam(j, 3).toString());
			listinx.add(dbh.getdatadietplam(j, 0).toString());
			recortnumber[j]=Integer.parseInt(dbh.getdatadietplam(j, 0).toString());
		}
		dbh.close();
		lst.setAdapter(adapter);
    }
    
    public void refreshSnack(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
		
        dbh.openDataBase();
		int i=dbh.countrecordssnack();
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.getdatasnack(j, 1).toString());
			listfav.add(dbh.getdatasnack(j, 3).toString());
			listinx.add(dbh.getdatasnack(j, 0).toString());
			recortnumber[j]=Integer.parseInt(dbh.getdatasnack(j, 0).toString());
		}
		dbh.close();
		lst.setAdapter(adapter);
    }
    
    public void refreshsportadvice(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
        dbh.openDataBase();
		int i=dbh.countrecordssportadvice();
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.getdatasportadvice(j, 1).toString());
			listfav.add(dbh.getdatasportadvice(j, 3).toString());
			listinx.add(dbh.getdatasportadvice(j, 0).toString());
			
			recortnumber[j]=Integer.parseInt(dbh.getdatasportadvice(j, 0).toString());
		}
		dbh.close();
		lst.setAdapter(adapter);
    }
    
    public void refreshdietfoodsearch(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
        String con=txtsearch.getText().toString();
        dbh.openDataBase();
		int i=dbh.countserachdietfood(con);
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.searchdietfood(j, 2,con).toString());
			listfav.add(dbh.searchdietfood(j, 6,con).toString());
			listinx.add(dbh.searchdietfood(j, 0,con).toString());
			recortnumber[j]=Integer.parseInt(dbh.searchdietfood(j, 0,con).toString());
    	}
		dbh.close();
		lst.setAdapter(adapter);
    }
    
    public void refreshbeinshapesearch(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
        String con=txtsearch.getText().toString();
        dbh.openDataBase();
		int i=dbh.countsearchbeinshape(con);
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.searchbeinshape(j, 1,con).toString());
			listfav.add(dbh.searchbeinshape(j, 3,con).toString());
			listinx.add(dbh.searchbeinshape(j, 0,con).toString());
			recortnumber[j]=Integer.parseInt(dbh.searchbeinshape(j, 0,con).toString());
		}
		dbh.close();
		lst.setAdapter(adapter);
    }
    
    public void refreshdietplansearch(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
        String con=txtsearch.getText().toString();
        dbh.openDataBase();
		int i=dbh.countserachdietplan(con);
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.searchdietplan(j, 1,con).toString());
			listfav.add(dbh.searchdietplan(j, 3,con).toString());
			listinx.add(dbh.searchdietplan(j, 0,con).toString());
			recortnumber[j]=Integer.parseInt(dbh.searchdietplan(j, 0,con).toString());
		}
		dbh.close();
		lst.setAdapter(adapter);
    }
    
    public void refreshSnacksearch(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
        String con=txtsearch.getText().toString();
        dbh.openDataBase();
		int i=dbh.countsearchsnack(con);
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.searchsnack(j, 1,con).toString());
			listfav.add(dbh.searchsnack(j, 3,con).toString());
			listinx.add(dbh.searchsnack(j, 0,con).toString());
			recortnumber[j]=Integer.parseInt(dbh.searchsnack(j, 0,con).toString());
		}
		dbh.close();
		lst.setAdapter(adapter);
    }
    
    public void refreshsportadvicesearch(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listfav = new ArrayList<String>();
		ArrayList<String> listinx = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(list,listfav,listinx, this);
        String con=txtsearch.getText().toString();
        dbh.openDataBase();
		int i=dbh.countsearchsportadvice(con);
		recortnumber=new int[i];
		for(int j=0;j<i;j++){
			list.add(dbh.searchsportadvice(j, 1,con).toString());
			listfav.add(dbh.searchsportadvice(j, 3,con).toString());
			listinx.add(dbh.searchsportadvice(j, 0,con).toString());
			recortnumber[j]=Integer.parseInt(dbh.searchsportadvice(j, 0,con).toString());
		}
		dbh.close();
		lst.setAdapter(adapter);
    }
    

}
