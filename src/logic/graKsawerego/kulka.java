package logic.graKsawerego;

import logic.ants.Ant;
import logic.base.classes.Drawable;
import logic.base.defaultImpl.ACObjectImpl;
import logic.base.interfaces.ACObject;
import logic.base.interfaces.SquareMesh;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class kulka extends ACObjectImpl {
    @Override
    public void NextTurn(SquareMesh currentMap) {
        int[] pos = getPosition();

        ArrayList<Ant> ants = currentMap.GetEveryACOBj(Ant.class,pos[0],pos[1]);
        if(ants.size() > 0)
        {
            currentMap.RemoveACObj(ants.get(0));
            currentMap.RemoveACObj(this);
        }

        boolean moved = currentMap.MoveACObj(this,pos[0]-1,pos[1]);
        if(!moved)
        {
            currentMap.RemoveACObj(this);
        }

    }

    public kulka()
    {
        super();
        try {
            getDrawable().setImg(ImageIO.read(new File("kulka.png")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        getDrawable().setDrawOrder(50);
    }
}
