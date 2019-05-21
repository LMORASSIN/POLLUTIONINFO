# POLLUTIONINFO

j'utilise un service WebService, 2 fragments (1 pour les 2 boutons de recherches et le champ de saisie de la ville et un pour les détails retournés par l'API)
La recherche par nom et la recherche par localisation fonctionnent.
L'application crache lorsque l'on change l'orientation de l'écran à cause du fragment des détails mais je n'ai pas eu le temps de chercher pourquoi.
J'ai également une classe Tools qui fait le traitement métier de la réponse Web (traitement du JSON) et une classe Gps qui gère la partie localisation.
Je n'ai pas eu le temps d'écrire des tests ni de sécuriser le token de l'API.

Description: Android Application for check pollution level in city or in current location
Author: MORASSIN Laurent
