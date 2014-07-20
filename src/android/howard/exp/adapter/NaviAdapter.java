package android.howard.exp.adapter;

import java.util.List;

import android.content.Context;
import android.howard.exp.bean.RootItemBean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bpok.fileex.R;

public class NaviAdapter extends BaseAdapter {

	private List<RootItemBean> contentList;
	private Context mContext;
	private ViewHolder viewHolder;

	/**
	 * @param contentList
	 * @param mContext
	 */
	public NaviAdapter(List<RootItemBean> contentList, Context mContext) {
		super();
		this.contentList = contentList;
		this.mContext = mContext;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.navi_item, null);
			viewHolder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_line_one);
			viewHolder.iv_icon = (ImageView) convertView
					.findViewById(R.id.iv_icon);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		RootItemBean content = contentList.get(position);
		viewHolder.tv_title.setText(content.getTitleString());
		if (content.getTitleString().equals("全部"))
			viewHolder.iv_icon.setBackgroundResource(R.drawable.ic_launcher);
		if (content.getTitleString().equals("音乐"))
			viewHolder.iv_icon.setBackgroundResource(R.drawable.music);
		if (content.getTitleString().equals("图片"))
			viewHolder.iv_icon.setBackgroundResource(R.drawable.camera);
		if (content.getTitleString().equals("视频"))
			viewHolder.iv_icon.setBackgroundResource(R.drawable.movie);
		if (content.getTitleString().equals("文档"))
			viewHolder.iv_icon.setBackgroundResource(R.drawable.txt);

		return convertView;
	}

	@Override
	public int getCount() {
		return contentList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	final class ViewHolder {
		private TextView tv_title;
		private ImageView iv_icon;
	}
}
