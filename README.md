# MagAgE3
Managment Panel for Agent Evolution 3

Aby uruchomic aplikacje, nalezy: 
- java org.age.mag.server.ServerInit

Aplikacja slucha domyslnie na porcie 8080.

Dopoki ponizszy blad nie zostanie naprawiony, to projekt nie bedzie sie budowac, chyba ze wklei sie zaleznosci AgEa i doda sie go do class pathu.

FAILURE: Build failed with an exception.

* What went wrong:
Could not resolve all dependencies for configuration ':compile'.
> Could not resolve org.age:age3:+.
  Required by:
      :MagAgE3:unspecified
   > Failed to list versions for org.age#age3;+.
      > Could not list versions using M2 pattern 'https://repository.age.agh.edu.pl/content/repositories/releases/[organisation]/[module]/[revision]/[artifact]-[revision](-[classifier]).[ext]'.
         > Could not GET 'https://repository.age.agh.edu.pl/content/repositories/releases/org/age/age3/'.
            > hostname in certificate didn't match: <repository.age.agh.edu.pl> != <*.iisg.agh.edu.pl> OR <*.iisg.agh.edu.pl>

