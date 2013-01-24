package com.rl2.macspin;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final String TAG = "MacSpinMain";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
    /** Called when the user clicks the run command button 
     * @throws InterruptedException 
     * @throws IOException */
    public void runCommand(View view) throws IOException, InterruptedException {
    	
    	String outputString = ifconfig();
    	TextView commandOutput = (TextView) findViewById(R.id.command_output);
    	commandOutput.setText(outputString);
    	
    	
    	
    }
    
    public String ifconfig() throws IOException, InterruptedException {

    	Log.v(TAG, "attempting to run command");
    	String outputString = "no output";
		
		Process process = new ProcessBuilder()
		.command("su", "-c", "ip link set wlan0 address 0A:12:23:34:45:56; busybox ifconfig wlan0")
		.redirectErrorStream(true)
		.start();
		try {
			InputStream in = process.getInputStream();
			//OutputStream out = process.getOutputStream();
         


			BufferedReader reader = new BufferedReader(
			        new InputStreamReader(in));
			int read;
			char[] buffer = new char[4096];
			StringBuffer output = new StringBuffer();
			while ((read = reader.read(buffer)) > 0) {
			    output.append(buffer, 0, read);
			}
			reader.close();
			process.waitFor();
         
         
			outputString =  output.toString();

       } finally {
    	   process.destroy();
       }
     
       

    	
    	Log.v(TAG, outputString);
    	
    	
    	return outputString;
    }
    

}
