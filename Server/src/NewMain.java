import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JList;
/*from   w  ww  . j a  va 2s.  c o m*/
public class NewMain {
  public static void main(String[] args) {
    JFrame f = new JFrame();
    JList list = new JList(new String[] { "one", "two", "three", "four" });
    list.setSelectionModel(new DefaultListSelectionModel() {
      public void setSelectionInterval(int index0, int index1) {
        if (index0 == index1) {
          if (isSelectedIndex(index0)) {
            removeSelectionInterval(index0, index0);
            System.out.println(""+list.getSelectedIndex());
            return;
          }
        }
        super.setSelectionInterval(index0, index1);
        System.out.println(""+list.getSelectedIndex());
      }

      @Override
      public void addSelectionInterval(int index0, int index1) {
        if (index0 == index1) {
          if (isSelectedIndex(index0)) {
            removeSelectionInterval(index0, index0);
            System.out.println(""+list.getSelectedIndex());
            return;
          }
          super.addSelectionInterval(index0, index1);
          System.out.println(""+list.getSelectedIndex());
        }
      }

    });
    System.out.println(list.getSelectedIndex());
    f.getContentPane().add(list);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
  }
}