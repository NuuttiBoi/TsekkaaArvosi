# TsekkaaArvosi

### Käyttöohjeet:

## 1. Etusivu:
Käyttäjän avatessa sovelluksen hän näkee ensimmäisenä etusivun, joka on esitetty oheisessa kuvassa. 

![Kuva 1](https://github.com/NuuttiBoi/TsekkaaArvosi/blob/main/TsekkaaArvosi/images/TsekkaaArvosi_kuva1.png)

Etusivulla on eri painikkeita, joista pääsee kirjaamaan verensokeri, verenpaine ja
veren happipitoisuus mittausarvoja sovelluksen muistiin, joita voi seurata graafisesti. Sivulta löytyy myös asetukset painike, joka vie asetuksiin.

## 2. Arvojen kirjaus
Sovelluksessa löytyy omat aktiviteetit kaikkien arvojen kirjaamiselle. Seuraavassa kuvassa on esitetty esimerkki yhdestä arvojen kirjauksen aktiviteetista.

![Kuva 2](https://github.com/NuuttiBoi/TsekkaaArvosi/blob/main/TsekkaaArvosi/images/TsekkaaArvosi_kuva2.png)

Verenpaineen kirjausaktiviteetissa voi kirjata ylös ylä- ja alaverenpaineen sekä sykkeen. Päivämäärä ja kellonaika ovat mukana, jotta voi kronologisesti seurata milloin on ottanut mittaukset. 
Takaisin painikkeella pääsee etusivulle, tallenna painike tallentaa antamasi arvot ja seuranta painikkeella pääsee näkemään graafin antamistaan arvoista.  

## 3. Asetukset
Sovellukseen kuuluu myös asetukset aktiviteetti, jossa käyttäjä voi valita muun muassa tumman teeman. Asetusaktiviteetti esitetty kuvassa alhaalla.

![Kuva 3](https://github.com/NuuttiBoi/TsekkaaArvosi/blob/main/TsekkaaArvosi/images/TsekkaaArvosi_kuva3.png)

Asetukset sivulta löytyy ilmoitukset nappi, joka vie ilmoitukset sivulle, jossa näkyvät kaikki muistutukset, joita olet laittanut kalenteriin ylös. Teema napista pääsee vaihtamaan teeman vaaleaksi tai tummaksi. 
Omat tiedot napin takaa löytyy kohta, johon voi kirjata oman nimen ja iän. Sen tarkoitus on näkyä asetukset sivulla omat tiedot napin alapuolella.

## 4. Kalenteri
Kalenteriaktiviteetissa käyttäjä voi valita kalenterinäkymästä haluamansa päivän, ja asettaa siihen muistutuksen esimerkiksi verenpaineen mittauksesta.
Muistutuksen lisäämisestä kerrotaan enemmän seuraavassa kappaleessa.

![Kuva 4](https://github.com/NuuttiBoi/TsekkaaArvosi/blob/main/TsekkaaArvosi/images/TsekkaaArvosi_kuva4.png)

Yllä esimerkkikuva kalenteriaktiviteetista. Kalenterin ylävasemmalla olevasta punaisesta pallosta näkee muistutusten määrän ja sitä painamalla pääsee ilmoitukset sivulle, jossa näkyy kaikki muistutukset. 
Pallosta näkyy vain tulevien muistutusten määrä, menneet muistutukset poistuvat järjestelmästä automaattisesti. Tehdyn muistutuksen voi myös poistaa.


## 5. Muistutusten Lisääminen
   Sovelluksessa voi lisätä mittausmuistutuksia tietylle päivälle tiettyyn kellonaikaan. Muistutuksen lisäämisen aktiviteetista kuva ohessa.
   
![Kuva 5](https://github.com/NuuttiBoi/TsekkaaArvosi/blob/main/TsekkaaArvosi/images/TsekkaaArvosi_kuva5.png)

Muistutuksen lisäys sivulla voi lisätä haluamalleen mittaukselle muistutuksen. Tällä sivulla voi vaihtaa päivämäärän sille päivälle mille haluaa muistutuksen. Käyttäjä voi valita, haluaako hän muistutuksen verenpaineen, sykkeen, verensokerin tai happisaturaation mittaamisesta, tai myös useampia voi valita.
Lisätietoja- kohtaan käyttäjä voi kirjoittaa esimerkiksi ohjeita itselleen.

## 6. Graafit
Jokaiselle arvolle löytyy oma graafi, josta käyttäjä voi nähdä minä päivänä hän on syöttänyt mitkäkin arvot, miten ne vertautuvat yleisiin viitearvoihin, sekä käyttäjän syöttämien arvojen keskiarvon.

![Kuva 6](https://github.com/NuuttiBoi/TsekkaaArvosi/blob/main/TsekkaaArvosi/images/TsekkaaArvosi_kuva6.png)

Yllä esitetyssä kuvassa esimerkki sovelluksen graafiaktiviteetista. Y-akselilla näkyvät verensokerin arvot sekä niiden yksikkö. X-akselilla näkyy mittauksen päivämäärä. 
Liian korkea ja liian matala- viivat kertovat verensokerin yleiset viitearvot, ja niiden perusteella käyttäjä voi vertailla omia vertaustuloksiaan niihin. Vasemmassa yläkulmassa näkyy myös käyttäjän syöttämien arvojen keskiarvo. 
Graafeissa virhetilanne syntyy, jos käyttäjä syöttää arvon, mikä ei ole graafin määriteltyjen arvojen joukossa. 
Tämä estetään kuitenkin sillä, että kirjausaktiviteeteissa käyttäjän syöttämiä arvoja ei hyväksytä, jos ne eivät kuulu määriteltyjen lukujen joukkoon.



