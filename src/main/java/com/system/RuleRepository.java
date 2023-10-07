package com.system;

import java.util.*;

public class RuleRepository {
    List<Rule> rules = new ArrayList<>();
    Set<Integer> conditions = new TreeSet<>();
    String vehicle = "";

    // 构造函数，传入用户输入的条件编号
    public RuleRepository(int[] conditions){
        for(int i = 0; i < conditions.length; i++) {
            this.conditions.add(conditions[i]);
        }
        init();
    }

    // 初始化规则库,初始有12条规则
    /*
    *       features = {"体型大", "体型小", "有轮子", "流线型", "在天上的", "在水里的",
            "在地上的", "冒黑烟的", "有机翼的", "有螺旋桨的", "有四个轮子的", "有两个轮子的",
            "车", "飞机", "船", "货轮", "航空飞机", "直升飞机",
            "汽车", "自行车", "渔船", "拖拉机"};
    * */
    private void init(){
        // 12条规则
        int[][][] expr = new int[][][]{
                {{1,2}, {12}},
                {{0,3}, {13}},
                {{4}, {13}},
                {{5}, {14}},
                {{6}, {12}},
                {{0,7,14}, {15}},
                {{8,13}, {16}},
                {{9,13}, {17}},
                {{10,12}, {18}},
                {{11,12}, {19}},
                {{0,2},{21}},
                {{1,14},{20}}
        };
        for(int i = 0; i < expr.length; i++){
            Rule rule = new Rule();
            for(int j = 0; j < expr[i][0].length; j++){
                rule.addCondition(expr[i][0][j]);
            }
            rule.setConclusion(expr[i][1][0]);
            rules.add(rule);
        }
    }

    // 正向推理
    public String reasoning(){
        String result = "";
        for (int i = 0; i < rules.size(); i++) {
            Rule rule = rules.get(i);
            if(rule.isSatisfied(conditions.toArray())){
                result += rule.toString();
                int con = rule.getConclusion();
                // 若满足的话要把结论加到已知条件中，且要维持有序（条件序列满足拓扑结构）
                conditions.add(rule.getConclusion());
                if(con >= 15){
                    vehicle += con;
                    vehicle += " ";
                }
            }
        }
        return result;
    }

    //获得交通工具
    public String getVehicle() {
        return vehicle;
    }
}