package com.zidian.teacher.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.DataManager;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.util.RxUtils;
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * 修改个人信息
 * Created by GongCheng on 2017/4/7.
 */

public class ChangeInfoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.civ_portrait)
    CircleImageView civPortrait;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_motto)
    TextView tvMotto;

    @Inject
    DataManager dataManager;

    private static final int REQUEST_CODE = 0;
    @Override
    protected int getLayout() {
        return R.layout.activity_change_info;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle("编辑个人资料");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();

            }
        });
    }


    @OnClick({R.id.ll_set_portrait, R.id.ll_set_nickname, R.id.ll_set_sex, R.id.ll_set_birthday,
            R.id.ll_set_age, R.id.ll_set_phone_number, R.id.ll_set_motto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_set_portrait:
                pickImages();
                break;
            case R.id.ll_set_nickname:
                break;
            case R.id.ll_set_sex:
                break;
            case R.id.ll_set_birthday:
                break;
            case R.id.ll_set_age:
                break;
            case R.id.ll_set_phone_number:
                break;
            case R.id.ll_set_motto:
                break;
        }
    }
    /**
     * 图片加载器
     */
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };

    /**
     * 图片选择器
     */
    private void pickImages() {
        ImgSelConfig config = new ImgSelConfig.Builder(loader)
                // 是否多选
                .multiSelect(false)
                // 确定按钮背景色
                .btnBgColor(Color.GRAY)
                // 确定按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
//                .statusBarColor(Color.parseColor("#3F51B5"))
                // 返回图标ResId
                .backResId(R.drawable.ic_arrow_back_24dp)
                .title("图片")
                .titleColor(Color.WHITE)
                .titleBgColor(Color.parseColor("#f74a4e"))
                .cropSize(1, 1, 100, 100)
                .needCrop(true)
                // 第一个是否显示相机
                .needCamera(true)
                // 最大选择图片数量
                .maxNum(5)
                .build();

        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);

            Glide.with(this).load(pathList.get(0)).into(civPortrait);
            File file = new File(pathList.get(0));

            RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
            /**
             * 创建多部分拿上面的请求体做参数
             * file 是上传是的参数key
             */
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", System.currentTimeMillis() + ".jpg", requestFile);
            RequestBody r1 = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPreferencesUtils.getUserName());
            RequestBody r2 = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPreferencesUtils.getToken());
            RequestBody r3 = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPreferencesUtils.getSchoolId());
            dataManager.setPortrait(r1, r2,
                    r3, part)
                    .compose(RxUtils.<NoDataResult>rxSchedulerIo())
                    .subscribe(new Subscriber<NoDataResult>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NoDataResult noDataResult) {

                }
            });
        }
    }
}
