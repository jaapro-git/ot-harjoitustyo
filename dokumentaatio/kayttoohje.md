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

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus syötekenttään ja painamalla _Login_.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista myös luoda uusi käyttäjätunnus.

Jos käyttäjä syöttää tunnuksen, jota ei ole vielä olemassa, sovellus kysyy halutaanko sellainen luoda. Käyttäjän täytyy samalla antaa käyttäjätunnusta vastaava nimi.

Kun uusi tunnus on luotu onnistuneesti, sovellus kirjautuu automaattisesti sisään uudella tunnuksella.

<img src="https://github.com/jaapro-git/ot-harjoitustyo/raw/master/dokumentaatio/uusikayttaja.png" width="400">

## Aikakirjausten luominen

Kun käyttäjän aikakirjaukset ovat näkyvissä, hän voi aloittaa uuden kirjauksen luomisen painamalla _Add_.

<img src="https://github.com/jaapro-git/ot-harjoitustyo/raw/master/dokumentaatio/lisaauusi.png width="400">

Tämän jälkeen käyttäjä syöttää aikakirjausta vastaavan kommentin tekstikenttään ja painaa _Start_, jolloin aikakirjaus tallentuu siihen ja käyetyn ajan mittaaminen alkaa. 

## Aikakirjausten lopettaminen

Kun käyttäjä haluaa lopettaa aikakirjauksen, hän valitsee taulukosta haluamansa rivin ja painaa _Complete_, jolloin ajan mittaaminen keskeytyy ja kirjaukseen käytetty aika lasketaan kokonaisina tunteina (pyöristettynä ylöspäin).

<img src="https://github.com/jaapro-git/ot-harjoitustyo/raw/master/dokumentaatio/lopeta.png width="400">
                                                                                                       
## Aikakirjausten vieminen Exceliin

Kaikki aikakirjaukset voi viedä Excel tiedostoksi (xlsx) painamalla _Export to Excel_. Tällöin sovellus luo uuden tiedoston käyttäjän kotihakemistoon.

<img src="https://github.com/jaapro-git/ot-harjoitustyo/raw/master/dokumentaatio/export.png width="400">

