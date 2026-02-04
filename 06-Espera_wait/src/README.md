# Activitat 06: Espera wait - Preguntes teòriques

## 1. Per què s'atura l'execució al cap d'un temps?

L'execució s'atura perquè arriba un moment on totes les places estan ocupades (placesDisponibles = 0) i tots els assistents que volen fer una reserva es queden bloquejats esperant amb `wait()`. Mentrestant, els assistents que no tenen cap reserva intenten cancel·lar però com que no tenen res a cancel·lar, no executen el `notifyAll()` i ningú desperta als que estan esperant. Així, el programa es queda en un punt mort on ningú pot avançar.

---

## 2. Què passaria si en lloc de una probabilitat de 50%-50% fora de 70%(ferReserva)-30%(cancel·lar)? I si foren al revés les probabilitats?

### Cas 1: 70% ferReserva - 30% cancel·lar

Si canviem el codi així:

```java
@Override
public void run() {
    try {
        while (true) {
            if (random.nextInt(100) < 70) {
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelaReserva(this);
            }
            Thread.sleep(random.nextInt(1000));
        }
    } catch (InterruptedException e) {
        System.out.println(nom + " ha estat interromput.");
    }
}
```

Les places s'ompliran molt ràpid perquè hi ha moltes més reserves que cancel·lacions. La sortida seria:

```
Assistent-0 ha fet una reserva. Places disponibles: 4
Assistent-1 ha fet una reserva. Places disponibles: 3
Assistent-2 ha fet una reserva. Places disponibles: 2
Assistent-3 ha fet una reserva. Places disponibles: 1
Assistent-4 ha fet una reserva. Places disponibles: 0
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
[Els assistents es queden bloquejats esperant places...]
```

El sistema es bloquejaria gairebé immediatament i hi hauria poca activitat després.

### Cas 2: 30% ferReserva - 70% cancel·lar

El codi seria:

```java
@Override
public void run() {
    try {
        while (true) {
            if (random.nextInt(100) < 30) {
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelaReserva(this);
            }
            Thread.sleep(random.nextInt(1000));
        }
    } catch (InterruptedException e) {
        System.out.println(nom + " ha estat interromput.");
    }
}
```

Ara hi hauria moltes més cancel·lacions que reserves. La sortida seria:

```
Assistent-0 ha fet una reserva. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-3 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-0 ha cancel·lat una reserva. Places disponibles: 5
Assistent-4 ha fet una reserva. Places disponibles: 4
[Molts intents de cancel·lació fallits...]
```

Gairebé sempre hi hauria places disponibles (4-5) i gairebé mai es bloquejarien assistents esperant.

---

## 3. Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves?

La llista és necessària perquè hem de controlar QUI té una reserva, no només QUANTES reserves hi ha.

Si només tinguéssim un comptador d'enters, podrien passar coses com:
- Un mateix assistent podria fer 5 reserves seguides i ocupar tot l'esdeveniment sol
- Qualsevol assistent podria cancel·lar reserves sense tenir-ne cap, i el comptador podria arribar a números negatius o superar el màxim

Per exemple, sense la llista:

```java
// MALAMENT - sense llista:
public synchronized void ferReserva(Assistent assistent) {
    while (placesDisponibles == 0) {
        wait();
    }
    placesDisponibles--;  // No comprova si ja té reserva!
}

public synchronized void cancelaReserva(Assistent assistent) {
    placesDisponibles++;  // Qualsevol pot cancel·lar!
    notifyAll();
}
```

Amb la llista podem:
1. Comprovar si un assistent ja té reserva abans de deixar-lo reservar una altra (`contains(assistent)`)
2. Verificar que només qui té reserva pot cancel·lar-la
3. Mantenir la coherència: cada assistent màxim una reserva

És a dir, la llista ens garanteix que les regles del sistema es compleixen correctament.