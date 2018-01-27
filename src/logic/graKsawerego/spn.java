package logic.graKsawerego;

import logic.base.defaultImpl.ACObjectImpl;
import logic.base.interfaces.SquareMesh;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class spn extends ACObjectImpl {
    @Override
    public void NextTurn(SquareMesh currentMap) {

    }

    public spn()
    {
        super();
        try {
            getDrawable().setImg(ImageIO.read(new File("spn.png")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        getDrawable().setDrawOrder(40);
    }
}
