# Vulnerability Analysis

## A1:2017 Injection
### Description
Injection is een van de meest voorkomende security valnerabilities. Bij injection is er bijvoorbeeld een invoerveld op een website waarin alles ingevuld kan worden. Via deze invoer kunnen aanvallers data invoerendie de informatie ophalen. Dit kan door bijvoorbeeld een querie.

### Risk 
Bij injection wordt er via bijvoorbeeld sql informatie meegegeven in een non-parameterized querie. Hierdoor kan de gebruiker een querie meegeven die data ophaalt uit de database. Denk aan privegevens.


### Counter-measures
Mogelijke oplossingen tegen injection zijn:
1. gebruik maken van een veilige api die nooit gebruik maakt van de volledige interpreter of een parameterized interface.2
2. Maak gebruik van een ORM. denk aan hibernate. Dit kan alsnog niet volledig veilig zijn.
3. Markeer bepaalde invoer. Hierdoor kan deze invoer niet doorgevoerd worden naar de database of de querie. Let op er kan iets over het hoofd gezien worden.
4. Limiteer sql controls. Hierdoor kan niet veel data tegelijk naar de aanvaller gaan.

## A3:2017 Sensitive Data Exposure
###Description
Bij sensitive data exposure wordt er gericht op het ophalen van gebruikersgegevens. Denk aan het ophalen van credit card informatie, wachtwoorden en andere persoonlijke informatie. Dit kan bij wachtwoorden geprobeerd worden met brute force(in korte tijd zoveel mogelijk opties invullen). Daarnaast kan er ook data exposure plaatsvinden als het weergegeven wordt als normale tekst. denk aan een wachtwoord in een tekstveld invoeren. Data exposure kan ook voorkomen doordat aanvallers een mail sturen naar een fake website(denk aan http ipv https) en hier vragen om de gebruikersgegevens. Zodra deze ingevoerd zijn kunnen de aanvallers bij die applicatie van de klant.

### Risk
Het risico bij data exposure is dat er bepaalde informatie van iemand is opgepakt door een aanvaller en deze aanvaller kan vervolgens ook bij andere applicaties van deze persoon. Dit kan gebeuren wanneer het wachtwoord achterhaald is van iemand en diegene hetzelfde wachtwoord voor zijn bankieren app, gmail, schoolgegevens, etc.

### Counter-measures
dit probleem is op te lossen door:
1. Encrypt de data van de gebruiker.
2. Voeg controles toe aan de hand van de data. denk aan 2 staps verificatie.
3. Cache geen gevoelige informatie.
## A9: 2017 Using components with known vulnerabilities
### Description
Bij de vulnerability using components with known vulnerabilities is het probleem dat er gebruik gemaakt wordt van bepaalde frameworks of dependencies die niet betrouwbaar zijn. Dit kan zijn omdat er een oude versie wordt gebruikt of er wordt gebruik gemaakt van software waar al van bekend is dat het vulnerabilities heeft.

### Risk
Wanneer er niet goed op alle dependencies of frameworks of andere gebruikte software gelet wordt/ gescand wordt op mogelijke vulnerabilities kan het zijn dat aanvallers via deze weg binnenkomen bij je systeem. Daarnaast wordt het risico steeds groter aan de hand van hoe outdated de desbetreffende software is.

### Counter-measures
mogelijke oplossingen hiertegen zijn:
1. Verwijder ongebruikte frameworks en dependencies. 
2. Houdt de gebruikte frameworks en dependencys up to date door gebruik te maken van bijvoorbeeld dependabot.
3. Gebruik alleen dependencies en frameworks van geverifieerde websites/ links.
