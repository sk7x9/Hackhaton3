package umkc.edu.challange2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;


	
public class MapView extends Activity {
	
	private double latitudeE51 = 39.034474;
	private double longitudeE51 =-94.580972;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		
		Button b = (Button)findViewById(R.id.mapview);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("geo:0,0?q=" + (latitudeE51+","+ longitudeE51)));
				startActivity(intent);
			}
		});
		/*webView = (WebView)findViewById(R.id.webViewmap);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl();*/
		
	}

}
