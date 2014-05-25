/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eduardo
 */
import jplay.Window;
import jplay.Scene;
import jplay.Keyboard;
import jplay.GameImage;
class Game {
    static Window window;
    static Scene scene;
    static Keyboard keyboard;
    static GameImage fundo;
    static void carregar(){
        window = MenuInicial.janela;
        scene = new Scene();
        scene.loadFromFile("fases/fase1.scn");
        fundo = new GameImage("images/fundo.png");
        scene.setDrawStartPos(-20, 450);
        keyboard = window.getKeyboard();
    }
    private static void executar(){
        boolean continuar = true;
        while(continuar){
            scene.draw();
            window.update();
            if(keyboard.keyDown(Keyboard.ESCAPE_KEY)){
                continuar = false;
            }
        }
    }
    private static void fechar(){
        window.exit();
    }
    public Game(){
        carregar();
        executar();
        fechar();
        System.out.println("Oi!");
    }
}
