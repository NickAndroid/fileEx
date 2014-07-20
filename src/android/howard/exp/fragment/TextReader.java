package android.howard.exp.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bpok.fileex.R;

public class TextReader extends Fragment {

	private View mRootView;
	private Context mContext;
	private TextView readerTextView;
	private Typeface typeface;

	@Override
	public void onAttach(Activity activity) {
		mContext = activity.getApplicationContext();
		super.onAttach(activity);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.reader, null);
		showText();
		return mRootView;
	}

	private void showText() {
		typeface = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/MONACO.TTF");
		readerTextView = (TextView) mRootView.findViewById(R.id.tv_reader);
		String contentString = getArguments().getString("content");
		if (contentString != null) {
			readerTextView.setTypeface(typeface);
			readerTextView.setText(contentString);
		}
	}
}
