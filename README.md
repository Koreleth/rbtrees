# rbtrees

Repository für *brtrees* – eine Bibliothek / ein Modul zur Arbeit mit Bäumen (z. B. Binärbäume, Suchbäume, Balancierte Bäume) in Java.

---

## Rot-Schwarz-Baum

Ein Rot-Schwarz-Baum (Red-Black Tree) ist eine selbstbalancierende binäre Suchbaumstruktur.
Damit der Baum ausgeglichen bleibt, muss er bestimmte Regeln (Eigenschaften) einhalten.

🔴⚫ Regeln eines Red-Black-Trees:

1. Jeder Knoten ist entweder rot oder schwarz.
2. Die Wurzel ist immer schwarz.
3. Jedes Blatt (NIL-Knoten bzw. NULL-Zeiger) ist schwarz.
→ Diese „NIL“-Knoten sind die unsichtbaren Blätter, die keine echten Werte enthalten.
4. Wenn ein Knoten rot ist, dann sind beide seiner Kinder schwarz.
→ Es dürfen keine zwei roten Knoten direkt aufeinander folgen („keine roten Eltern-Kind-Paare“).

Für jeden Knoten gilt:
Alle Pfade von diesem Knoten zu seinen Blättern (NIL-Knoten) enthalten die gleiche Anzahl schwarzer Knoten.
→ Diese Anzahl nennt man „Black-Height“.

---

## 📖 Überblick

*rbtrees* stellt Datenstrukturen und Algorithmen bereit, mit denen man Bäume effizient verwalten, durchsuchen und manipulieren kann.  
Das Ziel ist es, robuste und wiederverwendbare Baum-Funktionen für (z. B.) Sortierbäume, Suchbäume und Balancierungsverfahren zu bieten.

Beispiele für Einsatzbereiche:

- Einfügen / Löschen in Bäumen  
- Suchen / Traversieren  
- Balancierung (AVL, Rot-Schwarz, etc.)  
- Spezialfunktionen wie Baumrotationen, Subbaum-Operationen  

---

## 🏗️ Projektstruktur

```text
.
├── src/ # Quellcode der Baumklassen und Algorithmen
├── tests/ # Unit-Tests für Baumoperationen
├── examples/ # Beispielprogramme zur Demonstration
├── docs/ # Dokumentation (falls vorhanden)
└── README.md
```

---

## ✨ Funktionen & Features

Hier einige der typischen Funktionen, die *rbtrees* bereitstellt oder bereitstellen könnte (bitte anpassen):

- Baum-Erzeugung (leerer Baum, aus Liste / Array)  
- Einfügen eines Schlüssels / Werts  
- Löschen eines Schlüssels / Knotens  
- Suche nach Schlüssel / Wert  
- In-Order, Pre-Order, Post-Order Traversierung  
- Balancierungsmethoden (AVL, Rot-Schwarz)  
- Rotationen (links, rechts)  
- Höhen-/Tiefe-Ermittlung  
- Subbaum-Operationen (Teilen, Zusammenführen)  

---

## 🛠️ Installation & Nutzung

So kannst du *rbtrees* in deinem Projekt verwenden:

1. Klone das Repository:
   ```bash
   git clone https://github.com/Koreleth/brtrees.git
   cd brtrees
   ```

(Optional) Baue das Projekt, falls ein Build-Prozess vorhanden ist (z. B. mit Maven / Gradle / Make / Setup-Skript)

Importiere die Bibliothek in dein eigenes Projekt und verwende die Klassen, z. B.:
```java
// Beispiel in Java
Tree<Integer> myTree = new BinarySearchTree<>();
myTree.insert(5);
myTree.insert(3);
myTree.insert(7);
boolean exists = myTree.contains(3);
```
