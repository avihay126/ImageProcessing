import org.checkerframework.checker.units.qual.C;

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
    private URL url;
    private BufferedImage bufferedImage;
    private ImageIcon backGround;


    public ProcessingScene(int x, int y, int width, int height) {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.backGround = new ImageIcon(Constants.BACKGROUND_NAME);
        mainView();
        this.setDoubleBuffered(true);
        this.setVisible(true);
    }

    private void mainView() {
        this.textField = Helper.addTextField(this, this.getWidth() / 2 - Constants.TEXT_FIELD_WIDTH / 2, Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.searchButton = Helper.addButton(this, Constants.SEARCH_BUTTON, this.textField.getX(), this.textField.getY() + this.textField.getHeight() + Constants.TOP_MARGIN, this.textField.getWidth(), this.textField.getHeight());
        this.searchButton.addActionListener((e) -> {
            ImageUrl imageUrl = new ImageUrl(this.textField.getText());
            String url = imageUrl.getImage(imageUrl.getProfileName());
            if (this.picture != null) {
                this.remove(picture);
                this.remove(processPicture);
                repaint();
            }

            processView(url);
        });
    }

    private void processView(String urlLink) {
        try {
            this.url = ImageTransferring.getUrl(urlLink);
            this.bufferedImage = ImageIO.read(this.url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.picture = ImageTransferring.readImage(this.url);
        this.add(this.picture);
        this.processPicture = ImageTransferring.readImage(this.url);
        this.processPicture.setBounds(this.getWidth() - this.picture.getWidth() - Constants.RIGHT_MARGIN, 0, this.picture.getWidth(), this.picture.getHeight());
        this.add(this.processPicture);
        this.addProcessButtons();
        addTittlePic();
        addTittleLoading();
        repaint();
    }

    private void addTittlePic() {
        Helper.addLabel(this, Constants.AFTER_TITLE, (int) (this.picture.getWidth() / 2.5), this.picture.getHeight() + 15, 100, 50);
        Helper.addLabel(this, Constants.BEFORE_TITLE, (int) (this.getWidth() - this.picture.getWidth() / 1.5), this.picture.getHeight() + 15, 100, 50);
    }

    private void addTittleLoading() {
        Helper.addLabel(this, "Loading Please wait...", this.searchButton.getX() + 50, Constants.WIN_HEIGHT - 150, this.picture.getHeight() + 15, 50);
    }

    private void addProcessButtons() {
        JButton[] buttons = new JButton[6];
        buttons[0] = Helper.addButton(this, Constants.FILTER_COLOR_SHIFT_LEFT, this.searchButton.getX(), this.searchButton.getY() + this.searchButton.getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[1] = Helper.addButton(this, Constants.FILTER_COLOR_SHIFT_RIGHT, this.searchButton.getX(), buttons[0].getY() + buttons[0].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[2] = Helper.addButton(this, Constants.FILTER_NEGATIVE_COLOR, this.searchButton.getX(), buttons[1].getY() + buttons[1].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[3] = Helper.addButton(this, Constants.FILTER_GRAY_SCALE, this.searchButton.getX(), buttons[2].getY() + buttons[2].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[4] = Helper.addButton(this, Constants.FILTER_LIGHTER, this.searchButton.getX(), buttons[3].getY() + buttons[3].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[5] = Helper.addButton(this, Constants.FILTER_MIRROR, this.searchButton.getX(), buttons[4].getY() + buttons[4].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);

        buttons[0].addActionListener((e) -> {

            changeImage(ImageProcessing.colorShiftLeft(this.bufferedImage));

        });
        buttons[1].addActionListener((e) -> {
            changeImage(ImageProcessing.colorShiftRight(this.bufferedImage));

        });
        buttons[2].addActionListener((e) -> {
            changeImage(ImageProcessing.negativeColor(this.bufferedImage));


        });
        buttons[3].addActionListener((e) -> {
            changeImage(ImageProcessing.grayScale(this.bufferedImage));


        });
        buttons[4].addActionListener((e) -> {
            changeImage(ImageProcessing.lighter(this.bufferedImage));
        });
        buttons[5].addActionListener((e) -> {
            changeImage(ImageProcessing.mirror(this.bufferedImage));
        });

    }

    private void changeImage(JLabel newLabel) {
        this.remove(this.processPicture);
        this.processPicture = newLabel;
        this.processPicture.setBounds(this.getWidth() - this.picture.getWidth() - Constants.RIGHT_MARGIN, 0, this.picture.getWidth(), this.picture.getHeight());
        this.add(this.processPicture);
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(this.backGround.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }
}




