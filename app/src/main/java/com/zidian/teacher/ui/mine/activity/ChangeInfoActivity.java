package com.zidian.teacher.ui.mine.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.PersonInfo;
import com.zidian.teacher.presenter.ChangeInfoPresenter;
import com.zidian.teacher.presenter.contract.ChangeInfoContract;
import com.zidian.teacher.util.SharedPreferencesUtils;
import com.zidian.teacher.util.SnackbarUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * 修改个人信息
 * Created by GongCheng on 2017/4/7.
 */

public class ChangeInfoActivity extends BaseActivity implements ChangeInfoContract.View {
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
    ChangeInfoPresenter presenter;

    private static final int REQUEST_CODE = 0;
    private ProgressDialog progressDialog;

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
        toolbar.setTitle(getString(R.string.change_person_info));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setToolbarBack(toolbar);
        progressDialog = new ProgressDialog(this);
        checkNotNull(presenter);
        presenter.attachView(this);

        //init person info
        PersonInfo personInfo = (PersonInfo) getIntent().getSerializableExtra("personInfo");
        Glide.with(this).load(personInfo.getPortrait()).centerCrop().error(R.drawable.ic_teacher).into(civPortrait);
        tvNickname.setText(personInfo.getNickName());
        tvMotto.setText(personInfo.getPersonSignature());
        tvAge.setText(personInfo.getAge());
        tvSex.setText(personInfo.getSex());
        tvBirthday.setText(personInfo.getBirthday());
        tvPhoneNumber.setText(personInfo.getPhoneNumber());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @OnClick({R.id.ll_set_portrait, R.id.ll_set_nickname, R.id.ll_set_sex, R.id.ll_set_birthday,
            R.id.ll_set_age, R.id.ll_set_phone_number, R.id.ll_set_motto, R.id.btn_person_info_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_set_portrait:
                pickImages();
                break;
            case R.id.ll_set_nickname:
                setNickname();
                break;
            case R.id.ll_set_sex:
                setSex();
                break;
            case R.id.ll_set_birthday:
                setBirthday();
                break;
            case R.id.ll_set_phone_number:
                setPhoneNumber();
                break;
            case R.id.ll_set_motto:
                setMotto();
                break;
            case R.id.btn_person_info_commit:
                setPersonInfo();
                break;
        }
    }

    /**
     * 修改个人信息
     */
    private void setPersonInfo() {
        String motto = tvMotto.getText().toString().trim();
        String phoneNumber = tvPhoneNumber.getText().toString().trim();
        String sex = tvSex.getText().toString().trim();
        String birthday = tvBirthday.getText().toString().trim();
        String nickname = tvNickname.getText().toString().trim();
        presenter.setPersonInfo(motto, phoneNumber, sex, birthday, nickname);
    }

    /**
     * 设置昵称
     */
    private void setNickname() {
        new MaterialDialog.Builder(this)
                .title(R.string.please_input_nickname)
                .negativeText(R.string.cancel)
                .inputRangeRes(1, 15, R.color.colorPrimary)
                .input(null, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        tvNickname.setText(input);
                    }
                }).show();
    }

    /**
     * 设置性别
     */
    private void setSex() {
        new MaterialDialog.Builder(this)
                .title(R.string.please_choose_sex)
                .items(R.array.sex)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog materialDialog, View view, int i, CharSequence sex) {
                        tvSex.setText(sex);
                        return true;
                    }
                })
                .build()
                .show();
    }

    /**
     * 设置生日
     */
    private void setBirthday() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpg = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
                        tvBirthday.setText(getString(R.string.birthday, year, month + 1, day));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.CHINA);
                        int currentYear = Integer.parseInt(dateFormat.format(new Date()));
                        tvAge.setText(String.valueOf(currentYear - year));
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
        dpg.show(getSupportFragmentManager(), getString(R.string.please_choose_date));
    }

    /**
     * 设置电话号码
     */
    private void setPhoneNumber() {
        new MaterialDialog.Builder(this)
                .title(R.string.please_input_phone_number)
                .negativeText(R.string.cancel)
                .inputRangeRes(11, 11, R.color.colorPrimary)
                .input(null, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        tvPhoneNumber.setText(input);
                    }
                }).show();
    }

    /**
     * 设置个性签名
     */
    private void setMotto() {
        new MaterialDialog.Builder(this)
                .title(R.string.please_input_motto)
                .negativeText(R.string.cancel)
                .inputRangeRes(1, 20, R.color.colorPrimary)
                .input(null, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        tvMotto.setText(input);
                    }
                }).show();
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
        ImgSelConfig config = new ImgSelConfig.Builder(this, loader)
                // 是否多选
                .multiSelect(false)
                // 确定按钮背景色
                .btnBgColor(Color.GRAY)
                // 确定按钮文字颜色
                .btnTextColor(Color.BLUE)
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
            //创建多部分拿上面的请求体做参数
            //file 是上传是的参数key
            MultipartBody.Part image = MultipartBody.Part.createFormData("file", System.currentTimeMillis() + ".jpg", requestFile);

            RequestBody teacherId = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPreferencesUtils.getUserName());
            RequestBody token = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPreferencesUtils.getToken());
            RequestBody schoolId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(SharedPreferencesUtils.getSchoolId()));
            presenter.setPortrait(teacherId, token, schoolId, image);
        }
    }

    @Override
    public void showError(Throwable e) {
        progressDialog.dismiss();
    }

    @Override
    public void showLoading(String loadingMsg) {
        progressDialog.setMessage(loadingMsg);
        progressDialog.show();
    }

    @Override
    public void showSuccess(String successMsg) {
        progressDialog.dismiss();
        SnackbarUtils.showShort(toolbar, successMsg);
        //修改成功
        setResult(RESULT_OK);
    }
}
