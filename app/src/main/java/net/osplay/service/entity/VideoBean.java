package net.osplay.service.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by admin on 2017/9/8.
 * <p>
 * id : 67132
 * movieName : 《追捕》国际版预告
 * coverImg : http://img5.mtime.cn/mg/2017/08/16/162030.31009578.jpg
 * movieId : 222372
 * url : http://vfx.mtime.cn/Video/2017/08/16/mp4/170816125337243063.mp4
 * hightUrl : http://vfx.mtime.cn/Video/2017/08/16/mp4/170816125337243063.mp4
 * videoTitle : 追捕 国际先行预告片
 * videoLength : 57
 * rating : -1
 * type : ["剧情","犯罪"]
 * summary : 张涵予福山雅治双雄对峙
 */

public class VideoBean implements Parcelable {
    private String id;
    private String movieName;
    private String coverImg;
    private String movieId;
    private String url;
    private String hightUrl;
    private String videoTitle;
    private int videoLength;
    private String rating;
    private String summary;
    private List<String> type;

    protected VideoBean(Parcel in) {
        id = in.readString();
        movieName = in.readString();
        coverImg = in.readString();
        movieId = in.readString();
        url = in.readString();
        hightUrl = in.readString();
        videoTitle = in.readString();
        videoLength = in.readInt();
        rating = in.readString();
        summary = in.readString();
        type = in.createStringArrayList();
    }

    public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel in) {
            return new VideoBean(in);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHightUrl() {
        return hightUrl;
    }

    public void setHightUrl(String hightUrl) {
        this.hightUrl = hightUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public int getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(int videoLength) {
        this.videoLength = videoLength;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(movieName);
        dest.writeString(coverImg);
        dest.writeString(movieId);
        dest.writeString(url);
        dest.writeString(hightUrl);
        dest.writeString(videoTitle);
        dest.writeInt(videoLength);
        dest.writeString(rating);
        dest.writeString(summary);
        dest.writeStringList(type);
    }

}
