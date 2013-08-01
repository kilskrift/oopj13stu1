oopj13stu1
==========

Inlämningsuppgift 1 Objektorienterad programmering med Java 2013.


Inlämningsuppgift 1 - Perpetum Mobile
OOP med Java sommaren 13

I denna obligatoriska uppgift ska du göra ändringar i ett javaprogram. Alla ändringar ska göras i samma program. Du behöver alltså inte lämna in separata program för de olika deluppgifterna. Du har väl läst den allmänna informationen om inlämningsuppgifter?

Börja med att kopiera programmet BallWorld.java till ditt eget konto.

Deluppgift 1 - Fikapaus

Kompilera programmet med kommandot javac BallWorld.java och provkör det med kommandot java BallWorld. Beskriv med några få ord vad som händer.

Deluppgift 2 - Färgblindhet

Byt färg på bollen (till t.ex. vit) utan att ändra i konstruktorn Ball() där standardfärgen sätts. Du behöver göra ett anrop till en metod som sätter färgen för en boll. Vilket anrop och var i koden den skall utföras ska du lista ut själv. Beskriv med några få ord vilka ändringar du gjort.

Deluppgift 3 - Fängslande

En s.k. "bounding box" är en rektangel som anger gränserna som bollen studsar inom. Om du ändrar storleken på fönstret upptäcker du att området inom vilket bollen studsar är oförändrat. Metoden wasResized() anropas då fönsterstorleken ändras. Modifiera wasResized() så att "bounding boxen" ändras till fönstrets rätta storlek. Leta i koden för att hitta en metod du kan använda och för att få en uppfattning om hur den ska användas. Beskriv vilka ändringar du gjort.

Deluppgift 4 - Storlek

I klassen Ball finns metoder för att ändra färg och "bounding box", men det finns ingen metod för att ändra bollens storlek (diameter). Lägg till en sådan metod och använd den sedan för att ändra storleken på bollen. Beskriv vilka ändringar du gjort.

Deluppgift 5 - Dubbelt så många

Lägg in en extra boll med annan färg, storlek och hastighet så att det finns totalt två bollar som studsar. Beskriv vilka ändringar du gjort.

Deluppgift 6 - Pulsarer

Låt bollarna få pulserande storlek. För detta behöver du en boolesk tillståndsvariabel i klassen Ball som anger om bollen för tillfället krymper eller växer. Vidare behöver du tröskelvillkor som byter tillstånd på variabeln då bollen når sin minimistorlek och när den når sin maximistorlek (väljs med förstånd). Modifiera sedan metoden action() så att bollen får den pulserande egenskapen. Beskriv vilka ändringar du gjort.

