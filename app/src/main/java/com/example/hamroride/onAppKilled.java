package com.example.hamroride;

public class onAppKilled extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("driversAvailable");
        GeoFire geoFire = new GeoFire(ref);
        geoFire.removeLocation(userId);
    }
}

