package android.howard.exp.bean;

import android.graphics.Bitmap;
import android.howard.exp.views.SlideView;

public class RootItemBean {
	
	public SlideView slideView;
	
	private String titleString;
	private String sizeString;
	private String pathString;
	private String childCountString;
	private String createDateString;
	private Bitmap icon;
	private boolean isDir;
	private boolean isApp;
	private boolean isMusic;
	private boolean isTextFile;
	private boolean isMovie;
	private boolean isImage;
	
	private boolean isPdf;
	private boolean isDoc;
	private boolean isZipRar;
	private boolean isNotSupported;

	/**
	 * 
	 */
	public RootItemBean() {
		super();
	}

	public SlideView getSlideView() {
		return slideView;
	}

	public void setSlideView(SlideView slideView) {
		this.slideView = slideView;
	}

	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public String getSizeString() {
		return sizeString;
	}

	public void setSizeString(String sizeString) {
		this.sizeString = sizeString;
	}

	public String getPathString() {
		return pathString;
	}

	public void setPathString(String pathString) {
		this.pathString = pathString;
	}

	public String getChildCountString() {
		return childCountString;
	}

	public void setChildCountString(String childCountString) {
		this.childCountString = childCountString;
	}

	public String getCreateDateString() {
		return createDateString;
	}

	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	public boolean isApp() {
		return isApp;
	}

	public void setApp(boolean isApp) {
		this.isApp = isApp;
	}

	public boolean isMusic() {
		return isMusic;
	}

	public void setMusic(boolean isMusic) {
		this.isMusic = isMusic;
	}

	public boolean isTextFile() {
		return isTextFile;
	}

	public void setTextFile(boolean isTextFile) {
		this.isTextFile = isTextFile;
	}

	public boolean isMovie() {
		return isMovie;
	}

	public void setMovie(boolean isMovie) {
		this.isMovie = isMovie;
	}

	public boolean isImage() {
		return isImage;
	}

	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}

	public boolean isPdf() {
		return isPdf;
	}

	public void setPdf(boolean isPdf) {
		this.isPdf = isPdf;
	}

	public boolean isDoc() {
		return isDoc;
	}

	public void setDoc(boolean isDoc) {
		this.isDoc = isDoc;
	}

	public boolean isZipRar() {
		return isZipRar;
	}

	public void setZipRar(boolean isZipRar) {
		this.isZipRar = isZipRar;
	}

	public boolean isNotSupported() {
		return isNotSupported;
	}

	public void setNotSupported(boolean isNotSupported) {
		this.isNotSupported = isNotSupported;
	}


}
