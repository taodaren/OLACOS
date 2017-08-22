package net.osplay.service;

import net.osplay.app.I;
import net.osplay.service.entity.TestBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Retrofit 网络请求服务接口
 */

public interface RetrofitService {
    @GET(I.ADD_TEXT_DATA)
    Observable<List<TestBean>> getTextData(
            @Query("cat_id") int catId,
            @Query("page_id") int pageId,
            @Query("page_size") int pageSize
    );
}
