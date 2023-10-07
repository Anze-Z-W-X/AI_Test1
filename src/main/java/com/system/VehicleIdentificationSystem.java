package com.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VehicleIdentificationSystem extends JFrame {
    String[] features = {"体型大", "体型小", "有轮子", "流线型", "在天上的", "在水里的",
            "在地上的", "冒黑烟的", "有机翼的", "有螺旋桨的", "有四个轮子的", "有两个轮子的"};
    //声明复选框队列
    private JCheckBox[] checkBoxes = new JCheckBox[features.length];
    //初始化输入
    private String input = "";
    //int数组，存选中的条件编号
    private int[] conditions;
    //规则库声明
    private RuleRepository ruleRepository;

    private VehicleIdentificationSystem() {
        super("交通工具识别系统");
        //创建窗口的尺寸对象
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        dimension.width = (int) (dimension.width * 0.5);
        dimension.height = (int) (dimension.height * 0.5);
        //设置窗口布局
        this.setLayout(new FlowLayout());
        //创建面板对象
        JPanel option = new JPanel();
        //设置面板布局
        option.setLayout(new GridLayout(2, 6));
        option.setPreferredSize(new Dimension((int)(dimension.width * 0.95), (int) (dimension.height * 0.2)));
        for (int i = 0; i < features.length; i++) {
            //面板复选框创建与添加
            checkBoxes[i] = new JCheckBox(features[i]);
            option.add(checkBoxes[i]);
        }
        //添加面板进入窗口
        this.add(option);
        //创建垂直面板
        Box box = Box.createVerticalBox();
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(dimension.width, (int) (dimension.height * 0.40)));
        //初始文本显示
        JTextArea showResult = new JTextArea("2127406029赵文旭，这是我制作的交通工具识别系统\n");
        showResult.setPreferredSize(new Dimension(dimension.width, (int) (dimension.height * 0.40)));
        jPanel.add(showResult);
        //添加面板进入窗口
        box.add(jPanel);
        //设置判断按钮
        JButton confirm = new JButton("判断");
        //点击按钮后的处理
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // 清空上一次的结果
                input = "";
                showResult.setText("");
                for(int i = 0; i < features.length; i++) {
                    if(checkBoxes[i].isSelected()) {
                        input += i;
                        input += " ";
                    }
                }
                // 转化为数组，按空格分割
                String[] inputs = input.split("\\s+");
                conditions = new int[inputs.length];
                for(int i = 0; i < inputs.length; i++) {
                    conditions[i] = Integer.parseInt(inputs[i]);
                }
                //向规则库中传入条件编号
                ruleRepository = new RuleRepository(conditions);
                String res = ruleRepository.reasoning();
                showResult.setText(res);
            }
        });
        box.add(confirm);

        this.add(box);
        //设置尺寸
        this.setSize(dimension);
        //设置窗口可见
        this.setVisible(true);
        //构造一个窗口适配器
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                VehicleIdentificationSystem.this.dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        try {
            //UI管理器设置外观和感觉
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //展示窗口
        VehicleIdentificationSystem vehicleIdentificationSystem = new VehicleIdentificationSystem();
    }

}
