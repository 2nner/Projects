package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient extends JFrame implements ActionListener, Runnable {
    JTextArea textArea = new JTextArea();
    JScrollPane jp = new JScrollPane(textArea);
    JTextField input_Text = new JTextField();
    JMenuBar menuBar = new JMenuBar();

    Socket sk;
    BufferedReader br;
    PrintWriter pw;

    public SocketClient() {
        super("Hansei Talk Ver 1.1 made by 2nner");

        textArea.setBackground(Color.lightGray);
        textArea.setEditable(false);// TextArea 입력하기 비활성화

        JMenu helpMenu = new JMenu("Help");

        JMenuItem update = new JMenuItem("업데이트 정보");
        JMenuItem connect_List = new JMenuItem("접속자 리스트");

        helpMenu.add(update);
        helpMenu.add(connect_List);

        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        getContentPane().add(jp, "Center");
        getContentPane().add(input_Text, "South");
        setSize(500, 600);
        setVisible(true);

        input_Text.requestFocus(); //실행시 커서 놓기, 화면이 보여진 후 작업해야함

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        input_Text.addActionListener(this); //이벤트 등록
    }

    public void serverConnection() {
        try {
            String IP = JOptionPane.showInputDialog(this, "서버 IP를 입력해주세요.", JOptionPane.INFORMATION_MESSAGE);
            sk = new Socket(IP, 1234);

            String name = JOptionPane.showInputDialog(this, "닉네임을 입력해주세요.(7글자 이내)", JOptionPane.INFORMATION_MESSAGE);
            while (name.length() > 7) {
                name = JOptionPane.showInputDialog(this, "닉네임을 입력해주세요.(7글자 이내로 하시라구욧)", JOptionPane.INFORMATION_MESSAGE);
            }

            //읽기
            br = new BufferedReader(new InputStreamReader(sk.getInputStream()));

            //쓰기
            pw = new PrintWriter(sk.getOutputStream(), true);
            pw.println(name); // 서버측에 전송하기

            new Thread(this).start();

        } catch (Exception e) {
            System.out.println(e + " Socket 접속 오류");
        }
    }

    public static void main(String[] args) {
        new SocketClient().serverConnection(); //객체 생성과 동시에 메소드 호출
    }

    @Override
    public void run() {
        String data = null;
        try {
            while ((data = br.readLine()) != null) {
                textArea.append(data + "\n"); //textArea박스의 스크롤바의 위치를 입력된 Text길이 만큼 내리기
                textArea.setCaretPosition(textArea.getText().length());
            }
        } catch (Exception e) {
            System.out.println(e + "--> Client run fail");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String data = input_Text.getText();
        pw.println(data); // 서버측에 전송
        input_Text.setText("");
    }
}
