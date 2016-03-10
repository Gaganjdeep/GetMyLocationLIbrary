package gagan.ameba.mylocationg;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by GaGandeep on 10 Mar 2016.
 */
public abstract class CurrentLocActivityG extends AppCompatActivity implements

        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{


    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 60000;

    private GoogleApiClient mGoogleApiClient;

    private LocationRequest mLocationRequest;
    // Location updates intervals in sec

    private static int UPDATE_INTERVAL = 30000; // 30 sec

    private static int FATEST_INTERVAL = 5000; // 5 sec

    private static int DISPLACEMENT = 50; // 50 meters

    public static Location mLastLocation;

    public abstract void getCurrentLocationG(Location currentLocation);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if (checkPlayServices())
        {
            // Building the GoogleApi client
             buildGoogleApiClient();
            createLocationRequest();
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (mGoogleApiClient != null)
        {
            mGoogleApiClient.connect();
        }
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        if (mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.disconnect();
        }
    }


    public synchronized void buildGoogleApiClient()
    {
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }

    /**     * Creating location request object     */

    public void createLocationRequest()
    {
        mLocationRequest = new LocationRequest();

        mLocationRequest.setInterval(UPDATE_INTERVAL);

        mLocationRequest.setFastestInterval(FATEST_INTERVAL);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);

    }


    /**     * Method to verify google play services on the device     */

    public boolean checkPlayServices()
    {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getApplicationContext());

        if (resultCode != ConnectionResult.SUCCESS)
        {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode))
            {
                GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity)

                        getApplicationContext(), PLAY_SERVICES_RESOLUTION_REQUEST).show();

            }
            else
            {

                ((Activity) this).finish();
            }
            return false;
        }
        return true;
    }


    /**     * Method to display the location on main UI     */

    public Location displayLocation()
    {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return mLastLocation;
        }
        mLastLocation = LocationServices.FusedLocationApi.

                getLastLocation(mGoogleApiClient);
        if (mLastLocation != null)
        {
            getCurrentLocationG(mLastLocation);

            return mLastLocation;

        }

        return null;

    }


    @Override

    public void onConnected(Bundle bundle)
    {
        displayLocation();    }

    @Override

    public void onConnectionSuspended(int i)
    {
        //handler connection suspended here..
    }

    @Override

    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        //handler connection failed here..
    }
}

