package ai1028;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;

public class MouseEventTest3 extends JFrame {
    JLabel lblImg = new JLabel();

    public MouseEventTest3() {
        String[] btnsText = {"언더테이커", "헐크 호건", "카말라"};
        JPanel panel = new JPanel();
        for (int i = 0; i < btnsText.length; i++) {
            JButton btn = new JButton(btnsText[i]);
            btn.addMouseListener(btnListener);
            panel.add(btn);
        }
        add("North", panel);
        add("Center", lblImg);
        lblImg.setOpaque(true);
        lblImg.setBackground(Color.WHITE);


        SetFrame.setFrame(this,"버튼에 따른 이미지 변경", 500, 500);
    }

    MouseListener btnListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton eBtn = (JButton) e.getSource();
            ImageIcon imageIcon = null;

            switch (eBtn.getText()) {
                case "언더테이커":
                    imageIcon = new ImageIcon("imgs/img2.png");
                    break;
                case "헐크 호건":
                    imageIcon = new ImageIcon("imgs/wwe2.jpg");
                    break;
                case "카말라":
                    imageIcon = new ImageIcon("imgs/img1.png");
                    break;
            }

            lblImg.setIcon(imageIcon);
        }
    };



    static void main(String[] args) {
        new MouseEventTest3();
    }
}
