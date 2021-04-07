package com.bob.democonstrain.retrofit;

import com.bob.democonstrain.model.Post;

import java.util.List;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface IMyAPI {
    @GET("posts")
    Observable<List<Post>> getAllPost();
}
