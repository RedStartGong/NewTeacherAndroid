package com.zidian.teacher.ui.evaluate.listener;

/**
 * Created by GongCheng on 2017/4/19.
 */

public interface MyTaskOnClickListener {
    void evaluate(int position);

    void reject(int position);

    void agree(int position);

    void colleagueCheck(int position);

    void supervisorCheck(int position);
}
