package com.system;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    List<Integer> conditions = new ArrayList<Integer>();
    private int conclusion;
    String[] features = {"体型大", "体型小", "有轮子", "流线型", "在天上的", "在水里的",
            "在地上的", "冒黑烟的", "有机翼的", "有螺旋桨的", "有四个轮子的", "有两个轮子的",
            "车", "飞机", "船", "货轮", "航空飞机", "直升飞机",
            "汽车", "自行车", "渔船", "拖拉机"};

    // 添加一个条件
    public void addCondition(int condition) {
        conditions.add(condition);
    }

    // 设置结论
    public void setConclusion(int conclusion) {
        this.conclusion = conclusion;
    }

    public int getConclusion() {
        return conclusion;
    }

    // 这条规则是否满足
    public boolean isSatisfied(Object[] conditions) {
        boolean satisfied = true;
        for (int i = 0; i < this.conditions.size(); i++) {
            int rule = this.conditions.get(i);
            boolean flag = false;
            for(int j = 0; j < conditions.length; j++) {
                if(Integer.parseInt(conditions[j].toString()) == rule){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                // 有一个条件不满足都不满足
                satisfied = false;
                break;
            }
        }
        return satisfied;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("因为是");
        for (int i = 0; i < conditions.size(); i++) {
            res.append(features[conditions.get(i)]);
            if (i != conditions.size() - 1) {
                res.append("、");
            }
        }
        res.append(", 所以是");
        res.append(features[conclusion]);
        res.append("。\n");
        return res.toString();
    }
}