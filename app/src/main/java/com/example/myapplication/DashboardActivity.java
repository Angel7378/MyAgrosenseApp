package com.example.myapplication;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    TextView textCity, textWeatherTemp, textWeatherHumidity, textWeatherCondition;
    TextView textSoilValue, textPH, textNPK;
    TextView textPump, textRain;
    LinearLayout forecastContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize views
        textCity = findViewById(R.id.textCity);
        textWeatherTemp = findViewById(R.id.textWeatherTemp);
        textWeatherHumidity = findViewById(R.id.textWeatherHumidity);
        textWeatherCondition = findViewById(R.id.textWeatherCondition);

        textSoilValue = findViewById(R.id.textSoilValue);
        textPH = findViewById(R.id.textPH);
        textNPK = findViewById(R.id.textNPK);

        textPump = findViewById(R.id.textPump);
        textRain = findViewById(R.id.textRain);

        forecastContainer = findViewById(R.id.forecastContainer);

        // Example: Receiving mock values (Replace with live data from ThingSpeak or Firebase)
        updateDashboardWithMockData();
    }

    private void updateDashboardWithMockData() {
        // Weather
        textCity.setText("City: Pune");
        textWeatherTemp.setText("Temperature: 29°C");
        textWeatherHumidity.setText("Humidity: 58%");
        textWeatherCondition.setText("Condition: Clear Sky");

        // Soil
        textSoilValue.setText("Soil Moisture: 480 (Moist)");
        textPH.setText("pH Level: 7.0");
        textNPK.setText("NPK Level: 4.0");

        // Pump & Rain
        textPump.setText("Pump: OFF");
        textRain.setText("Rain: No Rain");

        // Forecast mock (only 2 entries for brevity)
        addForecastEntry("09:00 AM", "27°C", "50%", "Clear Sky");
        addForecastEntry("12:00 PM", "31°C", "45%", "Few Clouds");
    }

    private void addForecastEntry(String time, String temp, String humidity, String condition) {
        TextView forecastText = new TextView(this);
        forecastText.setText("Time: " + time + "\nTemp: " + temp +
                "\nHumidity: " + humidity + "\nCondition: " + condition);
        forecastText.setPadding(0, 12, 0, 12);
        forecastContainer.addView(forecastText);
    }
}
