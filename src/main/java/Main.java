import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        new Main();
    }


    public Main(){
        this.setLayout(null);
        this.setSize(Constants.WIN_WIDTH,Constants.WIN_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(new ProcessingScene(0,0,Constants.WIN_WIDTH,Constants.WIN_HEIGHT));
        this.setVisible(true);
        this.setTitle("image processing");

    }
}
