package ch.bailu.aat.map.mapsforge;

import android.graphics.Canvas;
import android.os.Build;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.model.common.Observer;

import ch.bailu.aat.dispatcher.DispatcherInterface;
import ch.bailu.aat.map.MapDensity;
import ch.bailu.aat.map.layer.MapPositionLayer;
import ch.bailu.aat.preferences.SolidMapViewAcceleration;
import ch.bailu.aat.services.ServiceContext;

public class MapsForgeView extends MapsForgeViewBase {

    private final MapsForgeForeground foreground;
    private final MapsForgeTileLayerStack stack;
    private final MapPositionLayer pos;

    public MapsForgeView(ServiceContext sc, DispatcherInterface dispatcher, String key) {
        super(sc, key, new MapDensity(sc.getContext()));

        pos = new MapPositionLayer(getMContext(), dispatcher);
        add(pos);

        stack = new MapsForgeTileLayerStack(this);
        add(stack);

        foreground = new MapsForgeForeground(this,
                getMContext(),
                new MapDensity(sc.getContext()),
                getLayers());

        setClickable(true);

        getModel().mapViewPosition.addObserver(new Observer() {
            private LatLong center = getModel().mapViewPosition.getCenter();

            @Override
            public void onChange() {
                LatLong newCenter = getModel().mapViewPosition.getCenter();

                if (newCenter.equals(center) == false) {
                    center = newCenter;
                    pos.onMapCenterChanged(center);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(new SolidMapViewAcceleration(getContext()).getValue(), null);
        }
    }


    @Override
    public void reDownloadTiles() {
        stack.reDownloadTiles();
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        foreground.dispatchDraw(canvas);
    }
}
