package ch.bailu.aat.menus;


import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ch.bailu.aat.services.cache.osm_features.MapFeaturesListEntry;
import ch.bailu.aat.views.osm_features.MapFeaturesListView;

public class MapFeaturesMenu extends AbsMenu {

    private final MapFeaturesListView.OnSelected onSelected;

    private final MapFeaturesListEntry element;

    private final ArrayList<String> variants;

    public MapFeaturesMenu(MapFeaturesListEntry d, MapFeaturesListView.OnSelected s) {
        element = d;
        onSelected = s;
        variants = element.getVariants();
    }


    @Override
    public void inflate(Menu menu) {

        int g = MapFeaturesListView.OnSelected.EDIT;
        int i = 0;
        for (String v : variants) {
            menu.add(g,i,Menu.NONE,v);
            i++;
        }
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public Drawable getIcon() {
        return null;
    }

    @Override
    public void prepare(Menu menu) {

    }

    @Override
    public boolean onItemClick(MenuItem item) {
        onSelected.onSelected(element, MapFeaturesListView.OnSelected.EDIT ,variants.get(item.getItemId()));
        return true;
    }


}
