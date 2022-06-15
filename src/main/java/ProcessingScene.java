import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ProcessingScene extends JPanel {
    private JTextField textField;
    private JButton searchButton;
    private JLabel picture;
    private JLabel processPicture;

    public ProcessingScene(int x, int y, int width, int height) {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        mainView();
        this.setDoubleBuffered(true);
        this.setVisible(true);
    }

    private void mainView() {
        this.textField = Helper.addTextField(this, this.getWidth() / 2 - Constants.TEXT_FIELD_WIDTH / 2, Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.searchButton = Helper.addButton(this, "Get Image", this.textField.getX(), this.textField.getY() + this.textField.getHeight() + Constants.TOP_MARGIN, this.textField.getWidth(), this.textField.getHeight());
        this.searchButton.addActionListener((e) -> {
//            ImageUrl imageUrl=new ImageUrl(this.textField.getText());
            this.picture=ImageTransferring.readImage("https://i.pinimg.com/474x/40/75/28/4075283d5b2eb08de87d1c7883edcf13.jpg");
            this.add(this.picture);
            this.processPicture=ImageTransferring.readImage("https://i.pinimg.com/474x/40/75/28/4075283d5b2eb08de87d1c7883edcf13.jpg");
            this.processPicture.setBounds(this.getWidth()-this.picture.getWidth()-Constants.RIGHT_MARGIN,0,this.picture.getWidth(),this.picture.getHeight());
            this.add(this.processPicture);
            addProcessButtons();
            repaint();
        });
    }

    private void addProcessButtons(){
        JButton[] buttons=new JButton[6];
        buttons[0]=Helper.addButton(this,"",this.searchButton.getX(),this.searchButton.getY()+this.searchButton.getHeight()+Constants.TOP_MARGIN,Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        buttons[1]=Helper.addButton(this,"",this.searchButton.getX(),buttons[0].getY()+buttons[0].getHeight()+Constants.TOP_MARGIN,Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        buttons[2]=Helper.addButton(this,"",this.searchButton.getX(),buttons[1].getY()+buttons[1].getHeight()+Constants.TOP_MARGIN,Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        buttons[3]=Helper.addButton(this,"",this.searchButton.getX(),buttons[2].getY()+buttons[2].getHeight()+Constants.TOP_MARGIN,Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        buttons[4]=Helper.addButton(this,"",this.searchButton.getX(),buttons[3].getY()+buttons[3].getHeight()+Constants.TOP_MARGIN,Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        buttons[5]=Helper.addButton(this,"",this.searchButton.getX(),buttons[4].getY()+buttons[4].getHeight()+Constants.TOP_MARGIN,Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
    }




}
