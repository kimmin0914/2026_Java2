package ai1103;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class MenuTest extends JFrame {
    private static final File OUTPUT_DIRECTORY = new File("C:/2026_Java2/결과물");
    JTextArea textArea = new JTextArea();
    JFileChooser fileChooser = new JFileChooser(OUTPUT_DIRECTORY);
    JFrame frame;
    JButton[] btns = new JButton[6];
    String[] iconFileNames = {"new", "open", "close"};
    Color[] colorNames = {Color.WHITE, Color.BLUE, Color.MAGENTA};
    String searchText = "";

    public MenuTest() {
        frame = this;

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        JToolBar toolBar = new JToolBar();
        add("North", toolBar);

        for (int i = 0; i < btns.length; i++) {
            if (i < 3) {
                btns[i] = new JButton(new ImageIcon("toolbar/" + iconFileNames[i] + ".jpg"));
            } else {
                btns[i] = new JButton("        ");
                btns[i].setBackground(colorNames[i - 3]);
            }
            btns[i].addActionListener(btnListener);
            toolBar.add(btns[i]);
        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        JMenuItem closeMenuItem = new JMenuItem("Close");
        JMenuItem saveDraftMenuItem = new JMenuItem("Save as temporary");

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(closeMenuItem);
        fileMenu.add(saveDraftMenuItem);

        JMenuItem whiteMenuItem = new JMenuItem("White");
        JMenuItem blueMenuItem = new JMenuItem("Blue");
        JMenuItem magentaMenuItem = new JMenuItem("Magenta");
        JMenuItem characterCountMenuItem = new JMenuItem("글자 수 확인");
        JMenuItem findMenuItem = new JMenuItem("찾기");
        JMenuItem findNextMenuItem = new JMenuItem("다음 찾기");
        JMenuItem replaceMenuItem = new JMenuItem("바꾸기");
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        findNextMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        replaceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        // JTextArea의 기본 삭제 단축키 대신 바꾸기 창을 엽니다.
        textArea.getInputMap().put(replaceMenuItem.getAccelerator(), "replaceText");
        textArea.getActionMap().put("replaceText", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replaceText();
            }
        });
        JCheckBoxMenuItem wrapMenuItem = new JCheckBoxMenuItem("자동 줄바꿈");

        editMenu.add(whiteMenuItem);
        editMenu.add(blueMenuItem);
        editMenu.add(magentaMenuItem);
        editMenu.addSeparator();
        editMenu.add(characterCountMenuItem);
        editMenu.addSeparator();
        editMenu.add(findMenuItem);
        editMenu.add(findNextMenuItem);
        editMenu.add(replaceMenuItem);
        viewMenu.add(wrapMenuItem);

        newMenuItem.addActionListener(fileListener);
        openMenuItem.addActionListener(fileListener);
        saveMenuItem.addActionListener(fileListener);
        closeMenuItem.addActionListener(fileListener);
        saveDraftMenuItem.addActionListener(fileListener);

        whiteMenuItem.addActionListener(backgroundListener);
        blueMenuItem.addActionListener(backgroundListener);
        magentaMenuItem.addActionListener(backgroundListener);
        characterCountMenuItem.addActionListener(characterCountListener);
        findMenuItem.addActionListener(editingListener);
        findNextMenuItem.addActionListener(editingListener);
        replaceMenuItem.addActionListener(editingListener);
        wrapMenuItem.addActionListener(editingListener);

        SetFrame.setFrame(this, "메뉴 만들기", 500, 500);
    }

    ActionListener editingListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem eItem = (JMenuItem) e.getSource();
            switch (eItem.getText()) {
                case "찾기":
                    findText();
                    break;
                case "다음 찾기":
                    findNextText();
                    break;
                case "바꾸기":
                    replaceText();
                    break;
                case "자동 줄바꿈":
                    JCheckBoxMenuItem wrapItem = (JCheckBoxMenuItem) eItem;
                    textArea.setLineWrap(wrapItem.isSelected());
                    textArea.setWrapStyleWord(true);
                    break;
            }
        }
    };

    public void findText() {
        String input = (String) JOptionPane.showInputDialog(frame,
                "찾을 내용을 입력하세요. (대소문자를 구분합니다.)", "찾기",
                JOptionPane.QUESTION_MESSAGE, null, null, searchText);
        if (input == null) {
            return;
        }
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "찾을 내용을 입력해주세요.",
                    "찾기", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        searchText = input;
        findNextText();
    }

    public void findNextText() {
        if (searchText.isEmpty()) {
            findText();
            return;
        }

        String text = textArea.getText();
        int index = text.indexOf(searchText, textArea.getSelectionEnd());
        if (index == -1) {
            index = text.indexOf(searchText);
        }
        if (index == -1) {
            JOptionPane.showMessageDialog(frame, "찾는 내용이 없습니다.",
                    "찾기", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        textArea.requestFocusInWindow();
        textArea.select(index, index + searchText.length());
    }

    public void replaceText() {
        JTextField findField = new JTextField(searchText, 20);
        JTextField replaceField = new JTextField(20);
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        inputPanel.add(new JLabel("찾을 내용"));
        inputPanel.add(findField);
        inputPanel.add(new JLabel("바꿀 내용"));
        inputPanel.add(replaceField);

        String[] options = {"모두 바꾸기", "취소"};
        int answer = JOptionPane.showOptionDialog(frame, inputPanel, "바꾸기",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (answer != 0) {
            return;
        }

        String target = findField.getText();
        if (target.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "찾을 내용을 입력해주세요.",
                    "바꾸기", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String text = textArea.getText();
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }
        if (count == 0) {
            JOptionPane.showMessageDialog(frame, "찾는 내용이 없습니다.",
                    "바꾸기", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        searchText = target;
        textArea.setText(text.replace(target, replaceField.getText()));
        JOptionPane.showMessageDialog(frame, count + "곳을 바꾸었습니다.",
                "바꾸기", JOptionPane.INFORMATION_MESSAGE);
    }

    ActionListener characterCountListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textArea.getText();
            int count = text.codePointCount(0, text.length());
            JOptionPane.showMessageDialog(frame,
                    "글자 수는 " + count + "자입니다.\n공백과 줄바꿈을 포함합니다.");
        }
    };

    ActionListener backgroundListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem eItem = (JMenuItem) e.getSource();

            switch (eItem.getText()) {
                case "White":
                    textArea.setBackground(Color.WHITE);
                    break;
                case "Blue":
                    textArea.setBackground(Color.BLUE);
                    break;
                case "Magenta":
                    textArea.setBackground(Color.MAGENTA);
                    break;
            }

        }
    };

    public String readFile(File selectedFile) {
        String totalLines = "";
        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                totalLines += line + "\n";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return totalLines;
    }

    public void saveFile(String currentString) {
        fileChooser.setCurrentDirectory(OUTPUT_DIRECTORY);
        fileChooser.setSelectedFile(new File(OUTPUT_DIRECTORY, "메모.txt"));
        if (fileChooser.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile.exists()) {
            int answer = JOptionPane.showConfirmDialog(frame,
                    "같은 이름의 파일이 있습니다. 덮어쓰시겠습니까?",
                    "저장 확인", JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try (FileWriter fw = new FileWriter(selectedFile)) {
            fw.write(currentString);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "파일을 저장하지 못했습니다.");
            return;
        }

        System.out.println("저장되었습니다.");
        JOptionPane.showMessageDialog(frame, "저장되었습니다.");
    }

    public void saveDraftFile(String currentString) {
        try (FileWriter fw = new FileWriter(new File(OUTPUT_DIRECTORY, "temp.txt"))) {
            fw.write(currentString);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "파일을 저장하지 못했습니다.");
            return;
        }
        System.out.println("저장되었습니다.");
    }

    ActionListener fileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile;
            JMenuItem eItem = (JMenuItem) e.getSource();

            switch (eItem.getText()) {
                case "New":
                    textArea.setText(null);
                    break;
                case "Open":
                    if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                        selectedFile = fileChooser.getSelectedFile();
                        textArea.setText(readFile(selectedFile));
                    }
                    break;
                case "Save":
                    saveFile(textArea.getText());
                    break;
                case "Close":
                    System.exit(0);
                    break;
                case "Save as temporary":
                    saveDraftFile(textArea.getText());
                    break;
            }
        }
    };

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile;
            JButton eBtn = (JButton) e.getSource();

            if (eBtn == btns[0]) {
                textArea.setText(null);
            } else if (eBtn == btns[1]) {
                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    textArea.setText(readFile(selectedFile));
                }
            } else if (eBtn == btns[2]) {
                System.exit(0);
            }

            for (int i = 0; i < colorNames.length; i++) {
                if (eBtn == btns[i + 3]) {
                    textArea.setBackground(colorNames[i]);
                }
            }
        }
    };

    public static void main(String[] args) {
        new MenuTest();
    }
}
