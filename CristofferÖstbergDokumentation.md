# Cristoffer Östberg

- [Planering](#planering)
- [Arbetet och dess genomförande](#arbetet-och-dess-genomförande)
- [Egna reflektioner](#egna-reflektioner)


## Projekt - BumbiBearSpringApp

### Beskrivning av projektet
En SpringBoot Apache Kafka Java-applikation, som låter användaren (via IDE-konsolen) både producera och visa data av en specifik typ, art och fullständigt namn på en BumbiBear. Alla data bearbetas genom Apache Kafka som en integrationsplattform, därför sparas all data i Apache Kafka localhost broker på port 8080. Datan som matas in går också genom ett webb-API (HTTP) som från början användes i detta program genom postman med hjälp av POST-begäran, men implementerades senare i detta program för att hantera indata istället av ett Http-protokoll och ett konsol-UI.

Datan som skickas genom kafka sparas också i både en lokal mySQL-värd på standardport 3306, och antingen mongoDB Atlas remote med din anslutningssträng eller lokala mongoDB på standardport 27017 om det första valet inte fungerar. Datan sparas även lokalt i appen i .Txt-filen bredvid där denna Readme-fil finns.

Allt sparas samtidigt med ett enda val och inmatning från användargränssnittet i konsolen.
Applikationen har även TDD (testdriven utveckling) i testklasser kopplade till variabelklasser i appen. De kan köras separat.

### Vad du har gjort
Emellan 2023-08-14 --> 2023-10-13 ca) har följt föreläsningar samt vidareutvecklat detta program vidare på egen hand med hjälp av tutorials om SpringBoot.
Specifikt användes föreläsning ifrån skola Marcus lektioner, samt guider ifrån Javaguides.net (https://www.javaguides.net/2022/06/spring-boot-apache-kafka-tutorial.html) rörande både hur man bygger upp en Apache Kafka applikation med mySQL som huvudserver och MongoDB (https://www.javaguides.net/2019/12/spring-boot-mongodb-crud-example-tutorial.html)
Javaguides är en väldigt omfattande sida, med många tutorials och som ger baskunskap om mycket inom just SpringBoot, så mycket av guiderna och kunskapen och lärdomen användes därifrån. Även chatGPT användes såklart för viss debugging och när man inte förstod vad man gjort fel- och inte, och behövde hjälp med viss implementering och hantering av ens specifika idéer.


## Planering

### Lösningsförslag innan uppgiften påbörjas
Klura ut hur man använder både en mySQL-server samt en MongoDB-server samtidigt, och även en .Txt-fil lokalt i programmet i olika consumers för att få de att fungera tillsammans, utan problem, var lite klurigt.
Testa olika implementationer med att först få in MongoDB i programmet, lära sig hur den anslutningen och connection fungerar emot kafka, separat, för att lära sig den. MySQL hade vi redan lärt oss tidigare med hjälp av JavaGuides.net samt Marcus föreläsningar. 

Hur man sparar data in till en .Txt tar jag hjälp av internet av med hjälp av tex fileWriter vilket jag använt i ett annat separat spel-projekt jag håller på med.

#### Hur du tänker försöka lösa uppgiften.(exempelvis)
Börjar med att ungefär halvvägs in i föreläsningar och kursen, tänka i stort hur jag vill ha min applikation och utvecklar/gör om grundkoncept vi haft tidigare efter föreläsningar (för det är svårt att utveckla en färdig applikation innan man ens har fått grundkunskaperna hur man skall utveckla applikationen) därför känns bäst (om än kanske lite extra stress-påslag) men pga att vi hade ganska så lång tid på oss i just detta projekt/utmaning så kändes det bäst att ändå vänta lite tills man hade varierande olika kunskaps-bitar inom olika delar och sedan bygga ihop de likt lego och sätta ihop det lego-hus man till slut ville ha.

#### Pseudokod.(exempelvis)
Hade ingen specifik, eller Github Project denna gång eller liknande mer bättre logg jag följde utan jag hade till detta inlämningsprojekt en lokal text-fil i denna app vilken går att se bredvid denna fil SAKER KVAR ATT GÖRA I APP där man kan se grundläggande implementering jag har haft, och velat implementera och hur det har gått med detta.

## Arbetet och dess genomförande

### Vad som varit svårt
Få till programmet att fungera simultant tillsammans med 2 olika databaser (3 consumers) där vi bara hade lärt oss via föreläsningar en typ (mySQL) databas tidigare. Det var lite lurigt, men efter diverse problemlösningar, många pauser och träningsbreak, meditation, god mat och tänk att aldrig ge upp så gav det sig detta också.
### Beskriv lite olika lösningar du gjort
* Testade först att försöka få ihop båda databaser med endast en Entity/payload-klass, som skulle hantera data sparad till båda databaser, men insåg sedan att det skulle inte gå för MongoDB har andra Id-värden som genereras automatiskt in till sig, och gillar inte Long-värde bla och hur man annoterar Entity-klassen som krävs för MySQL där Primary Key är som Id-värdet som genereras in till databasen automatiskt. Detta krånglade till mycket, och jag kom inte på hur jag skulle komma runt det.
* Kom sedan till insikt av olika anledningar att det var fel/man inte skulle ha endast en Entity-klass, utan man måste ju såklart göra en till, och även göra ett till Mongo-Repository-Interface som hanterar detta. När jag väl fick ordning på det och separerade detta blev allting genast väldigt mycket mer logiskt, och jag kunde kapa ned koden signifikt för att få den lätt att förstå och lätt att läsa med. 
* fetch-paketet är ett helt unikt paket där visande- och display av datan hanteras för att visa data enligt konsolval i konsolen på val nummer 2. Denna implenterades som en av de sista i projektet, för att försöka få den att visa data samt även den hålla så kort och liten och enkel att visa med.

* Detta är endast exempel på flertal olika lösningar med olika klasser/package:s konstruerade.


Detta har varit ett av mina grund- och drivmål i detta projekt med. Även om jag nu komplicerar detta projekt med fler databaser etc och mer implementeringar, så skall inte koden bli mer avancerad för det.
### Beskriv något som var besvärligt att få till
Kan bara referera till rubrik Vad som varit svårt ovanför. Få till flera olika databaser/consumers i samma program var lite rörigt när man inte visste helt vad man/hur man skulle göra det, men till slut så gick det.

### Beskriv om du fått byta lösning och varför i sådana fall
Entity-klasserna och bara ha en sådan då man har 2st olika databaser var ju helt fel tills den dag då det slog mig och separera detta var helt rätt. Om ärligt, så skulle vilja ha en lektion i modulhantering tidigare, och bygga ett projekt ifrån grunden mha moduler, hade nog blivit lite enklare.




## Egna reflektioner

### Vad gick bra
Att få ihop grundidén med att faktist bara inte ha en databas, utan ha flera och även en .Txt-fil, gjorde det hela lite roligt och mer spännande och utmanande att få till.

### Vad gick dåligt
Den första lösningen, som beskrivet ganska omfattande här ovan. Den blev bara rörig, och kände blev mer och mer kaotisk till slut. Kom sedan efter påkommet med fler Entity-klasser och börja om med det och skriv det bra mycket enklare och finare. Känns jättebra.

### Vad har du lärt dig
Implementera fler databaser, skriva app:ar ännu kortare och mer lättlästa. Jag tycker jag själv (iallafall) har försökt, och lyckats ganska bra med att banta ned just denna applikation till något mycket enklare, och mer lättläst och överskådligt så den känns väldigt simpel och lättläst.

### Vad hade du gjort annorlunda om ni gjort om projektet
Troligtvis velat haft en lektion om just modul-hantering och byggande och felsökande av det i IDE tidigare, och försökt implementera det på egen hand tidigare. Inte för att det på något sätt är nödvändigt, men det gör koden mycket mer lättläst och mer DRY och rekreativ etc.

### Vilka möjligheter ser du med de kunskaper du fått under kursen.
Bygga ännu mer avancerade program, med fler olika databashantering i ett och samma projekt samt bygga de samtidigt simplare/DRY och mer överskådligt, samtidigt som jag i stort fått kunskap om vad Systemintegration/Mikroservices är för något och hur det fungerar i praktiken, och vad det är bra för (Jag tycker det är skithäftigt/bra iallafall, då jag som sagt sedan liten älskat Lego+Minecraft) och möjligheten att kunna få bygga och kustomisera i princip applikationer, saker på dator och digitalt i princip hur man vill, tycker jag är fantastiskt. 

Jag har ända sedan liten varit en väldigt kreativ person.

### Varför jag valde just denna specifika lösning
Som förklarat tidigare, att jag ville utmana mig själv mer genom att lägga till fler element i app:en som fler databaser, etc.

### Förslag på förbättringar av koden
Också som tidigare förklarat, kunnat haft en lektion om modulhantering och läst på/tittat på mer om videor om just detta på internet tidigare för att lära mig det, verkar roligt. När man väl påbörjat ett projekt och kommit ganska långt in i det, kan det lätt bli kaotiskt och hela programmet måste göras om bara för att man ville ändra en grundimplementering mitt i, eller långt fram i.

### Exempel på lösningar jag valde att inte implementera
JavaFX, jag ville/vill ha in det men jag är inte säker/100% på hur jag skulle få det att fungera tillsammans med SpringBoot, så det kändes lite overkill eller mer resurskrävande att göra just nu så jag höll mig till MVP just där iallafall.
Hade även tankarna inne på Docker, för att få allt till en Docker-image som var enkel att bara köra igång och starta men sparar även denna till senare.

### Förslag på förbättringar av UI/UX för design + reflektion av den
Samma där, hade nog velat och kunnat implementera ett JavaFX GUI om jag haft tid/orkat sätta+lära mig mer tid om att lära kring det. Var en del implementeringar som verkade långa pch komplicerade för att få till där.