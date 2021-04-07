package com.bob.democonstrain.presenters;

import com.bob.democonstrain.model.Post;

import java.util.List;

public interface IOnFetchPost {
    void onFetchPostSuccess(List<Post> postList);
    void onFetchPostError();
}
