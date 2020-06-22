package di.uniba.map.game;

public class Compiler {

    public static Class<?> compiler() {

        LoadGameThread loadGameThread = new LoadGameThread();

        loadGameThread.start();

        try{
            loadGameThread.join();
        } catch (InterruptedException e){
            System.out.println("Errore nel caricamento della classe");
        }

        return loadGameThread.loadedClass;
    }

}
