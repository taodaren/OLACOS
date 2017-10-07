package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.olacos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFansFragment extends Fragment {


    private View inflate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_other_fans, container, false);
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            Log.e("O","fans"+bundle.getString("otherMemberId"));
        }
        return inflate;
    }
}
