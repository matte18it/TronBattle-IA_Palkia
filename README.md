# ⚪ IA Tron Battle - Gruppo Palkia ⚪
<div align="center">
  <img src="https://github.com/matte18it/TronBattle-IA_Palkia/blob/main/TronBattle/src/main/resources/githubIMG/palkia.gif?raw=true" alt="Palkia">
</div>
<div align="justify">
  Benvenuti al progetto IA Tron Battle del Gruppo Palkia! Questo progetto mira a sviluppare un'intelligenza artificiale (utilizzando Java e ASP) per competere nel gioco Tron Battle. Il nostro obiettivo è creare un sistema intelligente capace di prendere decisioni rapide e strategiche per sfidare avversari umani e altre IA.
</div>

## 🌃 Ambiente 🌃
Nel gioco Tron Battle, l'ambiente in cui opera il nostro agente basato sull'utilità ha le seguenti caratteristiche:
<ol>
  <li><strong>Totalmente Osservabile:</strong> perchè l'agente può osservare l'intero stato del gioco, inclusa la posizione degli altri giocatori e le tracce lasciate dalle moto.</li>
  <li><strong>Ambiente Multiagente Competitivo:</strong> perchè le azioni di un giocatore influenzano direttamente le possibilità di vittoria degli altri.</li>
  <li><strong>Strategico:</strong> perchè l'ambiente è deterministico tranne che per le azioni degli altri agenti.</li>
  <li><strong>Discreto:</strong> perchè le azioni sono limitate e il tempo è diviso in passi discreti.</li>
  <li><strong>Noto:</strong> le regole del gioco sono ben definite e note a tutti i giocatori. Non ci sono elementi nascosti o incognite sulle meccaniche di gioco.</li>
  <li><strong>Dinamico:</strong>  il campo di gioco cambia continuamente poiché le moto si muovono e lasciano tracce dietro di sé. Questi cambiamenti devono essere costantemente monitorati e gestiti dai giocatori.</li>
  <li><strong>Sequenziale:</strong> le decisioni prese in ogni momento influenzano le opzioni disponibili in futuro. Ad esempio, una mossa che lascia la moto in una posizione rischiosa può limitare drasticamente le scelte successive.</li>
</ol>

## 🤖 Funzionamento Strategia 🤖
<div align="justify">
   <strong>Step Base: </strong> come prima cosa, una volta presa la posizione del player, la nostra IA resetta le variabili utili per il suo funzionamento e successivamente chiude le celle presenti dietro di lei.
  <br><br>
  <strong>Step 1: </strong> una volta fatto ciò parte la strategia di gioco vera e priopria. La nostra IA verifica se si trova in una zona chiusa o aperta, se si trova in una zona chiusa viene attivato il modulo di ottimizzazione dello spazio per riuscire a sopravvivere il più a lungo possibile. 
Il modulo di ottimizzazione (scritto in ASP) prende le prossime celle 'legali' in cui il player può andare e successivamente, tramite 4 constraint, indica la cella migliore verso cui andare per ottimizzare al meglio lo spazio. I weak impongono che:
<ol>
  <li>L'IA deve cercare di andare verso una cella che gli permette di non chiudersi in un vicolo cieco.</li>
  <li>L'IA deve cercare di andare verso la cella con meno celle adiacenti.</li>
  <li>L'IA deve cercare di andare verso la cella che ha meno celle adiacenti a una certa profondità.</li>
  <li>L'IA deve cercare di mantenersi vicino ai bordi.</li>
</ol>
<strong>Step 3: </strong> se l'IA, invece, verifica che si trova in un ambiente aperto e non ha già un path da seguire (calcolato precedentemente), allora fa un check per verificare attualmente il nemico migliore da attaccare. Per trovare il nemico migliore da attaccare:
<ol>
  <li>La IA si concentra sui nemici che riesce a raggiungere.</li>
  <li>Successivamente verifica che i nemici che riesce a raggiungere non siano tutti vicini. Se sono tutti vicini, li ignora e si attiva il modulo di ottimizzazione dello spazio per sopravvivere il più a lungo in attesa di avere un nemico da attaccare.</li>
  <li>Se i nemici raggiungibili sono tutti lontani tra loro (o comunque è presente un nemico che è più lontano rispetto agli altri), utilizzo ASP per decretare il nemico migliore da attaccare. Il nemico migliore da attaccare tra quelli raggiungibili viene decretato secondo alcuni criteri:</li>
  <ul>
    <li>La IA cerca di prendere il nemico che è più lontano dagli altri, quindi la cui somma delle distanze verso tutti i nemici è più alta.</li>
    <li>La IA cerca di prendere il nemico attualmente più vicino a lei.</li>
    <li>La IA cerca di prendere il nemico che ha meno spazio libero.</li>
    <li>La IA cerca di prendere il nemico che è più vicino ad un bordo.</li>
  </ul>
</ol>
<strong>Step 4:</strong> una volta stabilito il nemico verso cui dirigersi, verifica se la distanza tra lei e il nemico è minore di un certo valore fissato, se è maggiore allora la IA cerca di avvicinarsi. La strategia di avvicinamento al nemico viene fatta utilizzando un modulo ASP che decreta la miglior cella presente nel suo campo di visione per far si che venga decretato un cammino minimo verso quella cella che porta la IA a raggiungere il nemico nel minor tempo possibile. Come prima cosa, vengono prese le celle sulla stessa X e sulla stessa Y della IA (presenti nel suo campo di visione ad una certa profondità) e vengono passate al modulo ASP. Il modulo ASP decreta la miglior cella attraverso una serie di parametri che sono:
<ol>
  <li>La IA deve cercare di scegliere una cella che non la porta in un vicolo cieco.</li>
  <li>La IA deve cercare di scegliere la cella più vicina al nemico che vuole attaccare.</li>
  <li>La IA deve cercare di scegliere la cella con più spazio adiacente.</li>
</ol>
Una volta decretata la cella migliore verso cui andare, viene calcolato il path minimo verso quella cella attraverso l'algoritmo A* e la IA segue quel path finchè non arriva a destinazione (verificando ad ogni passo che il cammino trovato rimanga valido, altrimenti viene ricalcolato).
<br><br>
<strong>Step 5:</strong> se la distanza tra la IA e il nemico è minore del valore fisso, allora la IA verifica se è presente un path di chiusura (calcolato attraverso un algoritmo di chiusura che utilizza A*) per determinare se esiste un path in grado di chiudere il nemico verso cui sto combattendo in una zona con meno spazio rispetto a quello della IA stessa. Se esiste questo path allora lo segue e chiude il nemico, altrimenti parte il modulo di attacco (scritto in ASP) che permette alla IA di avvicinarsi al nemico secondo alcuni criteri:
<ol>
  <li>La IA deve cercare di scegliere una cella tra quelle a lei successiva che non la porta in un vicolo cieco.</li>
  <li>La IA deve cercare di scegliere una cella tra quelle a lei successiva che le permette di avvicinarsi il più possibile al nemico contro cui sta combattendo.</li>
  <li>La IA deve cercare di scegliere una cella tra quelle a lei successiva che ha più spazio libero adiacente.</li>
</ol>

⚠️ La strategia viene poi ripetuta in loop fino alla fine della partita ⚠️

## 🔍 Ricerca 🔍
<div align="justify">
L'algoritmo A* viene utilizzato per calcolare il percorso ottimale tra due punti. Questo algoritmo considera sia il costo per raggiungere il nodo attuale sia una stima euristica del costo per raggiungere il nodo finale, garantendo così la determinazione del percorso più efficiente. Nel nostro progetto, A* viene impiegato per determinare il percorso minimo verso la cella target e per calcolare i path di chiusura.
</div>
<br>
<div align="justify">
BFS è utilizzato per esplorare le celle circostanti in modo sistematico. Questo algoritmo esplora tutti i nodi al livello corrente prima di passare ai nodi al livello successivo. Nel nostro progetto, BFS è utilizzato per calcolare le distanze tra le celle, identificare celle adiacenti e indirettamente collegate, e per altre operazioni di analisi dello spazio.
</div>

## ❗️ Disclaimer ❗️
<div align="justify">
  L'IA è stata sviluppata come progetto universitario del corso "Intelligenza Artificiale" del Dipartimento di Matematica e Informatica (DeMaCS) dell'Università della Calabria. Il progetto consiste nel sviluppare 4 IA, una per gruppo (4 gruppi da 2 persone ciascuno), utilizzando ASP e Java (collegando i moduli ASP a DLV2 con EmbASP) con l'obiettivo finale di farle competere tra loro nel famoso gioco Tron Battle. Inoltre, per far funzionare il progetto è necessario fare, se avviato in Intellij, tasto destro sulle librerie "antlr-4.7-complete" e "embASP" contenute nella cartella "lib" del progetto e poi cliccare sulla voce "Add as library...".

</div>
