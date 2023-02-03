# La carte aux trésors - Carbon IT

## Notice

Le projet prends 2 arguments en paramètre, le chemin absolue du fichier d'entrée (.txt) contenant les infos de la carte et des aventurier et le chemin absolue du fichier de destination fichier de destination


## Etape de réflexion et architécture utilisée :

### Réfléxion sur les objets et héritage à adopter

J'ai choisi de séparer les objes en 3 catégories, la Map, les Aventurier et les types de Case (Trésors / Montagne)
Chacun des objet hérite de la classe abstraite AbstractRow qui permet de les sérialiser et les déserialiser.
L'idées c'est de pouvoir utiliser le type générique Case pour pouvoir en ajouter facilement dans le futur. Les nouveaux objets auront juste à hérité de Case et à implémenter les règles propres à cette case
J'ai utilisé un design pattern visiteur pour definir les actions à effectuer lorsque l'on se déplace sur la case 

### Comment gérér l'état de la carte

J'ai choisie représenter treasureMap comme état et de modifier les objets pas référence dans mes services.
J'ai choisie d'utiliser des List pour stocker mes Case et mes Adventurer pour une meilleure maintenabilité plutôt qu'une Map de Map pour réprésenter mon grid en 2D qui offre une meilleur compléxité.

### Parsing

J'ai utiliser des expressions régulières pour vérifier la cohérence du fichier d'entrée

### Test

J'ai utilisé JUnit
J'ai écrit mes tests à la fin (je n'ai pas fait de TDD)
J'ai testé mes 3 services ainsi que les expressions régulières du parsing 

## Question que je me suis posée :

-> Est-ce qu'on peut utiliser des lib externes
-> L'ordre des lignes est-t-il important dans le fichier de parsing ?
-> Contrainte sur le nom de l'aventurier
-> Comment gérer les espaces dans le parsing du fichier (Ex "    # Comment")