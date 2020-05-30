package di.uniba.map.game.games;

import di.uniba.map.game.type.*;
import di.uniba.map.game.GameDescription;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GeneralGame extends GameDescription{
    @Override
    public void init() throws Exception {

    }

    @Override
    public boolean isWin() {
        return false;
    }

    @Override
    public boolean isLose() {
        return false;
    }
}
