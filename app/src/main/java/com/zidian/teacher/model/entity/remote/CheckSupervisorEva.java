package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/24.
 */

public class CheckSupervisorEva {


    /**
     * mapList : [{"labelThreeIndexName":"重点学科建设精品课程设置","threeIndexQuestionTea":"据您了解，这门课程是否符合重点学科建设精品课程设置？","mycChoiceLabel":"走向人生巅峰","labelList":["走向人生巅峰","大力丸","有长进","强行精品","啥玩意儿？"]},{"labelThreeIndexName":"教学大纲","threeIndexQuestionTea":"在您看来，这门课程的教学大纲设计合理，有利于学生实现教学目标？","mycChoiceLabel":"合理有效","labelList":["逆天","合理有效","可以","五毛大纲","我上我也行"]},{"labelThreeIndexName":"教材","threeIndexQuestionTea":"在您看来，选用的教材非常适合这门课程？","mycChoiceLabel":"可圈可点","labelList":["我给101分，不怕他骄傲","可圈可点","这很合理","哪里不懂读哪里","大写加粗的没用"]},{"labelThreeIndexName":"课外实践活动","threeIndexQuestionTea":"据您了解，任课老师经常带领学生进行课外实践活动？","mycChoiceLabel":"有声有色","labelList":["只有想不到的没有做不到的","有声有色","存在于年级干部之间的活动","放风十分钟","葛优躺"]},{"labelThreeIndexName":"教学资源","threeIndexQuestionTea":"在您看来，这门课程的教学资源十分丰富？","mycChoiceLabel":"nice！","labelList":["不禁竖起了大拇指","nice！","能用","凑凑才能用","zero！"]},{"labelThreeIndexName":"善于和学生沟通","threeIndexQuestionTea":"在您看来，任课老师具有良好的师生沟通能力？","mycChoiceLabel":"友谊的小船","labelList":["革命友情的巨轮","友谊的小船","点赞之交","我就看看，不说话","我在哪？谁在讲课？"]},{"labelThreeIndexName":"关爱学生","threeIndexQuestionTea":"在您看来，任课老师十分关爱学生？","mycChoiceLabel":"老棉袄","labelList":["亲爹/妈","老棉袄","有求有应","真老师下课从不回头看学生","旋转跳跃我闭上眼"]},{"labelThreeIndexName":"教学态度","threeIndexQuestionTea":"在您看来，任课老师教学态度十分端正？","mycChoiceLabel":"路人转粉","labelList":["业界良心","路人转粉","无感","混工资的","来啊！互相伤害啊"]},{"labelThreeIndexName":"教师素养","threeIndexQuestionTea":"在您看来，任课老师展现了良好的教师职业素养？","mycChoiceLabel":"标准老师水平","labelList":["王者水平","标准老师水平","还是有老师样的","你开心就好","逼我自学"]},{"labelThreeIndexName":"教学方法多样性","threeIndexQuestionTea":"在您看来，任课老师能够灵活使用多样的教学方法？","mycChoiceLabel":"因材施教","labelList":["无限剑制","因材施教","有几手","照着读","一条咸鱼"]},{"labelThreeIndexName":"教学方法有效性","threeIndexQuestionTea":"在您看来，任课老师的教学方法能够有效达到教学目的？","mycChoiceLabel":"没的黑","labelList":["醍醐灌顶","没的黑","简单点，教书的方式简单点","授人以鱼","拔笔四顾心茫然"]},{"labelThreeIndexName":"指定的参考资料丰富且有价值","threeIndexQuestionTea":"在您看来，任课老师对学生提供的参考资料丰富且有价值？","mycChoiceLabel":"老师是会玩的","labelList":["超高校级的资料","老师是会玩的","也不是没有道理","随缘","大忽悠"]},{"labelThreeIndexName":"教学计划和教学目标清晰","threeIndexQuestionTea":"在您看来，任课老师教学计划清晰且目标明确？","mycChoiceLabel":"了解","labelList":["教书我只服你","了解","都是套路","东一下西一下","被期末考试支配的恐惧"]},{"labelThreeIndexName":"内容量","threeIndexQuestionTea":"在您看来，这门课的内容丰富？","mycChoiceLabel":"一节更比六节强","labelList":["天降知识","一节更比六节强","能够消化","饱一顿饿一顿","感觉身体被掏空"]},{"labelThreeIndexName":"深浅程度","threeIndexQuestionTea":"在您看来，这门课的教学内容难度适合教学对象？","mycChoiceLabel":"错落有致","labelList":["见微知著","错落有致","深深浅浅傻傻分不清楚","一脸懵逼2.0","一本正经胡说八道"]},{"labelThreeIndexName":"有独到见解","threeIndexQuestionTea":"在您看来，任课老师对所教课程有独到见解？","mycChoiceLabel":"不明觉厉","labelList":["语惊四座","不明觉厉","我就听听","只会复制粘贴","井底之蛙"]},{"labelThreeIndexName":"反映前沿发展动态","threeIndexQuestionTea":"在您看来，这门课的教学内容能够反映该学科的前沿发展动态？","mycChoiceLabel":"这个老师有点灵","labelList":["预言帝","这个老师有点灵","前方低能预警","哪里要考讲哪里","一顿乱吹"]},{"labelThreeIndexName":"教学思路清晰，重难点突出","threeIndexQuestionTea":"在您看来，任课老师在教学过程中教学思路清晰，重难点突出？","mycChoiceLabel":"没瑕疵","labelList":["这门课程被老师承包了","没瑕疵","雨露均沾","只求及格","我选择死亡"]},{"labelThreeIndexName":"课堂氛围","threeIndexQuestionTea":"据您了解，这门课程的课堂氛围积极活跃？","mycChoiceLabel":"活跃欢快","labelList":["开心的像群猴子","活跃欢快","普通的一天听着普通的课","身不由己","全场梦游"]},{"labelThreeIndexName":"引导学习热情","threeIndexQuestionTea":"在您看来，任课老师能够积极激发学生的学习热情？","mycChoiceLabel":"拍案叫绝","labelList":["再来一堂！","拍案叫绝","我的内心毫无波澜","假装四处看风景","换个地方睡觉"]},{"labelThreeIndexName":"鼓励课堂讨论","threeIndexQuestionTea":"据您了解，任课老师在课堂教学中鼓励且善于引导学生进行课堂讨论？","mycChoiceLabel":"亦可赛艇（exciting）","labelList":["百家争鸣","亦可赛艇（exciting）","老师和积极学生的课","保佑不要点到我","老师solo全场"]},{"labelThreeIndexName":"产学研相结合","threeIndexQuestionTea":"据您了解，任课老师在教学过程中能够引导学生关注业界发展动态与最新研究成果？","mycChoiceLabel":"知识爆表","labelList":["打通任督二脉","知识爆表","学到了几招","活在梦里","纸上得来终觉浅"]},{"labelThreeIndexName":"专业能力提升","threeIndexQuestionTea":"据您了解，这门课程有效促进了学生的专业能力提升？","mycChoiceLabel":"青铜五","labelList":["学识如风，常伴吾身","开黑吗？我学习贼6","大学平均水平","我还是高三水平","青铜五"]},{"labelThreeIndexName":"语言表达能力提升","threeIndexQuestionTea":"据您了解，这门课程有效促进了学生语言表达能力的发展？","mycChoiceLabel":"上车吧，听我讲个段子！","labelList":["我可以哔哔一整天","上车吧，听我讲个段子！","是个会说话的人","发声训练开始","只想做个安静的美男（女）子"]},{"labelThreeIndexName":"沟通协作能力提升","threeIndexQuestionTea":"据您了解，这门课程有效提升了学生的沟通协作能力？","mycChoiceLabel":"easy","labelList":["leader","easy","稍有存在感","参与者","辣鸡"]},{"labelThreeIndexName":"创新能力提升","threeIndexQuestionTea":"据您了解，这门课程能够激发学生的创新能力？","mycChoiceLabel":"停不下来","labelList":["思维超前","停不下来","有点想法","江郎才尽","我只想回老家结婚"]},{"labelThreeIndexName":"学校教学质量总体满意度评价","threeIndexQuestionTea":"对于学校目前的教学质量，您感到很满意？","mycChoiceLabel":"hello-world","labelList":["身可死，母校之名不可辱","hello-world","算有个学校样","文凭get√","生无可恋"]}]
     * evaluateComment : null
     * evaluateFeedback :
     */

    private String evaluateComment;
    private String evaluateFeedback;
    private List<MapListBean> mapList;

    public String getEvaluateComment() {
        return evaluateComment;
    }

    public void setEvaluateComment(String evaluateComment) {
        this.evaluateComment = evaluateComment;
    }

    public String getEvaluateFeedback() {
        return evaluateFeedback;
    }

    public void setEvaluateFeedback(String evaluateFeedback) {
        this.evaluateFeedback = evaluateFeedback;
    }

    public List<MapListBean> getMapList() {
        return mapList;
    }

    public void setMapList(List<MapListBean> mapList) {
        this.mapList = mapList;
    }

    public static class MapListBean {
        /**
         * labelThreeIndexName : 重点学科建设精品课程设置
         * threeIndexQuestionTea : 据您了解，这门课程是否符合重点学科建设精品课程设置？
         * mycChoiceLabel : 走向人生巅峰
         * labelList : ["走向人生巅峰","大力丸","有长进","强行精品","啥玩意儿？"]
         */

        private String labelThreeIndexName;
        private String threeIndexQuestionTea;
        private String mycChoiceLabel;
        private List<String> labelList;

        public String getLabelThreeIndexName() {
            return labelThreeIndexName;
        }

        public void setLabelThreeIndexName(String labelThreeIndexName) {
            this.labelThreeIndexName = labelThreeIndexName;
        }

        public String getThreeIndexQuestionTea() {
            return threeIndexQuestionTea;
        }

        public void setThreeIndexQuestionTea(String threeIndexQuestionTea) {
            this.threeIndexQuestionTea = threeIndexQuestionTea;
        }

        public String getMycChoiceLabel() {
            return mycChoiceLabel;
        }

        public void setMycChoiceLabel(String mycChoiceLabel) {
            this.mycChoiceLabel = mycChoiceLabel;
        }

        public List<String> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<String> labelList) {
            this.labelList = labelList;
        }
    }
}
