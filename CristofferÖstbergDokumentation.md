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

### Vad gick dåligt
Den första lösningen, som beskrivet ganska omfattande här ovan. Den blev bara rörig, och kände blev mer och mer kaotisk till slut och kände mig själv gå ned i kaotiskt mående dessutom på snudd, så bästa där var egentligen bara skit i det mesta (OBS: ENBART - program då alltså, inga andra hemska personliga destruktiva tankar, de existerar inte :)  
och börja om med det och skriv det bra mycket enklare och finare. Känns jättebra.

### Vad har du lärt dig
Implementera getters+setters mer, göra Connection-klassen + KeyHandler-klasserna 'ren' för framtida bruk mer (kommer jag och skall använda i framtida MongoDB-projekt då jag hittils nu fastnat jättemycket i just MongoDB - tycker det är en svinkul, och bra och rolig DB-lösning med.

Jag har tänkt implementera den i ett eget 2D-spel jag håller på och bygger på fritiden :) Jag är ganska kreativ sedan barnsben (Musik+Instrument sedan 6 års ålder, Bygga/snickra det mesta, laga bilar, 3D-skriva, måla etc) som person så gillar att både baka in + slå ihop flera olika div. program och saker. Tycker det är superkul.

### Vad hade du gjort annorlunda om ni gjort om projektet
Troligtvis läst på mer om enklare struktur i program, googlat lite hur man enklast, möjligast kan lägga upp det enklaste 'struktur'-tänket i det hela, med bara ett visuellt diagram framför en. Hade nog hjälpt mycket. Jag tyckte jag själv hade en ganska bra grundplan i detta hela, med både väl förberedd innan och tänkte att detta skall jag bara ta det lugnt och bra, och ta det i rätt ände och bara börja med G-delen först, och kände så mycket pepp+inspiration för detta, men visade sig sen då att programmet mer blev rörigare och rörigare. Så jag hade nog behövt en tydligare struktur ifrån början, med både User+Todo med, då jag nog ändå ville+ville med det i projektet. Jag vill utmana mig litegrann.

### Vilka möjligheter ser du med de kunskaper du fått under kursen.
Bygga mer tydligare och enklare program, mer överskådliga och tusan chansen kanske finns nu, dessutom att det kanske är så att jag kommer ihåg ca 1 år framåt vad tusan det är jag har skrivit just här idag, och kommer ihåg + kan förfina det dessutom.

### Varför jag valde just denna specifika lösning
Som jag har förklarat lite ytterligare här ovanför i koden, så tyckte ja att just denna lösning med getters+setters för både User och Todo, och sedan en 'Manager' för var och en där CRUD-operationerna sköttes verkade ganska logiskt och lättläst. Både i programform samt i diagramform. Man förstår eg. vart alla pilar etc tar vägen och hur de går fram-och-tillbaka, och är ganska lätta att förstå sig på, iaf, tycker jag. Sedan en central TodoApplication vilken sköter texten och menyval och hämtar metoderna ifrån de 2 olika klasserna.

### Förslag på förbättringar av koden
Implementera ett Interface, göra både USerManager och TodoManager ännu mer lättläst och abstrakt. Tyckte det blev lite grötigt ändå (trots att jag refaktorerat nu, och fått det att se ännu bättre ut än tidigare dock), och funderade på ev. lösningar, men man får ta lite vad tid man har också. Man kan inte alltid ändra världen och rädda den helt och bygga ett nytt Rom på en och samma gång etc. På knapp 1,5 vecka utföra detta program med fulla tester på hela programmet + dokumentation, testning, samtestning av klasskamrat på egen hand totalt, dessutom, får man ändå väga in lite vad man själv kan samt skulle hunnit med.

### Exempel på lösningar jag valde att inte implementera
Interface, mer abstrakta lösningar, hade jag gärna velat haft med, men som ovan beskrivet så på grund av tidsbrist (midsommar med, faktiskt) så får man ibland väga tiden. Programmet kan säkerligen förbättras mer i framtiden, bara man lägger tid+tid på det.

### Förslag på förbättringar av UI/UX för design + reflektion av den
Har gjort en ganska tydlig/fin förbättring av utprintade menyvalen här i terminal iaf (så gott det går, tycker jag) men göra ett FX/GUI här i IntelliJ med, fram till presentation+Inlämning hade väl varit kul. Får se om det hinns med nu i dagarna, ev, tar det sist. Just nu är prio på att få alla testerna klara och fungerande, samt programmet med. Dagen är nu 2023-06-23, Midsommarafton.