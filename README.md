AlgoDist
========

Choix des structures : 
RMI -> pour Jeton et Requête.
Sémaphore -> pour message au sein du même processus lourd.


java -Djava.rmi.server.codebase=file:./ -Djava.rmi.security.policy=./java.policy  MainTestClient


java -Djava.rmi.server.codebase=file:./ -Djava.rmi.security.policy=./java.policy  MainTestServer


