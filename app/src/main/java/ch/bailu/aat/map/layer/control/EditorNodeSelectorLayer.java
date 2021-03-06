package ch.bailu.aat.map.layer.control;

import android.content.Context;
import android.view.View;

import org.mapsforge.core.model.Point;

import ch.bailu.aat.gpx.GpxInformation;
import ch.bailu.aat.gpx.GpxPointNode;
import ch.bailu.aat.gpx.InfoID;
import ch.bailu.aat.map.MapContext;
import ch.bailu.aat.services.editor.EditorHelper;
import ch.bailu.aat.util.HtmlBuilderGpx;

public class EditorNodeSelectorLayer extends NodeViewLayer {

    private final EditorHelper edit;
    private final Context context;

    public EditorNodeSelectorLayer(MapContext mc, EditorHelper e) {
        super(mc);
        edit=e;
        context = mc.getContext();
    }



    @Override
    public GpxPointNode getSelectedNode() {
        return edit.getEditor().getSelected();
    }




    @Override
    public void setSelectedNode(int IID, GpxInformation info, GpxPointNode node, int index) {
        edit.getEditor().select(node);

        HtmlBuilderGpx h = new HtmlBuilderGpx(context);

        h.appendInfo(info, index);
        h.appendNode(node, info);
        setHtmlText(IID, info, h.toString());
    }


    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public boolean onTap(Point tapXY) {
        return false;
    }


    @Override
    public void onAttached() {

    }

    @Override
    public void onDetached() {

    }
}
