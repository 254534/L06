# L06
![image](https://user-images.githubusercontent.com/92735548/147852565-630697ab-056c-4793-8031-aeff2d86577c.png)

A. Przygotować aplikację z ekranem głównym i ekranami dodatkowymi o następujących cechach i zawartości (patrz rys.):

1. Aplikacja składa się z głównej aktywności i fragmentów. Fragmenty są elementami wypełniającymi zawartość ekranu aktywności. Nawigacja realizowana jest frameworkiem Navigation.
3. Ekran aktywności oprócz fragmentu wypełniającego cały ekran zawiera na dole element nawigacji BottomNavigation, którym przełącza się do poszczególnych ekranów (fragmentów).
4. Ekrany
- Ekran główny zawierający tekst przywitania, obrazek i poniżej dane jakieś informacyjne (np. dane o autorze). 
Obrazek i teksty są wybierane/ustawiane w innych ekranach.
- Drugi ekran jest fragmentem zawierające kilka opcji do ustawień.
- Trzeci ekran wyświetla listę wprowadzonych danych (patrz opis niżej).
Zestaw danych (zawartość pojedynczego rekordu danych) ma być wymyślony indywidualnie przez autora aplikacji (poniżej są tylko przykłady). Powinien być to zestaw różnych typów danych.
4. Drugi kran powinien Spinner ustawiający jakiś element aplikacji - np. tekst przywitania, jakiś kolor lub tym podobne.
Dodatkowo może zawierać Grid z obrazkami do wyboru - do wyświetlenia na ekranie głównym.
5. Trzeci ekran wyświetla listę danych.
- jest realizowany z użyciem RecyclerView (lub ew. ListView wg wzorca ViewHolder)

a) wersja minimalna
- na liście wyświetl tylko część wprowadzonych danych (np. opisową nazwę, słowo kluczowe, gł. kategorię itp.)

b) wersja w pełni punktowana
lista prezentuje wprowadzone dane za pomocą własnego adaptera (w postaci listy z dostosowanym widokiem) - pozycja listy powinna zawierać
- obraz, który zmieniałby się w zależności od danych w konkretnej pozycji na liście (np. czy dane dotyczą kobiety czy mężczyzny, czy: samochód sportowy, zwykły samochód, czy ciężarówka, czy: pies, kot, gryzoń lub ptak itp. - tj. w zależności od tego, jaki zestaw danych zostanie wymyślony)
- tekst główny pozycji (nazwa opisowa, słowo kluczowe itp.) wyróżniający się kolorem i stylem czcionki,
jakaś dodatkowa informacja - tekst mniej „rzucający się w oczy” z innym rozmiarem i stylem czcionki.
- po kliknięciu pozycji listy wyświetla się ekran ze szczegółowymi danymi (wszystkimi)
- dane powinny być odpowiednio przekazywane 
- ekran musi zawierać opcję dodawania nowych rekordów danych (pozycji listy) - w postaci FAB lub opcji menu.

Uwaga: opcja a) i b) będą różnie punktowane.

6. Aktywność/fragment z detalami powinien prezentować różne dane w rożnych postaciach (patrz dalej dodawanie/edycja danych)
- ekran zawiera opcję powrotu do listy oraz modyfikacji danych.
- wybranie modyfikacji wyświetla kolejny ekran umożliwiający modyfikację danych.
7. Dodawanie danych i modyfikacja to ta sama aktywność/fragment wywoływany z rożnych miejsc
- przy dodawaniu danych są one początkowo "puste"
- przy modyfikacji dane są odpowiednio przekazywane do aktywności/fragmentu (przypomnij z ćwiczeń wcześniejszych).
8. Aktywność/fragment do wprowadzania/modyfikacji danych - powinna wykorzystywać różne kontrolki i obejmować między innymi:
- ciągi tekstowe i co najmniej jedną dane liczbowe, 
- ustawienie wybrane z 2 lub więcej możliwych - przyciski radiowe (np. kobieta/mężczyzna, ssak/ptak/ryba/gad, samochód/ciężarówka/samochód dostawczy, instrument dęty/instrument smyczkowy/... itd.)
- niektóre ustawienia stopniowane na zadanej skali - RatingBar (np. priorytet, poziom sympatii, ocena wartości itp.)
- płynne ustawienie kolorów (RGB) (np. ulubiony kolor, kolor sierści/karoserii/... itp.) za pomocą suwaka (SeekBar), - podczas ustawiania kolor powinien być widoczny na ekranie.
- powinna zawierać opcje/akcję zaniechania - wtedy powracamy do poprzedniego ekranu bez zmiany danych
- powinna zawierać opcję/akcję zatwierdzania - dane są zapamiętane i powracamy do ekranu, który wyświetla nowe dane
- we wszystkich przypadkach dane są odpowiednio przekazywane miedzy aktywnościami/fragmentami
9. Co najmniej jeden ekran - w tym ekran z listą - powinien obsługiwać zmianę orientacji ekranu.
- po obrocie do pozycji horyzontalnej obok listy pojawiają się dane szczegółowe (rys 3b.)
- schemat realizacji powinien być taki, że fragment z ekranu 4 jest ten sam co z ekranu 3b.
