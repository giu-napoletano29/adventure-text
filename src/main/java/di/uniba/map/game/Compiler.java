package di.uniba.map.game;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Compiler {

    public static Class<?> compiler(String fname) {

        LoadGameThread loadGameThread = new LoadGameThread(fname);

        loadGameThread.start();

        try{
            loadGameThread.join();
        } catch (InterruptedException e){
            System.out.println("Errore nel caricamento della classe");
        }

        return loadGameThread.loadedClass;
    }

    static class LoadGameThread extends Thread {

        private String fname;
        private Class<?> loadedClass;

        public LoadGameThread(String fname){
            this.fname = fname;
        }

        public void run() {
            //File gameFile = new File("target/classes/di/uniba/map/game/games/Game.java");
            //File gameFile = new File(System.getProperty("user.dir") + "/Game.java");
            File gameFile = new File(fname + "/Game.java");
            if (gameFile.getParentFile().exists() || gameFile.getParentFile().mkdirs()) {

                try {

                    /** Compilation Requirements *********************************************************************************************/
                    DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
                    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                    StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

                    // Class path usata dal compilatore.
                    List<String> optionList = new ArrayList<String>();
                    optionList.add("-classpath");
                    optionList.add(System.getProperty("java.class.path"));

                    System.out.println("Test PATH: " + System.getProperty("java.class.path"));

                    Iterable<? extends JavaFileObject> compilationUnit = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(gameFile));
                    JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList, null, compilationUnit);
                    /********************************************************************************************* Compilation Requirements **/
                    if (task.call()) {
                        /** Load and execute *************************************************************************************************/
                        // Class loader che punta alla classe compilata

                        URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(fname).toURI().toURL()});

                        System.out.println("classloader PATH: " + classLoader.getURLs()[0].toString());

                        // Caricamento classe dal nome
                        //loadedClass = classLoader.loadClass("di.uniba.map.game.games.Game");
                        loadedClass = classLoader.loadClass("Game");

                        //TODO: Eliminare Game.java e Game.class dopo il caricamento
                        /************************************************************************************************* Load and execute **/
                    } else {
                        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                            System.out.format("Error on line %d in %s%n", diagnostic.getLineNumber(), diagnostic.getSource().toUri());
                            System.out.println("Error: " + diagnostic.getCode() + " \nDiagnostic: \n" + diagnostic.toString());
                        }
                    }
                    fileManager.close();

                } catch (IOException | ClassNotFoundException exp) {
                    exp.printStackTrace();

                } catch (NullPointerException ex){
                    System.out.println("Errore: Stai eseguendo il compilatore in ambiente JRE");
                }

            }

        }


    }

}
