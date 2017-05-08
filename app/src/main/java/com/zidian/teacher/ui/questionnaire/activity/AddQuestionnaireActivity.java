package com.zidian.teacher.ui.questionnaire.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.presenter.QuestionnaireAddPresenter;
import com.zidian.teacher.presenter.contract.QuestionnaireAddContract;
import com.zidian.teacher.ui.questionnaire.adapter.QuestionnaireAddAdapter;
import com.zidian.teacher.ui.questionnaire.bean.QuestionAddBean;
import com.zidian.teacher.ui.questionnaire.event.QuesAddEvent;
import com.zidian.teacher.util.SharedPreferencesUtils;
import com.zidian.teacher.util.SnackbarUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * 添加问卷 activity
 * Created by GongCheng on 2017/5/4.
 */

public class AddQuestionnaireActivity extends BaseActivity implements QuestionnaireAddContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_ques_title)
    EditText tvQuesTitle;
    @BindView(R.id.tv_classes)
    TextView tvClasses;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    QuestionnaireAddPresenter presenter;

    private static final int REQUEST_CLASS = 1;
    private QuestionnaireAddAdapter adapter;
    private List<QuestionAddBean> questionAddBeanList = new ArrayList<>();
    private List<QuestionAddBean> adapterList = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_questionnaire;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);

        toolbar.setTitle("新增问卷");
        setSupportActionBar(toolbar);
        setToolbarBack(toolbar);
        questionAddBeanList = addQuestion(questionAddBeanList);
        adapterList = filtrateData(questionAddBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionnaireAddAdapter(this, adapterList);
        recyclerView.setAdapter(adapter);
        //初始化 adapter 的事件
        initAdapterListener();
        int x = adapter.getItemCount();
        presenter.attachView(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select, menu);
        return true;
    }

    /**
     * 菜单点击事件
     *
     * @param item menuItem
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getGroupId() == R.id.confirm) {
            presenter.addQuestionnaire(getQuestionnaireResult());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    private void initAdapterListener() {
        adapter.setOnFootItemClickListener(new QuestionnaireAddAdapter.OnFootItemClickListener() {
            @Override
            public void onFootItemClick(int position) {
                questionAddBeanList.addAll(addQuestion(questionAddBeanList));
                adapterList.clear();
                adapterList.addAll(filtrateData(questionAddBeanList));
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setOnItemFootItemClickListener(new QuestionnaireAddAdapter.OnItemFootItemClickListener() {
            @Override
            public void onItemFootItemClick(int pId, int position) {
                questionAddBeanList.clear();
                questionAddBeanList.addAll(addQuestionItem(adapterList, pId));
                adapterList.clear();
                adapterList.addAll(filtrateData(questionAddBeanList));
                adapter.notifyDataSetChanged();

            }
        });
        adapter.setOnQuestionDeleteListener(new QuestionnaireAddAdapter.OnQuestionDeleteListener() {
            @Override
            public void onQuestionDeleteListener(int pId, int position) {
                questionAddBeanList.clear();
                questionAddBeanList.addAll(deleteQuestionItem(adapterList, position));
                adapterList.clear();
                adapterList.addAll(filtrateData(questionAddBeanList));
                adapter.notifyDataSetChanged();
            }
        });
    }

    private List<QuestionAddBean> filtrateData(List<QuestionAddBean> datas) {

        List<QuestionAddBean> mList = new ArrayList<>();

        for (int i = 0; i < datas.size(); i++) {
            mList.add(datas.get(i));
            for (int j = 0; j < datas.get(i).getChild().size(); j++) {
                if (j == datas.get(i).getChild().size() - 1) {
                    datas.get(i).getChild().get(j).setIsLastChild(true);
                } else {
                    datas.get(i).getChild().get(j).setIsLastChild(false);
                }
                mList.add(datas.get(i).getChild().get(j));
            }
        }


        return mList;
    }

    /**
     * 选择班级
     */
    @OnClick(R.id.tv_publish_object)
    public void selectObject() {

        Intent intent = new Intent();
        intent.setClass(this, SelectClassActivity.class);
        startActivityForResult(intent, REQUEST_CLASS);

    }


    /**
     * 选择班级的返回结果
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        返回data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CLASS && resultCode == RESULT_OK) {
            tvClasses.setText(data.getStringExtra("classes"));
        }
    }

    private List<QuestionAddBean> addQuestion(List<QuestionAddBean> datas) {
        int id;
        if (datas.size() == 0) {
            id = 0;
        } else {
            id = datas.get(datas.size() - 1).getId() + 1;
        }
        List<QuestionAddBean> list = new ArrayList<>();
        QuestionAddBean bean = new QuestionAddBean(-2, id, false, false);
        QuestionAddBean bean2 = new QuestionAddBean(id, -1, true, true);
        list.add(bean);
        list.get(0).getChild().add(bean2);
        return list;
    }

    /**
     * 添加问卷选项
     *
     * @param data
     * @param pId
     * @return
     */
    private List<QuestionAddBean> addQuestionItem(List<QuestionAddBean> data, int pId) {
        List<QuestionAddBean> list = new ArrayList<>();
        QuestionAddBean bean2 = new QuestionAddBean(pId, -1, true, false);
        data.add(bean2);

        for (QuestionAddBean bean : data) {
            if (!bean.isChild()) {
                list.add(bean);
            }
        }

        for (QuestionAddBean bean : data) {
            bean.getChild().clear();
            if (bean.isChild()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId() == bean.getpId()) {
                        list.get(i).getChild().add(bean);
                    }
                }
            }
        }

        return list;
    }

    /**
     * 删除答案选项
     *
     * @param data
     * @return
     */
    private List<QuestionAddBean> deleteQuestionItem(List<QuestionAddBean> data, int position) {
        List<QuestionAddBean> list = new ArrayList<>();
        data.remove(position);
        for (QuestionAddBean bean : data) {
            if (!bean.isChild()) {
                list.add(bean);
            }
        }

        for (QuestionAddBean bean : data) {
            bean.getChild().clear();
            if (bean.isChild()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId() == bean.getpId()) {
                        list.get(i).getChild().add(bean);
                    }
                }
            }
        }
        return list;
    }

    private String getQuestionnaireResult() {
        List<QuestionAddBean> parent = new ArrayList<>();

        for (QuestionAddBean pitem : parent) {
            pitem.getChild().clear();
        }

        for (QuestionAddBean item : adapterList) {
            if (!item.isChild()) {
                parent.add(item);
            }
        }

        String json = "";

        try {
            JSONArray jsonarray = new JSONArray();
            for (int i = 0; i < parent.size(); i++) {
                String option = "";
                String optionsDescribe = "";
                if (parent.get(i).getQuestionName() == null || parent.get(i).getQuestionName().length() == 0) {
                    Snackbar.make(toolbar, "请输入问卷题目！", Snackbar.LENGTH_SHORT).show();
                    return "";
                } else {

                }


                for (int j = 0; j < parent.get(i).getChild().size() - 1; j++) {
                    if (parent.get(i).getChild().get(j).getItemName() == null || parent.get(i).getChild().get(j).getItemName().length() == 0) {
                        Snackbar.make(toolbar, "请输入选项内容！", Snackbar.LENGTH_SHORT).show();
                        return "";
                    } else {
                        if (parent.get(i).getChild().size() < 3) {
                            Snackbar.make(toolbar, "请至少输入2个选项！", Snackbar.LENGTH_SHORT).show();
                            return "";
                        }
                        if (parent.get(i).getChild().size() > 5) {
                            Snackbar.make(toolbar, "最多输入5个选项！", Snackbar.LENGTH_SHORT).show();
                            return "";
                        }
                        if (j == 0) {
                            optionsDescribe = parent.get(i).getChild().get(j).getItemName();
                            option = (j + 1) + "";
                        } else {
                            optionsDescribe += "!!" + parent.get(i).getChild().get(j).getItemName();
                            option += "!!" + (j + 1) + "";
                        }

                    }

                }

                if (optionsDescribe.length() == 0) {
                    Snackbar.make(toolbar, "至少添加2个选项！", Snackbar.LENGTH_SHORT).show();
                    return "";
                }

                JSONObject jsonObjAnswer = new JSONObject();//pet对象，json形式
                jsonObjAnswer.put("questionNumber", i + 1 + "");
                jsonObjAnswer.put("questionContent", parent.get(i).getQuestionName());
                jsonObjAnswer.put("options", option);
                jsonObjAnswer.put("optionsDescribe", optionsDescribe);
                jsonObjAnswer.put("problemTypes", "选择题");
                jsonObjAnswer.put("couplingIndex", "");
                jsonarray.put(jsonObjAnswer);
            }

            if (tvQuesTitle.getText().toString().trim().length() == 0) {
                Snackbar.make(toolbar, "请输入问卷标题！", Snackbar.LENGTH_SHORT).show();
                return "";
            } else if (tvClasses.getText().toString().trim().length() == 0) {
                Snackbar.make(toolbar, "请选择发布对象！", Snackbar.LENGTH_SHORT).show();
                return "";
            }

            JSONObject jsonObjResult = new JSONObject();
            jsonObjResult.put("publisherId", SharedPreferencesUtils.getUserName());
            jsonObjResult.put("releaseToWho", tvClasses.getText().toString().trim());
            jsonObjResult.put("publishObjects", "班级");
            jsonObjResult.put("questionnaireExplain", "教师发布");
            jsonObjResult.put("questionnaireTitle", tvQuesTitle.getText().toString().trim());
            jsonObjResult.put("questionnaireResult", jsonarray);

            json = jsonObjResult.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("T", json + "");
        return json;
    }

    @Override
    public void showError(Throwable e) {
        SnackbarUtils.showShort(toolbar, e.getMessage());
        progressDialog.dismiss();
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("加载中...");
        }
        progressDialog.show();

    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, "发布问卷成功", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new QuesAddEvent(true));
        finish();
    }
}
