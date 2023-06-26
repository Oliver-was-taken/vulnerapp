# Vulnerapp 🩹

> ## Goals
> 1. [x] Verwendung von korrekten REST-Verben.
> 2. [x] Implementierung einer Authentifizierungslösung (z.B. BasicAuth).
> 3. [x] Enforce RBAC (z.B. User- und Admin-Services unterscheiden).
> 4. [x] Aktivieren von CSRF-Protection
> 5. [x] Erklärung, warum CSRF Implementation funktioniert.
> 6. [x] Implementierung einer sicheren Passwort-Speicherung (Hashing, Passwortregeln).
> 7. [x] Strikte Inputvalidierung (für REST-Endpunkte und Datenbank).
> 8. [x] Behebung der initialen Sicherheitslücken (SQLi, XSS, CSRF).
> 9. [x] Implementierung von securityrelevanten (Unit-)Tests.

## CSRF-Implementation 👮🏻

    .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).csrfTokenRequestHandler(requestHandler))
Diese Zeile konfiguriert den CSRF-Schutz für das Webanwendungs-Sicherheit-framework. Es verwendet das CookieCsrfTokenRepository und den requestHandler zur Verwaltung des CSRF-Tokens.

    CookieCsrfTokenRepository.withHttpOnlyFalse()
Diese Methode erstellt eine Instanz des CookieCsrfTokenRepository und setzt httpOnly auf false, damit das CSRF-Token sowohl über JavaScript als auch über HTTP-Anfragen zugänglich ist.

    csrfTokenRequestHandler(requestHandler)
Diese Methode konfiguriert den requestHandler, der für die Generierung und Bereitstellung des CSRF-Tokens verantwortlich ist.

Wenn ein Benutzer die Anwendung aufruft, wird ihm ein CSRF-Token zugewiesen und in einem Cookie gespeichert. Das CSRF-Token wird dann für jede nachfolgende Anfrage verwendet, um sicherzustellen, dass sie von der gleichen Anwendung stammt und nicht von einer bösartigen Seite.

 
Durch die Verwendung des CSRF-Tokens im Header der Anfrage kann die Serverseite überprüfen, ob die Anfrage von
derselben Anwendung stammt, die den Benutzer authentifiziert hat. Wenn das CSRF-Token nicht übereinstimmt oder fehlt,
kann der Server die Anfrage ablehnen und vor CSRF-Angriffen schützen.

## Selbstevaluation
### Security Diskussion 
1. ***CSRF-Schutz (Cross-Site Request Forgery):*** In meiner Applikation wurde ein CSRF-Schutzmechanismus implementiert, um Angriffe zu verhindern, bei denen ein Angreifer den Benutzer dazu verleitet, ungewollte Aktionen auf der Website auszuführen. Durch die Verwendung von zufällig generierten Tokens wird sichergestellt, dass nur legitime Anfragen vom Benutzer akzeptiert werden. Dadurch wird die Sicherheit der Applikation erheblich verbessert.

2. ***RBAC (Role-Based Access Control):*** Ich habe RBAC implementiert, um den Zugriff auf verschiedene Funktionen und Ressourcen innerhalb meiner Applikation zu steuern. Durch die Zuweisung von Zugriffsrechten basierend auf den Rollen der Benutzer wird das Risiko unbefugter Zugriffe minimiert. Dies erhöht die Sicherheit der Applikation, da jeder Benutzer nur die erforderlichen Rechte für seine Rolle erhält.

3. ***Content Security Policy (CSP):*** Um Cross-Site Scripting-Angriffe zu verhindern, habe ich eine Content Security Policy implementiert. Diese Richtlinie legt Einschränkungen für die Ausführung von Skripten und den Zugriff auf Ressourcen fest. Durch eine restriktive Konfiguration der CSP wird die Anfälligkeit für XSS-Angriffe reduziert und die Sicherheit meiner Applikation erhöht.

4. ***XSS-Schutz (Cross-Site Scripting):*** Ich habe spezifische Maßnahmen ergriffen, um XSS-Angriffe zu verhindern. Durch die Aktivierung des XSS-Schutzes in den Webbrowsern können potenziell schädliche Skripte blockiert oder entfernt werden. Dies minimiert das Risiko von XSS-Angriffen und gewährleistet die Sicherheit der Benutzerdaten.

Neben den bereits implementierten Sicherheitsmechanismen gibt es weitere sinnvolle Maßnahmen, die ich in Betracht ziehen könnte:

    
1. ***Password Rules:*** Ich könnte Richtlinien für die Passwortkomplexität einführen, um die Sicherheit der Benutzerkonten weiter zu stärken. Dazu könnte ich Mindestlängen, die Verwendung von Groß- und Kleinbuchstaben, Zahlen und Sonderzeichen sowie das regelmäßige Aktualisieren der Passwörter empfehlen. Starke Passwörter erschweren es potenziellen Angreifern, Konten zu kompromittieren.

2. ***Input Validation:*** Eine gründliche Validierung der Benutzereingaben ist entscheidend, um potenzielle Sicherheitslücken zu vermeiden. Ich könnte die Eingaben auf zulässige Zeichen, Längenbeschränkungen und das Entfernen potenziell schädlicher Inhalte prüfen. Durch eine effektive Eingabevalidierung kann ich Angriffe wie SQL-Injection oder Code-Injection wirksam verhindern.

3. ***Verbot von HTML in Post-Eingaben:*** Obwohl die Verwendung von HTML oder Markdown als Eingabe nützlich sein kann gerade bei den Blogs, könnte man in bestimmten Fällen das Verbot oder die Einschränkung von HTML in Post-Eingaben in Betracht ziehen.

### Probleme
Ich hatte selten Probleme bei der implementierung und wenn ich welche hatte, dann wusste ich mir schnell zu helfen.

Zu meinem Glück hatte ich in der Zeit der Vulnerapp noch einen ÜK, welcher sich genau mit demselben Thema befasst hat. So habe ich in letzter Zeit viel Zeit mit Security etc. verbracht und konnte mich einigermassen in das Thema reinfuchsen 🦊.

Das Ganze hat mir viel mehr Spass gemacht, als ich dachte (Ich bin ja sonst eher ein Frontend-Liebhaber) und hat mich wieder ein wenig mehr in Richtung backend gezogen. daher bin ich eigentlich generell sehr froh über das Projekt unabhängig vom outcome.
