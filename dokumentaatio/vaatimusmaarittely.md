# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksella voidaan tehdä yksikertaista työaikakirjanpitoa. Ohjelma laskee käytetyn ajan, sekä antaa luoda kommentteja kirjauksille. Vanhoja kirjauksia voi katsella. Ohjelma tukee useita käyttäjiä.

## Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli _normaali käyttäjä_. Käyttäjiä voi olla useita. Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu _pääkäyttäjä_.

## Käyttöliittymäluonnos

Sovellus koostuu yhdestä näkymästä:

<img src="https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/thumbnail_20191105_132129.jpg" width="750">

Sovellus aukeaa päänäkymään, jossa vanhoja kirjauksia voit katsella tai poistaa sekä aloittaa uuden kirjauksen tekeminen. Käyttäjää voi vaihtaa.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle
  - jos käyttäjää ei olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

- käyttäjä näkee omat tehdyt kirjaukset

- käyttäjä voi poistaa vanhan kirjauksen

- käyttäjä voi aloittaa uuden kirjauksen tallentamisen

- käyttäjää voi vaihtaa

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

