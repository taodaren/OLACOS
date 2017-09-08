package net.osplay.service.entity;

import java.util.List;

/**
 * Created by admin on 2017/9/8.
 */

public class VideoMapperBean {
    List<VideoBean> trailers;

    public VideoMapperBean() {
    }

    public List<VideoBean> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<VideoBean> trailers) {
        this.trailers = trailers;
    }

}
