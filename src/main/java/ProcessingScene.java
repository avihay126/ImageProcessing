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
    private JLabel after;
    private JLabel before;
    private Thread loading;


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
            if (!this.textField.getText().equals("")){
                addTittleLoading();
                new Thread(() -> {
                    ImageUrl imageUrl = new ImageUrl(this.textField.getText());
                    String url = imageUrl.getImage(imageUrl.getProfileName());
                    if (this.picture != null) {
                        this.remove(picture);
                        this.remove(processPicture);
                        this.after.setVisible(false);
                        this.before.setVisible(false);
                        repaint();
                    }
                    processView(url);
                }).start();
            }
        });
    }

    private void processView(String urlLink) {
        try {
            this.url = ImageUrl.getUrl(urlLink);
            this.bufferedImage = ImageIO.read(this.url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.picture = ImageTransferring.readImage(this.url);
        this.add(this.picture);
        this.processPicture = ImageTransferring.readImage(this.url);
        this.processPicture.setBounds(this.getWidth() - this.picture.getWidth() - Constants.RIGHT_MARGIN, 0, this.picture.getWidth(), this.picture.getHeight());
        this.add(this.processPicture);
        processButtons();
        addTittlePic();
        repaint();
    }

    private void addTittlePic() {
        this.before=Helper.addLabel(this, Constants.BEFORE_TITLE, (int) (this.picture.getWidth() / 2.5), this.picture.getHeight() + Constants.LABEL_MARGIN, Constants.LABEL_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.after=Helper.addLabel(this, Constants.AFTER_TITLE, (int) (this.getWidth() - this.picture.getWidth() / 1.5), this.picture.getHeight() + Constants.LABEL_MARGIN, Constants.LABEL_WIDTH, Constants.TEXT_FIELD_HEIGHT);
    }

    private void addTittleLoading() {
        this.loading = new Thread(() -> {
            try {
                JLabel label = Helper.addLabel(this, Constants.LOADING_PRINT, this.getWidth() / 2 - Constants.TEXT_FIELD_WIDTH / 4, this.getHeight() * 3 / 4, Constants.TEXT_FIELD_WIDTH / 2, Constants.TEXT_FIELD_HEIGHT);
                repaint();
                Thread.sleep(Constants.LOADING);
                label.setVisible(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        this.loading.start();

    }

    private void processButtons() {
        JButton[] buttons = new JButton[Constants.BUTTON_SIZE];
        buttons[Constants.COLOR_SHIFT_LEFT_BUTTON] = Helper.addButton(this, Constants.FILTER_COLOR_SHIFT_LEFT, this.searchButton.getX(), this.searchButton.getY() + this.searchButton.getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[Constants.COLOR_SHIFT_RIGHT_BUTTON] = Helper.addButton(this, Constants.FILTER_COLOR_SHIFT_RIGHT, this.searchButton.getX(), buttons[Constants.COLOR_SHIFT_LEFT_BUTTON].getY() + buttons[Constants.COLOR_SHIFT_LEFT_BUTTON].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[Constants.NEGATIVE_COLOR_BUTTON] = Helper.addButton(this, Constants.FILTER_NEGATIVE_COLOR, this.searchButton.getX(), buttons[Constants.COLOR_SHIFT_RIGHT_BUTTON].getY() + buttons[Constants.COLOR_SHIFT_RIGHT_BUTTON].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[Constants.GRAY_SCALE_BUTTON] = Helper.addButton(this, Constants.FILTER_GRAY_SCALE, this.searchButton.getX(), buttons[Constants.NEGATIVE_COLOR_BUTTON].getY() + buttons[Constants.NEGATIVE_COLOR_BUTTON].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[Constants.LIGHTER_BUTTON] = Helper.addButton(this, Constants.FILTER_LIGHTER, this.searchButton.getX(), buttons[Constants.GRAY_SCALE_BUTTON].getY() + buttons[Constants.GRAY_SCALE_BUTTON].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        buttons[Constants.MIRROR_BUTTON] = Helper.addButton(this, Constants.FILTER_MIRROR, this.searchButton.getX(), buttons[Constants.LIGHTER_BUTTON].getY() + buttons[Constants.LIGHTER_BUTTON].getHeight() + Constants.TOP_MARGIN, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);

        buttons[Constants.COLOR_SHIFT_LEFT_BUTTON].addActionListener((e) -> {
            changeImage(ImageProcessing.colorShiftLeft(this.bufferedImage));
        });
        buttons[Constants.COLOR_SHIFT_RIGHT_BUTTON].addActionListener((e) -> {
            changeImage(ImageProcessing.colorShiftRight(this.bufferedImage));
        });
        buttons[Constants.NEGATIVE_COLOR_BUTTON].addActionListener((e) -> {
            changeImage(ImageProcessing.negativeColor(this.bufferedImage));
        });
        buttons[Constants.GRAY_SCALE_BUTTON].addActionListener((e) -> {
            changeImage(ImageProcessing.grayScale(this.bufferedImage));
        });
        buttons[Constants.LIGHTER_BUTTON].addActionListener((e) -> {
            changeImage(ImageProcessing.lighter(this.bufferedImage));
        });
        buttons[Constants.MIRROR_BUTTON].addActionListener((e) -> {
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




