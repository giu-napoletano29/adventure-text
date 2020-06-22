package di.uniba.map.game;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadGameThread extends Thread {

    public Class<?> loadedClass;

    public void run() {

        File gameFile = new File("target/classes/di/uniba/map/game/games/Game.java");
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

                //System.out.println("Test PATH: " + System.getProperty("java.class.path"));

                Iterable<? extends JavaFileObject> compilationUnit = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(gameFile));
                JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList, null, compilationUnit);
                /********************************************************************************************* Compilation Requirements **/
                if (task.call()) {
                    /** Load and execute *************************************************************************************************/
                    // Class loader che punta alla classe compilata

                    URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("./").toURI().toURL()});

                    // Caricamento classe dal nome
                    loadedClass = classLoader.loadClass("di.uniba.map.game.games.Game");

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

            }

        }

    }


}
