package clases;
import utils.Element;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Food extends Element{
    protected boolean vivo;
    protected int tiempoVida;
    protected Timer contador;
    protected TimerTask task;
    private boolean pauseContador;
    
    public Food(){
        super();
        vivo = true;
        pauseContador = false;
        tiempoVida = 0;
    }
    
    public Food(int positionInRow, int positionInColumn){
        super(positionInRow, positionInColumn);
        vivo = true;
        pauseContador = false;
        tiempoVida = 0;
    }
    
    protected void activarTimer() {
        contador = new Timer();
        task = new TimerTask(){
            public void run(){
                if(!pauseContador) {
                    if(tiempoVida > 0) {
                        tiempoVida = tiempoVida - 1;   
                    }else{
                        vivo = false;
                        contador.cancel();
                    }
                }
            }
        };
        contador = new Timer();
        contador.schedule(task, 1000, 1000);
    }
    
    public boolean vivo() {
        return vivo;
    }
    
    public void kill() {
        vivo = false;
        if (contador != null) {
            contador.cancel();
        }
    }
    
    public void pauseContador() {
        pauseContador = true;
    }
    
    public void resumeContador() {
        pauseContador = false;
    }
    
    public abstract void interactuar(Snake snake);
}


