package di.uniba.map.game.language;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LanguageSelector {

    public LanguageSelector(String language){

        this.language = language;

        if(language.equals("it")) {

            File lang = new File("strings_it.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            try{
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(lang);
                this.document = document;
            } catch (ParserConfigurationException | SAXException | IOException e){
                System.out.println("Errore nel selezionamento della lingua");
            }


        } else{
            File lang = new File("strings_en.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            try{
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(lang);
                this.document = document;
            } catch (ParserConfigurationException | SAXException | IOException e){
                System.out.println("Language selection error.");
            }
        }


    }

    private String language = null;

    private Document document = null;

    public String getLanguage(){return this.language;}

    public Document getDocument(){return this.document;}

}
