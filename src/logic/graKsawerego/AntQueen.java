package logic.graKsawerego;

import logic.ants.Ant;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AntQueen extends Ant {

    public AntQueen() {
        super();
        try {
            img = ImageIO.read(new File("antqueen.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
