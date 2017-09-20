package com.changju.fanlitou.bean.fund;

import java.util.List;

/**
 * Created by zhangming on 2017/8/4.
 */

public class FundQuestion {

    private String risk_level_mark;
    private String risk_level;
    private boolean is_user_evaluated;
    private List<QuestionListBean> question_list;

    public String getRisk_level_mark() {
        return risk_level_mark;
    }

    public void setRisk_level_mark(String risk_level_mark) {
        this.risk_level_mark = risk_level_mark;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    public boolean isIs_user_evaluated() {
        return is_user_evaluated;
    }

    public void setIs_user_evaluated(boolean is_user_evaluated) {
        this.is_user_evaluated = is_user_evaluated;
    }

    public List<QuestionListBean> getQuestion_list() {
        return question_list;
    }

    public void setQuestion_list(List<QuestionListBean> question_list) {
        this.question_list = question_list;
    }

    public static class QuestionListBean {
        /**
         * question : 1、您的年龄是:
         * answers : [{"answer":"A、25岁以下","answers_id":1,"is_checked":true},{"answer":"B、25-34岁","answers_id":2,"is_checked":false},{"answer":"C、35-44岁","answers_id":3,"is_checked":false},{"answer":"D、45岁及以上","answers_id":4,"is_checked":false}]
         * question_id : 1
         */

        private String question;
        private int question_id;
        private List<AnswersBean> answers;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(int question_id) {
            this.question_id = question_id;
        }

        public List<AnswersBean> getAnswers() {
            return answers;
        }

        public void setAnswers(List<AnswersBean> answers) {
            this.answers = answers;
        }

        public static class AnswersBean {
            /**
             * answer : A、25岁以下
             * answers_id : 1
             * is_checked : true
             */

            private String answer;
            private int answers_id;
            private boolean is_checked;

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public int getAnswers_id() {
                return answers_id;
            }

            public void setAnswers_id(int answers_id) {
                this.answers_id = answers_id;
            }

            public boolean isIs_checked() {
                return is_checked;
            }

            public void setIs_checked(boolean is_checked) {
                this.is_checked = is_checked;
            }
        }
    }
}
