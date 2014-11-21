package umkc.edu.challange2;

import java.util.ArrayList;

import bolts.Continuation;
import bolts.Task;

import com.ibm.mobile.services.data.IBMDataObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class JobsList extends Activity implements OnClickListener,OnInitListener {

	private static final String TAG = "JobsList";
	private Button search_button;
	private ListView Jobslist;
	private EditText search_edit;
	private Button Submit;
	TextView name,title;
	public static final String CLASS_NAME="JobDetails";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobs_list);

		Jobslist = (ListView) findViewById(R.id.jobslist);

		//use this in onclick to pass in exact value
		search_button=(Button) findViewById(R.id.search_button);
		search_edit=(EditText) findViewById(R.id.search_edit);
		search_button.setOnClickListener(this);
		Submit = (Button) findViewById(R.id.Submit);
		Submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//				Intent i = new Intent(getApplicationContext(), PaymentInfo.class);
				//				startActivity(i);
			}
		});
	}

	private class DownLoadItemList extends
	AsyncTask<String, Void, ArrayList<ParserObject>> {

		ListView listItems;

		public DownLoadItemList(ListView list) {
			this.listItems = list;
		}

		@Override
		protected ArrayList<ParserObject> doInBackground(String... params) {
			return new XMLParser().parseData(params[0]);
		}

		@Override
		protected void onPostExecute(ArrayList<ParserObject> list) {
			System.out.println("Returned list: "+list.size());
			listItems.setAdapter(new ItemListArrayAdapter(
					JobsList.this, list));
		}
	}

	public class ItemListArrayAdapter extends ArrayAdapter<ParserObject> implements OnInitListener {
		private final Context context;
		private final ArrayList<ParserObject> values;
		SparseBooleanArray mCheckStates;
		protected TextToSpeech tts;

		public ItemListArrayAdapter(Context context, ArrayList<ParserObject> values) {
			super(context, R.layout.list_display, values);
			this.context = context;
			this.values = values;
			mCheckStates = new SparseBooleanArray(50);
			tts= new TextToSpeech(context, this);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.list_display,
					parent, false);
			 name = (TextView) rowView
					.findViewById(R.id.name);
			TextView priceView = (TextView) rowView.findViewById(R.id.pay);
			TextView desc = (TextView) rowView.findViewById(R.id.desc);
			TextView exp = (TextView) rowView.findViewById(R.id.exp);
			TextView edu = (TextView) rowView.findViewById(R.id.edu);
			title = (TextView) rowView.findViewById(R.id.title);
			TextView emp_type = (TextView) rowView.findViewById(R.id.emp_type);
			TextView loc = (TextView) rowView.findViewById(R.id.loc);
			final Button details = (Button) rowView.findViewById(R.id.details);
			System.out.println("++++ rendering list view");
			name.setText("Name:-"+values.get(position).getName());
			priceView.setText("Pay:-"+values.get(position).getPay());
			desc.setText("Desc:-"+values.get(position).getDesc());
			//			desc.setText(" "+values.get(position).getDid());
			exp.setText("Exp:-"+values.get(position).getExp());
			title.setText("Title:-"+values.get(position).getTitle());
			emp_type.setText("Emp_Type:-"+values.get(position).getEmpType());
			loc.setText("Loc:-"+values.get(position).getloc());
			//			details.setText("Details:-"+values.get(position).getdetails());
			//			details.setEnabled(false);
			details.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					tts.speak("Directing to Company website",  TextToSpeech.QUEUE_FLUSH, null);
					
					String a=search_edit.getText().toString();
					String b=name.getText().toString();
					String c=title.getText().toString();
					JobDetails jb=new JobDetails();
					jb.setName(b);
					jb.setTitle(c);
					jb.setCountry(a);
					
					jb.save().continueWith(new Continuation<IBMDataObject, Void>() {

						@Override
						public Void then(Task<IBMDataObject> task) throws Exception {
		                    // Log if the save was cancelled.
		                    if (task.isCancelled()){
		                        Log.e(CLASS_NAME, "Exception : Task " + task.toString() + " was cancelled.");
		                    }
							 // Log error message, if the save task fails.
							else if (task.isFaulted()) {
								Log.e(CLASS_NAME, "Exception : " + task.getError().getMessage());
							}
							return null;

						}						
					}
				);
					
					Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse((String) values.get(position).getdetails()));
					startActivity(i);
				}
			});
			LinearLayout jobs= (LinearLayout)rowView.findViewById(R.id.jobs_row);

			Button apply=(Button) rowView.findViewById(R.id.apply);
			apply.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//					Intent i = new Intent(getApplicationContext(), XMLParser2.class);
					//					i.putExtra("did", values.get(position).getDid());
					//					startActivity(i);
				}
			});
			//			if(mCheckStates.get(position))
			//				state.setChecked(true);
			//			else
			//				state.setChecked(false);
			//			state.setOnTouchListener(new OnTouchListener() {
			//
			//				@Override
			//				public boolean onTouch(View v, MotionEvent event) {
			//					Log.v(TAG, "onlong click");
			//					if(event.getAction()==MotionEvent.ACTION_DOWN){
			//						View v1=(View) v.getParent().getParent().getParent();
			//						v1.findViewById(R.id.Submit).setVisibility(View.VISIBLE);
			//						state.toggle();
			//						mCheckStates.put(Jobslist.getPositionForView(state),state.isChecked());
			//					}
			//					return true;
			//				}
			//			});			
			jobs.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Log.v(TAG, "onlong click");
					View v1=(View) v.getParent().getParent();
					v1.findViewById(R.id.Submit).setVisibility(View.VISIBLE);

					//					state.toggle();
					//					mCheckStates.put(Jobslist.getPositionForView(state),state.isChecked());

				}
			});
			return rowView;
		}

		@Override
		public void onInit(int status) {
			
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search_button:
			String search_input=search_edit.getText().toString();
			new DownLoadItemList(Jobslist).execute(search_input);
			break;
		default:
			break;
		}
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

	}
}