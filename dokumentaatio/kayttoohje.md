# Käyttöohje

Lataa tiedosto [Timesheet-1.0-SNAPSHOT.jar](https://github.com/jaapro-git/ot-harjoitustyo/releases/latest)

## Konfigurointi

Sovellus ei vaadi konfiguraatiota.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar todoapp.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/jaapro-git/ot-harjoitustyo/raw/master/dokumentaatio/kirjautuminen.png" width="400">

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus syötekenttään ja painamalla _login_.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista myös luoda uusi käyttäjätunnus.

Jos käyttäjä syöttää tunnuksen, jota ei ole vielä olemassa, sovellus kysyy halutaanko sellainen luoda. Käyttäjän täytyy samalla antaa käyttäjätunnusta vastaava nimi.

Kun uusi tunnus on luotu onnistuneesti, sovellus kirjautuu automaattisesti sisään uudella tunnuksella.

<img src="https://github.com/jaapro-git/ot-harjoitustyo/raw/master/dokumentaatio/uusikayttaja.png" width="400">

## Todojen luominen ja tehdyksi merkkaaminen

Onnistuneen kirjautumisen myötä siirrytään käyttäjien tekemättömät työt listaavaan näkymään.

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/k-3.png" width="400">

Näkymä mahdollistaa olemassaolevien todojen merkkaamisen tehdyksi painikkeella _done_ sekä uusien todojen luomisen kirjoittamalla syötekenttään tehtävän kuvauksen ja painamalla _create_. 

Klikkaamalla näkymän oikean ylänurkan painiketta _logout_ käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään.
