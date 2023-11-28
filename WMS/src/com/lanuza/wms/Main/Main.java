package com.lanuza.wms.Main;

import com.lanuza.wms.ui.form.Dashboard;

public class Main {

	public static void main(String[] args) {
		//the above code is for the loading screen
		
		/*Loading load = new Loading();
		try{
		for(int i = 0; i <= 100; i++){
				Thread.sleep(40);
				load.Myprogress.setValue(i);
				load.Percentage.setText(Integer.valueOf(i) + "%")
			}
		}catch(Exception e){
			
		 }
		 */
		 	//load.dispose();
			 new Dashboard();		
		//new Login();
	

	}

}
