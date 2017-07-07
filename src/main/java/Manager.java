import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;


/**
 * Created by Adi on 07-Jul-17.
 */
public class Manager extends AbstractVerticle {

    public void start(Future<Void> startFuture) {
        vertx.eventBus().consumer(Constants.PHOTO_KEY, message -> {

            vertx.eventBus().consumer(Constants.DB_ANSWER, messageFromDb -> {
                System.out.println("Received message: " +
                        messageFromDb.body());
                //sent both params to method, one from client and pne frm DB
                //get photo from DB - DBHandler
                vertx.deployVerticle(new DBHandler());
//            compareImage()
            });



        });
    }

    public boolean compareImage(File fileA, File fileB) {
        try {
            // take buffer data from botm image files //
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            // compare data-buffer objects //
            if (sizeA == sizeB) {
                for (int i = 0; i < sizeA; i++) {
                    if (dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to compare image files ...");
            return false;
        }
    }

}
