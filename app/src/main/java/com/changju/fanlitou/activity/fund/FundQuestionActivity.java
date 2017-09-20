package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.fund.FundQuestion;
import com.changju.fanlitou.ui.dialog.EvaluateDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class FundQuestionActivity extends BaseActivity {

    private View include;
    private View include_load_error;
    private TextView tv_question;
    private RadioButton rb_1;
    private RadioButton rb_2;
    private RadioButton rb_3;
    private RadioButton rb_4;
    private TextView tv_question_before;
    private TextView tv_numberofquestion;
    private TextView bt_fund_question_commit;

    public static final int TAG_INDEX = 0x01;
    public static final int TAG_ANSWER_ID = 0x02;

    private RadioGroup rg_fund_question;
    private FundQuestion fundQuestion;
    private List<FundQuestion.QuestionListBean> questionListBeanList;

    private Map<Integer, Answer> answerIdList;

    @Override
    public void initParams(Bundle params) {

    }

    private int index;

    public synchronized void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_question;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "基金-风险测评");

        answerIdList = new HashMap<>();
        rg_fund_question = (RadioGroup) findViewById(R.id.rg_fund_question);
//        rg_fund_question.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(final RadioGroup group, @IdRes int checkedId) {
//                final RadioButton rb = (RadioButton) group.findViewById(checkedId);
//                if (checkedId > 0 && rb.isChecked()) {
//                    int answer_id = (int) rb.getTag();
//                    int answer_index = rb.getId();
//                    index = index > questionListBeanList.size() - 1 ? questionListBeanList.size() - 1 : index;
//                    Answer answer = new Answer(answer_index, answer_id);
//                    answerIdList.put(index, answer);
//                    if (index < questionListBeanList.size() - 1) {
//                        rb.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                group.clearCheck();
//                                nextQuestion(++index);
//                            }
//                        }, 200);
//                    } else {
//                        nextQuestion(++index);
//                    }
//                }
//            }
//
//        });

        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_3);
        rb_4 = (RadioButton) findViewById(R.id.rb_4);

        rb_1.setOnClickListener(this);
        rb_2.setOnClickListener(this);
        rb_3.setOnClickListener(this);
        rb_4.setOnClickListener(this);

        tv_question = (TextView) findViewById(R.id.tv_question);
        tv_question_before = (TextView) findViewById(R.id.tv_question_before);
        tv_question_before.setOnClickListener(this);
        tv_numberofquestion = (TextView) findViewById(R.id.tv_numberofquestion);
        bt_fund_question_commit = (TextView) findViewById(R.id.bt_fund_question_commit);
        bt_fund_question_commit.setOnClickListener(this);

        findViewById(R.id.activity_question_iv_back).setOnClickListener(this);
        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    private EvaluateDialog evaluateDialog;

    private void nextQuestion(int pos) {
        pos = pos > questionListBeanList.size() - 1 ? questionListBeanList.size() - 1 : pos;
        Answer answer = answerIdList.get(pos);
        boolean hasAnswer = false;

        //显示提交提交

        if (answer != null)
            hasAnswer = answer.getAnswer_id() > 0;

        if (pos == questionListBeanList.size() - 1) {
            bt_fund_question_commit.setVisibility(View.VISIBLE);
        } else {
            bt_fund_question_commit.setVisibility(View.GONE);
        }

        bt_fund_question_commit.setEnabled(hasAnswer);


        //当前答题进度
        tv_numberofquestion.setText(String.valueOf((pos + 1)) + "/" + questionListBeanList.size());

        //恢复的以前的答案
        if (hasAnswer) {
            RadioButton rb = (RadioButton) rg_fund_question.getChildAt(answer.getIndex());
            rb.setChecked(true);
        }

        if (pos > 0) {
            tv_question_before.setVisibility(View.VISIBLE);
        } else {
            tv_question_before.setVisibility(View.GONE);
        }

        FundQuestion.QuestionListBean question = questionListBeanList.get(pos);
        tv_question.setText(question.getQuestion());
        List<FundQuestion.QuestionListBean.AnswersBean> answers = question.getAnswers();
        if (answers == null || answers.size() < 1)
            rg_fund_question.setVisibility(View.GONE);
        else {
            for (int i = 0; i < 4; i++) {
                if (i > answers.size()) {
                    rg_fund_question.getChildAt(i).setVisibility(View.GONE);
                    break;
                }

                FundQuestion.QuestionListBean.AnswersBean answersBean = answers.get(i);

                RadioButton rb = (RadioButton) rg_fund_question.getChildAt(i);
                rb.setText(answersBean.getAnswer());
                rb.setTag(answersBean.getAnswers_id());

            }
        }

    }

    private long current;

    private void commitQuestion() {
        current = System.currentTimeMillis();

        String time = String.valueOf(System.currentTimeMillis());
        String random = ApiUtils.getRandom();

        if (evaluateDialog == null)
            evaluateDialog = EvaluateDialog.showDialog(this);

        evaluateDialog.show();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questionListBeanList.size(); i++) {
            Answer answer = answerIdList.get(i);
            if (answer != null) {
                sb.append((i + 1));
                sb.append(":");
                sb.append(answer.getAnswer_id());
                if (i < answerIdList.size() - 1) {
                    sb.append(";");
                }
            }

        }


        OkGo.post(ApiUtils.postQuestionId())
                .params("t", time)
                .params("random", random)
                .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                .params("user_name", UserUtils.getUserName(this))
                .params("login_token", UserUtils.getLoginToken(this))
                .params("questions", sb.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final String s, Call call, Response response) {
                        final long remain = response.receivedResponseAtMillis() - current;
                        if (remain >= 2000L) {
                            startToResult(s);
                        } else {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(2000 - remain);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            startToResult(s);
                                        }
                                    });
                                }
                            }).start();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(FundQuestionActivity.this, R.string.net_error);
                        evaluateDialog.dismiss();
                    }
                });
    }

    private void startToResult(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            boolean isSuccess = jsonObject.optBoolean("success");
            if (isSuccess) {
                Bundle args1 = new Bundle();
                args1.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                        jsonObject.optString("risk_level"));
                args1.putBoolean(FundQuestionResultActivity.class.getSimpleName(),
                        jsonObject.optBoolean("show_holding_higher_fund"));
                startActivity(FundQuestionResultActivity.class, args1);
                finish();
            } else {
                NormalUtils.showToast(FundQuestionActivity.this, jsonObject.optString("msg"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        evaluateDialog.dismiss();
    }

    @Override
    public void doBusiness(Context mContext) {
        OkGo.get(ApiUtils.getFundQuestion(mContext)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                fundQuestion = ParseUtils.parseByGson(s, FundQuestion.class);
                questionListBeanList = fundQuestion.getQuestion_list();
                //保存默认答案
                if (questionListBeanList != null && questionListBeanList.size() > 0){
                    for (int i = 0; i < questionListBeanList.size(); i++) {
                        List<FundQuestion.QuestionListBean.AnswersBean> answersBeans =
                                questionListBeanList.get(i).getAnswers();
                        if (answersBeans != null && answersBeans.size() > 0){
                            for (int j = 0; j < answersBeans.size(); j++) {
                                FundQuestion.QuestionListBean.AnswersBean answersBean = answersBeans.get(j);
                                if (answersBean.isIs_checked()){
                                    answerIdList.put(i, new Answer(j, answersBean.getAnswers_id()));
                                }
                            }

                        }
                    }

                }
                nextQuestion(index);
                tv_question_before.setVisibility(View.INVISIBLE);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FundQuestionActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    private int answer_index;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_question_iv_back:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
            case R.id.bt_fund_question_commit:
                commitQuestion();
                break;
            case R.id.tv_question_before:
                rg_fund_question.clearCheck();
                nextQuestion(--index);
                break;
            case R.id.rb_1:
                answer_index = 0;
                keepAnswer();
                break;
            case R.id.rb_2:
                answer_index = 1;
                keepAnswer();
                break;
            case R.id.rb_3:
                answer_index = 2;
                keepAnswer();
                break;
            case R.id.rb_4:
                answer_index = 3;
                keepAnswer();
                break;
        }
    }

    //是否正在保存答案
    private boolean isKeeping = false;

    private void keepAnswer() {
        if (isKeeping)
            return;

        isKeeping = true;
        RadioButton rb = (RadioButton) rg_fund_question.findViewById(
                rg_fund_question.getCheckedRadioButtonId());
        int answer_id = (int) rb.getTag();
        index = index > questionListBeanList.size() - 1 ? questionListBeanList.size() - 1 : index;
        Answer answer = new Answer(answer_index, answer_id);
        answerIdList.put(index, answer);

        if (index < questionListBeanList.size() - 1) {
            rg_fund_question.setEnabled(false);
            rb.postDelayed(new Runnable() {
                @Override
                public void run() {
                    rg_fund_question.clearCheck();
                    nextQuestion(++index);
                    isKeeping = false;
                }
            }, 300);
        } else {
            nextQuestion(++index);
            isKeeping = false;
        }


    }


    class Answer {

        private int index;
        private int answer_id;

        public Answer(int index, int answer_id) {
            this.index = index;
            this.answer_id = answer_id;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getAnswer_id() {
            return answer_id;
        }

        public void setAnswer_id(int answer_id) {
            this.answer_id = answer_id;
        }
    }
}
