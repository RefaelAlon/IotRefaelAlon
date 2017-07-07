import io.vertx.core.Vertx;

/**
 * Created by Refael on 07-Jul-17.
 */
public class MainClass {

    public static void main (String [] args){

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new Manager());
        vertx.deployVerticle(new RasPiClient());


    }
}
