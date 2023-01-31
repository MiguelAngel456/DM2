from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from Constructores import Deporte, Deportista, Olimpiada, Evento, Participacion, Equipo


def menu():
    num = (int)(input("""Hola, ¿qué quieres hacer hoy?
    1-Listado de deportistas participantes
    2-Modificar medalla deportista
    3-Añadir deportista/participación
    4-Eliminar participación
    0-Salir del programa

    """))

    if num == 1:
        metodo1()
    elif num == 2:
        metodo2()
    elif num == 3:
        metodo3()
    elif num == 4:
        metodo4()
    else:
        print("Adio")


def metodo1():
    bbdd = (int)(input("""¿Qué BBDD quieres usar?
    1-MySQL
    2-SQLite
    """))
    session = openBBDD(bbdd)

    temporada = (int)(input("""¿Qué Temporada: Winter o Summer (W/S)?
    1-Winter
    2-Summer
    """))

    # Winter
    if temporada == 1:
        temporada = "Winter"
    # Summer
    else:
        temporada = "Summer"
    listaOlimpiada = todasLasOlimpiadasPorTemporada(session, temporada)
    print("¿Qué olimpiada quieres?")
    cont = 1;
    for l in listaOlimpiada:
        print(cont, "-", l.nombre, "-", l.id_olimpiada)
        cont += 1
    resp3 = (int)(input())
    resp3 -= 1

    olimpiada = listaOlimpiada[resp3]
    idOlimpiada = olimpiada.id_olimpiada
    listaDeporte = todosLosDeportesPorOlimpiada(session, idOlimpiada)
    print("¿Qué deporte quieres?")
    cont = 1;
    for l in listaDeporte:
        print(cont, "-", l.nombre, "-", l.id_deporte)
        cont += 1
    resp4 = (int)(input())
    resp4 -= 1

    deporte = listaDeporte[resp4]
    idDeporte = deporte.id_deporte

    listaEventos = todosLosEventosPorOlimpiadaDeporte(session, idOlimpiada, idDeporte)
    print("¿Qué evento quieres?")
    cont = 1;
    for l in listaEventos:
        print(cont, "-", l.nombre)
        cont += 1
    resp5 = (int)(input())
    resp5 -= 1

    evento = listaEventos[resp5]
    idEvento = evento.id_evento

    print("\n\n")
    print("Temporada:", temporada)
    print("Olimpiada:", olimpiada.nombre)
    print("Deporte:", deporte.nombre)
    print("Evento:", evento.nombre)
    print("Deportistas participantes:\n")

    listaParticipaciones = todasLasParticipacionesPorEvento(session, idEvento)
    cont = 1;
    for p in listaParticipaciones:
        deportista = DeportistaPorParticipacion(session, p)
        equipo = EquipoPorParticipacion(session, p)
        print(cont, "-", deportista.nombre, "-", deportista.altura, "-", deportista.peso, "-", p.edad, "-",
              equipo.nombre, "-", p.medalla)
        cont += 1
    input("\nPulsa 'ENTER' para salir\n")
    menu()


def metodo2():
    sessionMy = openBBDD(1)
    sessionLite = openBBDD(2)

    nombre = input("Introduce el nombre de un deportista: ")

    listDeportistas = sessionMy.query(Deportista).all()

    print("Elije el deportista que quieres modificar")
    usados = []
    cont = 1
    for deportista in listDeportistas:
        if nombre in deportista.nombre:
            print(cont, "-\tid_deportista: ", deportista.id_deportista, " nombre: ", deportista.nombre)
            cont += 1
            usados.append(deportista)
    resp = int(input())
    resp -= 1

    deportista = usados[resp]
    print("Elige una participacion")
    listEventoParticipacion = sessionMy.query(Evento, Participacion).join(Participacion).filter(
        Participacion.id_deportista == deportista.id_deportista)
    auxParEven = []
    cont = 1
    for evento, participacion in listEventoParticipacion:
        print(cont, "-", " Evento: ", evento.nombre, " Medalla: ", participacion.medalla)
        auxParEven.append([participacion.id_deportista, evento.id_evento])
        cont += 1
    resp = int(input())
    resp -= 1
    idDeportista = auxParEven[int(resp)][0]
    idEvento = auxParEven[int(resp)][1]

    medalla = input("Escribe el tipo de medalla")
    if medalla == '':
        medalla = None

    sessionMy.query(Participacion).filter(Participacion.id_deportista == idDeportista,
                                          Participacion.id_evento == idEvento).update({Participacion.medalla: medalla},
                                                                                      synchronize_session=False)
    sessionLite.query(Participacion).filter(Participacion.id_deportista == idDeportista,
                                            Participacion.id_evento == idEvento).update(
        {Participacion.medalla: medalla}, synchronize_session=False)

    sessionMy.commit()
    sessionMy.close()
    sessionLite.commit()
    sessionLite.close()

    print("Medalla actualizada")

    menu()


def metodo3():
    sessionMy = openBBDD(1)
    sessionLite = openBBDD(2)

    nombre = input("Introduce el nombre de un deportista (si no existe se creara un deportista nuevo): ")

    listDeportistas = sessionMy.query(Deportista).all()

    usados = []
    cont = 1
    resp = -1
    for d in listDeportistas:
        if nombre in d.nombre:
            if cont == 1:
                print("Elije el deportista que quieres modificar")
            print(cont, "-", d.nombre)
            cont += 1
            usados.append(d)

    if cont != 1:
        print(cont, "- Uno Nuevo")
        resp = int(input())

    if resp == -1:
        deportista = CrearDeportista(listDeportistas, nombre, sessionMy, sessionLite)
    elif cont == 1:
        deportista = CrearDeportista(listDeportistas, nombre, sessionMy, sessionLite)
    else:
        resp -= 1
        deportista = usados[resp]

    temporada = (int)(input("""¿Qué Temporada: Winter o Summer (W/S)?
    1-Winter
    2-Summer
    """))

    # Winter
    if temporada == 1:
        temporada = "Winter"
    # Summer
    else:
        temporada = "Summer"
    listaOlimpiada = todasLasOlimpiadasPorTemporada(sessionMy, temporada)
    print("¿Qué olimpiada quieres?")
    cont = 1;
    for l in listaOlimpiada:
        print(cont, "-", l.nombre, "-", l.id_olimpiada)
        cont += 1
    resp3 = (int)(input())
    resp3 -= 1

    olimpiada = listaOlimpiada[resp3]
    idOlimpiada = olimpiada.id_olimpiada
    listaDeporte = todosLosDeportesPorOlimpiada(sessionMy, idOlimpiada)
    print("¿Qué deporte quieres?")
    cont = 1;
    for l in listaDeporte:
        print(cont, "-", l.nombre, "-", l.id_deporte)
        cont += 1
    resp4 = (int)(input())
    resp4 -= 1

    deporte = listaDeporte[resp4]
    idDeporte = deporte.id_deporte

    listaEventos = todosLosEventosPorOlimpiadaDeporte(sessionMy, idOlimpiada, idDeporte)
    print("¿Qué evento quieres?")
    cont = 1;
    for l in listaEventos:
        print(cont, "-", l.nombre)
        cont += 1
    resp5 = (int)(input())
    resp5 -= 1
    evento = listaEventos[resp5]
    idEvento = evento.id_evento


    listequipos = sessionMy.query(Equipo).all()
    equiposPosibles = []
    print("¿Qué equipo quieres?")
    cont = 1;
    for e in listequipos:
        print(cont, "-", e.nombre)
        cont += 1
        equiposPosibles.append(e.id_equipo)
    resp = (int)(input())
    resp -= 1
    idEquipo = listequipos[resp].id_equipo

    edad = int(input("¿Qué edad quieres? "))
    while edad < 0:
        edad = int(input("Introduce una edad válida"))

    medalla = input("¿Que medalla quieres? (Gold | Silver | Bronze | None) ")
    medallas = ['Gold', 'Silver', 'Bronze', 'None']
    while medalla not in medallas:
        medalla = input("Introduce una medalla valida. (Gold | Silver | Bronze | None) ")
    if medalla == 'None':
        medalla = None

    participacion = Participacion(deportista.id_deportista, idEvento, idEquipo, edad, medalla)
    participacion2 = Participacion(deportista.id_deportista, idEvento, idEquipo, edad, medalla)
    sessionMy.add(participacion)
    sessionMy.commit()
    sessionMy.close()

    sessionLite.add(participacion2)
    sessionLite.commit()
    sessionLite.close()

    menu()


def metodo4():
    sessionMy = openBBDD(1)
    sessionLite = openBBDD(2)

    nombre = input("Introduce el nombre de un deportista: ")

    listDeportistas = sessionMy.query(Deportista).all()

    print("Elije el deportista que quieres modificar")
    usados = []
    cont = 1
    for deportista in listDeportistas:
        if nombre in deportista.nombre:
            print(cont, "-", deportista.nombre)
            cont += 1
            usados.append(deportista)
    resp = int(input())
    resp -= 1

    deportista = usados[resp]






    print("Elige una participacion")
    listEventoParticipacion = sessionMy.query(Evento, Participacion).join(Participacion).filter(
        Participacion.id_deportista == deportista.id_deportista)
    auxParEven = []
    cont = 1
    for evento, participacion in listEventoParticipacion:
        print(cont, "-", evento.nombre, "(", participacion.medalla, ")")
        auxParEven.append([participacion.id_deportista, evento.id_evento])
        cont += 1
    resp = int(input())
    resp -= 1
    idDeportista = auxParEven[int(resp)][0]
    idEvento = auxParEven[int(resp)][1]

    participacion = sessionMy.query(Participacion).filter(Participacion.id_deportista == idDeportista, Participacion.id_evento == idEvento).one()
    sessionMy.delete(participacion)
    sessionMy.commit()
    sessionLite.delete(participacion)
    sessionLite.commit()
    print("Participacion eliminada correctamente")






    listaDepPar = sessionMy.query(Deportista, Participacion).join(Participacion).filter(
        Deportista.id_deportista == deportista.id_deportista, Participacion.id_deportista == deportista.id_deportista).all()

    if len(listaDepPar) <= 0:
        print("111")
        sessionMy.delete(deportista)
        sessionMy.commit()
        sessionLite.delete(deportista)
        sessionLite.commit()
        print("Deportista eliminado correctamente")





    sessionMy.close()
    sessionLite.close()

    menu()


def sacarDeportistasPorTexto(nombre, session):
    deportistas = session.query(Deportista).filter(Deportista.nombre.contains(nombre)).all()
    return deportistas


def sacarParticipacionPorDeprtista(deportista, session):
    participaciones = session.query(Participacion).filter(deportista.id_deportista == Participacion.id_deportista).all()
    return participaciones


def sacarEventoPorParticipacion(participacion, deportista, session):
    eventos = session.query(Evento).filter(participacion.id_evento == Evento.id_evento, deportista.id_deportista == participacion.id_deportista).one()
    return eventos


def openBBDD(bbdd):
    # MySQL
    if bbdd == 1:
        engineMy = create_engine('mysql+pymysql://admin:password@localhost/olimpiadas')
        Session = sessionmaker(bind=engineMy)
    # SQLite
    elif bbdd == 2:
        engineLite = create_engine("sqlite:///olimpiadas.db", echo=False)
        Session = sessionmaker(bind=engineLite)
    session = Session()
    if bbdd == 2:
        session.execute("pragma foreign_keys=on")

    return session


def todasLasOlimpiadasPorTemporada(session, temp):
    lista = session.query(Olimpiada).filter(Olimpiada.temporada == temp)
    return lista


def todosLosDeportesPorOlimpiada(session, idOlimpiada):
    lista = session.query(Evento).filter(idOlimpiada == Evento.id_olimpiada)
    listaDeportes = []
    for l in lista:
        dep = session.query(Deporte).filter(Deporte.id_deporte == l.id_deporte).one()
        if not listaDeportes.__contains__(dep):
            listaDeportes.append(dep)
    lista = listaDeportes
    return lista


def todosLosEventosPorOlimpiadaDeporte(session, idOlimpiada, idDeporte):
    lista = session.query(Evento).filter(Evento.id_olimpiada == idOlimpiada, Evento.id_deporte == idDeporte)
    return lista


def todasLasParticipacionesPorEvento(session, idEvento):
    lista = session.query(Participacion).filter(Participacion.id_evento == idEvento)
    return lista


def DeportistaPorParticipacion(session, participacion):
    deportista = session.query(Deportista).filter(participacion.id_deportista == Deportista.id_deportista).one()
    return deportista


def EquipoPorParticipacion(session, participacion):
    equipo = session.query(Equipo).filter(participacion.id_equipo == Equipo.id_equipo).one()
    return equipo


def CrearDeportista(listDeportistas, nombre, sessionMy, sessionLite):
    for d in listDeportistas:
        id_deportista = d.id_deportista + 1

    sexo = input("Introduce sexo (M/F)")
    while sexo not in ('M', 'F'):
        sexo = input("Introduce un sexo válido (M/F)")

    peso = int(input("Introduce peso"))
    while peso < 10 or peso > 500:
        peso = int(input("Introduce un peso válido"))

    altura = int(input("Introduce la altura"))
    while altura < 0 or altura > 300:
        altura = int(input("Introduce una altura válida"))

    print(id_deportista)
    print(nombre)
    print(sexo)
    print(peso)
    print(altura)

    deportista = Deportista(id_deportista, nombre, sexo, peso, altura)
    soyotrodeportistataparaquenoserompa = Deportista(id_deportista, nombre, sexo, peso, altura)
    sessionMy.add(deportista)
    sessionMy.commit()
    sessionLite.add(soyotrodeportistataparaquenoserompa)
    sessionLite.commit()
    return deportista

try:
    menu()
except:
    print("Ha ocurrido un error\n")
    menu()