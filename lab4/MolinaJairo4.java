/*
Jairo Molina
23499086
CSC 212-12A
Fall 2019

Assignment 4: Circle Class
Java program implementing a Banner Maker GUI

*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MolinaJairo4 {
  public static void main (String [] args) {
    BannerMaker banner = new BannerMaker();
    banner.setVisible(true);
  }
}

class BannerMaker extends JFrame {
  Container contentPane;
  JTextField updateText, bannerText;

  public BannerMaker() {
    setSize(300, 300);
    setTitle("Banner Maker");

    contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());

    /***************** center panel *****************/
    JTextField bannerText = new JTextField("Write Here");
    bannerText.setHorizontalAlignment(JTextField.CENTER);

    /***************** left panel *****************/
    ColorButtonListener changeColorObj = new ColorButtonListener(bannerText);

    JPanel leftSide = new JPanel();
    leftSide.setLayout(new GridLayout(3, 1));

    JButton buttonColorRed = new JButton("Red");
    buttonColorRed.addActionListener(changeColorObj);

    JButton buttonColorBlue = new JButton("Pink");
    buttonColorBlue.addActionListener(changeColorObj);

    JButton buttonColorYellow = new JButton("Yellow");
    buttonColorYellow.addActionListener(changeColorObj);

    leftSide.add(buttonColorRed);
    leftSide.add(buttonColorBlue);
    leftSide.add(buttonColorYellow);

    /***************** right panel *****************/
    StyleButtonListener changeStyleObjt = new StyleButtonListener(bannerText);

    JPanel rightSide = new JPanel();
    rightSide.setLayout(new GridLayout(3, 1));

    JButton buttonBold = new JButton("Bold");
    buttonBold.addActionListener(changeStyleObjt);

    JButton buttonItalic = new JButton("Italic");
    buttonItalic.addActionListener(changeStyleObjt);

    JButton buttonPlain = new JButton("Plain");
    buttonPlain.addActionListener(changeStyleObjt);

    rightSide.add(buttonBold);
    rightSide.add(buttonItalic);
    rightSide.add(buttonPlain);

    /***************** bottom panel *****************/
    FontButtonListener changeFontObj = new FontButtonListener(bannerText);
    JPanel bottomSide = new JPanel();
    bottomSide.setLayout(new GridLayout(0, 2));

    JButton buttonFont1 = new JButton("Serif");
    buttonFont1.addActionListener(changeFontObj);

    JButton buttonFont2 = new JButton("Sans_Serif");
    buttonFont2.addActionListener(changeFontObj);

    bottomSide.add(buttonFont1);
    bottomSide.add(buttonFont2);

    /***************** top panel *****************/
    JPanel topSide = new JPanel();
    topSide.setLayout(new GridLayout(0,4));

    JTextField sizeText = new JTextField("12"); //12 is default font size
    FontSizeListener changeSizeObj = new FontSizeListener(bannerText, sizeText);

    JButton buttonIncrease = new JButton("+");
    buttonIncrease.addActionListener(changeSizeObj);

    JButton buttonDecrease = new JButton("-");
    buttonDecrease.addActionListener(changeSizeObj);

    JButton buttonSize = new JButton("Update");
    buttonSize.addActionListener(changeSizeObj);

    topSide.add(buttonIncrease);
    topSide.add(buttonDecrease);
    topSide.add(sizeText);
    topSide.add(buttonSize);

    //adding panels to frame
    contentPane.add(bannerText, BorderLayout.CENTER);
    contentPane.add(leftSide, BorderLayout.WEST);
    contentPane.add(rightSide, BorderLayout.EAST);
    contentPane.add(bottomSide, BorderLayout.SOUTH);
    contentPane.add(topSide, BorderLayout.NORTH);
  }
}

class ColorButtonListener implements ActionListener {
  JTextField bannerText;

  public ColorButtonListener(JTextField bannerText) {
    this.bannerText = bannerText;
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Red"))
    {
      bannerText.setBackground(Color.RED);
    }
    else if(e.getActionCommand().equals("Pink"))
    {
      bannerText.setBackground(Color.PINK);
    }
    else if(e.getActionCommand().equals("Yellow"))
    {
      bannerText.setBackground(Color.YELLOW);
    }
  }
}

class StyleButtonListener implements ActionListener {
  JTextField bannerText;

  public StyleButtonListener(JTextField bannerText) {
    this.bannerText = bannerText;
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Bold"))
    {
      bannerText.setFont(bannerText.getFont().deriveFont(Font.BOLD));
    }
    else if(e.getActionCommand().equals("Italic"))
    {
      bannerText.setFont(bannerText.getFont().deriveFont(Font.ITALIC));
    }
    else if(e.getActionCommand().equals("Plain"))
    {
      bannerText.setFont(bannerText.getFont().deriveFont(Font.PLAIN));
    }
  }
}

class FontButtonListener implements ActionListener {
  JTextField bannerText;

  public FontButtonListener(JTextField bannerText) {
    this.bannerText = bannerText;
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Serif"))
    {
      bannerText.setFont(new Font(Font.SERIF, bannerText.getFont().getStyle(), bannerText.getFont().getSize()));
    }
    else if(e.getActionCommand().equals("Sans_Serif"))
    {
      bannerText.setFont(new Font(Font.SANS_SERIF, bannerText.getFont().getStyle(), bannerText.getFont().getSize()));
    }
  }
}

class FontSizeListener implements ActionListener {
  JTextField bannerText, sizeText;

  public FontSizeListener(JTextField bannerText, JTextField sizeText) {
    this.bannerText = bannerText;
    this.sizeText = sizeText;
  }

  public void actionPerformed(ActionEvent e) {
    int size = bannerText.getFont().getSize();

    if(e.getActionCommand().equals("+"))
    {
      bannerText.setFont(new Font(bannerText.getFont().getName(), bannerText.getFont().getStyle(), ++size ));
    }
    else if (e.getActionCommand().equals("-"))
    {
      bannerText.setFont(new Font(bannerText.getFont().getName(), bannerText.getFont().getStyle(), --size ));
    }
    else if(e.getActionCommand().equals("Update"))
    {
      bannerText.setFont(new Font(bannerText.getFont().getName(), bannerText.getFont().getStyle(), Integer.parseInt(sizeText.getText()) ));
    }
  }
}
