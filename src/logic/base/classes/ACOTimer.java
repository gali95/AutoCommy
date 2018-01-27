package logic.base.classes;

public class ACOTimer {

    private static double deltaNextTurnTime;

    public static double getDeltaNextTurnTime() {
        return deltaNextTurnTime;
    }

    public static void setDeltaNextTurnTime(double deltaNextTurnTim) {
        deltaNextTurnTime = deltaNextTurnTim;
    }
}
