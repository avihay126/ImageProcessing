import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessing {


    public static JLabel colorShiftRight(BufferedImage image) {
        JLabel processPic;
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int width = newImage.getWidth();
        int height = newImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int currentPixel = image.getRGB(x, y);
                Color color = new Color(currentPixel);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color newColor = new Color(blue, red, green);
                newImage.setRGB(x, y, newColor.getRGB());
            }
        }
        processPic = ImageTransferring.minimizeImage(width, height, newImage);
        return processPic;
    }

    public static JLabel colorShiftLeft(BufferedImage image) {
        JLabel processPic;
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int width = newImage.getWidth();
        int height = newImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int currentPixel = image.getRGB(x, y);
                Color color = new Color(currentPixel);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color newColor = new Color(green, blue, red);
                newImage.setRGB(x, y, newColor.getRGB());
            }
        }
        processPic = ImageTransferring.minimizeImage(width, height, newImage);
        return processPic;
    }

    public static JLabel grayScale(BufferedImage image) {
        JLabel processPic;
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int width = newImage.getWidth();
        int height = newImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int currentPixel = image.getRGB(x, y);
                Color color = new Color(currentPixel);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int newGray = (red + green + blue) / 3;
                Color newColor = new Color(newGray,newGray,newGray);
                newImage.setRGB(x, y, newColor.getRGB());
            }
        }
        processPic = ImageTransferring.minimizeImage(width, height, newImage);
        return processPic;
    }

    public static JLabel negativeColor(BufferedImage image) {
        JLabel processPic;
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int width = newImage.getWidth();
        int height = newImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int currentPixel = image.getRGB(x, y);
                Color color = new Color(currentPixel);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int newNegativeRed = 255 - red;
                int newNegativeBlue = 255 - blue;
                int newNegativeGreen = 255 - green;
                Color newColor = new Color(newNegativeRed, newNegativeBlue, newNegativeGreen);
                newImage.setRGB(x, y, newColor.getRGB());
            }
        }
        processPic = ImageTransferring.minimizeImage(width, height, newImage);
        return processPic;
    }

    public static JLabel lighter(BufferedImage image) {
        JLabel processPic;
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int width = newImage.getWidth();
        int height = newImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int currentPixel = image.getRGB(x, y);
                Color color = new Color(currentPixel);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int newLighterRed = tooBright(red);
                int newLighterBlue = tooBright(blue);
                int newLighterGreen = tooBright(green);
                Color newColor = new Color(newLighterRed, newLighterGreen,newLighterBlue );
                newImage.setRGB(x, y, newColor.getRGB());
            }
        }
        processPic = ImageTransferring.minimizeImage(width, height, newImage);
        return processPic;
    }


    public static JLabel mirror(BufferedImage image) {
        JLabel processPic;
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int width = newImage.getWidth();
        int height = newImage.getHeight();
        for (int x = 0; x < width / 2; x++) {
            for (int y = 0; y < height; y++) {
                int currentPixel = image.getRGB(x, y);
                Color currentPixelColor = new Color(currentPixel);
                int replacePixel = image.getRGB(width - 1 - x, y);
                Color replacePixelColor = new Color(replacePixel);
                newImage.setRGB(x, y, replacePixelColor.getRGB());
                newImage.setRGB(width - 1 - x, y, currentPixelColor.getRGB());
            }
        }
        processPic = ImageTransferring.minimizeImage(width, height, newImage);
        return processPic;
    }

    private static int tooBright(int color) {
        color += 80;
        if (color > 255) {
            color = 255;
        }
        return color;
    }
}
