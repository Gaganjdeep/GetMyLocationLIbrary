package gagan.ameba.mylocationlibrary;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import gagan.ameba.mylocationg.CurrentLocActivityG;


/**
 * Created by GaGandeep on 10 Mar 2016.
 */
public class ActivityUsingLocationLib extends CurrentLocActivityG
{

    @Override
    public void getCurrentLocationG(Location currentLocation)
    {
//        you will get your  current location here.............if you want to fetch latest lat long again ,just call method displayLocation();
//         it will give you latest location here..
        Toast.makeText(ActivityUsingLocationLib.this, "Location refreshed..!", Toast.LENGTH_LONG).show();

        tvLocation.setText("Lat :" + currentLocation.getLatitude() + " ,\n Lng :" + currentLocation.getLongitude());
    }

    TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_using_location_lib);


        tvLocation = (TextView) findViewById(R.id.tvLocation);


    }

    public void showLoc(View view)
    {
        displayLocation();
    }
}
