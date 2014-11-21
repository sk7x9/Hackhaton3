package umkc.edu.challange2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterScreeen extends Activity {
	private SharedPreferences pref;
	private Editor editor = null;
	private EditText regpassword;
	private EditText regname;
	private EditText regnum;
	private EditText regusername;
	private EditText regmail;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		regname=(EditText) findViewById(R.id.regname);
		regpassword=(EditText) findViewById(R.id.regpassword);
		regnum=(EditText) findViewById(R.id.regphnum);
		regusername=(EditText) findViewById(R.id.regusername);
		regmail = (EditText) findViewById(R.id.mailid);
		Button submit=(Button) findViewById(R.id.submit);
		pref=this.getSharedPreferences("edu.umkc.lee", 0);	
		editor=pref.edit();
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(regname.getText().toString().equals("") || regpassword.getText().toString().equals("")
						|| regnum.getText().toString().equals("") || regusername.getText().toString().equals("")
						|| regmail.getText().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please fill all the details", 5000).show();
				}
				else{
					editor.putString("password", regpassword.getText().toString());
					editor.commit();
					finish();
					Toast.makeText(getApplicationContext(), "Registered successfully" +regname.getText().toString(), 5000).show();
				}
			}
		});
	}
}
