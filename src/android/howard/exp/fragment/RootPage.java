package android.howard.exp.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.howard.exp.adapter.ImageReaderTask;
import android.howard.exp.adapter.ViedioReaderTask;
import android.howard.exp.bean.RootItemBean;
import android.howard.exp.utils.TextFileReader;
import android.howard.exp.views.ListViewCompat;
import android.howard.exp.views.SlideView;
import android.howard.exp.views.SlideView.OnSlideListener;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bpok.fileex.FileExApp;
import com.bpok.fileex.R;

public class RootPage extends BaseFragment implements OnItemClickListener,
		OnSlideListener {
	private View mRootView;
	private Context mContext;

	private ListViewCompat mListView;
	private SlideView mLastSlideViewWithStatusOn;
	private List<RootItemBean> mCurrlistContents;
	private RootListAdapter mAdapter;

	private String mCurrentFilePath;
	private TextFileReader textReader;

	private FileExApp baseActivity;

	@Override
	public void onAttach(Activity activity) {
		this.mContext = activity.getApplicationContext();
		baseActivity = (FileExApp) getActivity();
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.rootpage, null);
		findViews();
		getRootFiles();
		inflateList(mCurrlistContents, false);
		return mRootView;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void findViews() {
		mListView = (ListViewCompat) mRootView.findViewById(R.id.lv_local);
		mListView.setOnItemClickListener(this);
	}

	private void getRootFiles() {
		String rootPathString = Environment.getExternalStorageDirectory()
				.getPath();
		mCurrlistContents = getCurrentFiles(rootPathString);
	}

	private List<RootItemBean> getCurrentFiles(String path) {

		File file = new File(path);
		File[] files = file.listFiles();
		List<RootItemBean> currlistContents = new ArrayList<RootItemBean>();
		for (int i = 0; i < files.length; i++) {
			final RootItemBean item = new RootItemBean();
			String fileNameString = files[i].getName();
			String pathString = files[i].getPath();
			item.setTitleString(fileNameString);
			item.setPathString(pathString);
			item.setDir(files[i].isDirectory());
			// if is not a dir
			item.setSizeString(files[i].length() + "");
			if (fileNameString.endsWith(".apk")) {
				item.setApp(true);

			} else if (fileNameString.endsWith(".mp3")
					|| fileNameString.endsWith(".wma")
					|| fileNameString.endsWith(".ogg")
					|| fileNameString.endsWith(".wav")
					|| fileNameString.endsWith(".aac")

					// upper case
					|| fileNameString.endsWith(".MP3")
					|| fileNameString.endsWith(".WMA")
					|| fileNameString.endsWith(".WAV")
					|| fileNameString.endsWith(".OGG")
					|| fileNameString.endsWith(".AAC")) {
				item.setMusic(true);

			} else if (fileNameString.endsWith(".png")
					|| fileNameString.endsWith(".jpg")
					|| fileNameString.endsWith(".jpeg")
					|| fileNameString.endsWith(".bmp")

					|| fileNameString.endsWith(".PNG")
					|| fileNameString.endsWith(".JPG")
					|| fileNameString.endsWith(".JPEG")
					|| fileNameString.endsWith(".BMP")) {
				item.setImage(true);

			} else if (fileNameString.endsWith(".mp4")
					|| fileNameString.endsWith(".avi")
					|| fileNameString.endsWith(".wmv")
					|| fileNameString.endsWith(".flv")
					|| fileNameString.endsWith(".3gp")

					|| fileNameString.endsWith(".MP4")
					|| fileNameString.endsWith(".AVI")
					|| fileNameString.endsWith(".WMV")
					|| fileNameString.endsWith(".FLV")
					|| fileNameString.endsWith(".3GP")) {
				item.setMovie(true);

			} else if (fileNameString.endsWith(".zip")
					|| fileNameString.endsWith(".rar")
					|| fileNameString.endsWith(".tar")
					|| fileNameString.endsWith(".tar.gz")
					|| fileNameString.endsWith(".7z")
					|| fileNameString.endsWith(".iso")

					|| fileNameString.endsWith(".ZIP")
					|| fileNameString.endsWith(".RAR")
					|| fileNameString.endsWith(".TAR")
					|| fileNameString.endsWith(".TAR.GZ")
					|| fileNameString.endsWith(".7Z")
					|| fileNameString.endsWith(".ISO")) {
				item.setZipRar(true);

			} else if (fileNameString.endsWith(".txt")
					|| fileNameString.endsWith(".java")
					|| fileNameString.endsWith(".c")
					|| fileNameString.endsWith(".rc")
					|| fileNameString.endsWith(".log")
					|| fileNameString.endsWith(".prop")
					|| fileNameString.endsWith(".xml")
					|| fileNameString.endsWith(".sh")
					|| fileNameString.endsWith(".cfg")
					|| fileNameString.endsWith(".cpp")

					|| fileNameString.endsWith(".TXT")
					|| fileNameString.endsWith(".JAVA")
					|| fileNameString.endsWith(".C")
					|| fileNameString.endsWith(".RC")
					|| fileNameString.endsWith(".LOG")
					|| fileNameString.endsWith(".PROP")
					|| fileNameString.endsWith(".XML")
					|| fileNameString.endsWith(".SH")
					|| fileNameString.endsWith(".CFG")
					|| fileNameString.endsWith(".CPP")) {
				item.setTextFile(true);
			}

			else if (fileNameString.endsWith(".doc")
					|| fileNameString.endsWith(".DOC")) {
				item.setDoc(true);
			}

			else if (fileNameString.endsWith(".pdf")
					|| fileNameString.endsWith(".PDF")) {
				item.setPdf(true);
			}

			else {
				item.setNotSupported(true);
			}

			currlistContents.add(item);
		}
		return currlistContents;
	}

	/**
	 * inflate/update the list content
	 */
	private void inflateList(List<RootItemBean> list, boolean resetAdapter) {
		if (resetAdapter || mAdapter == null) {
			mAdapter = new RootListAdapter(mCurrlistContents, mContext);
		}
		if (mAdapter != null)
			mListView.setAdapter(mAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		RootItemBean item = mCurrlistContents.get(position);
		if (item.isDir()) {
			String _pathString = mCurrlistContents.get(position)
					.getPathString();
			mCurrlistContents = getCurrentFiles(_pathString);
			mCurrentFilePath = _pathString;
			inflateList(mCurrlistContents, true);
		} else if (item.isTextFile()) {
			if (textReader == null) {
				textReader = new TextFileReader(getActivity());
			}
			String readString = textReader.read(item.getPathString());
			if (readString != null)
				Log.i("howard", readString);
			Fragment fragment = new TextReader();
			Bundle args = new Bundle();
			args.putString("content", readString);
			fragment.setArguments(args);
			if (baseActivity != null) {
				baseActivity.showShow(fragment);
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void handleBackPressed() {
		if (mCurrentFilePath == null
				|| mCurrentFilePath.equals(Environment
						.getExternalStorageDirectory().getPath())) {
			baseActivity.quit();
		} else {
			File _File = new File(mCurrentFilePath);
			mCurrentFilePath = _File.getParent();
			mCurrlistContents = getCurrentFiles(mCurrentFilePath);
			inflateList(mCurrlistContents, true);
		}
	}

	@Override
	public void onSlide(View view, int status) {

		if (mLastSlideViewWithStatusOn != null
				&& mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}

		if (status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (SlideView) view;
		}
	}

	public class RootListAdapter extends BaseAdapter {

		private List<RootItemBean> contentList;
		private Context mContext;
		private ViewHolder viewHolder;

		/**
		 * @param contentList
		 * @param mContext
		 */
		public RootListAdapter(List<RootItemBean> contentList, Context mContext) {
			super();
			this.contentList = contentList;
			this.mContext = mContext;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {

			SlideView slideView = (SlideView) convertView;
			if (slideView == null) {
				viewHolder = new ViewHolder();
				View itemView = LayoutInflater.from(mContext).inflate(
						R.layout.root_item, null);
				slideView = new SlideView(mContext);
				slideView.setContentView(itemView);

				// find views
				viewHolder.tv_title = (TextView) slideView
						.findViewById(R.id.tv_line_one);
				viewHolder.iv_icon = (ImageView) slideView
						.findViewById(R.id.iv_icon);

				slideView.setOnSlideListener(RootPage.this);
				slideView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) slideView.getTag();
			}

			RootItemBean content = contentList.get(position);
			content.setSlideView(slideView);
			content.slideView.shrink();

			viewHolder.tv_title.setText(content.getTitleString());

			// update the icon
			if (content.isDir()) {
				viewHolder.iv_icon.setBackgroundResource(R.drawable.dir);
			} else if (content.isApp()) {
				viewHolder.iv_icon.setBackgroundResource(R.drawable.x);
			} else if (content.isMovie()) {
				viewHolder.iv_icon.setBackgroundResource(R.drawable.movie);
				new ViedioReaderTask(mContext, viewHolder.iv_icon, 0, 0)
						.execute(content.getPathString());
			} else if (content.isMusic()) {
				viewHolder.iv_icon.setBackgroundResource(R.drawable.music);
			} else if (content.isImage()) {
				viewHolder.iv_icon.setBackgroundResource(R.drawable.camera);
				new ImageReaderTask(mContext, viewHolder.iv_icon, 45, 45)
						.execute(content.getPathString());
			} else if (content.isTextFile()) {
				viewHolder.iv_icon.setBackgroundResource(R.drawable.txt);
			}

			return convertView;
		}

		@Override
		public int getCount() {
			return contentList.size();
		}

		@Override
		public Object getItem(int position) {
			return contentList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		final class ViewHolder {
			private TextView tv_title;
			private TextView tv_size;
			private TextView tv_childCount;
			private ImageView iv_icon;
			private ViewGroup deleteHolder;
		}
	}

}
