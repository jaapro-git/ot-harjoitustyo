# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/timesheet_pakkauskaavio.png" width="450">

Pakkaus _timesheet.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _timesheet.domain_ sovelluslogiikan ja _timesheet.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kaksi näkymää
- kirjautuminen
- tuntikirjauslista

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _timesheetServicen_ metodeja.

Graafinen käyttöliittymä on toteutettu määrittelemällä käyttöliittymän rakenne FXML-dokumentissa _Timesheet.fxml_. FXML-dokumentissa viitataan controller-luokkaan _TimesheetFXMLController_, joka sisältää käyttöliittymän tapahtumakäsittelijät.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat [User](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/Timesheet/src/main/java/timesheet/domain/User.java) ja [TimesheetEntry](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/Timesheet/src/main/java/timesheet/domain/TimesheetEntry.java), jotka kuvaavat käyttäjiä ja käyttäjien tuntikirjauksia:

<img src="https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/timesheet_luokkakaavio.png" width="400">

Toiminnallisista kokonaisuuksista vastaa luokka [TimesheetService](https://github.com/jaapro-git/ot-harjoitustyo/blob/master/Timesheet/src/main/java/timesheet/domain/TimesheetService.java). Luokka tarjoaa kaikille käyttäliittymän toiminnoille oman metodin.

<!--TimesheetServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-3c.png" width="450"> -->

## Tietojen pysyväistallennus

Pakkauksen _timsheetapp.dao_ luokat _DbTimesheetDao_ ja _DbUserDao_ huolehtivat tietojen tallentamisesta.

Luokat noudattavat [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat onkin eristetty rajapintojen _TimehseetDao_ ja _UserDao_ taakse ja sovelluslogiikka ei käytä luokkia suoraan.

### Tietokanta

Sovellus tallettaa käyttäjien ja tuntikirjausten tiedot SQLite tietokantaan.

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

#### Käyttäjän kirjautuminen

Kun kirjautumisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja klikataan _btnLogin_ etenee sovelluksen kontrolli seuraavasti:

<img src="https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/Timesheet Login.png" width="750">

#### Uuden käyttäjän luominen

Jos annettua käyttäjätunnusta ei ole vielä olemassa, sovellus kysyy halutaanko käyttäjä luoda. Sen lisäksi sovellus pyytää käyttäjätunnusta vastaavan nimen.

<img src="https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/Timesheet New User.png" width="750">

#### Aikakirjauksen luominen

Kirjautumisen jälkeen käyttäjä voi aloittaa uuden tuntikirjauksen luomisen antamalla kommentin ja painamalla _btnStart_ -nappia.

<img src="https://github.com/jaapro-git/ot-harjoitustyo/blob/master/dokumentaatio/Timesheet New Entry.png" width="750">

<!-- ## Ohjelman rakenteeseen jääneet heikkoudet -->

