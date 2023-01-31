from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from Constructores import Deporte, Deportista, Olimpiada, Evento, Participacion, Equipo


def modificarMedalla():


    engineMy = create_engine("mysql+pymysql://admin:password@localhost/olimpiadas")


    engineLite = create_engine('sqlite:///olimpiadas.db', echo=True)


    Session = sessionmaker(bind=engineMy)
    sesionMy = Session()
    Session = sessionmaker(bind=engineLite)
    sesionLite = Session()


    nombre = input("Introduce el nombre de un deportista: ")
    print("Deportistas: ")
    listDeportistas = sesionMy.query(Deportista).all()


    ids = []
    for deportista in listDeportistas:
        if nombre in deportista.nombre:
            print("\tid_deportista: " , deportista.id_deportista
                  , " nombre: " , deportista.nombre
                  , " altura: " , deportista.altura
                  , " peso: " , deportista.peso
                  , " sexo: " , deportista.sexo )
            ids.append(deportista.id_deportista)
    deportista = int(input("Introduce el id del deportista: "))
    while deportista not in ids:
        deportista = int(input("Introduce un id válido: "))


    print("Participaciones: ")
    listEventoParticipacion = sesionMy.query(Evento, Participacion).join(Participacion).filter(Participacion.id_deportista == deportista).all()
    ids = []
    cont = 0
    for evento, participacion in listEventoParticipacion:
        print("\t"
            + "id: " , cont
            , " Evento: " , participacion.id_evento
            , " Equipo: " , participacion.id_equipo
            , " Edad: " , participacion.edad
            , " Medalla: " , participacion.medalla)
        ids.append([participacion.id_deportista, participacion.id_evento])
        cont += 1
    participacion = int(input("Introduce el id de la participacion: "))
    while int(participacion) >= len(ids) or int(participacion) < 0:
        participacion = int(input("Introduce un id válido: "))


    deportista = ids[int(participacion)][0]
    evento = ids[int(participacion)][1]
    medalla = input("Que medalla le quieres introducir?(Gold/Silver/Bronze/None) ")
    medallas = ['Gold', 'Silver', 'Bronze', 'None']
    while medalla not in medallas:
        medalla = input("Introduce una medalla válida. (Gold/Silver/Bronze/None) ")
    if medalla == 'None':
        medalla = None
    sesionMy.query(Participacion).filter(Participacion.id_deportista == deportista,
                                        Participacion.id_evento == evento).update(
        {Participacion.medalla: medalla}, synchronize_session=False)


    sesionLite.query(Participacion).filter(Participacion.id_deportista == deportista,
                                         Participacion.id_evento == evento).update(
        {Participacion.medalla: medalla}, synchronize_session=False)


    # Cierre del conector
    sesionMy.commit()
    sesionLite.commit()
    sesionMy.close()
    sesionLite.close()


    print("Actualización terminada correctamente\n")
    print("Medalla actualizada")


def añadirDeportistaParticipacion():
    engineMy = create_engine("mysql+pymysql://admin:password@localhost/olimpiadas")


    engineLite = create_engine('sqlite:///olimpiadas.db', echo=True)


    Session = sessionmaker(bind=engineMy)
    sesionMy = Session()
    Session = sessionmaker(bind=engineLite)
    sesionLite = Session()




    nombre = input("Introduce el nombre de un deportista, si no encontramos uno, se añadirá: ")
    print("Deportistas: ")
    listDeportistas = sesionMy.query(Deportista).all()
    ids = []


    for deportista in listDeportistas:
        if nombre in deportista.nombre:
            print("\tid_deportista: ", deportista.id_deportista
                  , " nombre: ", deportista.nombre
                  , " altura: ", deportista.altura
                  , " peso: ", deportista.peso
                  , " sexo: ", deportista.sexo)
            ids.append(deportista.id_deportista)
    if len(ids) == 0:
        print("Vamos a introducir un nuevo deportista")
        sexo = input("Introduce el sexo (M/F) ")
        while sexo not in ('M', 'F'):
            sexo = input("Introduce un sexo válido (M/F) ")


        peso = int(input("Introduce el peso (Kg) "))
        while peso < 20 or peso > 500:
            peso = int(input("Introduce un peso válido (Kg) "))


        altura = int(input("Introduce la altura (cm) "))
        while altura < 20 or altura > 350:
            altura = int(input("Introduce una altura válida (cm) "))
        id_deportista=0
        for deportista in listDeportistas:
            id_deportista = 1 + deportista.id_deportista


        deportista = Deportista(id_deportista, nombre, sexo, peso, altura)
        sesionMy.add(deportista)
        sesionLite.add(deportista)
        sesionMy.commit()
        sesionLite.commit()
        print("Deportista insertado\n")
    else:
        id_deportista = int(input("Introduce el id del deportista: "))
        while id_deportista not in ids:
            id_deportista = int(input("Introduce un id válido: "))




    print("Introduce la participacion")
    temporada = input("En que temporada buscamos? (W/S)")
    while (temporada.lower() != "w") and (temporada.lower() != "s"):
        temporada = input("Introduzca una temporada correcta (W/S)")


    if temporada.lower() == 's':
        temporada = 'Summer'
    else:
        temporada = 'Winter'
    # Muestro todas la olimpiadas de esa temporada y voy guardando los ids para depues comprobar que introduce uno correcto
    print("Olimpiadas de la temporada " + temporada + ":")
    listolimpiadas = sesionMy.query(Olimpiada).filter(Olimpiada.temporada == temporada).all()
    ids = []
    for olimpiada in listolimpiadas:
        print("\tid: " , olimpiada.id_olimpiada , " año: " , olimpiada.anio , " ciudad: " , olimpiada.ciudad)
        ids.append(olimpiada.id_olimpiada)


    olimpiada = int(input("Introduce el id de una olimpiada: "))
    while olimpiada not in ids:
        olimpiada = int(input("Introduce un id válido: "))


    # Hacemos lo mismo pero con los deportes de la olimpiada seleccionada
    print("Deportes de la olimpiada ", olimpiada, ":")
    listDeporte = sesionMy.query(Deporte, Evento).join(Evento).filter(Evento.id_olimpiada == olimpiada).all()


    ids = []
    for deporte, evento in listDeporte:
        if evento.id_deporte not in ids:
            print("\t id: ", evento.id_deporte, " deporte: ", deporte.nombre)
            ids.append(evento.id_deporte)
    deporte = int(input("Introduce el id de un deporte: "))
    print(ids)
    while deporte not in ids:
        deporte = int(input("Introduce un id válido: "))


    # Hacemos lo mismo pero con los eventos del deporte seleccionado


    print("Eventos del deporte", deporte, " en la olimpiada ", olimpiada, ":")


    listaEventos = sesionMy.query(Evento).filter(Evento.id_deporte == deporte, Evento.id_olimpiada == olimpiada).all()
    ids = []
    for evento in listaEventos:
        print("\tid: ", evento.id_evento, " nombre: ", evento.nombre)
        ids.append(evento.id_evento)


    evento = int(input("Introduce el id de un evento: "))
    while evento not in ids:
        evento = int(input("Introduce un id válido: "))

    listequipos = sesionMy.query(Equipo).all()
    ids = []
    for equipo in listequipos:
        print("\tid: ", equipo.id_equipo, " año: ", equipo.nombre, " ciudad: ", equipo.iniciales)
        ids.append(equipo.id_equipo)

    equipo = int(input("Introduce el id de una equipo: "))
    while equipo not in ids:
        equipo = int(input("Introduce un id válido: "))

    edad = int(input("Introduce la edad del deportista en el momento de su participacion"))
    while edad < 0:
        edad = int(input("Introduce la edad del deportista en el momento de su participacion"))


    medalla = input("Que medalla le quieres introducir?(Gold/Silver/Bronze/None) ")
    medallas = ['Gold', 'Silver', 'Bronze', 'None']
    while medalla not in medallas:
        medalla = input("Introduce una medalla válida. (Gold/Silver/Bronze/None) ")
    if medalla == 'None':
        medalla = None
    #Introducimos la Participacion y cerramos las conexiones
    partiNueva= Participacion(id_deportista, evento, equipo, edad, medalla)
    sesionMy.add(partiNueva)
    sesionMy.commit()
    sesionLite.add(partiNueva)
    sesionLite.commit()
    sesionMy.close()
    sesionLite.close()


def eliminarParticipacion():
    engineMy = create_engine("mysql+pymysql://admin:password@localhost/olimpiadas")


    engineLite = create_engine('sqlite:///olimpiadas.db', echo=True)


    Session = sessionmaker(bind=engineMy)
    sesionMy = Session()
    Session = sessionmaker(bind=engineLite)
    sesionLite = Session()




    nombre = input("Introduce el nombre de un deportista: ")
    print("Deportistas: ")
    listDeportistas = sesionMy.query(Deportista).all()


    ids = []
    for deportista in listDeportistas:
        if nombre in deportista.nombre:
            print("\tid_deportista: ", deportista.id_deportista
                  , " nombre: ", deportista.nombre
                  , " altura: ", deportista.altura
                  , " peso: ", deportista.peso
                  , " sexo: ", deportista.sexo)
            ids.append(deportista.id_deportista)
    deportista = int(input("Introduce el id del deportista: "))
    while deportista not in ids:
        deportista = int(input("Introduce un id válido: "))


    # listEventoParticipacion = sesionMy.query(Evento, Participacion).join(Participacion).filter(Participacion.id_deportista == deportista).all()
    # ids = []
    # cont = 0
    # for evento, participacion in listEventoParticipacion:
    #     print("\t"
    #         + "id: " , cont
    #         , " Evento: " , participacion.id_evento
    #         , " Equipo: " , participacion.id_equipo
    #         , " Edad: " , participacion.edad
    #         , " Medalla: " , participacion.medalla)
    #     ids.append([participacion.id_deportista, participacion.id_evento])
    #     cont += 1
    # participacion = int(input("Introduce el id de la participacion: "))
    # while int(participacion) >= len(ids) or int(participacion) < 0:
    #     participacion = int(input("Introduce un id válido: "))
    #
    #
    # deportista = ids[int(participacion)][0]
    # evento = ids[int(participacion)][1]
    #
    #
    # partiBorrar=sesionMy.query(Participacion).filter(Participacion.id_deportista == deportista, Participacion.id_evento == evento).one()
    # sesionMy.delete(partiBorrar)
    # sesionMy.commit()
    # sesionLite.delete(partiBorrar)
    # sesionLite.commit()
    # print("Participacion eliminada correctamente")
    #Comprobamos que el deportista sigue teniendo participaciones, sino es así lo eliminamos
    listDeportistaParticipacion = sesionMy.query(Deportista, Participacion).join(Participacion).filter(
        Deportista.id_deportista == deportista, Participacion.id_deportista == deportista).all()
    cont = 0
    for row in listDeportistaParticipacion:
        cont += 1
    if cont <= 0:
        # Este deportista no tiene más participaciones en la tabla Participación
        deportistaborrar = sesionMy.query(Deportista).filter(Deportista.id_deportista == deportista).all()
        sesionMy.delete(deportistaborrar)
        sesionMy.commit()
        sesionLite.delete(deportistaborrar)
        sesionLite.commit()
        print("Deportista eliminado correctamente")


    # Cierre del conector
    sesionMy.close()
    sesionLite.close()


eliminarParticipacion()
