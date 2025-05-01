package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThingSpeakDataActivity extends AppCompatActivity {
    private TextView soilMoistureTextView, rainStatusTextView, motorStatusTextView;
    private Button manualModeButton, automaticModeButton;
    private Switch motorSwitch;
    private static final String THINGSPEAK_READ_API_KEY = "PRAOZ0318BSG2N9L";
    private static final String THINGSPEAK_CHANNEL_ID = "2934432";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thingspeak_data);

        soilMoistureTextView = findViewById(R.id.soilMoistureTextView);
        rainStatusTextView = findViewById(R.id.rainStatusTextView);
        motorStatusTextView = findViewById(R.id.motorStatusTextView);
        manualModeButton = findViewById(R.id.manualModeButton);
        automaticModeButton = findViewById(R.id.automaticModeButton);

        fetchThingSpeakData();

        manualModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle manual mode (turn motor on/off)
                motorSwitch.setEnabled(true);
            }
        });

        automaticModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle automatic mode (control motor based on data)
                motorSwitch.setEnabled(false);
                autoControlMotor();
            }
        });
    }

    private void fetchThingSpeakData() {
        // Use an AsyncTask or a background thread to fetch data from ThingSpeak API
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    // Construct API URL to get the latest data from ThingSpeak
                    URL url = new URL("https://api.thingspeak.com/channels/" + THINGSPEAK_CHANNEL_ID + "/feeds.json?api_key=" + THINGSPEAK_READ_API_KEY + "&results=1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // Parse the response (this is a simplified example, adjust according to the ThingSpeak JSON format)
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray feeds = jsonResponse.getJSONArray("feeds");
                    JSONObject feed = feeds.getJSONObject(0);
                    String soilMoisture = feed.getString("field1"); // Adjust field names accordingly
                    String rainStatus = feed.getString("field2"); // Adjust field names accordingly

                    return soilMoisture + "," + rainStatus;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if (result != null) {
                    String[] data = result.split(",");
                    soilMoistureTextView.setText("Soil Moisture: " + data[0] + "%");
                    rainStatusTextView.setText("Rain Status: " + data[1]);

                    // Update motor status based on soil moisture and rain status
                    if (Integer.parseInt(data[0]) < 30 && data[1].equals("No Rain")) {
                        motorStatusTextView.setText("Motor: On (Irrigation needed)");
                    } else {
                        motorStatusTextView.setText("Motor: Off");
                    }
                }
            }
        }.execute();
    }

    private void autoControlMotor() {
        // Implement logic for automatic motor control based on soil moisture and rain status
        // For example, turn on the motor if soil moisture is low and there's no rain
        if (Integer.parseInt(soilMoistureTextView.getText().toString().split(":")[1].trim()) < 30) {
            motorSwitch.setChecked(true); // Turn motor on
        } else {
            motorSwitch.setChecked(false); // Turn motor off
        }
    }
}
