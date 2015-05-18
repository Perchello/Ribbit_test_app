package perchello.ribbit;

import android.app.Application;

import com.parse.Parse;


public class RibbitApplication extends Application {
	
	@Override
	public void onCreate() { 
		super.onCreate();

	    Parse.initialize(this, "ZIVHiGMfSVLmvtBbbrcgx1dywMXOWgZC3n0WDwzI", "MFXD5CFctBHmP3BFs8vVXxWUGOQgy63pP0w7Jotn");
	}
}
