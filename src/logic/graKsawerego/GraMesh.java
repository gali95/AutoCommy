package logic.graKsawerego;

import logic.base.defaultImpl.SquareMeshImpl;
import logic.base.interfaces.ACObject;

import java.util.ArrayList;

public class GraMesh extends SquareMeshImpl {

    private ACObject player;
    private int pressedKeyCode;

    //// ustawione klawisze
    private int up=38,right=39,left=37,down=40;
    private int rzut_kulka=65;

    public int getPressedKeyCode() {
        return pressedKeyCode;
    }

    public void setPressedKeyCode(int pressedKeyCode) {
        this.pressedKeyCode = pressedKeyCode;
    }

    public ACObject getPlayer() {
        return player;
    }

    public void setPlayer(ACObject player) {
        this.player = player;
    }

    public void ExecuteMovement()
    {
        if(player == null) return;
        if(pressedKeyCode == up)
        {
            int[] pos = player.getPosition();
            MoveACObj(player,pos[0]-1,pos[1]);
        }
        else if(pressedKeyCode == down)
        {
            int[] pos = player.getPosition();
            MoveACObj(player,pos[0]+1,pos[1]);
        }
        else if(pressedKeyCode == right)
        {
            int[] pos = player.getPosition();
            MoveACObj(player,pos[0],pos[1]+1);
        }
        else if(pressedKeyCode == left)
        {
            int[] pos = player.getPosition();
            MoveACObj(player,pos[0],pos[1]-1);
        }
        else if(pressedKeyCode == rzut_kulka)
        {
            int[] pos = player.getPosition();
            InsertACObj(new kulka(),pos[0],pos[1]);
        }
        pressedKeyCode = -1;
    }

    @Override
    public void NextTurn() {
        super.NextTurn();
        ExecuteMovement();
    }
}
