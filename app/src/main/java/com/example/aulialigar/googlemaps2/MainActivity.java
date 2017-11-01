package com.example.aulialigar.googlemaps2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    static final CameraPosition BLITAR = CameraPosition.builder()
            .target(new LatLng(-8.095463, 112.160906))
            .zoom(10)
            .bearing(0)
            .tilt(45)
            .build();
    GoogleMap m_map;
    boolean mapReady = false;
    MarkerOptions Tulungagung;
    MarkerOptions Kediri;
    MarkerOptions Pare;
    MarkerOptions Wlingi;
    MarkerOptions Blitar;

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tulungagung = new MarkerOptions()
                .position(new LatLng(-8.091221, 111.964173))
                .icon(vectorToBitmap(R.drawable.ic_place_black_24dp, Color.parseColor("#A4C639")))
                .title("Tulungagung");

        Kediri = new MarkerOptions()
                .position(new LatLng(-7.848016, 112.017829))
                .icon(vectorToBitmap(R.drawable.ic_place_black_24dp, Color.parseColor("#A4C639")))
                .title("Kediri");

        Pare = new MarkerOptions()
                .position(new LatLng(-7.765512, 112.197899))
                .icon(vectorToBitmap(R.drawable.ic_place_black_24dp, Color.parseColor("#A4C639")))
                .title("Pare");

        Wlingi = new MarkerOptions()
                .position(new LatLng(-8.129380, 112.320999))
                .icon(vectorToBitmap(R.drawable.ic_place_black_24dp, Color.parseColor("#A4C639")))
                .title("Wlingi");

        Blitar = new MarkerOptions()
                .position(new LatLng(-8.095463, 112.160906))
                .icon(vectorToBitmap(R.drawable.ic_place_black_24dp, Color.parseColor("#A4C639")))
                .title("Blitar");

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapReady = true;
        m_map = map;
        m_map.addMarker(Tulungagung);
        m_map.addMarker(Kediri);
        m_map.addMarker(Pare);
        m_map.addMarker(Wlingi);
        m_map.addMarker(Blitar);
        flyTo(BLITAR);
    }

    private void flyTo(CameraPosition target) {
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }

    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
