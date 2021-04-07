package com.bob.democonstrain.presenters;

import android.util.Log;

import com.bob.democonstrain.model.Post;
import com.bob.democonstrain.retrofit.IMyAPI;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FetchDataPresenter {
    private CompositeDisposable mCompositeDisposable;
    private IMyAPI myAPI;
    private IOnFetchPost mIOnFetchPost;

    public FetchDataPresenter(CompositeDisposable mCompositeDisposable, IMyAPI myAPI, IOnFetchPost iOnFetchPost) {
        this.mCompositeDisposable = mCompositeDisposable;
        this.myAPI = myAPI;
        this.mIOnFetchPost = iOnFetchPost;
    }

    public void fetchPost(){
        mCompositeDisposable.add(myAPI.getAllPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Throwable {
                        if(mIOnFetchPost != null){
                            mIOnFetchPost.onFetchPostSuccess(posts);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        if(mIOnFetchPost != null){
                            mIOnFetchPost.onFetchPostError();
                        }

                    }

                }));
    }
}
