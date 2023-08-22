## Build

Requires Maven and Java 11+. 
Depends on JUnit 5 for unit-tests.
````shell
mvn clean package
````

Run JUnit tests
````shell
mvn test
````

## Annahmen

* Intervalle bestehen aus positiven Ganzzahlen <=Integer.MAX_VALUE(2147483647)
* Ungültige Eingaben können einfach ignoriert werden.
* Intervalle gelten als überlappend, wenn sie sich berühren. Wie z.B. \[0, a\] \[a, 2*a\] 

## Fragen

Laufzeit des Programms ist O(n*log(n)) und Speicherbedarf O(n), da die vollständige Eingabe sortiert wird.

Die Robustheit kann garantiert werden, indem die Menge der eingelesenen Daten begrenzt wird.
Falls das Ergebnis kleiner als die Eingabe ist, könnten entsprechend weitere Daten nachgeladen werden und die Verarbeitung wiederholt werden. 
Der verfügbare Speicher begrenzt grundsätzlich die Größe des Ergebnisses.