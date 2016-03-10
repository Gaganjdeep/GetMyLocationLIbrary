# GetMyLocationLIbrary
This is very very easy to use lib., by using this lib you can get your current location.this repository contain mylocationLibG + a sample project which will show how to use this lib...

example code :

public class ActivityUsingLocationLib extends CurrentLocActivityG
{

    @Override
    public void getCurrentLocationG(Location currentLocation)
    {
       //        you will get your  current location here.............if you want to fetch latest lat long again ,just call method displayLocation();
        //         it will give you latest location here..
        Toast.makeText(ActivityUsingLocationLib.this, "Location refreshed..!", Toast.LENGTH_LONG).show();

         //  tvLocation.setText("Lat :" + currentLocation.getLatitude() + " ,\n Lng :" + currentLocation.getLongitude());
    }

    TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_using_location_lib);


    }

  
}
