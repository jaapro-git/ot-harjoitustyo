# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien ytimen moudostavat sovelluslogiikkaa, eli pakkauksen [timesheet.domain](https://github.com/jaapro-git/ot-harjoitustyo/tree/master/Timesheet/src/main/java/timesheet/domain) luokkia testaavat integraatiotesti [TimesheetTest](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/Timesheet/src/test/java/TimesheetTest.java) jonka määrittelemät testitapaukset simuloivat käyttöliittymän [TimesheetService](hhttps://github.com/jaapro-git/ot-harjoitustyo/blob/master/Timesheet/src/main/java/timesheet/domain/TimesheetService.java)-olion avulla suorittamia toiminnallisuuksia.

### Testauskattavuus

<!-- Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 94% ja haarautumakattavuus 96% 

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/t-1.png" width="800">

Testaamatta jäivät tilanteet, joissa käyttäjät tai tehtävät tallettavia tiedostoja ei ole, tai niihin ei ole luku- ja kirjoitusoikeutta. -->

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

<!-- ### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla sekä OSX- että Linux-ympäristöön siten, että sovelluksen käynnistyshakemistossa on ollut käyttöohjeen kuvauksen mukainen _config.properties_-tiedosto.

Sovellusta on testattu sekä tilanteissa, joissa käyttäjät ja työt tallettavat tiedostot ovat olleet olemassa ja joissa niitä ei ole ollut jolloin ohjelma on luonut ne itse. -->

<!-- ### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/vaatimusmaarittely.md#perusversion-tarjoama-toiminnallisuus) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä. -->

<!-- ## Sovellukseen jääneet laatuongelmat

Sovellus ei anna tällä hetkellä järkeviä virheilmoituksia, seuraavissa tilanteissa
- konfiguraation määrittelemiin tiedostoihin ei ole luku/kirjoitusoikeuksia -->
