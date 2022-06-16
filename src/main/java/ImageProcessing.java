import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageProcessing {


    public static JLabel colorShiftLeft (URL url) {
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
                    Color newColor = new Color(blue, red, green);
                    bufferedImage.setRGB(x, y, newColor.getRGB());
                }
            }
            processPic = ImageTransferring.minimizeImage(width, height, bufferedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return processPic;
    }
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
    public static JLabel grayScale(URL url) {
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
                    int newGray=(red+green+blue)/3;
                    int newGrayRed=newGray;
                    int newGrayBlue=newGray;
                    int newGrayGreen=newGray;
                    Color newColor = new Color(newGrayRed, newGrayBlue, newGrayGreen);
                    bufferedImage.setRGB(x, y, newColor.getRGB());
                }
            }
            processPic = ImageTransferring.minimizeImage(width, height, bufferedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return processPic;
    }
    public static JLabel negativeColor(URL url) {
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
                    int newNegativeRed= 255-red;
                    int newNegativeBlue=255-blue;
                    int newNegativeGreen=255-green;
                    Color newColor = new Color(newNegativeRed, newNegativeBlue, newNegativeGreen);
                    bufferedImage.setRGB(x, y, newColor.getRGB());
                }
            }
            processPic = ImageTransferring.minimizeImage(width, height, bufferedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return processPic;
    }
    public static JLabel lighter (URL url) {
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
                    int newLighterRed= (3*(red-128)+128);
                    int newLighterBlue=(3*(blue-128)+128);
                    int newLighterGreen=(3*(green-128)+128);
                    Color newColor = new Color(newLighterRed, newLighterBlue, newLighterGreen);
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
