import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIexample extends JFrame {
  public static void main (String [] args) {
    GUIexample gui = new GUIexample();
    gui.setVisible(true);
  }

  public GUIexample() {
    super();
    setSize(300,300);
    setTitle("Editor GUI");

    Container c = getContentPane();
    c.setLayout(new BorderLayout());

    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    addWindowListener(new WindowAdapter(){
      int attempt = 0;
      JLabel alert = new JLabel();
      @Override
      public void windowClosing(WindowEvent e) {
          alert.setForeground(Color.RED);
         c.add(alert, BorderLayout.NORTH);
         switch(++attempt){
           case 1:
            alert.setText("Are you sure!");
            break;
          case 2:
            alert.setText("Are you really sure!");
            break;
          case 3:
            System.exit(0);
          default:
            break;
         }
      }
    });


    JMenuBar mb = new JMenuBar();
    JMenu menu1 = new JMenu("Languages");
    JMenuItem item1 = new JMenuItem("English to Spanish");
    JMenuItem item2 = new JMenuItem("Spanish to English");

    menu1.add(item1);
    menu1.add(item2);
    mb.add(menu1);

    setJMenuBar(mb);

    JPanel center_panel = new JPanel();
    center_panel.setLayout(new GridLayout(4,1));

    JLabel label1 = new JLabel("Type your text here:");
    JLabel label2 = new JLabel("Modified Text:");

    JTextArea txt1 = new JTextArea();
    JTextArea txt2 = new JTextArea();

    center_panel.add(label1);
    center_panel.add(txt1);
    center_panel.add(label2);
    center_panel.add(txt2);

    c.add(center_panel, BorderLayout.CENTER);

    JPanel bottom = new JPanel();
    bottom.setLayout(new FlowLayout());

    JButton b1 = new JButton("Check Spelling");
    JButton b2 = new JButton("Translate");

    bottom.add(b1);
    bottom.add(b2);

    c.add(bottom, BorderLayout.SOUTH);
  }
}
