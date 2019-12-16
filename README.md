# Timesheet sovellus

Sovelluksen avulla käyttäjät voivat ylläpitää tuntikirjauksia. Jokainen käyttäjä näkee vain omat kirjauksensa. Kirjaukset tallentuvat SQLite-tietokantaan.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testaus](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Käyttöohje](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

## Releaset

[Viikko 5](https://github.com/jaapro-git/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _OTimesheet-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/Timesheet/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_


<!-- # Ohjelmistotuotanto syksy 2019

Kurssin git-hub repo löytyy *tästä* kansiosta.

## Työaikakirjanpito
[kirjaukset](https://github.com/jaapro-git/ot-harjoitustyo/master/dokumentaatio/tyoaikakirjanpito.md)

## Viikko1
[gitlog](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/laskarit/viikko1/gitlog.txt)</br>
[tree](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/laskarit/viikko1/komentorivi.txt)

## Viikko2
[screenshot](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/laskarit/viikko2/screenshot.png)</br>
[vaatimusmäärittely](https://github.com/jaapro-git/ot-harjoitustyo/master/dokumentaatio/vaatimusmaarittely.md)

## Viikko3
[monopoli](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/laskarit/viikko3/monopoli.png)</br>
[Machine](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/laskarit/viikko3/Machine.png)</br>
[HSL](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/laskarit/viikko3/HSL.png)</br>
-->
