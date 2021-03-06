package ch.bailu.aat.views;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.mapsforge.map.model.common.Observer;

import ch.bailu.aat.menus.MultiViewMenu;
import ch.bailu.aat.util.ui.AppTheme;
import ch.bailu.aat.views.description.MultiView;

public class MultiViewSelector extends LinearLayout {
    private final MultiView multiView;

    private final TextView label;

    public MultiViewSelector(MultiView mv) {
        super(mv.getContext());
        multiView = mv;

        label = new TextView(mv.getContext());
        label.setText(mv.getLabel());
        label.setSingleLine();

        AppTheme.themify(label);
        AppTheme.themify(this);
        addView(label);

        setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        setOrientation(HORIZONTAL);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new MultiViewMenu(multiView).showAsPopup(getContext(),
                        MultiViewSelector.this);
            }
        });


        multiView.addObserver(new Observer() {
            @Override
            public void onChange() {
                label.setText(multiView.getLabel());
            }
        });


    }




}
