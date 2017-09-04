package com.zidian.teacher.ui.questionnaire.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.model.entity.remote.QuesSurveyDetail;
import com.zidian.teacher.presenter.QuesSurveyDetailPresenter;
import com.zidian.teacher.presenter.contract.QuesSurveyDetailContract;
import com.zidian.teacher.ui.questionnaire.adapter.QuesSurveyDetailAdapter;
import com.zidian.teacher.ui.questionnaire.event.QuesSurveyFinishEvent;
import com.zidian.teacher.util.SharedPreferencesUtils;
import com.zidian.teacher.util.SnackbarUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/5/5.
 */

public class QuesSurveyDetailActivity extends BaseActivity implements QuesSurveyDetailContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.question_evaluate_type)
    TextView questionEvaluateType;
    @BindView(R.id.tv_publisher)
    TextView tvPublisher;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Inject
    QuesSurveyDetailPresenter presenter;

    private List<Map<String, String>> data = new ArrayList<>();
    private Map<Integer, Map<String, String>> update = new HashMap<>();
    private QuesSurveyDetailAdapter adapter;
    private QuesSurveyDetail questionBean;
    private int questionnaireId;
    private int count = 0;
    private int selectItem = -1;
    private Map<Integer, Integer> so = new HashMap<>();
    private MaterialDialog progressDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_ques_survey_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        checkNotNull(presenter);

        progressDialog = new MaterialDialog.Builder(this)
                .progress(true, 10)
                .content("加载中...")
                .build();
        questionnaireId = this.getIntent().getIntExtra("questionnaireId", 0);
        toolbar.setTitle("问卷调查");
        setToolbarBack(toolbar);
        adapter = new QuesSurveyDetailAdapter(this, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new QuesSurveyDetailAdapter.OnChoseItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int temp = 0;
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).put("result", position + "");
                }

                if (count == -1) {
                    temp = questionBean.getQuestion().size() - 1;
                } else {
                    temp = count - 1;
                }

                selectItem = position;
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", questionBean.getQuestion().get(temp).getQuestionTypes());
                map.put("questionNumber", questionBean.getQuestion().get(temp).getQuestionNumber() + "");
                map.put("stuOption", selectItem + 1 + "");
                update.put(temp, map);
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setOnItemClickListener(new QuesSurveyDetailAdapter.OnStarItemClickListener() {
            @Override
            public void onItemClick(float rating, int position, RatingBar ratingbar) {
                Log.e("S", rating + "");
                int temp ;
                so.put(position, (int) rating);
                for (int i = 0; i < data.size(); i++) {
                    if (so.get(i) == null) {
                        so.put(i, 1);
                    }
                }
                String stuOption = "1" + "!" + so.get(0);
                for (int i = 1; i < data.size(); i++) {
                    stuOption += "_" + (i + 1) + "!" + so.get(i);
                }

                if (count == -1) {
                    temp = questionBean.getQuestion().size() - 1;
                } else {
                    temp = count - 1;
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", questionBean.getQuestion().get(temp).getQuestionTypes());
                map.put("questionNumber", questionBean.getQuestion().get(temp).getQuestionNumber() + "");
                map.put("stuOption", stuOption);
                update.put(temp, map);
            }
        });

        presenter.attachView(this);
        presenter.getQuesDetail(String.valueOf(questionnaireId));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @OnClick(R.id.btn_submit)
    public void submit() {
        if (count == -1) {
            if (selectItem == -1 && !questionBean.getQuestion()
                    .get(questionBean.getQuestion().size() - 1).getQuestionTypes().equals("打分题")) {
                Snackbar.make(toolbar,"请选择选项!",Snackbar.LENGTH_SHORT).show();
            } else {
                String jsonResult = "";//定义返回字符串

                try {
                    JSONArray jsonarray = new JSONArray();//json数组，里面包含的内容为pet的所有对象
                    JSONObject jsonObj = new JSONObject();//pet对象，json形式

                    jsonObj.put("questionnaireId", questionnaireId);//向pet对象里面添加值
                    jsonObj.put("respondentId", SharedPreferencesUtils.getUserName());

                    for (int i = 0; i < questionBean.getQuestion().size(); i++) {
                        JSONObject jsonObjAnswer = new JSONObject();//pet对象，json形式
                        jsonObjAnswer.put("questionNumber", update.get(i).get("questionNumber"));
                        jsonObjAnswer.put("stuOption", update.get(i).get("stuOption"));
                        jsonarray.put(jsonObjAnswer);
                    }

                    jsonObj.put("answerResults", jsonarray);
                    jsonResult = jsonObj.toString();//生成返回字符串

                    presenter.quesSubmit(jsonResult);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (selectItem == -1 && !questionBean.getQuestion().get(count - 1).getQuestionTypes().equals("打分题")) {
                Snackbar.make(toolbar,"请选择选项!",Snackbar.LENGTH_SHORT).show();
            } else {
                getRefreshData();
                selectItem = -1;
            }

        }
    }
    /**
     * 获取测试数据
     */
    private void getRefreshData() {
        if (count >= questionBean.getQuestion().size() - 1) {
            btnSubmit.setText("提交");
        } else {
            btnSubmit.setText("下一题");
        }
        data.clear();

        if (count != -1) {
            tvTitle.setText(questionBean.getQuestion().get(count).getQuestioncontent());

            for (int j = 0; j < questionBean.getQuestion().get(count).getOptions().size(); j++) {
                Map<String, String> map = new HashMap<>();
                map.put("optionsDescribe", questionBean.getQuestion().get(count).getOptions().get(j).getOptionsDescribe());
                map.put("option", questionBean.getQuestion().get(count).getOptions().get(j).getOption());
                map.put("result", "");
                data.add(map);
            }

            if (questionBean.getQuestion().get(count).getQuestionTypes().equals("打分题")) {
                for (int i = 0; i < data.size(); i++) {
                    if (so.get(i) == null) {
                        so.put(i, 1);
                    }
                }
                String stuOption = "1" + "!" + so.get(0);
                for (int i = 1; i < data.size(); i++) {
                    stuOption += "_" + (i + 1) + "!" + so.get(i);
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("type", questionBean.getQuestion().get(count).getQuestionTypes());
                map.put("questionNumber", questionBean.getQuestion().get(count).getQuestionNumber() + "");
                map.put("stuOption", stuOption);
                update.put(count, map);
                adapter.setState(0);
            } else {
                adapter.setState(1);
            }
            adapter.notifyDataSetChanged();
            count++;
        }
        if (count > questionBean.getQuestion().size() - 1) {
            count = -1;
        }
    }

    @Override
    public void showError(Throwable e) {
        SnackbarUtils.showShort(toolbar, e.getMessage());
    }

    @Override
    public void showQuesDetail(QuesSurveyDetail quesSurveyDetail) {
        questionBean = quesSurveyDetail;
        tvTitle.setText(questionBean.getQuestionnaireTitle());
        tvPublisher.setText(questionBean.getPublisher());
        tvTime.setText(questionBean.getReleaseTime());
        questionEvaluateType.setText(questionBean.getQuestionnaireExplain());
        getRefreshData();
    }

    @Override
    public void showSubmitLoading() {
        progressDialog.show();
    }

    @Override
    public void showSubmitSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, "提交成功",Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new QuesSurveyFinishEvent(true));
        finish();
    }

}
