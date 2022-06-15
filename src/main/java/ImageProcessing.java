import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageProcessing {


    public static JLabel colorShiftRight(URL url) {
        JLabel processPic = new JLabel();
        try {
            BufferedImage bufferedImage = ImageIO.read(url);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int currentPixel = bufferedImage.getRGB(x, y);
                    Color color = new Color(currentPixel);
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    Color newColor = new Color(green, blue, red);
                    bufferedImage.setRGB(x, y, newColor.getRGB());
                }
            }
            processPic = ImageTransferring.minimizeImage(width, height, bufferedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return processPic;
    }
}
