package com.example.guojia.activity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.example.bxj.R;
import com.example.guojia.model.City;
import com.example.guojia.model.ContentHandler;
import com.example.guojia.model.CoolWeatherDB;
import com.example.guojia.model.County;
import com.example.guojia.model.Province;
import com.example.guojia.util.HttpUtil;
import com.example.guojia.util.Utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseAreaActivity extends Activity {
	private String _long = null;
	private String _lati = null;
	public static final int LEVEL_PROVINCE = 0;
	public static final int LEVEL_CITY = 1;
	public static final int LEVEL_COUNTY = 2;

	private ProgressDialog progressDialog;
	private TextView titleText;
	private ListView listView;
	private EditText e_train_find;
	private Button g_location;

	private Button b_train_find;
	private ArrayAdapter<String> adapter;
	private CoolWeatherDB coolWeatherDB;
	private List<String> dataList = new ArrayList<String>();
	/*
	 * 省锟叫憋拷
	 */
	private List<Province> provinceList;
	/*
	 * 锟斤拷锟叫憋拷
	 */
	private List<City> cityList;
	/*
	 * 锟斤拷锟叫憋拷
	 */
	private List<County> countyList;
	/*
	 * 选锟叫碉拷省锟斤拷
	 */
	private Province selectedProvince;
	/*
	 * 选锟叫的筹拷锟斤拷
	 */
	private City selectedCity;
	/*
	 * 锟斤拷前选锟叫的硷拷锟斤拷
	 */
	/*
	 * 鏄惁浠巜eatherActivity涓烦杞繃鏉�
	 */
	private boolean isFromWeatherActivity;
	private int currentLevel;

	private LocationManager locationManager;
	private String provider;
	private static final int SHOW_RESPONSE = 0;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				String response = (String) msg.obj;
				parsexml(response);
			}
		}
	};

	private void parsexml(String xmlData) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			XMLReader xmlReader = factory.newSAXParser().getXMLReader();
			ContentHandler handler = new ContentHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(xmlData)));
			// Toast.makeText(ChooseAreaActivity.this,judges(handler.getCity(),handler.getDistrict()),
			// Toast.LENGTH_LONG).show();
			e_train_find.setText(judges(handler.getCity(),
					handler.getDistrict()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String judges(String city, String district) {
		String newstr = null;
		int a = district.indexOf("鍖�");
		if (a == -1) {
			// 涓嶅瓨鍦�
			int b = district.length();
			newstr = district.substring(0, b - 1);
		} else {
			int b = city.length();
			newstr = city.substring(0, b - 1);
		}
		return newstr;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isFromWeatherActivity = getIntent().getBooleanExtra(
				"from_weather_activity", false);
		// SharedPreferences
		// prefs=PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
		// 宸茬粡閫夋嫨浜嗗煄甯備笖涓嶆槸浠巜eatherActivity璺宠浆杩囨潵锛屾墠浼氱洿鎺ヨ烦杞埌weatherActivity

		if (prefs.getBoolean("city_selected", false) && !isFromWeatherActivity) {
			
		
			finish();
			return;
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);
		
		listView = (ListView) findViewById(R.id.list_view);
		titleText = (TextView) findViewById(R.id.title_text);

		e_train_find = (EditText) findViewById(R.id.e_train_find);// 瑕佷慨鏀圭殑鍦版柟
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		List<String> providerList = locationManager.getProviders(true);
		if (providerList.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else {
			Toast.makeText(ChooseAreaActivity.this,
					"No location provider to use", Toast.LENGTH_LONG).show();
			return;
		}
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			_long = String.format("%f", location.getLongitude());
			_lati = String.format("%f", location.getLatitude());
		}

		g_location = (Button) findViewById(R.id.g_location);
		g_location.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 澶勭悊鏁版嵁杩斿洖鍊�
				// e_train_find.setText(_result);
				sendRequest(_long, _lati);
			}
		});

		b_train_find = (Button) findViewById(R.id.b_train_find);
		b_train_find.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("county_code", e_train_find.getText()
						.toString());
				setResult(RESULT_OK, intent);
				finish();

			}
		});
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);
		coolWeatherDB = CoolWeatherDB.getInstance(this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int index,
					long arg3) {
				Log.d("ssssssssssss", "sssssssssssssssssssssssssss");
				if (currentLevel == LEVEL_PROVINCE) {
					selectedProvince = provinceList.get(index);
					Log.d("sssssssss", selectedProvince.getProvinceName());
					Log.d("sssssssss", selectedProvince.getProvinceCode());
					queryCities();
				} else if (currentLevel == LEVEL_CITY) {
					selectedCity = cityList.get(index);
					Log.d("mainactivity", selectedCity.getCityCode());
					queryCounties();
				} else if (currentLevel == LEVEL_COUNTY) {
					// String countyCode=countyList.get(index).getCountyCode();
					String countyCode = countyList.get(index).getCountyName();
					Log.d("countyCode", countyCode);
					
				
					finish();
				}
			}

		});

		queryProvinces();
	}

	private void queryProvinces() {
		provinceList = coolWeatherDB.loadProvinces();
		if (provinceList.size() > 0) {
			dataList.clear();
			for (Province province : provinceList) {
				dataList.add(province.getProvinceName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText("中国地区");
			currentLevel = LEVEL_PROVINCE;
		} else {
			queryFromServer(null, "province");
		}
	}

	private void queryCities() {
		cityList = coolWeatherDB.loadCities(selectedProvince.getId());
		Log.d("xxxxxxxxxxx", String.format("%d", selectedProvince.getId()));
		if (cityList.size() > 0) {
			Log.d("wwwwwwwwww", "wwwwwwwwwww");
			dataList.clear();
			for (City city : cityList) {
				dataList.add(city.getCityName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedProvince.getProvinceName());
			currentLevel = LEVEL_CITY;
		} else {
			Log.d("queryFromServler", "ssssssssssssssss");
			queryFromServer(selectedProvince.getProvinceCode(), "city");
		}
	}

	/*
	 * 锟斤拷询选锟斤拷锟斤拷锟斤拷锟斤拷锟叫碉拷锟截ｏ拷锟斤拷锟饺达拷锟斤拷锟捷匡拷锟窖拷锟斤拷锟斤拷没锟叫诧拷询锟斤拷锟斤拷去锟斤拷锟斤拷锟斤拷锟较诧拷询锟斤拷
	 */
	private void queryCounties() {
		countyList = coolWeatherDB.loadCounties(selectedCity.getId());
		if (countyList.size() > 0) {
			dataList.clear();
			for (County county : countyList) {
				dataList.add(county.getCountyName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedCity.getCityName());
			currentLevel = LEVEL_COUNTY;
		} else {
			// Log.d("maincatti",selectedCity.getCityCode());
			queryFromServer(selectedCity.getCityCode(), "county");
		}
	}

	/*
	 * 锟斤拷锟捷达拷锟斤拷拇锟斤拷藕锟斤拷锟斤拷痛臃锟斤拷锟斤拷锟斤拷喜锟窖★拷锟斤拷氐锟斤拷锟斤拷荨锟�
	 */
	private void queryFromServer(final String code, final String type) {
		// Log.d("ssssssssssxxxxxx",code);
		Log.d("xssssssssssssssssss", type);
		String address;
		if (!TextUtils.isEmpty(code)) {
			// Log.d("qqqqqqqqqqqqqqq",code+"pppppppppppppppp");
			if (code.length() == 1) {
				address = "http://www.weather.com.cn/data/list3/city0" + code
						+ ".xml";
			} else {
				address = "http://www.weather.com.cn/data/list3/city" + code
						+ ".xml";
			}
		} else {
			address = "http://www.weather.com.cn/data/list3/city.xml";
			// address="http://www.weather.com.cn/data/list3/city01.xml";
		}
		showProgressDialog();
		HttpUtil.sendHttpRequest(address,
				new com.example.guojia.util.HttpCallbackListener() {

					@Override
					public void onFinish(String response) {
						// TODO Auto-generated method stub
						Log.d("qqqqqqqqqqqqqqq", "qqqqqqqqqqpppppppppppppppp");
						boolean result = false;
						if ("province".equals(type)) {
							result = Utility.handleProvinceResponse(
									coolWeatherDB, response);
						} else if ("city".equals(type)) {
							result = Utility.handleCitiesResponse(
									coolWeatherDB, response,
									selectedProvince.getId());
						} else if ("county".equals(type)) {
							result = Utility.handleCountiesResponse(
									coolWeatherDB, response,
									selectedCity.getId());
						}
						if (result) {
							// 通锟斤拷runOnUiThread()锟斤拷锟斤拷锟截碉拷锟斤拷锟竭程达拷锟斤拷锟竭硷拷
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									closeProgressDialog();
									if ("province".equals(type)) {
										queryProvinces();
									} else if ("city".equals(type)) {
										queryCities();
									} else if ("county".equals(type)) {
										queryCounties();
									}
								}

							});
						}

					}

					@Override
					public void onError(Exception e) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								// closeProgressDialog();
								Toast.makeText(ChooseAreaActivity.this,
										"鍚屾澶辫触锛�", Toast.LENGTH_LONG).show();
							}
						});
					}

				});
	}

	/*
	 * 锟斤拷示锟斤拷锟饺对伙拷锟斤拷
	 */
	private void showProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("鍚屾涓�併�併��");
			progressDialog.setCanceledOnTouchOutside(false);
		}
		progressDialog.show();
	}

	/*
	 * 锟截闭斤拷锟饺对伙拷锟斤拷
	 */
	private void closeProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	/*
	 * 锟斤拷锟斤拷back锟斤拷锟斤拷锟斤拷锟斤拷锟捷碉拷前锟侥硷拷锟斤拷锟斤拷锟叫断ｏ拷锟斤拷时应锟矫凤拷锟斤拷锟斤拷锟叫憋拷省锟叫憋拷锟斤拷锟斤拷直锟斤拷锟剿筹拷锟斤拷
	 */
	@Override
	public void onBackPressed() {
		if (currentLevel == LEVEL_COUNTY) {
			queryCities();
		} else if (currentLevel == LEVEL_CITY) {
			queryProvinces();
		} else {
			if (isFromWeatherActivity) {
				
				
			}
			finish();
		}
	}

	private void sendRequest(final String longitude, final String latitude) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpURLConnection connection = null;
				try {
					URL url = new URL(
							"http://api.map.baidu.com/geocoder/v2/?"
									+ "ak=vaOUF8QqjkrhdQLgkmY9RwsvuufYAlCW"
									+ "&mcode=8C:88:5A:1A:46:21:78:E5:D2:E2:3B:3E:64:FC:44:06:6F:5F:6A:CD;com.example.guojia.activity"								
									+ "&callback=renderReverse&location="
									+ latitude + "," + longitude + "&out"
									+ "put=xml&pois=0");
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					Message message = new Message();
					message.what = SHOW_RESPONSE;
					message.obj = response.toString();
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
			}
		}).start();
	}
}
