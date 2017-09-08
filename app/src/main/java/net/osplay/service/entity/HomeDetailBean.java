package net.osplay.service.entity;

import java.util.List;

/**
 * 暂时用视频接口数据
 */

public class HomeDetailBean {

    private List<TrailersBean> trailers;

    public List<TrailersBean> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<TrailersBean> trailers) {
        this.trailers = trailers;
    }

    public static class TrailersBean {
        /**
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

        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private int videoLength;
        private int rating;
        private String summary;
        private List<String> type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
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

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
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
    }
}
