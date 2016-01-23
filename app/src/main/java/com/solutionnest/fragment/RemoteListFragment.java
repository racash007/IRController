package com.solutionnest.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.solutionnest.activity.R;
import com.solutionnest.adapter.DeviceListAdaper;
import com.solutionnest.bean.Device;
import com.solutionnest.listener.OnFragmentInteractionListener;
import com.solutionnest.utils.db.ApplicationDBOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class RemoteListFragment extends Fragment implements AbsListView.OnItemClickListener {


    private List<Device> deviceList=null;


    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private ListView mListView;
   /* private View mheader;
    private View mfooter;*/

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static RemoteListFragment newInstance(String param1, String param2) {
        RemoteListFragment fragment = new RemoteListFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RemoteListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_list, container, false);
      /*  mfooter = inflater.inflate(R.layout.list_footer,null,false);
        mheader = inflater.inflate(R.layout.list_header,null,false );*/
        mListView = (ListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (deviceList==null)
        {
            deviceList = new ArrayList<Device>();
            ApplicationDBOpenHelper dbAdapter = new ApplicationDBOpenHelper(getActivity());
            for (int i=0;i<10;i++)
            {
                Device device=new Device();
                device.setDeviceName("Test");
                deviceList.add(device);
            }

            deviceList.addAll(dbAdapter.getDeviceList());
        }
        mAdapter = new DeviceListAdaper(getActivity(),  deviceList);
        mListView.setAdapter(mAdapter);
        //mListView.addHeaderView(mheader);
       // mListView.addFooterView(mfooter);
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(deviceList.get(position).getDeviceName());
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }





}
