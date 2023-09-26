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
    private JCheckBox[] checkBoxes = new JCheckBox[features.length];
    private String input = "";
    private int[] conditions;
    private RuleRepository ruleRepository;

    private VehicleIdentificationSystem() {
        super("交通工具识别系统");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        dimension.width = (int) (dimension.width * 0.5);
        dimension.height = (int) (dimension.height * 0.5);

        this.setLayout(new FlowLayout());

        JPanel option = new JPanel();
        option.setLayout(new GridLayout(2, 6));
        option.setPreferredSize(new Dimension((int)(dimension.width * 0.95), (int) (dimension.height * 0.2)));
        for (int i = 0; i < features.length; i++) {
            checkBoxes[i] = new JCheckBox(features[i]);
            option.add(checkBoxes[i]);
        }
        this.add(option);

        Box box = Box.createVerticalBox();
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(dimension.width, (int) (dimension.height * 0.38)));
        jPanel.setBackground(Color.blue);
        JTextArea showResult = new JTextArea("2127406029赵文旭，这是我制作的交通工具识别系统\n");
        showResult.setPreferredSize(new Dimension(dimension.width, (int) (dimension.height * 0.38)));
        jPanel.add(showResult);
        box.add(jPanel);

        JButton confirm = new JButton("判断");
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
                ruleRepository = new RuleRepository(conditions);
                String res = ruleRepository.reasoning();
                showResult.setText(res);
            }
        });
        box.add(confirm);

        this.add(box);

        this.setSize(dimension);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                VehicleIdentificationSystem.this.dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        VehicleIdentificationSystem vehicleIdentificationSystem = new VehicleIdentificationSystem();
    }

}
