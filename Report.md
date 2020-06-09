# Relazione tecnica

# Adventure-text Engine

# Realizzato da Giuseppe Napoletano


## 1. Introduzione

Per il corso di studi **Metodi Avanzati di Programmazione** è stato portato a termine il progetto per la realizzazione di un motore di gioco per avventure testuali. Per la sua realizzazione è stato utilizzato **Java** come linguaggio di programmazione e **Maven** come tool di automazione.

## 2. Requisiti

Il sistema deve fornire dei requisiti minimi per poter essere considerato valido. Il sistema dev'essere in grado di:
* Definire i vari elementi di un'avventura e poterla eseguire.
* Interpretare correttamente i comandi impartiti da tastiera.
* Poter eseguire diverse avventure grafiche senza dover necessariamente modificare il codice del motore.

## 3. Caratteristiche tecniche

(Descrizione strutture dati)
(Strumenti utilizzati)

## 4. System Design

## 5. OO Design
Diagramma delle classi:
![Class Diagram](doc/diagram.jpg)

## 6. Manuale dell'utente
All'avvio dell'applicazione verrà mostrato all'utente un menù da cui sarà possibile scegliere l'avventura da eseguire.

![menu](doc/menu.png)

Saranno presenti, per questa versione del motore, due opzioni: *'Play GothicGame'* e *'Load from file'*.
Selezionando la prima opzione verrà eseguita l'avventura di default pre-caricata nell'engine.
Immagine di gioco:

![game](doc/Immagine.jpg)

Altrimenti con la seconda opzione è possibile caricare un'avventura testuale personalizzata.
E' possibile riscontrare degli errori durante il caricamento da file. Il motore accetta in input file di testo *.txt* o file classe *.java* e nel caso venga fornito un formato non riconosciuto, verrà restituito un errore.

![game](doc/errore_formato.png)

Una volta selezionato un file nel formato corretto, verrà effettuato un controllo sul codice. In caso vengano rilevate inesattezze, un messaggio di errore sarà mostrato e nella console di gioco verranno forniti ulteriori dettagli come il tipo di errore e la parte di codice affetta.

![game](doc/errore_comp.png)

*(Messaggio di errore)*

![game](doc/compilatore_err.png)
*(Dettagli errore)*

### 6.1 Creare file avventura ###
Per creare una nuova avventura testuale importabile nell'engine di gioco è possibile utilizzare una classe java (*.java*) o un file di testo con codice java (*.txt*).

Le regole per programmare la propria avventuara sono identiche per entrambi i metodi.
Nel file è importante definire il package a cui fare riferimento che sarà:
+ **``package di.uniba.map.game.games;``**

Per utilizzare appieno tutte le funzionalità è importante importare le seguenti classi:

**N.B. Queste sono fondamentali per la corretta compilazione del gioco** 
+ **``import di.uniba.map.game.GameDescription;``**
+ **``import di.uniba.map.game.type.*;``**

E' comunque possibile importare ulteriori classi per aggiungere nuove funzionalità. Queste sono, ad esempio, le classi aggiuntive utilizzate per realizzare il file gioco di esempio:
+ ``import java.util.List;``
+ ``import java.util.Scanner;``
+ ``import java.util.stream.Collectors;``
