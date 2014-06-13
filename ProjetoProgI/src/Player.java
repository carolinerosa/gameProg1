
/**
 *
 * @author Eduardo
 */
import jplay.Sprite;
import jplay.Sound;
import jplay.Keyboard;

public class Player extends Sprite {

    private int vidas, stateX;
    private int pontuacao;
    private Sound jumpSound, dieSound;
    Sprite[] hearts;
    Keyboard keyboard;
    final private int RIGHT, LEFT, STOP;

    public Player(int posX, int posY, int chao) {
        super("images/Mario.png", 42);
        setSequence(0,1);
        setTotalDuration(4200);
        x = posX;
        y = posY;
        vidas = 3;
        hearts = new Sprite[vidas];
        for (int i = 0; i < hearts.length; i++) {
            hearts[i] = new Sprite("images/heart.png");
        }
        keyboard = Game.keyboard;
        setGravity(0.001 * Game.window.deltaTime());
        setFloor(chao);
        RIGHT = 1;
        LEFT = 2;
        STOP = 0;
        stateX = STOP;
    }
    public Player(){
        this(0,0,800);
    }

//    public void move() {
//    moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 0.6*Game.window.deltaTime());
//        if (keyboard.keyDown(Keyboard.LEFT_KEY)) {
//            moveEsquerda();
//        } else {
//            if (keyboard.keyDown(Keyboard.RIGHT_KEY)) {
//                moveDireita();
//            } else {
//                parar();
//            }
//        }
//    }
//
//    private void moveDireita() {
//       setSequence(0, 2);
//       play();
//    }
//
//    private void moveEsquerda() {
//        setSequence(20,22);
//        play();
//    }
//
//    private void parar() {
//        setCurrFrame(0);
//    }
    public void animate(){
        this.play();
        this.update();
    }
    public int getVidas(){
        return vidas;
    }
    public int getPontuacao(){
        return pontuacao;
    }
}
