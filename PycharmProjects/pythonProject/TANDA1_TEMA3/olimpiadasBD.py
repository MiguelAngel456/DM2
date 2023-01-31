from builtins import print

import mysql.connector
import csv
import sqlite3


class olimpiadasDB:

# ------------- Crear conexion MYSQL -------------------
    def crearConexionMYSQL(self):

        try:
            conDB = mysql.connector.connect(user='admin', password='password', host='localhost')
            # cursor para ejecutar consulta
            cursor = conDB.cursor()
            with open("olimpiadas.sql") as f:
                sql = f.read()
                resultados = cursor.execute(sql, multi=True)
            for r in resultados:
                print(r)
            # cerrar cursor
            cursor.close()
            # cerrar conexion
            conDB.database = "olimpiadas"
            return conDB
        except Exception as ex1:
            print("Error -> no se ha podido establecer conexion")

# ------------- llenar bases -------------------

    def llenarBase(self,conn,cual):
        # Crear diccionarios
        dicDeportistas = {}
        dicOlimpiadas = {}
        dicEventos = {}
        dicEquipo = {}
        dicDeportes = {}
        dicParticipaciones = {}
        id_equipo = 1
        id_deporte = 1
        id_olimpiada = 1
        id_evento = 1
        cont = 0
        try:
            ruta = input("introduce el nombre del csv de informacion")  # "athlete_events_recortado.csv"
            with open(ruta) as csvfile:
                reader = csv.DictReader(csvfile)
                for row in reader:
                    cont += 1
                    if row["Team"] not in dicEquipo.keys():
                        dicEquipo[row["Team"]] = [id_equipo, row["Team"], row["NOC"]]
                        id_eq_actual = id_equipo
                        id_equipo += 1
                    else:
                        id_eq_actual = dicEquipo[row["Team"]][0]
                    if row["ID"] not in dicDeportistas.keys():
                        if row["Weight"] == "NA":
                            peso = 0
                        else:
                            peso = row["Weight"]
                        if row["Height"] == "NA":
                            altura = 0
                        else:
                            altura = row["Height"]
                        dicDeportistas[row["Name"]] = [int(row["ID"]), row["Name"], row["Sex"], peso, altura]
                    if (row["Sport"]) not in dicDeportes.keys():
                        dicDeportes[row["Sport"]] = [id_deporte, row["Sport"]]
                        id_dep_actual = id_deporte
                        id_deporte += 1
                    else:
                        id_dep_actual = dicDeportes[row["Sport"]][0]
                    if (row["Games"]) not in dicOlimpiadas.keys():
                        dicOlimpiadas[row["Games"]] = [id_olimpiada, row["Games"], row["Year"], row["Season"],
                                                       row["City"]]
                        id_ol_actual = id_olimpiada
                        id_olimpiada += 1
                    else:
                        id_ol_actual = dicOlimpiadas[row["Games"]][0]
                    clave = row["Event"] + str(id_ol_actual);
                    if clave not in dicEventos.keys():
                        dicEventos[clave] = [id_evento, row["Event"], id_ol_actual, id_dep_actual]
                        id_ev_actual = id_evento
                        id_evento += 1
                    else:
                        id_ev_actual = dicEventos[row["Event"] + str(id_ol_actual)][0]
                    dicParticipaciones[cont] = [int(row["ID"]), id_ev_actual, id_eq_actual, row["Age"], row["Medal"]]
        except Exception as e:
            print("El archivo csv no existe")
            self.borrarDatos(conn)
        # LLenar tablas
        if(cual == 1):
            self.llenarMYSQL(conn,dicEquipo,dicDeportistas,dicDeportes,dicOlimpiadas,dicEventos,dicParticipaciones)
        else:
            self.llenarSQLite(conn,dicEquipo,dicDeportistas,dicDeportes,dicOlimpiadas,dicEventos,dicParticipaciones)

# ------------- llenar conexion MYSQL -------------------


    def llenarMYSQL(self,conn,dicEquipo,dicDeportistas,dicDeportes,dicOlimpiadas,dicEventos,dicParticipaciones):
        try:
            # cursor para ejecutar consulta
            cursor = conn.cursor()
            sql = "INSERT INTO Equipo (id_equipo, nombre, iniciales) VALUES  (%s,%s,%s);"
            cursor.executemany(sql, list(dicEquipo.values()))
            sql = "INSERT INTO Deportista (id_deportista, nombre, sexo, peso, altura) VALUES  (%s,%s,%s,%s,%s);"
            cursor.executemany(sql, list(dicDeportistas.values()))
            sql = "INSERT INTO Deporte (id_deporte, nombre) VALUES  (%s,%s);"
            cursor.executemany(sql, list(dicDeportes.values()))
            sql = "INSERT INTO Olimpiada (id_olimpiada, nombre, anio, temporada, ciudad)  VALUES  (%s,%s,%s,%s,%s);"
            cursor.executemany(sql, list(dicOlimpiadas.values()))
            sql = "INSERT INTO Evento (id_evento, nombre, id_olimpiada, id_deporte) VALUES (%s,%s,%s,%s);"
            cursor.executemany(sql, list(dicEventos.values()))
            conn.commit()
            sql = "INSERT INTO Participacion (id_deportista, id_evento, id_equipo, edad, medalla) VALUES (%s,%s,%s,%s,%s);"
            cursor.executemany(sql, list(dicParticipaciones.values()))
            conn.commit()
            cursor.close()
            conn.close()
            print("La carga se ha realizado correctamente")

        except Exception as e:
            print("Error -> Ha ocurrido algun error en la carga")
            self.borrarDatos(conn)

# ------------- llenar conexion SQLite -------------------

    def llenarSQLite(self,conn,dicEquipo,dicDeportistas,dicDeportes,dicOlimpiadas,dicEventos,dicParticipaciones):
        try:
            cursor = conn.cursor()
            sql = "INSERT INTO Equipo (id_equipo, nombre, iniciales) VALUES  (?,?,?);"
            cursor.executemany(sql, list(dicEquipo.values()))
            sql = "INSERT INTO Deportista (id_deportista, nombre, sexo, peso, altura) VALUES  (?,?,?,?,?);"
            cursor.executemany(sql, list(dicDeportistas.values()))
            sql = "INSERT INTO Deporte (id_deporte, nombre) VALUES  (?,?);"
            cursor.executemany(sql, list(dicDeportes.values()))
            sql = "INSERT INTO Olimpiada (id_olimpiada, nombre, anio, temporada, ciudad)  VALUES  (?,?,?,?,?);"
            cursor.executemany(sql, list(dicOlimpiadas.values()))
            sql = "INSERT INTO Evento (id_evento, nombre, id_olimpiada, id_deporte) VALUES (?,?,?,?);"
            cursor.executemany(sql, list(dicEventos.values()))
            conn.commit()
            sql = "INSERT INTO Participacion (id_deportista, id_evento, id_equipo, edad, medalla) VALUES (?,?,?,?,?);"
            cursor.executemany(sql, list(dicParticipaciones.values()))
            conn.commit()
            cursor.close()
            conn.close()
            print("La carga se ha realizado correctamente")
        except Exception as e:
            print("Error -> Ha ocurrido algun error en la carga")
            self.borrarDatos(conn)

# ------------- Borrar datos -------------------

    def borrarDatos(self,conn):
        try:
            # cursor para ejecutar consulta
            cursor = conn.cursor()
            sql = "Delete from Participacion"
            cursor.execute(sql)
            sql = "Delete from Evento"
            cursor.execute(sql)
            sql = "Delete from Olimpiada"
            cursor.execute(sql)
            sql = "Delete from Equipo"
            cursor.execute(sql)
            sql = "Delete from Deportista"
            cursor.execute(sql)
            sql = "Delete from Deporte"
            cursor.execute(sql)
            conn.commit()
            cursor.close()
        except Exception as e:
            print("Error -> al borrar de las tablas")

# ------------- Ejercicio 1 -------------------

    def parte1(self):
        conexion = self.crearConexionMYSQL()
        self.borrarDatos(conexion)
        self.llenarBase(conexion,1)

# ------------- Crear conexion SQLite -------------------

    def crearConexionSQLite(self):
        conexion = sqlite3.connect("../../embebido.db")
        cursor = conexion.cursor()
        with open("olimpiadas.db.sql") as f:
            cursor.executescript(f.read())
        cursor.close()

# ------------- Para poder consultar el embebido -------------------

    def consultarEmbebido(self):
        conexion = sqlite3.connect("../../embebido.db")
        cursor = conexion.cursor()
        sql = "select nombre from Equipo"
        resultados = cursor.execute(sql)
        for r in resultados:
            print(r)
        cursor.close()
        conexion.close()

# ------------- Ejercicio 2 -------------------

    def parte2(self):
        self.crearConexionSQLite()
        conexion = sqlite3.connect("../../embebido.db")
        self.borrarDatos(conexion)
        self.llenarBase(conexion,2)

# ------------- Ejercicio 3 -------------------

    def parte3(self):
        opcion = -1
        while opcion != 1 and opcion != 2:
            opcion = int(input("Listado de deportistas\n¿Que base quieres usar?\n\n\t[1]MySQL\n\t[2]SQLite\n"))
        if opcion == 1:
            # MySQL
            conexion = mysql.connector.connect(user='admin', password='password',
                                               host='localhost',
                                               database='olimpiadas')
            embebida = False
        else:
            # SQLite
            conexion = sqlite3.connect("../../embebido.db")
            embebida = True
        cursor = conexion.cursor()
        # if embebida:
        sql = "select Deportista.*,Participacion.*,Deporte.nombre,Equipo.*,Evento.*,Olimpiada.*  from Deportista,Deporte,Participacion,Evento,Olimpiada,Equipo where Deportista.id_deportista = Participacion.id_deportista and Participacion.id_evento = Evento.id_evento and Equipo.id_equipo = Participacion.id_equipo and Olimpiada.id_olimpiada = Evento.id_olimpiada and Evento.id_deporte = Deporte.id_deporte and 1<(select count(distinct(Evento.id_deporte))  from Evento,Participacion where Evento.id_evento =Participacion.id_evento  and Participacion.id_deportista = Deportista.id_deportista)"
        # else:
        # sql = "select olimpiadas.Deportista.*,olimpiadas.Participacion.*,olimpiadas.Deporte.nombre,olimpiadas.Equipo.*,olimpiadas.Evento.*,olimpiadas.Olimpiada.*  from olimpiadas.Deportista,olimpiadas.Deporte,olimpiadas.Participacion,olimpiadas.Evento,olimpiadas.Olimpiada,olimpiadas.Equipo where olimpiadas.Deportista.id_deportista = olimpiadas.Participacion.id_deportista and olimpiadas.Participacion.id_evento = olimpiadas.Evento.id_evento and olimpiadas.Equipo.id_equipo = olimpiadas.Participacion.id_equipo and olimpiadas.Olimpiada.id_olimpiada = olimpiadas.Evento.id_olimpiada and olimpiadas.Evento.id_deporte = olimpiadas.Deporte.id_deporte and 1<(select count(distinct(olimpiadas.Evento.id_deporte))  from olimpiadas.Evento,olimpiadas.Participacion where olimpiadas.Evento.id_evento =olimpiadas.Participacion.id_evento  and olimpiadas.Participacion.id_deportista = olimpiadas.Deportista.id_deportista)"
        cursor.execute(sql)
        resultados = cursor.fetchall()
        Depactual = ""
        if embebida:
            for r in resultados:
                if (r[1] != Depactual):
                    print("Deportista: ", r[1], "\n\t Sexo: ", r[2], "\n\t Altura: ", r[4], "\n\tPeso: ", r[3])
                    Depactual = r[1]
                print("\n\n\tDeporte: ", r[10], "\n\tEdad: ", r[8], "\n\tEvento: ", r[15], "\n\tEquipo: ", r[12],
                      "\n\tJuegos: ", r[19], "\n\tMedalla: ", r[9])
        else:
            for r in resultados:
                if (r[1] != Depactual):
                    print("Deportista: ", r[1], "\n\t Sexo: ", r[2], "\n\t Altura: ", r[3], "\n\tPeso: ", r[4])
                    Depactual = r[1]
                print("\n\n\tDeporte: ", r[11], "\n\tEdad: ", r[9], "\n\tEvento: ", r[16], "\n\tEquipo: ", r[13],
                      "\n\tJuegos: ", r[20], "\n\tMedalla: ", r[10])
        cursor.close()
        conexion.close()


# ------------- Ejercicio 4 -------------------


    def parte4(self):
        # --------------- Elegir base -----------------
        opcion = -1
        while opcion != 1 and opcion != 2:
            opcion = int(input("Listado de deportistas\n¿Que base quieres usar?\n\n\t[1]MySQL\n\t[2]SQLite\n"))
        if opcion == 1:
            # MySQL
            conexion = mysql.connector.connect(user='admin', password='password',
                                               host='localhost',
                                               database='olimpiadas')
        else:
            # SQLite
            conexion = sqlite3.connect("../../embebido.db")
        # --------------- Elegir temporada -----------------
        temporada = 'P'
        while temporada != 'S' and temporada != 'W':
            temporada = input("Que temporada?(W/S)")
        # --------------- Enseñar ediciones ----------------
        cursor = conexion.cursor()

        if temporada == 'S':
            sql = "select * from Olimpiada where temporada = 'Summer'"
            temp = "Summer"
        else:
            sql = "select * from Olimpiada where temporada = 'Winter'"
            temp = "Winter"
        cursor.execute(sql)
        resultados = cursor.fetchall()
        print("Elige una edicion por su codigo:")
        posibles = []
        for r in resultados:
            print("\tCódigo ", r[0], " Olimpiada " + r[1] + " celebrada en " + r[4])
            posibles.append(r[0])
        edicioncodigo = int(input("Codigo: "))
        while edicioncodigo not in posibles:
            edicioncodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))

        # --------- Cual deporte ----------
        sql = "Select distinct(Deporte.id_deporte), Deporte.nombre from Participacion,Deporte,Evento where Participacion.id_evento=Evento.id_evento and Deporte.id_deporte = Evento.id_deporte and Evento.id_olimpiada = " + str(
            edicioncodigo)

        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = []
        print("Elige uno de los deportes por su codigo: ")
        for r in resultados:
            print("\tCódigo: ", r[0], " Deporte: " + r[1])
            posibles.append(r[0])
        deportecodigo = int(input("Codigo:"))
        while deportecodigo not in posibles:
            deportecodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))

        # --------- Elegir evento ---------

        sql = "select id_evento,Evento.nombre,Olimpiada.nombre from Evento,Olimpiada where id_deporte = " + str(
            deportecodigo) + " and Evento.id_olimpiada = " + str(
            edicioncodigo) + " and Olimpiada.id_olimpiada=Evento.id_olimpiada"
        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = []
        print("Elige uno de los eventos por su codigo: ")
        for r in resultados:
            print("\tCódigo: ", r[0], " Evento: " , r[1] , " (" , r[2] , ")")
            posibles.append(r[0])

        eventocodigo = int(input("Codigo:"))
        while eventocodigo not in posibles:
            eventocodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))

        # --------- Mostrar info ----------

        sql = "select Participacion.*,Evento.nombre,Equipo.nombre,Olimpiada.nombre,Olimpiada.temporada,Deporte.nombre,Deportista.* from " \
              "Participacion,Evento,Equipo,Olimpiada,Deporte,Deportista where " \
              "Participacion.id_evento=Evento.id_evento and Equipo.id_equipo = Participacion.id_equipo and " \
              "Olimpiada.id_olimpiada=Evento.id_olimpiada and Deporte.id_deporte=Evento.id_deporte and " \
              "Deportista.id_deportista = Participacion.id_deportista and Olimpiada.temporada = '" + temp + "' and " \
               "Olimpiada.id_olimpiada = " + str(edicioncodigo) + " and Deporte.id_deporte = " + str(deportecodigo) + " and Evento.id_evento =" + str(
            eventocodigo)
        cursor.execute(sql)
        resultados = cursor.fetchall()
        primero = True
        for r in resultados:
            if primero:
                print("\n\n\tTemporada: ",r[8],"\n\tEdicion: ",r[7],"\n\tDeporte: ",r[9],"\n\tEvento: ",r[5],"\n")
                primero = False
            print("\nDeportista: ",r[11]," Altura: ",r[14]," Peso: ",r[13]," Edad: ",r[3],"Equipo: ",r[6]," Medalla: ",r[4],)
        cursor.close()
        conexion.close()

# ------------- Ejercicio 5 -------------------

    def parte5(self):
        filtro = input("Introduce el filtro para buscar al deportista: ")
        conexion = sqlite3.connect("../../embebido.db")
        cursor = conexion.cursor()

        # -------------- seleccionar deportista ---------------

        sql = "select id_deportista,nombre from Deportista"
        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = []
        print("Selecciona un/a deportista: ")
        for r in resultados:
            if filtro.lower() in r[1].lower():
                print("\tCodigo: ",r[0]," Deportista:",r[1])
                posibles.append(r[0])
        deportistacodigo = int(input("Codigo:"))
        while deportistacodigo not in posibles:
            deportistacodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))

        # -------------- seleccionar participacion ---------------
        print("Selecciona una participacion: \n")
        sql = "select Participacion.id_evento,Evento.nombre ,Olimpiada.nombre from Participacion,Evento,Olimpiada " \
              "where Participacion.id_evento=Evento.id_evento and Olimpiada.id_olimpiada=Evento.id_olimpiada and " \
              " Participacion.id_deportista = "+str(deportistacodigo)
        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = []
        for r in resultados:
            print("\tCodigo: ", r[0], " Evento:", r[1]," celebrado en ",r[2])
            posibles.append(r[0])
        particodigo = int(input("Codigo:"))
        while particodigo not in posibles:
            particodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))

        # -------------- actualizar medalla ---------------
        medallanueva = input("Que medalla deseas poner?")
        sql = "update Participacion set medalla = '"+str(medallanueva)+"' where id_deportista="+str(deportistacodigo)+" and id_evento="+str(particodigo)
        try:
            cursor.execute(sql)
            print("Medalla actualizada en SQLite")
            cursor.close()
            conexion.close()
            conexion = mysql.connector.connect(user='admin', password='password',
                                               host='localhost',
                                               database='olimpiadas')
            cursor = conexion.cursor()
            cursor.execute(sql)
            print("Medalla actualizada en MySQL")
            conexion.commit()
            cursor.close()
            conexion.close()
        except Exception as e:
            print("Ha ocurrido un error al modificar la medalla")


# ------------- Ejercicio 6 -------------------

    def parte6(self):
        filtro = input("Introduce el filtro para buscar al deportista: ")
        conexion = mysql.connector.connect(user='admin', password='password',
                                           host='localhost',
                                           database='olimpiadas')
        cursor = conexion.cursor()

        # ------------- Deportista -------------
        sql = "select id_deportista,nombre from Deportista"
        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = []

        entra = False
        for r in resultados:
            if filtro.lower() in r[1].lower():
                entra = True
                print("\tCodigo: ", r[0], " Deportista:", r[1])
                posibles.append(r[0])
        if entra:
            deportistacodigo = int(input("Codigo:"))
            while deportistacodigo not in posibles:
                deportistacodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))
        else:
            # crear un nuevo deportista
            print("No hay deportistas com esas caracteristicas\ncreando uno nuevo...\n\n")
            deportistacodigo= self.nuevo_deportista()
        # ------------- Temporada -------------
        temporada = 'P'
        while temporada != 'S' and temporada != 'W':
            temporada = input("Que temporada?(W/S)")
        # --------------- Enseñar ediciones ----------------
        cursor = conexion.cursor()

        if temporada == 'S':
            sql = "select * from Olimpiada where temporada = 'Summer'"

        else:
            sql = "select * from Olimpiada where temporada = 'Winter'"

        cursor.execute(sql)
        resultados = cursor.fetchall()
        print("Elige una edicion por su codigo:")
        posibles = []
        for r in resultados:
            print("\tCódigo ", r[0], " Olimpiada " + r[1] + " celebrada en " + r[4])
            posibles.append(r[0])
        edicioncodigo = int(input("Codigo: "))
        while edicioncodigo not in posibles:
            edicioncodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))
        # --------- Cual deporte ----------
        sql = "Select distinct(Deporte.id_deporte), Deporte.nombre from Participacion,Deporte,Evento where Participacion.id_evento=Evento.id_evento and Deporte.id_deporte = Evento.id_deporte and Evento.id_olimpiada = " + str(
            edicioncodigo)

        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = []
        print("Elige uno de los deportes por su codigo: ")
        for r in resultados:
            print("\tCódigo: ", r[0], " Deporte: " + r[1])
            posibles.append(r[0])
        deportecodigo = int(input("Codigo:"))
        while deportecodigo not in posibles:
            deportecodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))

        # --------- Elegir evento ---------

        sql = "select id_evento,Evento.nombre,Olimpiada.nombre from Evento,Olimpiada where id_deporte = " + str(
            deportecodigo) + " and Evento.id_olimpiada = " + str(
            edicioncodigo) + " and Olimpiada.id_olimpiada=Evento.id_olimpiada"
        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = []
        print("Elige uno de los eventos por su codigo: ")
        for r in resultados:
            print("\tCódigo: ", r[0], " Evento: ", r[1], " (", r[2], ")")
            posibles.append(r[0])

        eventocodigo = int(input("Codigo:"))
        while eventocodigo not in posibles:
            eventocodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))

        # --------- Cual equipo ----------
        sql = "select * from Equipo"
        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = []
        print("Elige uno de los equipos por su codigo: ")
        for r in resultados:
            print("\tCódigo: ", r[0], " Equipo: " + r[1])
            posibles.append(r[0])
        equipocodigo = int(input("Codigo:"))
        while equipocodigo not in posibles:
            equipocodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))
        edad =input("Introduce la edad: ")
        medalla = input("Medalla:")

        self.nueva_participacion(deportistacodigo,eventocodigo,equipocodigo,edad,medalla)

# ------------- Crear participacion -------------------
    def nueva_participacion(self,id_deportista,id_evento,id_equipo,edad,medalla):
        conexion = mysql.connector.connect(user='admin', password='password',
                                           host='localhost',
                                           database='olimpiadas')
        cursor = conexion.cursor()
        sql = "insert into Participacion values ("+str(id_deportista)+","+str(id_evento)+","+str(id_equipo)+","+str(edad)+",'"+str(medalla)+"')"
        cursor.execute(sql)
        conexion.commit()
        cursor.close
        conexion.close()
        conexion = sqlite3.connect("../../embebido.db")
        cursor = conexion.cursor()
        cursor.execute(sql)
        conexion.commit()
        cursor.close()

# ------------- Crear deportista -------------------
    def nuevo_deportista(self):
        nombre = input("Introduce el nombre del deportista")
        sexo = input("Introduce el sexo del deportista")
        peso  = input("Introduce el peso del deportista")
        altura = input("Introduce la altura del deportista")

        conexion = mysql.connector.connect(user='admin', password='password',
                                           host='localhost',
                                           database='olimpiadas')
        cursor = conexion.cursor()
        sql = "select max(id_deportista) from Deportista"
        cursor.execute(sql)
        resultado = cursor.fetchone()
        resultado = resultado[0]+1
        sql = "insert into Deportista (id_deportista,nombre,sexo,peso,altura) values (" +str(resultado)+",'"+nombre+"','"+sexo+"','"+peso+"','"+altura+"')"
        cursor.execute(sql)
        conexion.commit()
        cursor.close
        conexion.close()
        conexion = sqlite3.connect("../../embebido.db")
        cursor = conexion.cursor()
        cursor.execute(sql)
        conexion.commit()
        cursor.close()
        return resultado

# ------------- Ejercicio 7 -------------------

    def parte7(self):
        #Eliminar participacion
        filtro = input("Introduce el filtro para buscar al deportista: ")
        conexion = sqlite3.connect("../../embebido.db")
        cursor = conexion.cursor()

        # -------------- seleccionar deportista ---------------

        sql = "select id_deportista,nombre from Deportista"
        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = []
        print("Selecciona un/a deportista: ")
        for r in resultados:
            if filtro.lower() in r[1].lower():
                print("\tCodigo: ", r[0], " Deportista:", r[1])
                posibles.append(r[0])
        deportistacodigo = int(input("Codigo:"))
        while deportistacodigo not in posibles:
            deportistacodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))

        # ---------- seleccionar participacion -------------
        sql = "select Participacion.id_deportista,Olimpiada.nombre,Evento.nombre ,Evento.id_evento from Participacion,Olimpiada,Evento where Participacion.id_evento=Evento.id_evento and Evento.id_olimpiada=Olimpiada.id_olimpiada and Participacion.id_deportista="+str(deportistacodigo)
        cursor.execute(sql)
        resultados = cursor.fetchall()
        posibles = {}
        cont = 1
        for r in resultados:
            posibles[cont] = r
            print("Codigo: ",cont," olimpiada: ",r[1]," evento: ",r[2])
            cont += 1
        if(cont>1):
            participacioncodigo = int(input("Codigo:"))
            while participacioncodigo not in posibles.keys():
                participacioncodigo = int(input("Codigo incorrecto,prueba otra vez\nCodigo: "))
            self.borrarParticipacion(posibles.get(participacioncodigo)[0],posibles.get(participacioncodigo)[3])
        else:
            if cont == 1:
                print("deportista sin participaciones")
            else:
                participacioncodigo = 1
                self.borrarParticipacion(posibles.get(participacioncodigo)[0], posibles.get(participacioncodigo)[3])
                print("borrando deportista")
                self.borrarDeportista(posibles.get(participacioncodigo)[0])

# ------------- Borrar participacion -------------------
    def borrarParticipacion(self,id_deportista,id_evento):
        conexion = mysql.connector.connect(user='admin', password='password',
                                           host='localhost',
                                           database='olimpiadas')
        cursor = conexion.cursor()
        sql = "delete from Participacion where id_deportista = "+str(id_deportista)+" and id_evento="+str(id_evento)
        cursor.execute(sql)
        conexion.commit()
        cursor.close
        conexion.close()
        conexion = sqlite3.connect("../../embebido.db")
        cursor = conexion.cursor()
        cursor.execute(sql)
        conexion.commit()
        cursor.close()

# ------------- Borrar deportista -------------------
    def borrarDeportista(self,id):
        conexion = mysql.connector.connect(user='admin', password='password',
                                           host='localhost',
                                           database='olimpiadas')
        cursor = conexion.cursor()
        sql = "delete from Deportista where id_deportista = " + str(id)
        cursor.execute(sql)
        conexion.commit()
        cursor.close
        conexion.close()
        conexion = sqlite3.connect("../../embebido.db")
        cursor = conexion.cursor()
        cursor.execute(sql)
        conexion.commit()
        cursor.close()

# ------------- Menu principal -------------------
    def menu(self):
        opcion = -1
        while opcion != 1 and opcion != 2 and opcion != 3 and opcion != 4 and opcion != 5 and opcion != 6 and opcion != 7 and opcion != 0:
            print("OLIMPIADAS BD\n\t¿Qué quieres hacer?\n")
            print("\t[1] Crear BBDD MySQL\n")
            print("\t[2] Crear BBDD SQLite\n")  # mantener orden
            print("\t[3] Listado de deportistas en diferentes deportes\n")
            print("\t[4] Listado de deportistas principiantes\n")
            print("\t[5] Modificar medalla deportista\n")
            print("\t[6] Añadir deportista/participacion\n")
            print("\t[7] Eliminar participacion\n")
            print("\t[0] Salir\n")
            opcion = int(input())
        if opcion == 1:
            self.parte1()
            self.menu()
        else:
            if opcion == 2:
                self.parte2()
                self.menu()
            else:
                if opcion == 3:
                    self.parte3()
                    self.menu()
                else:
                    if opcion == 4:
                        self.parte4()
                        self.menu()
                    else:
                        if opcion == 5:
                            self.parte5()
                            self.menu()
                        else:
                            if opcion == 6:
                                self.parte6()
                                self.menu()
                            else:
                                if opcion == 7:
                                    self.parte7()
                                    self.menu()


o = olimpiadasDB()
o.menu()
