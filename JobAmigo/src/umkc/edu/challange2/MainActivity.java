package umkc.edu.challange2;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button searchbycountry;
	private Button routefinder;
	private Button weatherfinder;
	private Button apis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		searchbycountry=(Button)findViewById(R.id.searchbycountry);
		routefinder=(Button)findViewById(R.id.routefinder);
		weatherfinder=(Button)findViewById(R.id.weatherfinder);

		apis=(Button)findViewById(R.id.apis);

		apis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), GenerateQRCode.class);
				startActivity(i);
			}
		});

		routefinder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, MapView.class);
				startActivity(i);
			}
		});
		weatherfinder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, WeatherDetail.class);
				startActivity(i);
			}
		});
		searchbycountry.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, JobsList.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
