# Bibliotekshanteraren

Det här är ett enkelt konsolprogram i Java för att hantera ett bibliotek. Man kan lägga till böcker och medlemmar, låna och lämna tillbaka böcker, samt söka i biblioteket.

## Vad programmet kan göra

1. **Lägga till en bok:** Spara ny bok med ISBN, titel och författare.
2. **Registrera medlem:** Skapa ny medlem med ID och namn.
3. **Låna bok:** Koppla en bok till en medlem. En bok kan inte lånas om den redan är utlånad, och en medlem får max låna 3 böcker samtidigt.
4. **Lämna tillbaka bok:** Gör boken ledig igen.
5. **Söka efter bok:** Sök på titel eller författare (spelar ingen roll med stora/små bokstäver).
6. **Visa alla böcker:** Se alla böcker och om de är lediga eller vem som har lånat dem.

## Hur koden är uppbyggd

* **`Book` (record):** Används för böcker eftersom informationen om en bok (ISBN, titel, författare) aldrig ändras när boken väl är skapad. Det var smidigt att använda `record` för att slippa skriva massa extra kod.
* **Record vs. Klass:** Jag valde `record` för `Book` eftersom en boks data (ISBN, titel, författare) är oföränderlig (*immutable*) när den väl skapats. Det gör koden renare och minskar risken för buggar. 
* För `Member` valde jag en vanlig `class` eftersom en medlems tillstånd ändras över tid (antalet aktiva lån ökar och minskar)
* **`Member` (klass):** Används för medlemmar eftersom antalet aktiva lån ändras när man lånar eller lämnar tillbaka böcker.
* **`Library` (klass):** Håller koll på alla böcker och medlemmar i vanliga arrayer. Den håller också koll på vilka böcker som är utlånade till vem.
* **`LibraryApp` (klass):** Innehåller menyn och loopen som körs tills man stänger av programmet.

## Hur man kör programmet

1. Öppna projektet i din IDE (t.ex. IntelliJ IDEA).
2. Kör `LibraryApp.java`.
3. Följ instruktionerna i menyn i terminalen.