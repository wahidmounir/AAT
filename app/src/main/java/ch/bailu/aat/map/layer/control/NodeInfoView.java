package ch.bailu.aat.map.layer.control;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import ch.bailu.aat.gpx.GpxInformation;
import ch.bailu.aat.gpx.InfoID;
import ch.bailu.aat.map.MapColor;
import ch.bailu.aat.map.layer.gpx.Factory;
import ch.bailu.aat.views.html.HtmlScrollTextView;
import ch.bailu.aat.views.PercentageLayout;
import ch.bailu.aat.views.graph.DistanceAltitudeGraphView;


public class NodeInfoView extends PercentageLayout {
    private final HtmlScrollTextView htmlView;
    private final DistanceAltitudeGraphView graphView;


    private int backgroundColor;

    public NodeInfoView(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);

        backgroundColor = MapColor.LIGHT;

        htmlView = new HtmlScrollTextView(context);
        htmlView.getTextView().setTextColor(MapColor.TEXT);
        htmlView.setBackgroundColor(backgroundColor);
        add(htmlView, 60);



        graphView = new DistanceAltitudeGraphView(context);
        graphView.setVisibility(GONE);
        graphView.setBackgroundColor(MapColor.DARK);
        graphView.showLabel(false);
        add(graphView, 40);

        setBackgroundColor(Color.TRANSPARENT);
    }


    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        htmlView.getTextView().setOnLongClickListener(l);
        super.setOnLongClickListener(l);
    }


    public void setHtmlText(int IID, GpxInformation info, String htmlText) {
        int newBackgroundColor = MapColor.getColorFromIID(IID);

        if (backgroundColor != newBackgroundColor) {
            backgroundColor = newBackgroundColor;
            htmlView.setBackgroundColor(toBackgroundColorLight(backgroundColor));
            graphView.setBackgroundColor(toBackgroundColorDark(backgroundColor));
        }

        htmlView.setHtmlText(htmlText);
    }

    private int toBackgroundColorLight(int color) {
        //color = MapColor.setSaturation(color, 0.15f);
        //color = MapColor.setAlpha(color, MapColor.ALPHA_LOW);
        return MapColor.toLightTransparent(color);

    }


    private int toBackgroundColorDark(int color) {
        //color = MapColor.setValue(color, 0.15f);
        //color = MapColor.setAlpha(color, MapColor.ALPHA_LOW);
        return MapColor.toDarkTransparent(color);
    }

    public void setGraph(GpxInformation info, int index) {
        graphView.setVisibility(info);
        graphView.onContentUpdated(InfoID.ALL, info, index);
    }
}
