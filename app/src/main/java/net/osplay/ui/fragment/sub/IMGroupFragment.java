package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.olacos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IMGroupFragment extends Fragment {
    private View inflate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_imgroup, container, false);
        return inflate;
    }

}
