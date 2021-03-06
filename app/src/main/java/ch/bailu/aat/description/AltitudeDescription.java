package ch.bailu.aat.description;

import android.content.Context;

import java.util.Locale;

import ch.bailu.aat.R;
import ch.bailu.aat.gpx.GpxInformation;
import ch.bailu.aat.preferences.SolidUnit;

public class AltitudeDescription extends FloatDescription {

    private final SolidUnit unit;
    
    
    public AltitudeDescription(Context context) {
        super(context);
        unit = new SolidUnit(context);
    }

    
    
    @Override
    public String getLabel() {
        return getContext().getString(R.string.altitude);
    }

    @Override
    public String getUnit() {
        return unit.getAltitudeUnit();
    }

    public String getValue() {
        return getValue(getCache());
    }

    
    public String getValue(float v) {
        float f = unit.getAltitudeFactor();
        return String.format((Locale)null, "%.0f", v * f);
    }
    

    public String getValueUnit(float v) {
        float f = unit.getAltitudeFactor();
        return String.format((Locale)null, "%.0f %s", v * f, getUnit());
        
    }
    
    @Override
    public void onContentUpdated(int iid, GpxInformation info) {
        setCache( ((float)info.getAltitude()) );
    }
    


    
}
