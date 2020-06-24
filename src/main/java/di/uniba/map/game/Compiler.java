package di.uniba.map.game;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import di.uniba.map.game.language.LanguageSelector;

public class Compiler {

    public static Class<?> compiler(String fname, LanguageSelector language) {
        LoadGameThread loadGameThread = new LoadGameThread(fname);
        loadGameThread.start();
        try{
            loadGameThread.join();
        } catch (InterruptedException e){
            System.out.println(language.getDocument().getElementsByTagName("class_error").item(0).getTextContent());
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
            File gameFile = new File(fname + "/Game.java");
            gameFile.deleteOnExit();
            if (gameFile.getParentFile().exists() || gameFile.getParentFile().mkdirs()) {
                try {
                    DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
                    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                    StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

                    // Class path usata dal compilatore.
                    List<String> optionList = new ArrayList<String>();
                    optionList.add("-classpath");
                    optionList.add(System.getProperty("java.class.path"));

                    Iterable<? extends JavaFileObject> compilationUnit = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(gameFile));
                    JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList, null, compilationUnit);

                    if (task.call()) {
                        // Class loader che punta alla classe compilata
                        URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(fname).toURI().toURL()});
                        // Caricamento classe dal nome
                        loadedClass = classLoader.loadClass("Game");

                        try{
                            File f = new File(fname + "/Game.class");
                            f.delete();
                            f = new File(fname + "/Game.java");
                            f.delete();
                        }catch(Exception ex){
                            System.out.println("Errore nel rimuovere i file temporanei: " + ex + " (Non fatale)");
                        }

                    } else {
                        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                            System.out.format("Errore sulla riga %d in %s%n", diagnostic.getLineNumber(), diagnostic.getSource().toUri());
                            System.out.println("Errore: " + diagnostic.getCode() + " \nDiagnostica: \n" + diagnostic.toString());
                        }
                    }
                    fileManager.close();

                } catch (IOException | ClassNotFoundException exp) {
                    exp.printStackTrace();

                } catch (NullPointerException ex){
                    System.out.println("Errore: Stai tentando di eseguire il compilatore in ambiente JRE");
                }

            }

        }


    }

}
