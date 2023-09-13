package pack.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is just
 * for loading image
 */
public class ImageLoader {

    /**
     * this method just
     * return buffered image
     * at specify path
     * @param path
     * @return
     */
    public static BufferedImage load(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
