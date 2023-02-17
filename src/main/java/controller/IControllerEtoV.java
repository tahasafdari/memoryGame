package controller;

import model.MemoryObject;

import java.util.ArrayList;

public interface IControllerEtoV {
    void clearPair(ArrayList<Integer> storage);
    void setEasyGame(ArrayList<MemoryObject> memoryObjects);
    void setMediumGame(ArrayList<MemoryObject> memoryObjects);
    void setHardGame(ArrayList<MemoryObject> memoryObjects);

    void gameOver();

}
