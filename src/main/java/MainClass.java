
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Created by Refael on 07-Jul-17.
 */
public class MainClass {

    public static void main (String [] args){

//        Manager manager = new Manager();
//        manager.start();
        try {
            System.out.println();
            System.out.println();
//            System.out.println(Base64.encode(extractBytes("C:\\Users\\Adi\\Desktop\\temp\\5.jpg")));

            byte[] encodedBytes = Base64.encodeBase64(extractBytes("C:\\Users\\Adi\\Desktop\\temp\\5.jpg"));
            System.out.println(new String(encodedBytes));
//            byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
//            System.out.println("decodedBytes " + new String(decodedBytes));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }
}
