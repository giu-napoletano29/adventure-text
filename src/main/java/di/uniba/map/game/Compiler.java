package di.uniba.map.game;

import di.uniba.map.game.Engine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Compiler {

    public static Class<?> compiler() {
        /*
        StringBuilder sb = new StringBuilder(64);
        sb.append("package resources;\n");
        sb.append("public class HelloWorld implements di.uniba.map.game.Compiler.DoStuff {\n");
        sb.append("    public void doStuff() {\n");
        sb.append("        System.out.println(\"Hello world\");\n");
        sb.append("    }\n");
        sb.append("}\n");*/

        //File gameFile = new File("resources/HelloWorld.java");
        //File gameFile = new File("src/main/java/di/uniba/map/game/games/Game.java");
        File gameFile = new File("target/classes/di/uniba/map/game/games/Game.java");
        Class<?> loadedClass = null;
        if (gameFile.getParentFile().exists() || gameFile.getParentFile().mkdirs()) {

            try {
                /*
                Writer writer = null;
                try {
                    writer = new FileWriter(helloWorldJava);
                    writer.write(sb.toString());
                    writer.flush();
                } finally {
                    try {
                        writer.close();
                    } catch (Exception e) {
                    }
                }*/

                /** Compilation Requirements *********************************************************************************************/
                DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

                // This sets up the class path that the compiler will use.
                // I've added the .jar file that contains the DoStuff interface within in it...
                List<String> optionList = new ArrayList<String>();
                optionList.add("-classpath");
                optionList.add(System.getProperty("java.class.path"));

                System.out.println("Test PATH: " + System.getProperty("java.class.path"));

                Iterable<? extends JavaFileObject> compilationUnit = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(gameFile));
                JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList, null, compilationUnit);
                /********************************************************************************************* Compilation Requirements **/
                if (task.call()) {
                    /** Load and execute *************************************************************************************************/
                    //System.out.println("Yipe");
                    // Create a new custom class loader, pointing to the directory that contains the compiled
                    // classes, this should point to the top of the package structure!

                    URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("./").toURI().toURL()});

                    URL[] urlss = (classLoader.getURLs());

                    for(URL urla: urlss){
                        System.out.println("test: " + urla.getFile());
                    }
                    // Load the class from the classloader by name....
                    loadedClass = classLoader.loadClass("di.uniba.map.game.games.Game");
                    // Create a new instance...
                    Object obj = loadedClass.newInstance();

                    /************************************************************************************************* Load and execute **/
                } else {
                    for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                        System.out.format("Error on line %d in %s%n", diagnostic.getLineNumber(), diagnostic.getSource().toUri());
                        System.out.println("Error: " + diagnostic.getCode() + " \nDiagnostic: \n" + diagnostic.toString());
                    }
                }
                fileManager.close();

            } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException exp) {
                exp.printStackTrace();

            }

        }
        return loadedClass;
    }

}
