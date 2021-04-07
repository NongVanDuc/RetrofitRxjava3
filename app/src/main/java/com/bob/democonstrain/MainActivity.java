package com.bob.democonstrain;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bob.democonstrain.model.Post;
import com.bob.democonstrain.presenters.FetchDataPresenter;
import com.bob.democonstrain.presenters.IOnFetchPost;
import com.bob.democonstrain.retrofit.IMyAPI;
import com.bob.democonstrain.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements IOnFetchPost {

    private RecyclerView mRcvPost;
    private IMyAPI myAPI;
    private CompositeDisposable mCompositeDisposable;
    private FetchDataPresenter mFetchDataPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        // initAPI
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(IMyAPI.class);
        mCompositeDisposable = new CompositeDisposable();
        mFetchDataPresenter = new FetchDataPresenter(mCompositeDisposable,myAPI,this);
        // fetch data API
        mFetchDataPresenter.fetchPost();
        //initData();
    }

//    private void initData() {
//        mCompositeDisposable.add(myAPI.getAllPost()
//        .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<Post>>() {
//                    @Override
//                    public void accept(List<Post> posts) throws Throwable {
//                        Log.e("xxx", "accept: " + posts.size());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Log.e("xxx", "accept: Error!!!" );
//                    }
//
//                }));
//    }

    private void initView() {
        mRcvPost = (RecyclerView) findViewById(R.id.rcv_post);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    @Override
    public void onFetchPostSuccess(List<Post> postList) {
        Log.e("xxxx", "accept: " + postList.size());
    }

    @Override
    public void onFetchPostError() {
        Log.e("xxxx", "accept: Error!!!" );
    }
}