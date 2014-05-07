package edu.hbut.livestock;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.http.util.EncodingUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import edu.hbut.livestock.http.HttpGetCall;
import edu.hbut.livestock.login.Login;
import edu.hbut.livestock.util.SettingSystem;

/**
 * 登陆界面
 * 
 * @author Hang
 * 
 */
public class LoginActivity extends Activity {

	private Button submitButton;

	private Button resetButton;

	private EditText useridEdit;

	private EditText passwordEdit;
	
	private String UserId ="";
	private String password ="";
	
	private String[] myDate = {"用户登录","管理员登录"};
	private Spinner spinner;
	private ArrayAdapter<String> adapter ;
	private int userGroup;
	Handler myHandler;
	private ImageView setting;
	private EditText settingEdit;
	private String settingFile="livestock.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		String net = this.readFileData(settingFile);
		if (net!=null&&!"".equals(net)) {
			SettingSystem.NET_WORK = net;
			HttpGetCall.BASE_URL = net;
		}
		
		myHandler = new MyHandler();
		useridEdit = (EditText) findViewById(R.id.userid_input);
		passwordEdit = (EditText) findViewById(R.id.password_input);
		
		spinner = (Spinner)findViewById(R.id.chooseUser);
		adapter = new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_spinner_item,myDate);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);  
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				userGroup = arg2;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		spinner.setVisibility(View.VISIBLE);
		
		submitButton = (Button) findViewById(R.id.submit);
		submitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				new Thread(new Runnable() {
					public void run() {
						UserId = useridEdit.getText().toString();
						password = passwordEdit.getText().toString();
						
						Message msg = new Message();
			            Bundle b = new Bundle();// 存放数据
						Login log = new Login(UserId,password);
						if (UserId.equals("")) {
							b.putString("message", "用户名不能为空！");
						}else{
							boolean result = log.login();
				            b.putBoolean("result", result);
							if(!result){
								b.putString("response", log.getText());
							}
						}
						msg.setData(b);
						
						LoginActivity.this.myHandler.sendMessage(msg);
					}
				}).start();
				
				
			}
		});

		resetButton = (Button) findViewById(R.id.reset);
		
		resetButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Editable editable = useridEdit.getText();
				editable.clear();
				editable = passwordEdit.getEditableText();
				editable.clear();
				useridEdit.requestFocus();
			}
		});
		
		setting = (ImageView)findViewById(R.id.setting);
		setting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addNetSetting();
			}
		});
	}
	
	public void addNetSetting(){
		settingEdit = new EditText(this);
		settingEdit.setText("http://192.168.1.114:8080/livestock/");
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("设置网络参数");
		builder.setView(settingEdit);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String bookMarkName = settingEdit.getText().toString();
				if (bookMarkName!=null && !"".equals(bookMarkName.trim())) {
					writeFileData(settingFile,bookMarkName);
				}
			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}
	
	public String readFileData(String fileName){ 
        String res=""; 
        try{ 
	         FileInputStream fin = openFileInput(fileName); 
	         int length = fin.available(); 
	         byte [] buffer = new byte[length]; 
	         fin.read(buffer);     
	         res = EncodingUtils.getString(buffer, "UTF-8"); 
	         fin.close();     
        }catch(Exception e){ 
        	e.printStackTrace(); 
        } 
        return res; 
    }   
	
	public void writeFileData(String fileName,String message){ 
	       try{ 
		        FileOutputStream fout =openFileOutput(fileName, MODE_PRIVATE);
		        byte [] bytes = message.getBytes(); 
		        fout.write(bytes); 
		        fout.close(); 
	       }catch(Exception e){ 
	        	e.printStackTrace(); 
	       } 
	}
	
	@SuppressLint("HandlerLeak")
	class MyHandler extends Handler{
        public MyHandler() {
        }
        
		public MyHandler(Looper L) {
            super(L);
        }

        // 子类必须重写此方法,接受数据
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 此处可以更新UI
            Bundle b = msg.getData();
            String message = b.getString("message");
            if (UserId.equals("")) {
            	Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
				useridEdit.requestFocus();
			}else{
				boolean result = b.getBoolean("result");
				if (result) {
	                if(userGroup==0){
	    				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
	    				startActivity(intent);
	    				LoginActivity.this.finish();
	    			}else if(userGroup==1){
	    				LoginActivity.this.finish();
	    			}else{
	    				Toast.makeText(LoginActivity.this, "请选择用户", Toast.LENGTH_LONG);
	    			}
				}else{
					String response = b.getString("response");
					Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
				}
			}
            
            
        }
    }
}
