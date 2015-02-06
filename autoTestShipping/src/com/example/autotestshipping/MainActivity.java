package com.example.autotestshipping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("SdCardPath") public class MainActivity extends Activity {

    private Button testall;
	private Button download_apk;
	private static final String TestShippinghttpUrl="http://cdn6.down.apk.gfan.com/asdf/Pfiles/2014/7/26/845851_08c4cd6d-15b4-480a-a9cc-c2b55325269f.apk";
	private static final String ShippinghttpUrl = "http://192.168.31.106:280/TestShipping.apk";


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        testall = (Button)findViewById(R.id.button_testall);
        download_apk = (Button)findViewById(R.id.button_download);
        
        testall.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		MainActivity.this.startInstrumentation(new ComponentName("com.testshipping","android.test.InstrumentationTestRunner"),null,null);
        	}
        });
        
        download_apk.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		MainActivity.this.downLoadFile(TestShippinghttpUrl,"Shipping.apk");
        		MainActivity.this.downLoadFile(ShippinghttpUrl,"TestShipping.apk");
        	}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    protected void downLoadFile(final String httpUrl ,final String fileName){
//    	final String fileName = "TestShipping.apk";
    	File tmpFile = new File("/sdcard/update");
    	if(!tmpFile.exists()){
    		tmpFile.mkdir();
    	}
    	final File file = new File("/sdcard/update/" + fileName);
    	
		new Thread(){
			@SuppressWarnings("unused")
			public void run(){
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(httpUrl);
				HttpResponse response;
				try{
					response = client.execute(get);
					HttpEntity entity = response.getEntity();
					long length = entity.getContentLength();
					InputStream is = entity.getContent();
					FileOutputStream fileOutputStream = null;
					if(is!=null){
						fileOutputStream = new FileOutputStream(file);
						byte[] buf = new byte[1024];
						int ch = -1;
						int count = 0;
						while((ch=is.read(buf)) != -1){
							fileOutputStream.write(buf,0,ch);
							count += ch;
							if(length>0){
								
							}
						}
					}
					fileOutputStream.flush();
					if(fileOutputStream !=null){
						fileOutputStream.close();
					}
					openFile(file);
					
				}catch( ClientProtocolException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}.start();
    }

    private void openFile(File file){
    	Log.e("OpenFile",file.getName());
    	Intent intent = new Intent();
    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	intent.setAction(android.content.Intent.ACTION_VIEW);
    	intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
    	startActivity(intent);
    }
    
}
